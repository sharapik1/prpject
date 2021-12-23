package com.deathspirit.worldcinema.films

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.deathspirit.worldcinema.R
import com.deathspirit.worldcinema.common.HTTP

class filmsAdapter( private val values: ArrayList<films>,
                    private val activity: Activity
): RecyclerView.Adapter<filmsAdapter.ViewHolder>(){
    private var itemClickListener: ((films) -> Unit)? = null

    fun setItemClickListener(itemClickListener: (films) -> Unit) {
        this.itemClickListener = itemClickListener
    }

    // Метод onCreateViewHolder вызывается при создании визуального элемента
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // грузим layout, который содержит вёрстку элемента списка (нарисуйте сами)
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.film_item,
                parent,
                false)

        // создаем на его основе ViewHolder
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = values.size

    // заполняет визуальный элемент данными
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.titleFilmTextView.text = "${values[position].name} C"
        // onIconLoad.invoke(holder.iconImageView, values[position].weatherIcon)

        holder.container.setOnClickListener {
            //кликнули на элемент списка
            itemClickListener?.invoke(values[position])
        }

        /*HTTP.getImage("http://cinema.areas.su/movies${values[position].weatherIcon}.png") { bitmap, error ->
            if (bitmap != null) {
                activity.runOnUiThread {
                    try {
                        holder.imageImageView.setImageBitmap(bitmap)
                    } catch (e: Exception) {

                    }
                }
            } else
                Log.d("KEILOG", error)
        }*/
    }

    //Реализация класса ViewHolder, хранящего ссылки на виджеты.
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var imageImageView: ImageView = itemView.findViewById(R.id.imageFilm)
        var titleFilmTextView: TextView = itemView.findViewById(R.id.titleFilm)
        var container: LinearLayout = itemView.findViewById(R.id.container)
    }
}