package com.etwicaksono.academy.ui.academy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.etwicaksono.academy.databinding.FragmentAcademyBinding
import com.etwicaksono.academy.utils.DataDummy
import com.etwicaksono.academy.viewmodel.ViewModelFactory


class AcademyFragment : Fragment() {

    private lateinit var binding: FragmentAcademyBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAcademyBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val factory=ViewModelFactory.getInstance(requireActivity())
            val viewModel=ViewModelProvider(this,factory)[AcademyViewModel::class.java]
            val courses = viewModel.getCourses()
            val academyAdapter = AcademyAdapter()
            academyAdapter.setCourses(courses)

            with(binding.rvAcademy) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = academyAdapter
            }
        }
    }
}