package pl.edu.pwr.kotlin.mytasks

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import com.mcxiaoke.koi.ext.onClick
import kotlinx.android.synthetic.main.activity_new_task.*
import android.view.KeyEvent.KEYCODE_BACK
import android.widget.Toast


class NewTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)

        newTask_button_save.onClick {

            val resultIntent = Intent()

            if(!(newTaskName.text.toString().equals(""))){
                resultIntent.putExtra("name", newTaskName.text.toString())

                if(newTaskDescription.text.toString().equals("")) {
                    newTaskDescription.setText("<no description>")
                    resultIntent.putExtra("description", newTaskDescription.text.toString())
                }

                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
            Toast.makeText(applicationContext, "Input task data", Toast.LENGTH_LONG).show()
        }

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            setResult(Activity.RESULT_CANCELED, Intent()) // w chuj ważne! żeby data w startActivity for result nie bylio null trzeba wyslac cokolwiek czyli Intent()
            finish()
        }
        return super.onKeyDown(keyCode, event)
    }

    // TasksProvider.add(Task(name = "implement add new task"))
}
