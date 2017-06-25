package pl.edu.pwr.kotlin.mytasks.model

import pl.edu.pwr.kotlin.mytasks.data.Task

/**
 * Created by mzc on 6/24/2017.
 */
object TasksProvider {
    val tasks = ArrayList<Task>()

    fun add( task : Task) {
        tasks.add(task)
    }

    fun remove( task : Task) {
        tasks.remove(task)
    }
}