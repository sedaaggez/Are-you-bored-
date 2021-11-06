package com.sedaaggez.areyoubored.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.Navigation
import com.sedaaggez.areyoubored.R
import kotlinx.android.synthetic.main.fragment_filter.*

class FilterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val types = resources.getStringArray(R.array.type).toList()
        spinnerType.adapter = ArrayAdapter(requireContext(), R.layout.spinner_item, types)

        buttonSave.setOnClickListener {
            val action = FilterFragmentDirections.actionFilterFragmentToActivityFragment(false)
            Navigation.findNavController(it).navigate(action)
            // TODO: add arguments
        }

    }

}