package com.coyote.android

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun CustomDialog(
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
                .wrapContentHeight(Alignment.CenterVertically)
                .fillMaxWidth(0.90f)
                // Temporary placeholder for a button
                .clickable { onDismiss() },
            color = MaterialTheme.colorScheme.background,
            shape = RoundedCornerShape(20.dp),
        ) {
            Text(
                text = "Nothing to see here yet :P",
                modifier = Modifier.padding(10.dp),
                color = MaterialTheme.colorScheme.primary,
                fontFamily = FontFamily.Monospace,
                fontSize = TextUnit(4f, TextUnitType.Em),
                textAlign = TextAlign.Center
            )
        }
    }
    
}