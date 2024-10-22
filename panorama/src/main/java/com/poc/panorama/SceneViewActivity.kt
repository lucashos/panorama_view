package com.poc.panorama

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.poc.panorama.sdk.databinding.ActivityPanoramaBinding
import io.github.sceneview.math.Position
import io.github.sceneview.math.Scale
import io.github.sceneview.node.ModelNode
import kotlinx.coroutines.launch

class SceneViewActivity : AppCompatActivity() {

    private val binding by lazy { ActivityPanoramaBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val sceneView = binding.vrPanoramaView

        lifecycleScope.launch {
            sceneView.cameraNode.apply {
                position = Position(z = 4.0f)
            }
            val modelFile = "models/MaterialSuite.glb"
            val modelInstance = sceneView.modelLoader.createModelInstance(modelFile)

            val modelNode = ModelNode(
                modelInstance = modelInstance,
                scaleToUnits = 2.0f,
            )
            modelNode.scale = Scale(0.05f)
            sceneView.addChildNode(modelNode)
        }
    }
}