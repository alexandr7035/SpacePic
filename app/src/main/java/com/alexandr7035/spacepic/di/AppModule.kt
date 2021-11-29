package com.alexandr7035.spacepic.di

import com.alexandr7035.spacepic.data.*
import com.alexandr7035.spacepic.data.remote.ApodListRemoteToDataMapper
import com.alexandr7035.spacepic.data.remote.ApodListRemoteToDataMapperImpl
import com.alexandr7035.spacepic.data.remote.ApodRemoteToDataMapper
import com.alexandr7035.spacepic.data.remote.ApodRemoteToDataMapperImpl
import com.alexandr7035.spacepic.domain.*
import com.alexandr7035.spacepic.ui.ApodDomainToUiMapperImpl
import com.alexandr7035.spacepic.ui.ApodsDomainToUiMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    // Network
    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return  OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .retryOnConnectionFailure(false)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/")
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideCloudDataSource(apiService: ApiService): ApodsCloudDataSource {
        return ApodsCloudDataSourceImpl(apiService)
    }

    // Mappers
    @Provides
    fun provideApodRemoteToDataMapper(): ApodRemoteToDataMapper {
        return ApodRemoteToDataMapperImpl()
    }

    @Provides
    fun provideApodListRemoteToDataMapper(mapper: ApodRemoteToDataMapper): ApodListRemoteToDataMapper {
        return ApodListRemoteToDataMapperImpl(mapper)
    }

    @Provides
    fun provideApodDataToDomainMapper(): ApodDataToDomainMapper {
        return ApodDataToDomainMapperImpl()
    }

    @Provides
    fun provideApodsDataToDomainMapper(apodDataToDomainMapper: ApodDataToDomainMapper): ApodsDataToDomainMapper {
        return ApodsDataToDomainMapperImpl(apodDataToDomainMapper)
    }

    @Provides
    fun provideApodDomainToUiMapper(): ApodDomainToUiMapper {
        return ApodDomainToUiMapperImpl()
    }

    @Provides
    fun provideApodsDomainToUiMapper(apodDomainToUiMapper: ApodDomainToUiMapper): ApodsDomainToUiMapper {
        return ApodsDomainToUiMapperImpl(apodDomainToUiMapper)
    }

    // Repository
    @Provides
    fun provideApodsRepository(cloudDataSource: ApodsCloudDataSource, mapper: ApodListRemoteToDataMapper): ApodsRepository {
        return ApodsRepositoryImpl(cloudDataSource, mapper)
    }

    // Interactor
    @Provides
    fun provideApodsInteractor(repository: ApodsRepository, apodsDataToDomainMapper: ApodsDataToDomainMapper): ApodsInteractor {
        return ApodsInteractorImpl(repository, apodsDataToDomainMapper)
    }

}