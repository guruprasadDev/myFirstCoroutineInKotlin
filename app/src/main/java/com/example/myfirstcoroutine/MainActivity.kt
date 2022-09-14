package com.example.myfirstcoroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {
   private var result1="Api result 1"
   private var result2="Api result 2"
   private var result3="Api result 3"
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
//        CoroutineScope(IO).async{
//            val executionTime = measureTimeMillis {
//
//                val result1 :   Deferred<String> = async {
//                    println("deb1 : launching job1: ${Thread.currentThread().name}")
//                    getApiResult1()
//                }
//                val result2 :   Deferred<String> = async {
//                    println("deb1 : launching job2: ${Thread.currentThread().name}")
//                    getApiResult2()
//                }
//                val result3 :   Deferred<String> = async {
//                    println("deb1 : launching job3: ${Thread.currentThread().name}")
//                    getApiResult3()
//                }
//                setTextOnMainThread("Got ${result1.await()}")
//                setTextOnMainThread("Got ${result2.await()}")
//                setTextOnMainThread("Got ${result3.await()}")
//            }
//            println("deb1 : total time elapsed: $executionTime")
//        }
        // execute  Jobs in parallel on background threads at the same time

        val startTime = System.currentTimeMillis()
        val parentJob = CoroutineScope(IO).launch{
            val job1 = launch {
                val time1 = measureTimeMillis {
                    println("debugs : launching job1 in thread: ${Thread.currentThread().name}")
                    val result1 = getApiResult1()
                    setTextOnMainThread("Got $result1")
                }
                println("debugs : Completed job1 in $time1 ms. ")
            }
            val job2 = launch {
                val time2 = measureTimeMillis {
                    println("debug: launching job1 in thread: ${Thread.currentThread().name}")
                    val result2 = getApiResult2()
                    setTextOnMainThread("Got $result2")
                }
                println("debugs : Completed job1 in $time2  ms. ")
            }
            val job3 = launch {
                val time3 = measureTimeMillis {
                    println("debug: launching job1 in thread: ${Thread.currentThread().name}")
                    val result2 = getApiResult3()
                    setTextOnMainThread("Got $result3")
                }
                println("debugs : Completed job1 in $time3  ms. ")
            }

        }
        parentJob.invokeOnCompletion {
            println("debugs : total elapsed time : ${System.currentTimeMillis() - startTime}")
        }

    }
    private suspend fun getApiResult1():String{
        delay(10000)
        logThread("GetApiResult1")
        return result1
    }

    private suspend fun getApiResult2():String{
        logThread("GetApiResult2")
        return result2
    }

    private suspend fun getApiResult3():String{
        delay(10000)
        logThread("GetApiResult3")
        return result3
    }

    private fun logThread(method:String){
        println("Debugs $method : ${Thread.currentThread().name}")
    }
}