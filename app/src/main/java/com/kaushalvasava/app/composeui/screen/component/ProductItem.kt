package com.kaushalvasava.app.composeui.screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaushalvasava.app.composeui.R
import com.kaushalvasava.app.composeui.model.Product

@Preview(showBackground = true)
@Composable
fun ProductItem(
    product: Product = Product(
        id = "1",
        "Shoes - Pink 10",
        color = android.graphics.Color.MAGENTA,
        price = 1200f,
        discountPrice = 1100f,
        size = 10,
        rating = 4.5f,
        imageRes = R.drawable.s_1
    ),
    onClick: () -> Unit = {},
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }
    var isFavorite by remember {
        mutableStateOf(false)
    }
    val color by remember {
        mutableStateOf(Color(product.color))
    }
    Box(
        modifier = Modifier
            .padding(20.dp)
            .clickable(
                onClick = { onClick() },
                interactionSource = interactionSource,
                indication = rememberRipple(bounded = true)
            )
            .size(168.dp, 210.dp)
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .alpha(0.2f)
                .background(color = color, shape = RoundedCornerShape(22.dp))
        )
        IconButton(
            modifier = Modifier.align(Alignment.TopStart),
            onClick = {
                isFavorite = !isFavorite
            }
        ) {
            Icon(
                imageVector = if (isFavorite)
                    Icons.Rounded.Favorite
                else
                    Icons.Rounded.FavoriteBorder,
                contentDescription = "Favorite",
            )
        }
        Text(
            text = product.size.toString(),
            fontWeight = FontWeight.Bold,
            color = color.copy (0.3f),
        fontSize = 120.sp,
        modifier = Modifier.align(Alignment.TopCenter)
        )
        Image(
            painter = painterResource(id = product.imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(1f)
                .align(Alignment.Center)
                .rotate(-30f)
                .offset(30.dp, (-20).dp)
        )
        Column(modifier = Modifier.align(Alignment.BottomEnd)) {
            Text(
                text = "Rs. ${product.discountPrice}",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = "Rs. ${product.price}",
                style = TextStyle(
                    textDecoration = TextDecoration.LineThrough
                ),
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(end = 8.dp, bottom = 8.dp)
                    .align(Alignment.End)
            )
        }
    }
}