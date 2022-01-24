package com.example.recyclerviewlibrarydemo.ui.views

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.recyclerviewlibrarydemo.R
import com.example.recyclerviewlibrarydemo.domain.entities.Property
import kotlinx.android.synthetic.main.grid_view_item.view.*

class PhotosGridAdapter(private val properties: List<Property>) : RecyclerView.Adapter<PhotosGridAdapter.PhotosGridViewHolder>() {
    inner class PhotosGridViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val image: ImageView = itemView.cell_image
        private val text = itemView.textViewPropertyType
        private val card = itemView.propertyTypeCard

        fun bind(prop: Property) {
            val url = prop.imageUrl
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
            Glide.with(itemView.context).load(url)
                .apply(requestOptions)
                .into(image)
            text.text = prop.type
            card.setCardBackgroundColor(prop.getColor())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosGridViewHolder {
        val context = parent.context
        val layoutInflater = LayoutInflater.from(context)
        val photoView = layoutInflater.inflate(R.layout.grid_view_item, parent, false)
        return PhotosGridViewHolder(photoView)
    }

    override fun onBindViewHolder(holder: PhotosGridViewHolder, position: Int) {
        val property = properties[position]
        holder.bind(property)
    }

    override fun getItemCount(): Int {
        return properties.size
    }
}