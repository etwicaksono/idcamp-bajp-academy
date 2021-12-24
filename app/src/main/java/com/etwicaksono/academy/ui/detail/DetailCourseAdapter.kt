package com.etwicaksono.academy.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.etwicaksono.academy.data.ModuleEntity
import com.etwicaksono.academy.databinding.ItemsModuleListBinding

class DetailCourseAdapter : RecyclerView.Adapter<DetailCourseAdapter.ModuleViewHolder>() {
    inner class ModuleViewHolder(private val binding: ItemsModuleListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(module: ModuleEntity) {
            binding.tvModuleTitle.text = module.title
        }
    }

    private val listModules=ArrayList<ModuleEntity>()

    fun setModules(modules:List<ModuleEntity>?){
        if(modules==null)return
        this.listModules.clear()
        this.listModules.addAll(modules)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailCourseAdapter.ModuleViewHolder {
        val itemsModuleListBinding=ItemsModuleListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ModuleViewHolder(itemsModuleListBinding)
    }

    override fun onBindViewHolder(holder: DetailCourseAdapter.ModuleViewHolder, position: Int) {
       val module=listModules[position]
        holder.bind(module)
    }

    override fun getItemCount(): Int=listModules.size
}