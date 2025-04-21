package pro.number.app.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pro.number.app.presentation.ui.theme.AppTheme

@Composable
fun GroupItem(
    modifier: Modifier = Modifier,
    groupName: String,
    dateOfTheEvent: String,
    itemsCount: Int,
    tips: Byte,
    waiter: String,
    total: Float,
    onDetailsClickListener: () -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth(),
        color = MaterialTheme.colorScheme.surfaceContainer,
        shape = ShapeDefaults.Medium.copy(all = CornerSize(20.dp)),
    ) {
        Column(
            modifier = Modifier.padding(all = 20.dp)
        ) {
            Text(
                text = groupName,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Дата события: $dateOfTheEvent",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Количество позиций в чеке: $itemsCount",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Чаевые: $tips%",
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 13.sp,
            )
            Text(
                text = "Счёт закрыл: $waiter",
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 13.sp,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    fontSize = 15.sp,
                    text = "Итог: $total руб.",
                    fontWeight = FontWeight.Medium
                )
                TextButton(onClick = onDetailsClickListener) {
                    Text(
                        "Детали",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Normal,
                        fontSize = 13.sp
                    )
                }
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
private fun PreviewGroupItem() {
    AppTheme {
        GroupItem(
            modifier = Modifier.padding(all = 20.dp),
            groupName = "Ресторан ‘Русская рыбалка’",
            dateOfTheEvent = "03.03.2025",
            itemsCount = 15,
            tips = 10,
            waiter = "Иванов И.И.",
            total = 1500f
        ) {}
    }
}