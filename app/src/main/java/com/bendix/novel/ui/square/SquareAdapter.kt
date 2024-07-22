package com.bendix.novel.ui.square

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.bendix.novel.R
import com.bendix.novel.model.BookBean

class SquareAdapter: RecyclerView.Adapter<SquareAdapter.MyViewHolder>() {
    
    private val mList: MutableList<BookBean> =ArrayList()
    inner class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        var ivCover: ImageView = itemView.findViewById(R.id.iv_item_cover)
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvAuthor: TextView = itemView.findViewById(R.id.tv_item_author)
        var tvDesc: TextView = itemView.findViewById(R.id.tv_item_desc)

    }

    interface OnBookItemClickListener {
        /**
         * 打开事件
         */
        fun openItem(t: BookBean)
    }

    /**
     * 点击项目事件
     */
    var itemClickListener: OnBookItemClickListener? = null
    
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            
       return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_book,parent,false))
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d("onBindViewHolder","onBindViewHolder")

        val bookModel = mList[position]
        Log.d("onBindViewHolder",bookModel.name)
        Log.d("onBindViewHolder",bookModel.toString())
        Log.d("onBindViewHolder",bookModel.author)
        Log.d("onBindViewHolder",bookModel.cover)

        holder.tvName.text = bookModel.name
        holder.tvAuthor.text =bookModel.author
        holder.tvDesc.text = bookModel.desc
        holder.ivCover.load(bookModel.cover)
        holder.itemView.setOnClickListener {
            itemClickListener?.openItem(bookModel)
        }
    }

    fun refreshItems(bookList: List<BookBean>) {
        mList.clear()
        mList.addAll(bookList)
        notifyDataSetChanged()
    }

    fun addItems(bookList: List<BookBean>) {
        Log.d("zzladdItems","addItems")
        mList.addAll(bookList)
        Log.d("zzladdItems",mList.toString())
        notifyDataSetChanged()
    }


}