package com.kaushalvasava.app.composeui.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaushalvasava.app.composeui.R
import kotlin.math.roundToInt

data class Order(
    val id: String,
    // product id, and it's quantity
    val items: Map<String, Int>,
    val status: String,
    val orderDate: Long,
    val deliveryTime: Long,
)

data class Product(
    val id: String,
    val name: String,
    val price: String,
    val minQuantity: Int,
    val maxQuantity: Int,
    @DrawableRes val image: Int,
    val unit: String,
    val inventory: Int,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Orders") }, navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(Icons.Rounded.ArrowBack, null)
                }
            })
        },
    ) {
        LazyColumn(Modifier.padding(it)) {
            items(8) {
                OrderItem()
            }
        }
    }
}

@Composable
fun OrderItem() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.s_3),
            contentDescription = null,
            modifier = Modifier.size(80.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text("Shoes Pink", style = MaterialTheme.typography.titleMedium)
            Text("Items: 3", style = MaterialTheme.typography.bodyMedium)
            Text("Quantity: 1", style = MaterialTheme.typography.bodyMedium)
            Text("Price: Rs. 1200", style = MaterialTheme.typography.bodyMedium)
            Text("Delivery Status: Pending", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

private data class DottedShape(
    val step: Dp,
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ) = Outline.Generic(Path().apply {
        val stepPx = with(density) { step.toPx() }
        val stepsCount = (size.height / stepPx).roundToInt()
        val actualStep = size.height / stepsCount
        val dotSize = Size(width = size.width, height = actualStep / 2)
        for (i in 0 until stepsCount) {
            addRect(
                Rect(
                    offset = Offset(y = i * actualStep, x = 30f),
                    size = dotSize
                )
            )
        }
        close()
    })
}

@Preview(showBackground = false)
@Composable
fun OrderTracking(modifier: Modifier = Modifier) {
    Card(modifier.padding(8.dp)) {
        Column(Modifier.padding(8.dp)) {

            Row(Modifier.fillMaxWidth()) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_location),
                    contentDescription = "null"
                )
                Column {
                    Text("Pickup Point", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text("Zadeshwar", fontWeight = FontWeight.Light, fontSize = 14.sp)
                }
            }
            Box(
                Modifier
                    .width(2.dp)
                    .height(50.dp)
                    .background(Color.Gray, shape = DottedShape(step = 10.dp))
            )
            Row(Modifier.fillMaxWidth()) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_location),
                    contentDescription = "null"
                )
                Column {
                    Text("Delivery Point", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text("Zadeshwar no.2", fontWeight = FontWeight.Light, fontSize = 14.sp)
                }
            }

        }
    }
}
