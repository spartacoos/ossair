package com.example.ossair

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ossair.data.remote.HttpRoutes
import com.example.ossair.data.remote.PostsService
import com.example.ossair.data.remote.dto.PostResponse
import com.example.ossair.ui.theme.OssairTheme

class MainActivity : ComponentActivity() {
    // Networking
    private val service = PostsService.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Networking
            val posts = produceState<List<PostResponse>>(
                initialValue = emptyList(),
                producer = {
                    value = service.getPosts()
                }
            )
            OssairTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF101010) //MaterialTheme.colors.background
                ) {
                    LazyColumn {
                        items(posts.value) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Text(text = it.dataFirst, fontSize = 30.sp, color = Color.White)
                                //Spacer(modifier = Modifier.height(4.dp))
                                Text(text = it.dataSecond, fontSize = 24.sp, color = Color.White)
                            }
                        }
                    }

                    //Greeting("Android")
                    Greeting("Hiya")
                    Greeting(posts.value.toString())
                    Greeting(HttpRoutes.POSTS)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!", color = Color.White)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OssairTheme {
        Greeting("Android")
    }
}