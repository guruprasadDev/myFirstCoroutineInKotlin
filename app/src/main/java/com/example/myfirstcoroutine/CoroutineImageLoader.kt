package com.example.myfirstcoroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.example.myfirstcoroutine.databinding.ActivityCotlineImageLoaderBinding

class CoroutineImageLoader : AppCompatActivity() {

    private lateinit var  binding:ActivityCotlineImageLoaderBinding

    private val link ="https://webneel.com/wallpaper/sites/default/files/images/08-2018/3-nature-wallpaper-mountain.jpg"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =   ActivityCotlineImageLoaderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.img1.load(link){
            crossfade(true)
            placeholder(R.drawable.ic_launcher_background)
            crossfade(10000)
            transformations(RoundedCornersTransformation(100f),
                )
        }
        binding.img2.load(R.drawable.mm){
            crossfade(true)
            crossfade(1000)
            transformations(RoundedCornersTransformation(200f))

        }

        /**
        Coil - Coroutine Image Loader.
        It is an image loading library backed by Kotlin Coroutines.
        Coil is a pretty neat image loading library for Android.
        - Its has features that make testing easier.
        - No annotation processing! The big one

        " An image loading library for Android backed by Kotlin Coroutines. Coil is:
        - Fast
        - Lightweight
        - Easy to use
        - Modern "

        Dependency:
        implementation("io.coil-kt:coil:1.1.1")

         */



    }
}