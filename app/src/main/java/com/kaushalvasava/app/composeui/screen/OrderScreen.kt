package com.kaushalvasava.app.composeui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kaushalvasava.app.composeui.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = false)
@Composable
fun OrderScreen(navController: NavController = rememberNavController()) {

    var isBottomSheet by remember {
        mutableStateOf(false)
    }

    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val coroutineScope = rememberCoroutineScope()

    if (isBottomSheet) {
        ModalBottomSheet(
            sheetState = bottomSheetState,
            onDismissRequest = {
                coroutineScope.launch {
                    isBottomSheet = false
                    bottomSheetState.hide()
                }
            }) {
            OrderTrackingScreen()
        }
    }
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
                OrderItem {
                    coroutineScope.launch {
                        isBottomSheet = true
                        bottomSheetState.show()
                    }
//                    navController.navigate(NavigationItem.ORDER_TRACKING)
                }
            }
        }
    }
}

@Composable
fun OrderItem(onClick: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
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
            Text("Shoes Green", style = MaterialTheme.typography.titleMedium)
            Text("Items: 3", style = MaterialTheme.typography.bodyMedium)
            Text("Quantity: 1", style = MaterialTheme.typography.bodyMedium)
            Text("Price: Rs. 1200", style = MaterialTheme.typography.bodyMedium)
            Text("Delivery Status: Pending", style = MaterialTheme.typography.bodyMedium)
        }
    }
}