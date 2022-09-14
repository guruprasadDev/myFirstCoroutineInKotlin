package com.example.myfirstcoroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.myfirstcoroutine.framework.util.ThreadUtil

/**
 * How to use asyncTask to update ui by thread
 * 1. Lets go, first, built ui
 * 2. run app, ok
 * 3. Ill update 3 textView one by one.
 * for example Ill update textViewOnes text after 2 seconds
 * and the ill update textViewTwos text after 3 seconds
 * 4. ok, lets build thread tool
 */
class AsynchronouslyUpdateUI : AppCompatActivity() {

    private lateinit var textOne :TextView
    private lateinit var textTwo :TextView
    private lateinit var textThree :TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asynchronously_update_ui)

        findView()
        updateViewTextOne()
        updateViewTextTwo()
        updateViewTextThree()
    }

    private fun findView(){
        textOne = findViewById(R.id.textViewOne)
        textTwo = findViewById(R.id.textViewTwo)
        textThree = findViewById(R.id.textViewThree)
    }

    private fun updateViewTextOne(){
        ThreadUtil.startThread{
            Thread.sleep(3000)

            ThreadUtil.startUIThread(0){
                textOne.text = "Hello update one"


            }
        }
    }

//after  three seconds 3 textView will be updated almost same time, and thread, sleep will not block the ui,
// so we can scroll up or scroll down
    private fun updateViewTextTwo(){
        ThreadUtil.startThread{
            Thread.sleep(3000)

            ThreadUtil.startUIThread(0){
                textTwo.text = "Hello update Two"
            }
        }
    }


    private fun updateViewTextThree(){
        ThreadUtil.startThread{
            Thread.sleep(3000)

            ThreadUtil.startUIThread(0){
                textThree.text = "Hello update Three"
            }
        }
    }
}

/**
android asynchronously update UI | android update UI from thread | android asynchronous example
 */