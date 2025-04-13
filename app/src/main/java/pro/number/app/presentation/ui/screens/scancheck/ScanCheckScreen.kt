package pro.number.app.presentation.ui.screens.scancheck

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.createBitmap
import pro.number.app.R
import pro.number.app.presentation.ViewModelFactory
import pro.number.app.presentation.ui.components.Header
import pro.number.app.presentation.ui.screens.groups.GroupsViewModel
import pro.number.app.presentation.ui.theme.AppTheme

@Composable
fun ScanCheckScreen(
    viewModelFactory: ViewModelFactory,
    image: Bitmap,
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
                onClick = { TODO("Клик на кнопку добавления новой группы") }
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
            ReceiptCard(image = image)
            Button(
                onClick = { TODO("Кнопка далее") },
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

@Preview
@Composable
private fun ScanCheckScreenPreview() {
    AppTheme {
        val testBitmap: Bitmap = createBitmap(100, 100)
        ScanCheckScreen(ViewModelFactory.createForPreview(GroupsViewModel()), testBitmap) { }
    }
}

@Composable
fun ReceiptCard(
    modifier: Modifier = Modifier,
    image: Bitmap? = null
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 3.dp,
                color = MaterialTheme.colorScheme.tertiary,
                shape = RoundedCornerShape(20.dp),
            )
            .clip(RoundedCornerShape(20.dp))
    ) {
        if (image != null) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                bitmap = image.asImageBitmap(),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        } else {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(R.drawable.placeholder_check),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReceiptCardPreview() {
    AppTheme {
        ReceiptCard()
    }

}