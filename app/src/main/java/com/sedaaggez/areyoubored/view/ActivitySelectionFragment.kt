package com.sedaaggez.areyoubored.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.sedaaggez.areyoubored.R
import kotlinx.android.synthetic.main.fragment_activity_selection.*
import kotlinx.android.synthetic.main.fragment_welcome.*
import kotlinx.android.synthetic.main.fragment_welcome.buttonYes

class ActivitySelectionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_activity_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonRandom.setOnClickListener {
            val action = ActivitySelectionFragmentDirections.actionActivitySelectionFragmentToActivityFragment(true)
            Navigation.findNavController(it).navigate(action)
        }
        buttonFilter.setOnClickListener {
            val action = ActivitySelectionFragmentDirections.actionActivitySelectionFragmentToFilterFragment()
            Navigation.findNavController(it).navigate(action)
        }

    }

}