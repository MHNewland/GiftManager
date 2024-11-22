package com.mnewland.amazonitemtest

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mnewland.amazonitemtest.network.WishlistItem
import com.mnewland.amazonitemtest.network.amazonParser
import com.mnewland.amazonitemtest.ui.theme.AmazonItemTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AmazonItemTestTheme {
                val items = remember { mutableStateOf<List<WishlistItem>>(emptyList()) }

                LaunchedEffect(Unit) {
                    items.value = amazonParser("38GW09BMJEUQI")
                }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        elements = items.value,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(elements: List<WishlistItem>, modifier: Modifier = Modifier) {
    LazyColumn {
        items(elements) { item ->
            card(item)
        }
    }

}

@Composable
fun card(test: WishlistItem, modifier: Modifier = Modifier){
    Row(
        modifier = modifier.fillMaxWidth()
    ){
        Column(
            modifier = modifier.fillMaxHeight()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(test.imageUrl)
                    .crossfade(true)
                    .listener(
                        onStart = { request -> Log.d("Coil", "Image request started: $request") },
                        onSuccess = { request, metadata ->
                            Log.d(
                                "Coil",
                                "Image request succeeded: $request"
                            )
                        },
                        onError = { request, throwable ->
                            Log.e(
                                "Coil",
                                "Image request failed: $request"
                            )
                        }
                    )
                    .build(),
                contentDescription = test.imageUrl,
                contentScale = ContentScale.FillWidth,
                modifier = modifier
                    .fillMaxWidth(.25f)
                    .border(2.dp, Color.Blue)
            )
        }
        Column() {
            Text(
                text = test.title
            )
            Text(
                text = test.price
            )
            Text(
                text= test.itemUrl
            )
        }
    }
}

@Preview
@Composable
fun PreviewTest(){
    AmazonItemTestTheme {
    val TestWishlistItem = WishlistItem(
        title = "test item",
        price = "20.99",
        imageUrl = "https://m.media-amazon.com/images/I/41wBgHn1ztL._SS135_.jpg",
        itemUrl = "https://www.amazon.com/dp/B0CPMDT3MW/?coliid=I22JQS3QG2PM2G&colid=3V4DD3EF5H3JX"
    )
    card(TestWishlistItem, modifier = Modifier)
        }
}