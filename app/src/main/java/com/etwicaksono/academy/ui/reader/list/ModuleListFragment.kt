package com.etwicaksono.academy.ui.reader.list

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.etwicaksono.academy.CourseReaderActivity
import com.etwicaksono.academy.data.ModuleEntity
import com.etwicaksono.academy.databinding.FragmentModuleListBinding
import com.etwicaksono.academy.ui.reader.CourseReaderCallback
import com.etwicaksono.academy.utils.DataDummy


class ModuleListFragment : Fragment(), MyAdapterClickListener {

    private lateinit var fragmentModuleListBinding: FragmentModuleListBinding
    private lateinit var adapter: ModuleListAdapter
    private lateinit var courseReaderCallback: CourseReaderCallback

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentModuleListBinding = FragmentModuleListBinding.inflate(inflater, container, false)
        return fragmentModuleListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ModuleListAdapter(this)
        populateRecyclerView(DataDummy.generateDummyModules("a14"))
    }

    private fun populateRecyclerView(modules: List<ModuleEntity>) {
        with(fragmentModuleListBinding){
            progressBar.visibility=View.GONE
            adapter.setModules(modules)
            rvModule.layoutManager=LinearLayoutManager(context)
            rvModule.setHasFixedSize(true)
            rvModule.adapter=adapter

            val dividerItemDecoration=DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL)
            rvModule.addItemDecoration(dividerItemDecoration)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        courseReaderCallback = context as CourseReaderActivity
    }

    override fun onItemClicked(position: Int, moduleId: String) {
        courseReaderCallback.moveTo(position,moduleId)
    }

    companion object {
        val TAG: String = ModuleListFragment::class.java.simpleName

        fun newInstance(): ModuleListFragment = ModuleListFragment()
    }
}