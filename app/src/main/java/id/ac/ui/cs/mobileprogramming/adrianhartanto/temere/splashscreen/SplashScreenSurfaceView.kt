package id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.splashscreen

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.opengl.GLSurfaceView
import android.view.MotionEvent
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.MainActivity


class SplashScreenSurfaceView(context: Context) : GLSurfaceView(context) {

    private val renderer: SplashScreenRenderer

    init {

        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2)

        renderer = SplashScreenRenderer()

        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(renderer)
    }

    override fun onTouchEvent(e: MotionEvent): Boolean {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.

        val myIntent = Intent(context, MainActivity::class.java)
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        (context as Activity).finish()
        (context as Activity).overridePendingTransition(0, 0)
        context.startActivity(myIntent)
        (context as Activity).overridePendingTransition(0, 0)

        return true
    }
}