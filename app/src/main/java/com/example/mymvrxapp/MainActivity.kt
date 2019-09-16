package com.example.mymvrxapp

import android.os.Bundle
import com.airbnb.mvrx.*
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

data class FormState(
    val name: String = "",
    val city: String = "",
    val loggedIn: Async<Boolean> = Uninitialized
) : MvRxState

class FormViewModel(initialState: FormState) :
    BaseMvRxViewModel<FormState>(initialState, debugMode = BuildConfig.DEBUG) {
    init {
        logStateChanges()
    }

    fun setNameAndCity(name: String, city: String) {
        setState { copy(city = city, name = name) }
    }

    fun doLogIn() {
        Single.just(true)
            .delaySubscription(5, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .execute { copy(loggedIn = it) }
    }

    fun logout() {
        setState {
            copy(loggedIn = Uninitialized)
        }
    }
}

class MainActivity() : BaseMvRxActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}