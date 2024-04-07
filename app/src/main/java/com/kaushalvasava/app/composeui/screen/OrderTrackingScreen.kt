package com.kaushalvasava.app.composeui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaushalvasava.app.composeui.R
import kotlin.math.roundToInt

@Preview(showBackground = true)
@Composable
fun OrderTrackingScreen() {
    var isDelivered by remember {
        mutableStateOf(false)
    }
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.s_3),
                contentDescription = null,
                modifier = Modifier.size(60.dp)
            )
            Column {
                Text("Shoes S2 - Green", fontWeight = FontWeight.Bold)
                Text("Quantity: 1", fontSize = 14.sp)
            }
        }
        OrderTrackingCard {
            isDelivered
        }
        Text(
            "Related Information",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                Icons.Rounded.Person,
                contentDescription = "",
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.Green.copy(0.2f))
                    .size(50.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text("Kaushal V.")
                Text("Courrier")
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = {
                    //do call to driver
                },
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.Green.copy(0.2f))
            ) {
                Icon(Icons.Rounded.Phone, contentDescription = "call")
            }
        }
        Button(
            onClick = {
                isDelivered = !isDelivered
            },
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .drawBehind {
                    drawRect(
                        Brush.linearGradient(
                            listOf(Color.Blue, Color.Green)
                        )
                    )
                }
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                "Pay Now Rs. 1200",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun OrderTrackingCard(modifier: Modifier = Modifier, isDelivered: () -> Boolean = { false }) {
    Card(
        modifier,
        colors = CardDefaults.cardColors(
            containerColor = Color.Green.copy(0.2f)
        )
    ) {
        Column(Modifier.padding(8.dp)) {
            Row(Modifier.fillMaxWidth()) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_location),
                    contentDescription = "null"
                )
                Column(Modifier.weight(1f)) {
                    Text("Pickup Point", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(
                        "Shop no.16 K Store, Zadeshwar ",
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Light,
                        fontSize = 14.sp
                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text("Order Time", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text("12 pm", fontWeight = FontWeight.Light, fontSize = 14.sp)
                }
            }
            Box(
                Modifier
                    .width(4.dp)
                    .height(80.dp)
                    .background(
                        color = if (isDelivered()) {
                            Color(0xFF16AB1D)
                        } else
                            Color.Gray,
                        shape = DottedShape(step = 15.dp)
                    )
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
                Spacer(Modifier.weight(1f))
                Column(horizontalAlignment = Alignment.End) {
                    Text("Delivery Time", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text("3 pm", fontWeight = FontWeight.Light, fontSize = 14.sp)
                }
            }
        }
    }
}


private data class DottedShape(
    val step: Dp,
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
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
