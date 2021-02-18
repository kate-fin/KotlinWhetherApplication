package com.example.kotlinwhetherapplication

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class GetData: LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public fun getData(){
        println("get data")

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public fun sendData(){
        println("send data")
    }

}