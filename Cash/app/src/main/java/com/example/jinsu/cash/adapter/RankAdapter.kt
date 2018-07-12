package com.example.jinsu.cash.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.RequestManager
import com.example.jinsu.cash.R
import com.example.jinsu.cash.model.Rank

class RankAdapter(val list: ArrayList<Rank>, val glide: RequestManager) : RecyclerView.Adapter<RankAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rank, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.rank_num!!.text = list[position].rank_num
        holder.rank_name!!.text = list[position].nickname
        holder.rank_sum!!.text = list[position].run_sum
        glide.load(R.drawable.star).into(holder.im_star)


    }

    override fun getItemCount(): Int {
        return list.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var rank_num = itemView.findViewById<TextView>(R.id.rank_txt_num)
        var rank_name = itemView.findViewById<TextView>(R.id.rank_txt_name)
        var rank_sum = itemView.findViewById<TextView>(R.id.rank_txt_sum)
        var im_profile = itemView.findViewById<ImageView>(R.id.rank_im_profile)
        var im_star = itemView.findViewById<ImageView>(R.id.rank_im_star)



    }
}
