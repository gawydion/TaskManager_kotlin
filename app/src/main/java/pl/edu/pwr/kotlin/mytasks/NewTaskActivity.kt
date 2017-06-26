package pl.edu.pwr.kotlin.mytasks

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mcxiaoke.koi.ext.onClick
import kotlinx.android.synthetic.main.activity_new_task.*

class NewTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)

        newTask_button_save.onClick {

            val resultIntent = Intent()

            resultIntent.putExtra("name", newTaskName.text.toString())
            resultIntent.putExtra("description", newTaskDescription.text.toString())
            setResult(Activity.RESULT_OK, resultIntent)
            finish()

        }

    }

    // TasksProvider.add(Task(name = "implement add new task"))
}
