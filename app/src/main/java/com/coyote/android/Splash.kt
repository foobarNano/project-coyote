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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.coyote.android.ui.theme.MainTheme

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

        val a1 = remember { Animatable(0f) }
        val a2 = remember { Animatable(0f) }

        LaunchedEffect(
            key1 = true,
            block = {
                a1.animateTo(1f, animationSpec = tween(1000))
                a2.animateTo(1f, animationSpec = tween(1000))
        })
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(50.dp, Alignment.CenterVertically)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.alpha(a1.value)
            )
            Button(
                shape = CircleShape,
                modifier = Modifier
                    .alpha(a2.value)
                    .size(90.dp),
                onClick = {
                    startActivity(Intent(this@Splash, Main::class.java))
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.power),
                    contentDescription = null
                )
            }
        }
    }
}