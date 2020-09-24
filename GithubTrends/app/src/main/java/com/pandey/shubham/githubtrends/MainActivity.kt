package com.pandey.shubham.githubtrends

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.setContent
import androidx.ui.foundation.AdapterList
import androidx.ui.foundation.Text
import androidx.ui.graphics.Color
import androidx.ui.text.TextStyle
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.sp
import com.pandey.shubham.githubtrends.ui.GithubTrendsTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AdapterList(
                data = (1..20).toList()
            ) {
                if (it % 2 == 0) {
                    Text("$it Even", style = TextStyle(fontSize = 40.sp, color = Color.Gray))
                } else {
                    Text(text = "$it Odd", style = TextStyle(fontSize = 70.sp))
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GithubTrendsTheme {
        Greeting("Android")
    }
}