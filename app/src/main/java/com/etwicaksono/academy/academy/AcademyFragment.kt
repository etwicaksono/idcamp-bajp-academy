package com.etwicaksono.academy.academy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.etwicaksono.academy.R
import com.etwicaksono.academy.databinding.FragmentAcademyBinding
import com.etwicaksono.academy.utils.DataDummy


class AcademyFragment : Fragment() {

    private lateinit var binding: FragmentAcademyBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAcademyBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val courses = DataDummy.generateDummyCourse()
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