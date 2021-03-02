package com.example.kotlinwhetherapplication

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinwhetherapplication.Common.Common
import com.example.kotlinwhetherapplication.Interface.RetrofitServieces
import com.example.kotlinwhetherapplication.Model.Forecast
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.concurrent.Callable

class MainActivity : AppCompatActivity() {
    val DEGREE = "°"
    lateinit var mService: RetrofitServieces
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var alertDialog: AlertDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mService = Common.weatherRetrofitServieces
        linearLayoutManager = LinearLayoutManager(this)
        alertDialog = SpotsDialog.Builder().setCancelable(true).setContext(this).build()

        getAllWeatherList()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.todo_id -> intent = Intent(this, TodoActivity::class.java)
            R.id.marvel_id -> intent = Intent(this, MarvelActivity::class.java)
        }
        startActivity(intent)
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
                lowTemp.text = response.body()?.main?.temp_min.toString()
                highTemp.text = response.body()?.main?.temp_max.toString()

//                alertDialog.dismiss()
            }
        })
    }


}