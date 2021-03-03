package com.example.kotlinwhetherapplication

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinwhetherapplication.сommon.Common
import com.example.kotlinwhetherapplication.interfaces.RetrofitServieces
import com.example.kotlinwhetherapplication.model.Forecast
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val DEGREE = "°"
    lateinit var mService: RetrofitServieces
    lateinit var alertDialog: AlertDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mService = Common.weatherRetrofitServieces
        alertDialog = SpotsDialog.Builder().setCancelable(true).setContext(this).build()

        getAllWeatherList()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent: Intent? = when (item.itemId){
            R.id.todo_id -> Intent(this, TodoActivity::class.java)
            R.id.marvel_id -> Intent(this, MarvelActivity::class.java)
            else -> null
        }
        if (intent != null){
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    fun getAllWeatherList(){
//        alertDialog.show()

        mService.getWeatherList().enqueue(object: Callback<Forecast>{
            override fun onFailure(call: Call<Forecast>, t: Throwable) {
                call.cancel()
            }

            override fun onResponse(call: Call<Forecast>, response: Response<Forecast>) {
                currentTemp.text = response.body()?.main?.temp.toString()
                lowTemp.text = response.body()?.main?.tempMin.toString()
                highTemp.text = response.body()?.main?.tempMax.toString()
//                alertDialog.dismiss()
            }
        })
    }



}