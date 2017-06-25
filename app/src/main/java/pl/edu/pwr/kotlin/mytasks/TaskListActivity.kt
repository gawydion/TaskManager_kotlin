package pl.edu.pwr.kotlin.mytasks

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import com.mcxiaoke.koi.log.logv
import kotlinx.android.synthetic.main.activity_task_list.*
import pl.edu.pwr.kotlin.mytasks.adapters.TasksAdapter
import pl.edu.pwr.kotlin.mytasks.data.Task
import pl.edu.pwr.kotlin.mytasks.model.TasksProvider

class TaskListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)
        logv("onCreate()")
        initView()
        initYourTODOs()
    }

    private fun initYourTODOs() {
        TasksProvider.add(Task(name = "build me!"))
        TasksProvider.add(Task(name = "implement add new task"))
        TasksProvider.add(Task(name = "show task detail"))
        TasksProvider.add(Task(name = "add task status"))
        TasksProvider.add(Task(name = "show task's status and type on list"))
        TasksProvider.add(Task(name = "should whole task be editable?"))
        TasksProvider.add(Task(name = "refactor app if needed"))
        TasksProvider.add(Task(name = "start love Kotlin ;)"))
    }

    private fun initView() {
        val layoutManager = LinearLayoutManager(applicationContext)
        tasksTasksList.layoutManager = layoutManager
        tasksTasksList.itemAnimator = DefaultItemAnimator()
        tasksTasksList.adapter = TasksAdapter(this, TasksProvider.tasks)
    }


}


