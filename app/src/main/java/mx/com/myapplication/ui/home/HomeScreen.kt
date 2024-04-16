package mx.com.myapplication.ui.home

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import mx.com.domain.items.PokemonItem
import mx.com.myapplication.R
import mx.com.myapplication.ui.composables.BaseTextField
import mx.com.myapplication.ui.composables.BottomSheetPokemonInformation
import mx.com.myapplication.ui.composables.CustomImage
import mx.com.myapplication.ui.composables.CustomMainMenu
import mx.com.myapplication.ui.composables.DoubleButton
import mx.com.myapplication.ui.composables.Loading
import mx.com.myapplication.ui.composables.PokemonSaved
import mx.com.myapplication.ui.composables.SimpleButton
import mx.com.myapplication.ui.home.view.event.HomeViewEvent
import mx.com.myapplication.ui.home.view.state.HomeViewState

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    CustomMainMenu(
        navController = navController,
        titleMenu = R.string.home,
    ) {
        val state by viewModel.getState<HomeViewState>().collectAsState()

        LaunchedEffect(key1 = Unit) {
            viewModel.onEvent(HomeViewEvent.SearchNewPokemon)
        }

        Loading(isLoading = state.isLoading)

        ModalBottomSheetInformationPokemon(
            state.isVisibleBottomSheetPokemon,
            state.pokemonFind,
            closeModal = { viewModel.onEvent(HomeViewEvent.HideBottomSheetDialog) }
        )

        ModalSaveSuccess(
            state.isVisibleModalSaved,
            closeModal = { viewModel.onEvent(HomeViewEvent.HideModal) }
        )

        Spacer(modifier = Modifier.fillMaxHeight(.05f))
        
        Text(
            text = stringResource(id = R.string.title_home),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.fillMaxHeight(.06f))

        CardInformationPokemon(
            state.pokemonFind,
            state.startAnimationPokemonFind,
            endAnimation = { viewModel.onEvent(HomeViewEvent.PokemonFindAnimationEnd) },
            savePokemon = { viewModel.onEvent(HomeViewEvent.SavePokemon) },
            pokemonInformation = { viewModel.onEvent(HomeViewEvent.ShowBottomSheetDialog) }
        )

        Spacer(modifier = Modifier.fillMaxHeight(.1f))

        CardWhoIsThisPokemon(
            state.whoIsThisPokemon,
            state.pokemonWrite,
            verifyPokemon = { viewModel.onEvent(HomeViewEvent.IsThisPokemon) },
            pokemonWritten = { viewModel.onEvent(HomeViewEvent.WritePokemon(it)) },
            showName = { viewModel.onEvent(HomeViewEvent.ShowNamePokemon) }
        )

        Spacer(modifier = Modifier.weight(1f))

        ButtonSearchNewPokemon { viewModel.onEvent(HomeViewEvent.SearchNewPokemon) }

        Spacer(modifier = Modifier.fillMaxHeight(.15f))
    }
}

@Composable
private fun CardInformationPokemon(
    pokemonFound: PokemonItem?,
    showPokemonAnimationFind: Boolean,
    endAnimation: () -> Unit,
    savePokemon: () -> Unit,
    pokemonInformation: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.4f)
            .padding(horizontal = 20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        if (pokemonFound == null)
            PokemonNotFindYet()
        else
            LastPokemonFound(
                pokemonFound,
                showPokemonAnimationFind,
                endAnimation = endAnimation,
                savePokemon = savePokemon,
                pokemonInformation = pokemonInformation
            )
    }
}

@Composable
private fun PokemonNotFindYet() {
    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(25.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomImage(
            image = R.drawable.ic_pokeball_color,
            modifier = Modifier
                .fillMaxSize(.3f)
                .clip(CircleShape),
            contentScale = ContentScale.Inside
        )

        Text(
            text = stringResource(id = R.string.pokemon_not_find),
            style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.primary),
            modifier = Modifier.fillMaxWidth(.6f),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun LastPokemonFound(
    pokemonFound: PokemonItem,
    showPokemonAnimationFind: Boolean,
    endAnimation: () -> Unit,
    savePokemon: () -> Unit,
    pokemonInformation: () -> Unit
) {
    val scaleAnimation by animateFloatAsState(
        targetValue = if (showPokemonAnimationFind) 1.1f else 1f,
        animationSpec = tween(500, easing = FastOutLinearInEasing),
    )

    LaunchedEffect(key1 = showPokemonAnimationFind) {
        if (showPokemonAnimationFind) {
            delay(500)
            endAnimation()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .scale(scaleAnimation),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomImage(
            image = pokemonFound.image,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(.5f)
        )

        Spacer(modifier = Modifier.fillMaxHeight(.2f))

        Text(
            text = pokemonFound.name,
            style = MaterialTheme.typography.labelLarge.copy(color = MaterialTheme.colorScheme.primary)
        )

        Spacer(modifier = Modifier.weight(1f))

        DoubleButton(
            modifier = Modifier.fillMaxWidth(),
            textFirstButton = R.string.save,
            textSecondButton = R.string.Info,
            clickFirstButton = savePokemon,
            clickSecondButton = pokemonInformation
        )
    }
}

@Composable
private fun CardWhoIsThisPokemon(
    whoIsThisPokemon: PokemonItem?,
    pokemonWrite: String,
    verifyPokemon: () -> Unit,
    pokemonWritten: (String) -> Unit,
    showName: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.7f)
            .padding(horizontal = 20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomImage(
                image = whoIsThisPokemon?.image ?: "",
                modifier = Modifier.fillMaxSize(.4f).clickable { showName() },
                tint = Color.Gray
            )

            Spacer(modifier = Modifier.weight(1f))

            WriteWhoIsThisPokemon(
                pokemonWrite,
                pokemonWrite = { pokemonWritten(it) },
                sendPokemon = { verifyPokemon() }
            )
        }
    }
}

@Composable
private fun WriteWhoIsThisPokemon(
    pokemonWritten: String,
    pokemonWrite: (String) -> Unit,
    sendPokemon: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        BaseTextField(
            modifier = Modifier.weight(1f),
            value = pokemonWritten,
            valueChange = { pokemonWrite(it) }
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_send),
            contentDescription = "",
            Modifier
                .fillMaxWidth(.1f)
                .fillMaxHeight(.2f)
                .clickable { sendPokemon() }
        )
    }
}

@Composable
private fun ButtonSearchNewPokemon(newPokemon: () -> Unit) {
    SimpleButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        textButton = R.string.find_pokemon,
        shapeButton = RoundedCornerShape(10.dp),
        clickButton = newPokemon
    )
}

@Composable
private fun ModalBottomSheetInformationPokemon(
    isVisible: Boolean,
    pokemon: PokemonItem?,
    closeModal: () -> Unit
) {
    BottomSheetPokemonInformation(
        isVisible,
        pokemon,
        closeModal = closeModal
    )
}

@Composable
private fun ModalSaveSuccess(
    isVisible: Boolean,
    closeModal: () -> Unit
) {
    PokemonSaved(
        isVisible,
        closeModal = closeModal
    )
}