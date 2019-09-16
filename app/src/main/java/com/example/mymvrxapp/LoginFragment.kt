package com.example.mymvrxapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.airbnb.mvrx.*
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : BaseMvRxFragment() {
    // Fetch the ViewModel scoped to the current activity
    private val formViewModel: FormViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginButton.setOnClickListener {
            formViewModel.setNameAndCity(
                loginNameText.text.toString(),
                loginCityText.text.toString()
            )
            formViewModel.doLogIn()
        }

    }

    override fun invalidate() {
        withState(formViewModel) { state ->
            if (state.loggedIn is Success) {
                findNavController().navigate(R.id.action_loginFragment_to_landingFragment2)
            }

            loadingIndicator.isVisible = state.loggedIn is Loading
            loginNameText.isVisible = state.loggedIn !is Loading
            loginCityText.isVisible = state.loggedIn !is Loading
            loginButton.isVisible = state.loggedIn !is Loading
        }
    }
}