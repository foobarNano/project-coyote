package com.coyote.android

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.coyote.android.models.AppData

@Composable
fun CustomDialog(
    app: AppData?,
    onDismiss:() -> Unit
) {
    
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Surface(
            modifier = Modifier
                .wrapContentSize(Alignment.Center)
                // Temporary placeholder for a button
                .clickable { onDismiss() },
            color = MaterialTheme.colorScheme.background,
            shape = RoundedCornerShape(20.dp),
        ) {
            Image(
                modifier = Modifier.padding(20.dp),
                bitmap = app!!.iconBitmapBig.asImageBitmap(),
                contentDescription = null
            )
//            Text(
//                text = "Nothing to see here yet :P",
//                modifier = Modifier.padding(10.dp),
//                color = MaterialTheme.colorScheme.primary,
//                fontFamily = FontFamily.Monospace,
//                fontSize = TextUnit(4f, TextUnitType.Em),
//                textAlign = TextAlign.Center
//            )
        }
    }
    
}