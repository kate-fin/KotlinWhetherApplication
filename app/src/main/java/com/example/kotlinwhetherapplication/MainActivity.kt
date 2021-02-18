package com.example.kotlinwhetherapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Lifecycle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val getdata = GetData()
    var counter:Int = 0
    val DEGREE = "°"
    val text:String = "Карл <у Клары> украл <кораллы>, а Клара у Карла"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        general()
        println(object: Any() {}.javaClass.enclosingMethod?.name)
        println("1onCreate ${counter++} ${lifecycle.currentState}")
        lifecycle.addObserver(getdata)
        if(lifecycle.currentState==Lifecycle.State.CREATED){
            println("1Created")
        }
        if(lifecycle.currentState==Lifecycle.State.DESTROYED){
            println("1Destroyed")
        }
        if(lifecycle.currentState==Lifecycle.State.INITIALIZED){
            println("1INITIALIZED")
        }
        if(lifecycle.currentState==Lifecycle.State.RESUMED){
            println("1Resumed")
        }
        if(lifecycle.currentState==Lifecycle.State.STARTED){
            println("1Started")
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.todo_id -> intent = Intent(this, TodoActivity::class.java)
        }
        startActivity(intent)
        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()
        println("1onStart ${counter++} ${lifecycle.currentState}")
        if(lifecycle.currentState==Lifecycle.State.CREATED){
            println("1Created")
        }
        if(lifecycle.currentState==Lifecycle.State.DESTROYED){
            println("1Destroyed")
        }
        if(lifecycle.currentState==Lifecycle.State.INITIALIZED){
            println("1INITIALIZED")
        }
        if(lifecycle.currentState==Lifecycle.State.RESUMED){
            println("1Resumed")
        }
        if(lifecycle.currentState==Lifecycle.State.STARTED){
            println("1Started")
        }
    }

    override fun onResume() {
        super.onResume()
        println("1on Resume ${counter++} ${lifecycle.currentState}")
        if(lifecycle.currentState==Lifecycle.State.CREATED){
            println("1Created")
        }
        if(lifecycle.currentState==Lifecycle.State.DESTROYED){
            println("1Destroyed")
        }
        if(lifecycle.currentState==Lifecycle.State.INITIALIZED){
            println("1INITIALIZED")
        }
        if(lifecycle.currentState==Lifecycle.State.RESUMED){
            println("1Resumed")
        }
        if(lifecycle.currentState==Lifecycle.State.STARTED){
            println("1Started")
        }
    }

    override fun onRestart() {
        super.onRestart()
        println("1onRestart ${counter++} ${lifecycle.currentState}")
        if(lifecycle.currentState==Lifecycle.State.CREATED){
            println("1Created")
        }
        if(lifecycle.currentState==Lifecycle.State.DESTROYED){
            println("1Destroyed")
        }
        if(lifecycle.currentState==Lifecycle.State.INITIALIZED){
            println("1INITIALIZED")
        }
        if(lifecycle.currentState==Lifecycle.State.RESUMED){
            println("1Resumed")
        }
        if(lifecycle.currentState==Lifecycle.State.STARTED){
            println("1Started")
        }
    }

    override fun onPause() {
        super.onPause()
        println("1onPause ${counter++} ${lifecycle.currentState}")
        if(lifecycle.currentState==Lifecycle.State.CREATED){
            println("1Created")
        }
        if(lifecycle.currentState==Lifecycle.State.DESTROYED){
            println("1Destroyed")
        }
        if(lifecycle.currentState==Lifecycle.State.INITIALIZED){
            println("1INITIALIZED")
        }
        if(lifecycle.currentState==Lifecycle.State.RESUMED){
            println("1Resumed")
        }
        if(lifecycle.currentState==Lifecycle.State.STARTED){
            println("1Started")
        }
    }

    override fun onStop() {
        super.onStop()
        println("1onStop ${counter++} ${lifecycle.currentState}")
        if(lifecycle.currentState==Lifecycle.State.CREATED){
            println("1Created")
        }
        if(lifecycle.currentState==Lifecycle.State.DESTROYED){
            println("1Destroyed")
        }
        if(lifecycle.currentState==Lifecycle.State.INITIALIZED){
            println("1INITIALIZED")
        }
        if(lifecycle.currentState==Lifecycle.State.RESUMED){
            println("1Resumed")
        }
        if(lifecycle.currentState==Lifecycle.State.STARTED){
            println("1Started")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        println("1onDestroy ${counter++} ${lifecycle.currentState}")
        if(lifecycle.currentState==Lifecycle.State.CREATED){
            println("1Created")
        }
        if(lifecycle.currentState==Lifecycle.State.DESTROYED){
            println("1Destroyed")
        }
        if(lifecycle.currentState==Lifecycle.State.INITIALIZED){
            println("1INITIALIZED")
        }
        if(lifecycle.currentState==Lifecycle.State.RESUMED){
            println("1Resumed")
        }
        if(lifecycle.currentState==Lifecycle.State.STARTED){
            println("1Started")
        }
    }




    private fun general(){
        val subText:String = text.substringAfter('<')
        firstWord.text = subText.substringBefore('>')
        secondWord.text = subText.substringAfter('<').substringBefore('>')

        val name = "Kate"
        val age = 22
        val greeting = "Hi, there. My name is $name. I'm $age years old"
        currentTemp.text = greeting
        val currentTempText: String = currentTemp.text.toString()
        showGreetings("Hello there")
        val answer = sum(2, 5)
        println("answer = $answer")

        val main = Main()
        main.temp = 90
        main.minTemp = 20
        main.maxTemp = 100

        val weather = Weather()
        weather.main = main
        presentTemp(main)
    }

    @SuppressLint("SetTextI18n")
    private fun presentTemp(main: Main) {
        currentTemp.text = "${main.temp}$DEGREE"
        lowTemp.text ="${main.minTemp}$DEGREE"
        highTemp.text = "${main.maxTemp}$DEGREE"

    }

    private fun sum(num1: Int, num2: Int): Int{
        return num1 + num2
    }
    private fun showGreetings(text: String){
        println(text)
    }
}