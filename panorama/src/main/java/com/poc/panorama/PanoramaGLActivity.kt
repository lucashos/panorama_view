package com.poc.panorama

import android.hardware.SensorManager
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.drawToBitmap
import com.panoramagl.PLConstants
import com.panoramagl.PLISceneElement
import com.panoramagl.PLITexture
import com.panoramagl.PLImage
import com.panoramagl.PLManager
import com.panoramagl.PLSceneElementBase
import com.panoramagl.PLSphericalPanorama
import com.panoramagl.PLTexture
import com.panoramagl.hotspots.ActionPLHotspot
import com.panoramagl.hotspots.HotSpotListener
import com.panoramagl.utils.PLUtils
import com.poc.panorama.sdk.R
import com.poc.panorama.sdk.databinding.ActivityPanoramaGlBinding

class PanoramaGLActivity : AppCompatActivity(), HotSpotListener {

    private val binding by lazy { ActivityPanoramaGlBinding.inflate(layoutInflater) }
    private val plManager by lazy { PLManager(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        plManager.run {
            setContentView(binding.root)
            isAccelerometerEnabled = false
            isInertiaEnabled = false
            isZoomEnabled = false


            onCreate()
            val panoramaImage = PLUtils.getBitmap(this@PanoramaGLActivity, R.drawable.panorama)
            val panoramaView = PLSphericalPanorama()
            panoramaView.camera.lookAt(30.0f, 90.0f)
            panoramaView.setImage(PLImage(panoramaImage, false))

            val plHotspot2 = ActionPLHotspot(
                this@PanoramaGLActivity,
                100,
                PLImage(PLUtils.getBitmap(this@PanoramaGLActivity, R.drawable.hotspot)),
                0f,
                0f,
                PLConstants.kDefaultHotspotSize,
                PLConstants.kDefaultHotspotSize
            )

            val tooltip = binding.ctnTooltip.drawToBitmap()
            val plTooltip = ActionPLHotspot(
                this@PanoramaGLActivity,
                101,
                PLImage(tooltip),
                1f,
                0f,
                PLConstants.kDefaultHotspotSize * 1.5f,
                PLConstants.kDefaultHotspotSize * 1.5f
            )

            panorama.addHotspot(plHotspot2)
            panorama.addHotspot(plTooltip)

            panorama = panoramaView
            startSensorialRotation()
            
        }
    }

    override fun onResume() {
        super.onResume()
        plManager.onResume();
    }

    override fun onPause() {
        plManager.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        plManager.onDestroy()
        super.onDestroy()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return plManager.onTouchEvent(event)
    }

    override fun onHotspotClick(identifier: Long) {
        runOnUiThread { AlertDialog.Builder(this@PanoramaGLActivity)
            .setTitle("Button clicked")
            .setMessage("Clicked on icon $identifier")
            .create().show()
        }
    }

}