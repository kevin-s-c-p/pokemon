package mx.com.myapplication.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.com.web.ApiService
import mx.com.web.Token
import mx.com.web.serializers.BooleanDeserializer
import mx.com.web.serializers.BooleanSerializer
import mx.com.web.serializers.DateDeserializaer
import mx.com.web.serializers.DateSerializer
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import java.util.Date
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {
    @Provides
    fun provideRetrofit(): ApiService {
        Token.retrofitInstance = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create(gson()))
            .client(provideHttpClient())
            .build()
            .create(mx.com.web.ApiService::class.java)

        return Token.retrofitInstance!!
    }

    private fun provideHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor())
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder().build()
                chain.proceed(newRequest)
            }
            .build()
    }

    private fun loggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor

    }

    private fun gson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(Boolean::class.java, mx.com.web.serializers.BooleanSerializer())
            .registerTypeAdapter(Boolean::class.java, mx.com.web.serializers.BooleanDeserializer())
            .registerTypeAdapter(Boolean::class.javaPrimitiveType,
                mx.com.web.serializers.BooleanSerializer()
            )
            .registerTypeAdapter(Boolean::class.javaPrimitiveType,
                mx.com.web.serializers.BooleanDeserializer()
            )
            .registerTypeAdapter(Date::class.java, mx.com.web.serializers.DateSerializer())
            .registerTypeAdapter(Date::class.java, mx.com.web.serializers.DateDeserializaer())
            .serializeNulls()
            .create()
    }
}