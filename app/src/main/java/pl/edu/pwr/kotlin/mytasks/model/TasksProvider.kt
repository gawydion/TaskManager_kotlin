package pl.edu.pwr.kotlin.mytasks.model

import pl.edu.pwr.kotlin.mytasks.data.Task
import java.util.*
import kotlin.Comparator

/**
 * Created by mzc on 6/24/2017.
 */
object TasksProvider {
    val tasks = ArrayList<Task>()

    fun add( task : Task) {
        tasks.add(task)

        /*
        Collections.sort(tasks, object: Comparator<Task>(){
            override fun compare(o1: Task?, o2: Task?): Int {
                if (o2 != null) return o1.prio.compareTo(o2.prio)
            }
        })
*/
        // Sorting
        //TODO sortowanie
    }

    fun remove( task : Task) {
        tasks.remove(task)
    }

    fun  getTaksWithId(id: Int): Task  {
        return  tasks.get(id)
    }


}