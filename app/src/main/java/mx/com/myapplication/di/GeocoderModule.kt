package mx.com.myapplication.di

import android.content.Context
import android.location.Geocoder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class GeocoderModule {
    @Provides
    fun geocoderProvider(
        @ApplicationContext
        context: Context
    ): Geocoder = Geocoder(context)
}