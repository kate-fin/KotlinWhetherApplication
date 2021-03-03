package com.example.kotlinwhetherapplication

import android.content.Context
import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_todo.*
import java.io.*
import java.util.ArrayList


class TodoActivity : AppCompatActivity() {
    lateinit var listAdapter: MyArrayAdapter
//    var imageView = findViewById(R.id.icon) as ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)

        listAdapter = MyArrayAdapter(this)
        open()
        listAdapter.setModeSelected(MyArrayAdapter.Mode.ALL)
        listAdapter.setShowNow()
        listView.adapter = listAdapter

        inputButton.setOnClickListener {
            val inputText: String = inputEditText.text.toString()
            println("input button val $inputText")
            if (inputText != "") {
                listAdapter.addIntoCheckSelected(false)
                listAdapter.addIntoListItems(inputText)
                listAdapter.setShowNow()
                listAdapter.notifyDataSetChanged()
                inputEditText.text.clear()
            }
        }

        allInput.setOnClickListener {
            print("All Button ")
            println("checkArr - ${MyArrayAdapter.getCheckSelected()}")
            listAdapter.setModeSelected(MyArrayAdapter.Mode.ALL)
            listAdapter.setShowNow()
            listAdapter.notifyDataSetChanged()
        }

        activeInput.setOnClickListener {
            print("Active Button ")
            println("checkArr - ${MyArrayAdapter.getCheckSelected()}")
            listAdapter.setModeSelected(MyArrayAdapter.Mode.ACTIVE)
            listAdapter.setShowNow()
            listAdapter.notifyDataSetChanged()
        }

        completedInput.setOnClickListener {
            print("Completed button ")
            println("checkArr - ${MyArrayAdapter.getCheckSelected()}")
            listAdapter.setModeSelected(MyArrayAdapter.Mode.COMPLETED)
            listAdapter.setShowNow()
            listAdapter.notifyDataSetChanged()
        }
    }

    fun open() {
        val text = readFile()
        if (text != "") {
            val myClass = Gson().fromJson(text, Array<DataItems>::class.java).toList()
            for (ob in myClass) {
                listAdapter.addIntoCheckSelected(ob.state)
                listAdapter.addIntoListItems(ob.thing)
                println("open: ${ob.thing} - ${ob.state}")
            }
        }
        Toast.makeText(this, "Данные загружены", Toast.LENGTH_SHORT).show()
    }

    fun save() {
        val jsonArr = mutableListOf<DataItems>()
        println("mList ${listAdapter.getListItems()}")
        for (i in 0 until listAdapter.getListItems().size) {
            val valString = listAdapter.getListItems()[i]
            val state =
                MyArrayAdapter.getCheckSelected(i)//i in completedInd //if (i+1 in activeArr) true else false
            jsonArr.add(DataItems(valString, state))
        }
        val myJSON = Gson().toJson(jsonArr)
        println("______________________________________")
        println("myJSON ${myJSON}")
        writeFile(myJSON)
        println("Файл записан")
    }

    override fun onResume() {
        super.onResume()
        println("todo onResume")
    }


    var FILENAME = "my_things.json"
    fun writeFile(string: String) {
        try {
            // отрываем поток для записи
            val bw = BufferedWriter(
                OutputStreamWriter(
                    openFileOutput(FILENAME, Context.MODE_PRIVATE)
                )
            )
            // пишем данные
            bw.write(string)
            // закрываем поток
            bw.close()
            println("записанный файл: $string")
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun readFile(): String {
        try {
            // открываем поток для чтения
            val br = BufferedReader(
                InputStreamReader(
                    openFileInput(FILENAME)
                )
            )
            println("Файл прочитан")
            return br.readText()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return ""
    }

    override fun onStop() {
        super.onStop()
        println("todo onStop")
        save()
    }
}