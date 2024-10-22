package com.poc.panorama

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.poc.panorama.demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.webview.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    Image360Activity::class.java
                )
            )
        }
        binding.sceneView.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    SceneViewActivity::class.java
                )
            )
        }
        binding.panoramaGl.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    PanoGlActivity::class.java
                )
            )
        }

    }

}