package pl.edu.pwr.kotlin.mytasks

import android.content.ClipData
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.mcxiaoke.koi.log.logv
import kotlinx.android.synthetic.main.activity_task_list.*
import pl.edu.pwr.kotlin.mytasks.adapters.TasksAdapter
import pl.edu.pwr.kotlin.mytasks.data.Task
import pl.edu.pwr.kotlin.mytasks.model.TasksProvider
import android.view.ContextMenu.ContextMenuInfo
import android.widget.Toast
import com.mcxiaoke.koi.ext.find
import com.mcxiaoke.koi.ext.onClick
import com.mcxiaoke.koi.ext.toast
import kotlinx.android.synthetic.main.activity_task_list.*
import android.app.Activity





class TaskListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)
        logv("onCreate()")

        val newTaskName = intent.getStringExtra("name")
        val newTaskDescription = intent.getStringExtra("description")

        initView()
        initYourTODOs()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if(item?.itemId == R.id.main_button_addTask){

            val intent = Intent(this,NewTaskActivity::class.java)
            startActivityForResult(intent, 1)

            return true
        }

        return super.onOptionsItemSelected(item)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1 -> {
                if (resultCode == Activity.RESULT_OK) {

                    Toast.makeText(this, data.getStringExtra("name"), Toast.LENGTH_SHORT).show()

                    initView()
                    //initYourTODOs()
                    TasksProvider.add(Task(name = data.getStringExtra("name")))


                }
            }
        }
    }

    private fun initYourTODOs() {
        //TasksProvider.add(Task(name = "build me!"))
        //TasksProvider.add(Task(name = "implement add new task"))
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



