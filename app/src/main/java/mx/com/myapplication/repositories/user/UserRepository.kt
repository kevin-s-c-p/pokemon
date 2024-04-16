package mx.com.myapplication.repositories.user

import android.content.Context
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun verifyUser(): Flow<Boolean>

    fun getLocation(context: Context): Flow<LatLng?>
}