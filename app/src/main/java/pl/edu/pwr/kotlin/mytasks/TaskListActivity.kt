package pl.edu.pwr.kotlin.mytasks

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
import android.app.Activity
import android.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import pl.edu.pwr.kotlin.mytasks.support.DBhandler
import pl.edu.pwr.kotlin.mytasks.support.ItemClickSupport


class TaskListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)
        logv("onCreate()")

        //val newTaskName = intent.getStringExtra("name")
        //val newTaskDescription = intent.getStringExtra("description")

        //val dbHelper = DBhandler(this)
        //dbHelper.open()
        //dbHelper.deleteAllTasks()

        initView()
        initYourTODOs()

        val dlgAlert = AlertDialog.Builder(this)
        ItemClickSupport.addTo(tasksTasksList).setOnItemClickListener(object : pl.edu.pwr.kotlin.mytasks.support.ItemClickSupport.OnItemClickListener {
            override fun onItemClicked(recyclerView: RecyclerView, position: Int, v: View) {

                dlgAlert.setMessage(TasksProvider.getTaksWithId(position).desc);
                dlgAlert.setTitle(TasksProvider.getTaksWithId(position).name);
                dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setCancelable(true);
                dlgAlert.setPositiveButton("Ok"
                ) { dialog, which ->
                    //dismiss the dialog
                }
                dlgAlert.setNegativeButton("Delete"
                ) { dialog, which ->
                    TasksProvider.remove(TasksProvider.getTaksWithId(position))
                    initView()
                }
                dlgAlert.create().show();
            }
        })

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
                    initView()

                    TasksProvider.add(Task(name = data.getStringExtra("name"), desc = data.getStringExtra("description") ))

                    val dbHelper = DBhandler(this)
                    dbHelper.open()
                    dbHelper.createTask(data.getStringExtra("name"), "typ", data.getStringExtra("description"), "jakas data")
                    dbHelper.close()
                }
            }
        }
    }

    private fun initYourTODOs() {
        //TasksProvider.add(Task(name = "build me!"))
        //TasksProvider.add(Task(name = "implement add new task"))
        //TasksProvider.add(Task(name = "show task detail"))
        //TasksProvider.add(Task(name = "add task status"))
        //TasksProvider.add(Task(name = "show task's status and type on list"))
        //TasksProvider.add(Task(name = "should whole task be editable?"))
        //TasksProvider.add(Task(name = "refactor app if needed"))
        //TasksProvider.add(Task(name = "start love Kotlin ;)"))

        //val columns = arrayOf<String>(DBhandler.Tasks._ID, DBhandler.Tasks.COLUMN_NAME_NAME, DBhandler.Tasks.COLUMN_NAME_TYPE, DBhandler.Tasks.COLUMN_NAME_TYPE, DBhandler.Tasks.COLUMN_NAME_TYPE)


        val dbHelper = DBhandler(this)
        //val dbHelper = DBhandler(this)
        //dbHelper.deleteAllTasks()
        dbHelper.open()

        val coursor = dbHelper.fetchAllTasks()

        while(coursor.position < coursor.count){

            TasksProvider.add(Task(name = coursor.getString(1).toString(), desc = coursor.getString(3).toString()))

            coursor.moveToNext()
        }

        dbHelper.close()
        //Log.w("BD", coursor.getString(0).toString()) id
        //Log.w("BD", coursor.getString(1).toString()) name
        //Log.w("BD", coursor.getString(2).toString()) typ
        //Log.w("BD", coursor.getString(3).toString()) desc
        //Log.w("BD", coursor.getString(4).toString()) due date

    }

    private fun initView() {

        val layoutManager = LinearLayoutManager(applicationContext)
        tasksTasksList.layoutManager = layoutManager
        tasksTasksList.itemAnimator = DefaultItemAnimator()
        tasksTasksList.adapter = TasksAdapter(this, TasksProvider.tasks)
    }





}



