package id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.splashscreen

import android.app.Activity
import android.content.Intent
import android.opengl.GLSurfaceView
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.notification.NotificationService

class SplashScreenActivity : Activity() {

    private lateinit var gLView: GLSurfaceView

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Intent(this, NotificationService::class.java).also { intent ->
            startService(intent)
        }

        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity.
        gLView = SplashScreenSurfaceView(this)
        setContentView(gLView)

        val textView = TextView(this)
        textView.text = "Welcome to Temere\n Tap to Continue..."
        addContentView(textView, ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT))

    }
}