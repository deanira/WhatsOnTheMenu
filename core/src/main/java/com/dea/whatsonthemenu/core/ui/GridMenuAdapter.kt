package com.dea.whatsonthemenu.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dea.whatsonthemenu.core.R
import com.dea.whatsonthemenu.core.databinding.ItemMenuBinding
import com.dea.whatsonthemenu.core.domain.model.Menu
import com.dea.whatsonthemenu.core.utils.Helper.loadImage
import eightbitlab.com.blurview.RenderScriptBlur
import java.util.ArrayList

class GridMenuAdapter : RecyclerView.Adapter<GridMenuAdapter.GridViewHolder>() {

    private var listData = ArrayList<Menu>()
    var onItemClick: ((Menu) -> Unit)? = null

    fun setData(newListData: List<Menu>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemMenuBinding.bind(itemView)
        fun bind(data: Menu) {
            with(binding) {
                civMenu.loadImage(data.image)
                tvTitle.text = data.title
                tvRestaurant.text = data.restaurant
                tvPrice.text = "Not Available"
//                blurView.setupWith(this.root)
//                    .setBlurAlgorithm(RenderScriptBlur(itemView.context))
//                    .setBlurRadius(10f)
//                    .setBlurAutoUpdate(true)
//                    .setHasFixedTransformationMatrix(true)
            }
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder =
        GridViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false))

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size
}