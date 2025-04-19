package pro.number.app.presentation.ui.screens.scancheck

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pro.number.app.presentation.ViewModelFactory
import pro.number.app.presentation.ui.components.Header


@Composable
fun ScanCheckScreen(
    viewModelFactory: ViewModelFactory,
    needRotate: Boolean,
    groupId: Long,
    imageName: String,
    addNewCheck: (String) -> Unit,
    onContinueClick: (groupId: Long) -> Unit,
    onBackClick: () -> Unit
) {
    val viewModel = remember(Unit) {
        viewModelFactory.create(ScanCheckScreenViewModel::class.java)
    }

    Scaffold(
        topBar = {
            Header(title = "Сканирование", onBackClickListener = onBackClick)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors().copy(
                    containerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                    contentColor = MaterialTheme.colorScheme.onSurface
                ),
                contentPadding = PaddingValues(horizontal = 30.dp, vertical = 25.dp),
                shape = ShapeDefaults.Medium.copy(all = CornerSize(20.dp)),
                onClick = {
                    addNewCheck(imageName)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
                Text(
                    text = "Добавить новый чек",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            ReceiptCard(imageName = imageName, needRotate = needRotate)
            Button(
                onClick = { onContinueClick(groupId) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 62.dp),
                colors = ButtonDefaults.buttonColors()
                    .copy(containerColor = MaterialTheme.colorScheme.tertiary)
            ) {
                Text(text = "Далее", fontSize = 16.sp)
            }
        }
    }
}

@Composable
private fun ReceiptCard(
    modifier: Modifier = Modifier,
    imageName: String,
    needRotate: Boolean
) {
    val stroke = Stroke(
        width = 7f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    )
    val borderColor = MaterialTheme.colorScheme.tertiary

    Box(
        modifier = modifier
            .size(270.dp, 420.dp)
            .drawBehind {
                drawRoundRect(
                    color = borderColor,
                    style = stroke,
                    cornerRadius = CornerRadius(20.dp.toPx()),
                )
            }
    ) {
        ClipRoundedImage(imageName = imageName, needRotate = needRotate)
    }
}

@Composable
private fun ClipRoundedImage(modifier: Modifier = Modifier, imageName: String, needRotate: Boolean) {
    val degrees = if(needRotate) 90f else 0f
    Image(
        modifier = modifier.fillMaxWidth().clip(RoundedCornerShape(20.dp)),
        bitmap = rotateBitmap(BitmapFactory.decodeFile(imageName), degrees).asImageBitmap(),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}

private fun rotateBitmap(original: Bitmap, degrees: Float): Bitmap {
    val matrix = Matrix()
    matrix.preRotate(degrees)
    val rotatedBitmap =
        Bitmap.createBitmap(original, 0, 0, original.width, original.height, matrix, true)
    return rotatedBitmap
}