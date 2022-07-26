package com.neveragain.prototype.mopt.fragment.list

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neveragain.prototype.mopt.R
import com.neveragain.prototype.mopt.data.Child
import com.neveragain.prototype.mopt.databinding.CustomRecyclerRowBinding

class ChildListAdapter(private val context: ListFragment) :
    RecyclerView.Adapter<ChildListAdapter.ChildViewHolder>() {
    private var childList = emptyList<Child>()

    class ChildViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = CustomRecyclerRowBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        return ChildViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_recycler_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        val currentItem = childList[position]
        holder.binding.childNameText.text = currentItem.fullName
        if (currentItem.sex == "M") {
            holder.binding.sexIndicatorImage.setImageResource(R.drawable.ic_baseline_male_face_24)
        } else {
            holder.binding.sexIndicatorImage.setImageResource(R.drawable.ic_baseline_female_face_24)
        }
        holder.binding.clickableLayout.setOnClickListener {
            context.onClick(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return childList.size
    }

    fun setData(children: List<Child>) {
        this.childList = children
        notifyDataSetChanged()
    }

}