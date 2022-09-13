package com.example.myfirstcoroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class MainActivity : AppCompatActivity() {
   private var result1="Guru is result 1"
   private var result2="Guru is result 2"
    private val textHelloWorld : TextView get() = findViewById(R.id.textView)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.btn)
        btn.setOnClickListener {
            CoroutineScope(IO).launch {

                fakeApiRequest()

            }
        }
    }

    private fun setNewText(input:String){
        val newText =  textHelloWorld.text.toString() + "\n $input"
        textHelloWorld.text= newText
    }

    private suspend fun setTextOnMainThread(input: String){
        withContext(Main){
            setNewText(input)
        }
    }

    private suspend fun fakeApiRequest(){
        val result1 = getApiResult1()
        setTextOnMainThread(result1)

        val result2 = getApiResult2()

        setTextOnMainThread(result2)
    }
    private suspend fun getApiResult1():String{
        logThread("GetApiResult1")
        delay(1000)
        return result1
    }

    private suspend fun getApiResult2():String{
        logThread("GetApiResult1")
        delay(1000)
        return result2
    }

    private fun logThread(method:String){
        println("Debug $method : ${Thread.currentThread().name}")
    }
}