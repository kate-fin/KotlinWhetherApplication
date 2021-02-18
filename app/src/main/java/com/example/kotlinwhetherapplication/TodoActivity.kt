package com.example.kotlinwhetherapplication

import android.content.Context
import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_todo.*
import java.io.*


class TodoActivity : AppCompatActivity() {

    private val todoList = mutableListOf<String>()
    private val activeInd = mutableListOf<Int>()
//    var imageView = findViewById(R.id.icon) as ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)

        open()

        var listAdapter = ArrayAdapter<String>(this,
            android.R.layout.simple_expandable_list_item_2,  todoList)

        inputButton.setOnClickListener {
            val inputText: String = inputEditText.text.toString()
            println("input button val $inputText")
            if (inputText != "") {
                todoList.add(inputText)
                activeInd.add(todoList.size - 1)
                println("input button list $todoList")
                listView.adapter = listAdapter
                inputEditText.text.clear()
            }
        }

        listView.onItemClickListener = OnItemClickListener { parent, v, position, id ->
            val selected: SparseBooleanArray = listView.checkedItemPositions
            activeInd.clear()
            for (i in todoList.size - 1 downTo 0) {
                if (!selected[i]){
                    activeInd.add(i)
                }
            }
        }

        allInput.setOnClickListener {

            listAdapter = ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice,  todoList)
            listView.adapter = listAdapter
            for(i in 0 until todoList.size) {
                if (i !in activeInd){
                    listView.setItemChecked(i, true)
                }
            }
        }

        activeInput.setOnClickListener {
            val activeList = mutableListOf<String>()
            activeInd.sort()
            for(i in activeInd){
                activeList.add(todoList[i])
            }
            listAdapter = ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice,  activeList)
            listView.adapter = listAdapter
        }

        completedInput.setOnClickListener {
            val completedList = mutableListOf<String>()
            for (i in 0 until todoList.size){
                if (i !in activeInd){
                    completedList.add(todoList[i])
                }
            }
            listAdapter = ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice,  completedList)
            listView.adapter = listAdapter
            for(i in 0 until completedList.size){
                listView.setItemChecked(i, true)
            }
        }

        println("2onCreate ${lifecycle.currentState}")
        if(lifecycle.currentState==Lifecycle.State.CREATED){
            println("2Created")
        }
        if(lifecycle.currentState==Lifecycle.State.DESTROYED){
            println("2Destroyed")
        }
        if(lifecycle.currentState==Lifecycle.State.INITIALIZED){
            println("2INITIALIZED")
        }
        if(lifecycle.currentState==Lifecycle.State.RESUMED){
            println("2Resumed")
        }
        if(lifecycle.currentState==Lifecycle.State.STARTED){
            println("2Started")
        }
    }

    fun printAll(){
        val listAdapter = ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_multiple_choice,  todoList)
        listView.adapter = listAdapter
        for(i in 0 until todoList.size) {
            if (i !in activeInd){
                listView.setItemChecked(i, true)
            }
        }
    }

    fun open() {
        val text = readFile()
        if (text != ""){
            val myClass = Gson().fromJson(text, Array<DataItems> :: class.java).toList()
            for (ob in myClass){
                todoList.add(ob.thing)
                if(ob.state){
                    activeInd.add(myClass.indexOf(ob))
                }
                println("open: ${ob.thing} - ${ob.state}")
            }
        }
        printAll()
        Toast.makeText(this, "Данные загружены", Toast.LENGTH_SHORT).show()
    }

    fun save() {
        val jsonArr = mutableListOf<DataItems>()
        println("todoList $todoList")
        println("activeInd $activeInd")
//        println("completedInd $completedInd")
        for (i in 0 until todoList.size){
            val valString = todoList[i]
            val state = i in activeInd //if (i+1 in activeArr) true else false
            jsonArr.add(DataItems(valString, state))
//            println("$valString - $state - ${jsonArr[i].thing} ${jsonArr[i].state}")
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

    fun readFile():String {
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















    override fun onStart() {
        super.onStart()
        println("2onStart ${lifecycle.currentState}")
        if(lifecycle.currentState== Lifecycle.State.CREATED){
            println("2Created")
        }
        if(lifecycle.currentState== Lifecycle.State.DESTROYED){
            println("2Destroyed")
        }
        if(lifecycle.currentState== Lifecycle.State.INITIALIZED){
            println("2INITIALIZED")
        }
        if(lifecycle.currentState== Lifecycle.State.RESUMED){
            println("2Resumed")
        }
        if(lifecycle.currentState== Lifecycle.State.STARTED){
            println("2Started")
        }
    }

    override fun onRestart() {
        super.onRestart()
        println("2onRestart ${lifecycle.currentState}")
        if(lifecycle.currentState== Lifecycle.State.CREATED){
            println("2Created")
        }
        if(lifecycle.currentState== Lifecycle.State.DESTROYED){
            println("2Destroyed")
        }
        if(lifecycle.currentState== Lifecycle.State.INITIALIZED){
            println("2INITIALIZED")
        }
        if(lifecycle.currentState== Lifecycle.State.RESUMED){
            println("2Resumed")
        }
        if(lifecycle.currentState== Lifecycle.State.STARTED){
            println("2Started")
        }
    }

    override fun onPause() {
        super.onPause()
        println("2onPause ${lifecycle.currentState}")
        if(lifecycle.currentState== Lifecycle.State.CREATED){
            println("2Created")
        }
        if(lifecycle.currentState== Lifecycle.State.DESTROYED){
            println("2Destroyed")
        }
        if(lifecycle.currentState== Lifecycle.State.INITIALIZED){
            println("2INITIALIZED")
        }
        if(lifecycle.currentState== Lifecycle.State.RESUMED){
            println("2Resumed")
        }
        if(lifecycle.currentState== Lifecycle.State.STARTED){
            println("2Started")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        println("2onDestroy ${lifecycle.currentState}")
        if(lifecycle.currentState== Lifecycle.State.CREATED){
            println("2Created")
        }
        if(lifecycle.currentState== Lifecycle.State.DESTROYED){
            println("2Destroyed")
        }
        if(lifecycle.currentState== Lifecycle.State.INITIALIZED){
            println("2INITIALIZED")
        }
        if(lifecycle.currentState== Lifecycle.State.RESUMED){
            println("2Resumed")
        }
        if(lifecycle.currentState== Lifecycle.State.STARTED){
            println("2Started")
        }
    }


}