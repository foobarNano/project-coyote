package com.coyote.android

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.coyote.android.tools.getClipborder
import com.coyote.android.tools.grabbers.getAppListGrabber
import com.coyote.android.ui.theme.MainTheme
import com.coyote.android.views.MainViewModel

class Main : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        @Suppress("DEPRECATION")
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)

        setContent {
            MainTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ActivityMain(viewModel)
                }
            }
        }
    }

    @Composable
    fun ActivityMain(viewModel: MainViewModel, modifier: Modifier = Modifier) {

        val context = LocalContext.current
        val resourceGrabber = getAppListGrabber(context)
        val clipboarder = getClipborder(context)

        val userApps = resourceGrabber.getUserApps()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(30.dp, Alignment.CenterVertically)
        ) {
            Text(
                text = "Installed apps:",
                color = MaterialTheme.colorScheme.primary,
                fontFamily = FontFamily.Monospace,
                fontSize = TextUnit(7f, TextUnitType.Em),
                textAlign = TextAlign.Center
            )
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f),
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    for(app in userApps) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .wrapContentHeight()
                                .offset(0.dp, 5.dp)
                                .clickable {
                                    viewModel.showInfo(app)
                                }
                        ) {
                            val txt = app.name + '\n' +
                                    "Category:\t\t" + app.category + '\n' +
                                    "Class:\t\t" + app.className + '\n'

                            Image(
                                bitmap = app.iconBitmapSmall.asImageBitmap(),
                                contentDescription = "",
                                modifier = Modifier
                                    .padding(15.dp, 15.dp)
                            )
                            Text(
                                text = txt,
                                modifier = Modifier
                                    .padding(5.dp)
                                    .offset(0.dp, 5.dp)
                                    .fillMaxWidth(),
                                color = MaterialTheme.colorScheme.primary,
                                fontFamily = FontFamily.Monospace,
                                fontSize = TextUnit(3f, TextUnitType.Em),
                                lineHeight = TextUnit(1.2f, TextUnitType.Em),
                                softWrap = false,
                                textAlign = TextAlign.Start
                            ) }
                    }
                }
            }
            Surface(
                modifier = modifier
                    .fillMaxWidth()
                    .height(70.dp),
                shape = RoundedCornerShape(50.dp),
                color = MaterialTheme.colorScheme.secondary
            ) {
                TextButton(
                    onClick = {
                        clipboarder.postClipboard("Installed Apps", userApps.joinToString(separator = "\n"))
                    }
                ) {
                    Text(
                        text = "Copy",
                        color = MaterialTheme.colorScheme.primary,
                        fontFamily = FontFamily.Monospace,
                        fontSize = TextUnit(6f, TextUnitType.Em),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        if (viewModel.isActive) {
            CustomDialog(viewModel.appShown, onDismiss = { viewModel.hideInfo() })
        }
    }
}