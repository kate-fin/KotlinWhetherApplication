package com.example.kotlinwhetherapplication

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinwhetherapplication.adapter.MyMovieAdapter
import com.example.kotlinwhetherapplication.сommon.Common
import com.example.kotlinwhetherapplication.interfaces.RetrofitServieces
import com.example.kotlinwhetherapplication.model.Movie
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_marvel.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MarvelActivity : AppCompatActivity() {
    lateinit var mService1: RetrofitServieces
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var adapter: MyMovieAdapter
    lateinit var alertDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marvel)

        mService1 = Common.marvelRetrofitServieces
        recyclerMovieList.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerMovieList.layoutManager = linearLayoutManager
        alertDialog = SpotsDialog.Builder().setCancelable(true).setContext(this).build()

        getAllMovieList()

    }

    private fun getAllMovieList(){
        alertDialog.show()
        mService1.getMovieList().enqueue(object:Callback<MutableList<Movie>>{
            override fun onFailure(call: Call<MutableList<Movie>>, t: Throwable) {
                call.cancel()
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