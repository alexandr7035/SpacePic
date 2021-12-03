package com.alexandr7035.spacepic.di

import android.app.Application
import com.alexandr7035.spacepic.data.*
import com.alexandr7035.spacepic.data.remote.*
import com.alexandr7035.spacepic.domain.*
import com.alexandr7035.spacepic.ui.ApodDomainToUiMapperImpl
import com.alexandr7035.spacepic.ui.apods_list.ApodsDomainToUiMapperImpl
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
    fun provideApodDomainToUiMapper(resourceProvider: ResourceProvider): ApodDomainToUiMapper {
        return ApodDomainToUiMapperImpl(resourceProvider)
    }

    @Provides
    fun provideApodsDomainToUiMapper(apodDomainToUiMapper: ApodDomainToUiMapper, resourceProvider: ResourceProvider): ApodsDomainToUiMapper {
        return ApodsDomainToUiMapperImpl(apodDomainToUiMapper, resourceProvider)
    }

    // Repository
    @Provides
    fun provideApodsRepository(cloudDataSource: ApodsCloudDataSource, mapper: ApodListRemoteToDataMapper, apodRemoteToDataMapper: ApodRemoteToDataMapper): ApodsRepository {
        return ApodsRepositoryImpl(cloudDataSource, mapper, apodRemoteToDataMapper)
    }

    // Interactor
    @Provides
    fun provideApodsInteractor(repository: ApodsRepository, apodsDataToDomainMapper: ApodsDataToDomainMapper, apodsDatesHelper: ApodsDatesHelper, apodDataToDomainMapper: ApodDataToDomainMapper): ApodsInteractor {
        return ApodsInteractorImpl(repository, apodsDataToDomainMapper, apodDataToDomainMapper, apodsDatesHelper)
    }

    @Provides
    fun provideApodsDatesHelper(): ApodsDatesHelper {
        return ApodsDatesHelper.Impl()
    }

    // Common
    @Provides
    fun provideResourceProvider(application: Application): ResourceProvider {
        return ResourceProviderImpl(application)
    }

}