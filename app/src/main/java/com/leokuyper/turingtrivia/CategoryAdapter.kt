package com.leokuyper.turingtrivia

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.leokuyper.turingtrivia.data.DataModel
import kotlinx.android.synthetic.main.category_item.view.*
import kotlinx.android.synthetic.main.fragment_game.view.*

class CategoryAdapter(
    private val categoryList: List<CategoryItem>,
    var cClickListener:OnCategoryCLickListener
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = categoryList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = categoryList[position]
//        holder.textView.text = currentItem.text

        holder.initialiser(categoryList.get(position), cClickListener)
    }

    inner class  ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textView : TextView = itemView.category_text

        fun initialiser(categoryItem: CategoryItem, action: OnCategoryCLickListener){
            textView.text = categoryItem.text
            textView.setOnClickListener{ view ->
                action.onCategoryClick(categoryItem, adapterPosition, view)
            }
        }
    }
}

interface OnCategoryCLickListener{
    fun onCategoryClick(category: CategoryItem, position: Int, view: View)
}