package com.example.mymvrxapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.airbnb.mvrx.BaseMvRxFragment
import com.airbnb.mvrx.existingViewModel
import com.airbnb.mvrx.withState
import kotlinx.android.synthetic.main.landing_fragment.*

class LandingFragment : BaseMvRxFragment() {

    private val formViewModel: FormViewModel by existingViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.landing_fragment, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logoutButton.setOnClickListener {
            formViewModel.logout()
            findNavController().popBackStack()

        }
    }

    override fun invalidate() {
        withState(formViewModel) { state ->
            landingCityText.text = state.city
            landingNameText.text = state.name
        }
    }
}