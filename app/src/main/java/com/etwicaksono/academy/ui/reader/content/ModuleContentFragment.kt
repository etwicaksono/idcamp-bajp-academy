package com.etwicaksono.academy.ui.reader.content

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.etwicaksono.academy.data.ContentEntity
import com.etwicaksono.academy.data.ModuleEntity
import com.etwicaksono.academy.databinding.FragmentModuleContentBinding
import com.etwicaksono.academy.ui.reader.CourseReaderViewModel

class ModuleContentFragment : Fragment() {

    private lateinit var fragmentModuleContentBinding: FragmentModuleContentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentModuleContentBinding= FragmentModuleContentBinding.inflate(inflater,container,false)
        return fragmentModuleContentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(activity!=null){
            val viewModel = ViewModelProvider(requireActivity(),ViewModelProvider.NewInstanceFactory())[CourseReaderViewModel::class.java]
            val module = viewModel.getSelectedModule()
            populateWebView(module)
        }
    }

    private fun populateWebView(module: ModuleEntity) {
        fragmentModuleContentBinding.webView.loadData(module.contentEntity?.content?:"","text/html","UTF-8")
    }

    companion object {
        val TAG: String = ModuleContentFragment::class.java.simpleName
        fun newInstace(): ModuleContentFragment = ModuleContentFragment()
    }
}