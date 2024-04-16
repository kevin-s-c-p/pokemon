package mx.com.myapplication.repositories.user

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import android.location.LocationManager
import android.util.Log
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import mx.com.database.DBPokemonApp
import mx.com.myapplication.App
import mx.com.myapplication.repositories.ApiResponse
import mx.com.myapplication.repositories.BaseRepository
import mx.com.web.ApiService
import java.util.concurrent.Executor
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(
    private val apiService: ApiService,
    private val geocoder: Geocoder
): BaseRepository(), UserRepository {
    override fun verifyUser(): Flow<Boolean> = flow {
        emit(true)
    }

    @SuppressLint("MissingPermission")
    override fun getLocation(context: Context): Flow<LatLng?> = callbackFlow {
        try {
            val focusLocation = LocationServices.getFusedLocationProviderClient(App.appContext)
            focusLocation.lastLocation.addOnCompleteListener {
                if (it.isSuccessful) {
                    trySend(LatLng(it.result.latitude, it.result.longitude))
                }
            }
        } catch (exception: Exception) {
            Log.e("ERROR LOCATION", exception.message ?: "")
        }

        awaitClose {  }
    }

}