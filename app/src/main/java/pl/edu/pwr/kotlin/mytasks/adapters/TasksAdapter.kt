package pl.edu.pwr.kotlin.mytasks.adapters

import android.content.Context
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import pl.edu.pwr.kotlin.mytasks.R
import pl.edu.pwr.kotlin.mytasks.data.Task

/**
 * Created by mzc on 6/24/2017.
 */
class TasksAdapter(private val mContext: Context, private val mTasks: ArrayList<Task>) : RecyclerView.Adapter<TasksAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val typeImg = itemView.findViewById(R.id.itemTaskTypeImg) as ImageView
        val nameStr = itemView.findViewById(R.id.itemTaskNameTV) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_task, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val name = mTasks[position].name
        holder.nameStr.text=name;

        val prio = mTasks[position].prio

        val color = when(prio){
            0 -> color(R.color.task0)
            1 -> color(R.color.task1)
            2 -> color(R.color.task2)
            3 -> color(R.color.task3)
            4 -> color(R.color.task4)
            else -> Color.BLACK
        }
        holder.typeImg.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
    }

    override fun getItemCount() = mTasks.size

    private fun color(rId : Int) = ContextCompat.getColor(mContext, rId)

}