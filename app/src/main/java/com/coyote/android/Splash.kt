package com.coyote.android

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import com.coyote.android.ui.theme.MainTheme
import kotlinx.coroutines.delay

class Splash : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        @Suppress("DEPRECATION")
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)

        setContent {
            MainTheme {
                SplashScreen()
            }
        }
    }

    @Composable
    private fun SplashScreen() {

        val alpha = remember {
            Animatable(0f)
        }

        LaunchedEffect(key1 = true, block = {
            alpha.animateTo(1f, animationSpec = tween(1000))
            delay(1000)
            startActivity(Intent(this@Splash, Main::class.java))
        })
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.alpha(alpha.value))
        }
    }
}