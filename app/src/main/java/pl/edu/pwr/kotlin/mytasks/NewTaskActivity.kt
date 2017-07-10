package pl.edu.pwr.kotlin.mytasks

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.R.attr.color
import android.view.KeyEvent
import com.mcxiaoke.koi.ext.onClick
import kotlinx.android.synthetic.main.activity_new_task.*
import android.view.KeyEvent.KEYCODE_BACK
import android.widget.Toast
import android.widget.ArrayAdapter
import android.graphics.ColorFilter
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Spinner
import org.jetbrains.anko.sdk25.coroutines.onItemClick
import org.jetbrains.anko.sdk25.coroutines.onItemSelectedListener
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener




class NewTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)

        val resultIntent = Intent()
        resultIntent.putExtra("prio", 0)
        //

        val staticAdapter = ArrayAdapter.createFromResource(this, R.array.prio_spinner, android.R.layout.simple_spinner_item)

        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        newTaskTypeSpinner.setAdapter(staticAdapter)

        val color = color(R.color.task0)
        newTaskTypeImg.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)

        newTaskTypeSpinner.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                val color = when(position){
                    0 -> color(R.color.task0)
                    1 -> color(R.color.task1)
                    2 -> color(R.color.task2)
                    3 -> color(R.color.task3)
                    4 -> color(R.color.task4)
                    else -> Color.BLACK
                }
                newTaskTypeImg.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
                resultIntent.putExtra("prio", position)
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // your code here
            }

        })

        //

        newTask_button_save.onClick {

            if(!(newTaskName.text.toString().equals(""))){
                resultIntent.putExtra("name", newTaskName.text.toString())

                if(newTaskDescription.text.toString().equals("")) {
                    newTaskDescription.setText("<no description>")
                }

                resultIntent.putExtra("description", newTaskDescription.text.toString())

                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
            //Toast.makeText(applicationContext, "Input task data", Toast.LENGTH_LONG).show()
        }

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            setResult(Activity.RESULT_CANCELED, Intent()) // w chuj ważne! żeby data w startActivity for result nie bylio null trzeba wyslac cokolwiek czyli Intent()
            finish()
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun color(rId : Int) = ContextCompat.getColor(applicationContext, rId)

    // TasksProvider.add(Task(name = "implement add new task"))
}


