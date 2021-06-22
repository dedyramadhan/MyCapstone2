package com.capstoneproject2.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstoneproject2.core.R
import com.capstoneproject2.core.databinding.ItemMoviesBinding
import com.capstoneproject2.core.domain.model.MovieModel

class MovieAdapter(private val listener: MovieAdapterClickListener) :
    RecyclerView.Adapter<MovieAdapter.ListViewHolder>() {

    private var listData = ArrayList<MovieModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movies, parent, false)
        )
    }

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    fun setData(newListData: List<MovieModel>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemMoviesBinding.bind(itemView)
        fun bind(data: MovieModel) {
            with(itemView) {
                Glide.with(context)
                    .load(
                        context.resources.getString(
                            R.string.image_url_string,
                            data.movieBackgroundImage
                        )
                    )
                    .into(binding.ivItemImage)
                binding.tvItemTitle.text = data.movieTitle
                setOnClickListener {
                    listener.onMovieClickListener(listData[adapterPosition])
                }
            }
        }
    }
}