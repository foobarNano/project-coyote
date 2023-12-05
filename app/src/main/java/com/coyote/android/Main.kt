package com.coyote.android

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.coyote.android.tools.Clipboarder
import com.coyote.android.tools.ResourceGrabber
import com.coyote.android.ui.theme.MainTheme

class Main : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContent {
            MainTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.hsv(220f, 0.15f, 0.15f)
                ) {
                    ActivityMain()
                }
            }
        }
    }
}

@Composable
fun ActivityMain(modifier: Modifier = Modifier, dummylist: List<String> = listOf()) {

    val context = LocalContext.current
    val resourceGrabber = ResourceGrabber(context)
    val clipboarder = Clipboarder(context)

    val userApps = resourceGrabber.getUserApps()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Installed apps:",
            color = Color.White,
            fontFamily = FontFamily.Monospace,
            fontSize = TextUnit(7f, TextUnitType.Em),
            textAlign = TextAlign.Center
        )
        Surface(
            modifier = Modifier
                .width(420.dp)
                .height(420.dp)
                .offset(0.dp, 20.dp)
                .fillMaxHeight(),
            color = Color.hsv(220f, 0.15f, 0.20f),
            shape = RoundedCornerShape(20.dp)
        ) {
            LazyColumn {
                items(userApps) {
                        app -> Row {
                    val txt = app.name + '\n' +
                            "Category:\t\t" + app.category + '\n' +
                            "Class:\t\t" + app.className + '\n'

                    Image(
                        bitmap = app.iconBitmap.asImageBitmap(),
                        contentDescription = "",
                        modifier = Modifier.padding(15.dp, 20.dp, 15.dp, 0.dp)
                    )
                    Text(
                        text = txt,
                        modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp),
                        color = Color.White,
                        fontFamily = FontFamily.Monospace,
                        fontSize = TextUnit(3f, TextUnitType.Em),
                        lineHeight = TextUnit(1.2f, TextUnitType.Em),
                        softWrap = false
                    ) }
                }
                items(dummylist) {
                        dummy -> Row {
                    Text(
                        text = dummy,
                        modifier = Modifier.padding(10.dp, 10.dp, 0.dp, 0.dp),
                        color = Color.White,
                        fontFamily = FontFamily.Monospace,
                        fontSize = TextUnit(3f, TextUnitType.Em),
                        lineHeight = TextUnit(1.2f, TextUnitType.Em),
                        softWrap = false
                    )
                }
                }
            }
        }
        Surface(
            modifier = modifier
                .width(420.dp)
                .height(70.dp)
                .offset(0.dp, 40.dp),
            color = Color.hsv(0f, 0.7f, 0.9f),
            shape = RoundedCornerShape(100.dp)
        ) {
            TextButton(
                content = {
                    Text(
                        text = "Copy",
                        color = Color.White,
                        fontFamily = FontFamily.Monospace,
                        fontSize = TextUnit(6f, TextUnitType.Em),
                        textAlign = TextAlign.Center
                    )
                },
                onClick = {
                    clipboarder.postClipboard("Installed Apps", userApps.joinToString(separator = "\n"))
                }
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun MainPreview() {
    MainTheme {
        ActivityMain(dummylist = listOf("com.a", "com.b", "com.c", "com.d", "com.e"))
    }
}