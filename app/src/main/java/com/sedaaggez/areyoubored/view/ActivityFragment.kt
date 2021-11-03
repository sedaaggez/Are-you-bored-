package com.sedaaggez.areyoubored.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sedaaggez.areyoubored.R
import com.sedaaggez.areyoubored.viewmodel.ActivityViewModel
import kotlinx.android.synthetic.main.fragment_activity.*

class ActivityFragment : Fragment() {

    private lateinit var viewModel: ActivityViewModel
    private var isRandom = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_activity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ActivityViewModel::class.java)

        arguments?.let {
            isRandom = ActivityFragmentArgs.fromBundle(it).isRandom
        }

        if (isRandom) {
            viewModel.getDataRandom()
        }

        observeLiveData()

    }

    private fun observeLiveData() {

        viewModel.activity.observe(viewLifecycleOwner, { activity ->
            activity?.let {
                textViewActivity.text = activity.activity

            }
        })

        viewModel.activityError.observe(viewLifecycleOwner, {error ->
            error?.let {
                if(it) {
                    textViewError.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                    textViewActivity.visibility = View.GONE
                } else {
                    textViewError.visibility = View.GONE

                }
            }
        })

        viewModel.activityLoading.observe(viewLifecycleOwner, {loading ->
            loading?.let {
                if(it) {
                    progressBar.visibility = View.VISIBLE
                    textViewError.visibility = View.GONE
                    textViewActivity.visibility = View.GONE

                } else {
                    progressBar.visibility = View.GONE

                }
            }
        })

    }
}