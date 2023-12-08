package com.plcoding.graphqlcountriesapp.di

import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.plcoding.graphqlcountriesapp.data.ApolloProductsClient
import com.plcoding.graphqlcountriesapp.domain.ProductClient
import com.plcoding.graphqlcountriesapp.domain.useCases.GetCategoriesUseCase
import com.plcoding.graphqlcountriesapp.domain.useCases.GetProductUseCase
import com.plcoding.graphqlcountriesapp.domain.useCases.GetProductsUseCase
import com.plcoding.graphqlcountriesapp.domain.useCases.GetSectionsUseCase
import com.plcoding.graphqlcountriesapp.domain.useCases.GetSliderUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import javax.inject.Named
import javax.inject.Singleton

//private class AuthorizationInterceptor(private val authToken: String) : Interceptor {
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val request = chain.request().newBuilder()
//            .addHeader("Authorization", "Bearer $authToken")
//            .build()
//        return chain.proceed(request)
//    }
//}
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val YOUR_ACCESS_TOKEN = "6f256924a0828cd86aae0bedb5b5e5f583062cafc990a88ad1867854c36f4ccb1f75b4ea1ecd2837c7b3bf16cf6350854b9e877b308574c45d497721a53356e75775656bb8cb1528264039cdae64b34c8d84a2ddab603b16d7b8a72251b13b29d239cbef3dbc1782ee61bc46a0fc62d2b562a1e88b05bd3fcc795f97de0bfdd5"
    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                // Add the Authorization header with your token
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("Authorization", "6f256924a0828cd86aae0bedb5b5e5f583062cafc990a88ad1867854c36f4ccb1f75b4ea1ecd2837c7b3bf16cf6350854b9e877b308574c45d497721a53356e75775656bb8cb1528264039cdae64b34c8d84a2ddab603b16d7b8a72251b13b29d239cbef3dbc1782ee61bc46a0fc62d2b562a1e88b05bd3fcc795f97de0bfdd5")
                    .method(original.method, original.body)
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .build()
        Log.e("header", okHttpClient.interceptors.toString())

        return ApolloClient.Builder()
            .serverUrl("https://surtymas-cms.onrender.com/graphql")
            .okHttpClient(okHttpClient)
            .build()
    }
    @Provides
    @Singleton
    fun provideProductsClient(apolloClient: ApolloClient): ProductClient {
        return ApolloProductsClient(apolloClient)
    }
    @Provides
    @Singleton
    fun provideGetProductsUseCase(productClient: ProductClient): GetProductsUseCase {
        return GetProductsUseCase(productClient)
    }
    @Provides
    @Singleton
    fun provideGetProductUseCase(productClient: ProductClient): GetProductUseCase {
        return GetProductUseCase(productClient)
    }
    @Provides
    @Singleton
    fun provideGetCategoriesUseCase(productClient: ProductClient): GetCategoriesUseCase {
        return GetCategoriesUseCase(productClient)
    }
    @Provides
    @Singleton
    fun provideGetSliderUseCase(productClient: ProductClient): GetSliderUseCase {
        return GetSliderUseCase(productClient)
    }
    @Provides
    @Singleton
    fun provideGetSectionsUseCase(productClient: ProductClient): GetSectionsUseCase {
        return GetSectionsUseCase(productClient)
    }
}