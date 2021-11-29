package com.sedaaggez.areyoubored.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.sedaaggez.areyoubored.R
import com.sedaaggez.areyoubored.databinding.FragmentActivityBinding
import com.sedaaggez.areyoubored.viewmodel.ActivityViewModel
import kotlinx.android.synthetic.main.fragment_activity.*

class ActivityFragment : Fragment() {

    private lateinit var viewModel: ActivityViewModel
    private var isRandom = false
    private var type = "Education"
    private var participants = 0
    private var price = 0.0f
    private var accessibility = 0.0f

    private lateinit var dataBinding: FragmentActivityBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_activity,
            container,
            false
        )
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ActivityViewModel::class.java)

        arguments?.let {
            isRandom = ActivityFragmentArgs.fromBundle(it).isRandom
            type = ActivityFragmentArgs.fromBundle(it).type
            participants = ActivityFragmentArgs.fromBundle(it).participants
            price = ActivityFragmentArgs.fromBundle(it).price
            accessibility = ActivityFragmentArgs.fromBundle(it).accessibility
        }

        if (isRandom) {
            viewModel.getDataRandom()
        } else {
            viewModel.getDataFilter(type.lowercase(), participants, price, accessibility)
        }

        observeLiveData()

    }

    private fun observeLiveData() {

        viewModel.activity.observe(viewLifecycleOwner, { activity ->
            activity?.let {
                if(activity.activity == null) {
                    textViewActivity.text = "No activity found with the specified parameters"
                } else {
                    dataBinding.activity = activity
                }
                progressBar.visibility = View.GONE
                textViewError.visibility = View.GONE
                imageViewActivity.visibility = View.VISIBLE
            }
        })

        viewModel.activityError.observe(viewLifecycleOwner, { error ->
            error?.let {
                if (it) {
                    textViewError.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                    textViewActivity.visibility = View.GONE
                } else {
                    textViewError.visibility = View.GONE
                }
            }
        })

        viewModel.activityLoading.observe(viewLifecycleOwner, { loading ->
            loading?.let {
                if (it) {
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