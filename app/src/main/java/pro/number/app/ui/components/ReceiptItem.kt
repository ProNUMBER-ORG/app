package pro.number.app.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pro.number.app.ui.theme.AppTheme

@Composable
fun ReceiptItem(
    modifier: Modifier = Modifier,
    productName: String,
    total: Int,
    onClickItem: () -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClickItem),
        color = MaterialTheme.colorScheme.surfaceContainer,
        shape = ShapeDefaults.Medium.copy(all = CornerSize(20.dp)),
    ) {
        Column(modifier = Modifier.padding(all = 20.dp)) {
            Text(
                text = productName,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(text = "+ разделить позицию с участником", fontSize = 13.sp)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "$total руб.", fontSize = 15.sp, fontWeight = FontWeight.Medium)
        }

    }
}

@Preview
@Composable
private fun PreviewReceiptItem() {
    AppTheme {
        ReceiptItem(
            productName = "Салат ‘Каприз’",
            total = 1500
        ) { }
    }
}