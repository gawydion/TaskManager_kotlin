package pl.edu.pwr.kotlin.mytasks.data

import java.util.*

/**
 * Created by mzc on 6/24/2017.
 */
data class Task(var name : String, var type : TaskType = TaskType.TASK ,  var desc : String = "", var dueDate: Date? = null)