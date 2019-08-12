package io.github.johnnynanjiang.android.moneytreelight.data

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RemoteAccountsRepository {
    companion object {
        private const val BASE_URL = "https://raw.githubusercontent.com/johnnynanjiang/MoneytreeLight/"

        private var instance: AccountsRepository? = null

        fun getInstance(): AccountsRepository {
            if (instance == null) {
                instance = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(AccountsRepository::class.java)
            }

            return instance!!
        }
    }
}