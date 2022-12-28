package com.example.animatevisibility

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animatevisibility.ui.theme.AnimateVisibilityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimateVisibilityTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    var boxVisible by remember { mutableStateOf(true) }
    val onClick = { newState : Boolean ->
        boxVisible = newState
    }

    Column(
        modifier = Modifier
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CustomButton(text = "Show", targetState = true, onClick = onClick, Color.Magenta)
            CustomButton(text = "Hide", targetState = false, onClick = onClick)
        }

        Spacer(modifier = Modifier.height(20.dp))

        /*if (boxVisible) {
            Box(modifier = Modifier
                .size(height = 200.dp, width = 200.dp)
                .background(Color.Blue))
        }*/
        AnimatedVisibility(visible = boxVisible) {
            Box(modifier = Modifier
                .size(height = 200.dp, width = 200.dp)
                .background(Color.Blue))
        }
    }
}

@Composable
fun CustomButton(text: String, targetState: Boolean, 
                 onClick: (Boolean) -> Unit, bgColor: Color = Color.Blue) {
    Button(
        onClick = { onClick(targetState) },
        colors = ButtonDefaults.buttonColors(backgroundColor = bgColor,
                        contentColor = Color.White)
    ) {
        Text(text = text)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    AnimateVisibilityTheme {
        MainScreen()
    }
}