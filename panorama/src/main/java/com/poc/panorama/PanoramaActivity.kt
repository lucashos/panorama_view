package com.poc.panorama

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import io.github.sceneview.math.Position
import io.github.sceneview.math.Scale
import io.github.sceneview.node.ModelNode
import kotlinx.coroutines.launch
import missing.namespace.databinding.ActivityPanoramaBinding

class PanoramaActivity : AppCompatActivity() {
    private val binding by lazy { ActivityPanoramaBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        lifecycleScope.launch {
            val hdrFile = "environments/studio_small_09_2k.hdr"

            binding.vrPanoramaView.run {
                environmentLoader.loadHDREnvironment(hdrFile).apply {
                    indirectLight = this?.indirectLight
                    skybox = this?.skybox
                }
                cameraNode.apply {
                    position = Position(z = 4.0f)
                }
                val modelFile = "models/MaterialSuite.glb"
                val modelInstance = modelLoader.createModelInstance(modelFile)

                val modelNode = ModelNode(
                    modelInstance = modelInstance,
                    scaleToUnits = 2.0f,
                )
                modelNode.scale = Scale(0.05f)
                addChildNode(modelNode)
            }
        }
    }
}