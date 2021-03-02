package com.example.kotlinwhetherapplication

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinwhetherapplication.Adapter.MyMovieAdapter
import com.example.kotlinwhetherapplication.Common.Common
import com.example.kotlinwhetherapplication.Interface.RetrofitServieces
import com.example.kotlinwhetherapplication.Model.Movie
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_marvel.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class MarvelActivity : AppCompatActivity() {
    lateinit var mService: RetrofitServieces
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var adapter: MyMovieAdapter
    lateinit var alertDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marvel)

        mService = Common.retrofitServieces
        recyclerMovieList.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerMovieList.layoutManager = linearLayoutManager
        alertDialog = SpotsDialog.Builder().setCancelable(true).setContext(this).build()

        getAllMovieList()

    }

    fun getAllMovieList(){
        alertDialog.show()
        mService.getMovieList().enqueue(object:Callback<MutableList<Movie>>{
            override fun onFailure(call: Call<MutableList<Movie>>, t: Throwable) {

            }

            override fun onResponse(call: Call<MutableList<Movie>>, response: Response<MutableList<Movie>>) {
                adapter = MyMovieAdapter(baseContext, response.body() as MutableList<Movie>)
                adapter.notifyDataSetChanged()
                recyclerMovieList.adapter = adapter

                alertDialog.dismiss()
            }
        })
    }
}