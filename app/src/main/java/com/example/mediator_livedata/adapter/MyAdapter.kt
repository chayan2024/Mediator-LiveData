package com.example.mediator_livedata.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mediator_livedata.R
import com.example.mediator_livedata.model.FastFood
import com.example.mediator_livedata.model.MainCourse

class MyAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var data: List<Any> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER_FAST_FOOD -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fast_food, parent, false)
                HeaderViewHolder(view)
            }
            TYPE_HEADER_MAIN_COURSE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main_course, parent, false)
                HeaderViewHolder(view)
            }
            TYPE_FAST_FOOD -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fast_food, parent, false)
                ViewHolderFastFood(view)
            }
            TYPE_MAIN_COURSE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main_course, parent, false)
                ViewHolderMainCourse(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolderFastFood -> {
                val fastFoodItem = data[position] as FastFood
                holder.bind(fastFoodItem)
            }
            is ViewHolderMainCourse -> {
                val mainCourseItem = data[position] as MainCourse
                holder.bind(mainCourseItem)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (data[position]) {
            is FastFood -> TYPE_FAST_FOOD
            is MainCourse -> TYPE_MAIN_COURSE
            else -> throw IllegalArgumentException("Invalid data type")
        }
    }

    fun setData(data: List<Any>) {
        this.data = data
        notifyDataSetChanged()
    }

    inner class ViewHolderFastFood(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewFastFood: TextView = itemView.findViewById(R.id.textViewFastFood)

        fun bind(fastFood: FastFood) {
            textViewFastFood.text = fastFood.name
        }
    }

    inner class ViewHolderMainCourse(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewMainCourse: TextView = itemView.findViewById(R.id.textViewMainCourse)

        fun bind(mainCourse: MainCourse) {
            textViewMainCourse.text = mainCourse.name
        }
    }

    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    companion object {
        private const val TYPE_HEADER_FAST_FOOD = 0
        private const val TYPE_HEADER_MAIN_COURSE = 1
        private const val TYPE_FAST_FOOD = 2
        private const val TYPE_MAIN_COURSE = 3
    }
}

