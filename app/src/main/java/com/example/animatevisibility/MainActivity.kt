package com.example.animatevisibility

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
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

@OptIn(ExperimentalAnimationApi::class)
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
            //CustomButton(text = "Show", targetState = true, onClick = onClick, Color.Magenta)
            //CustomButton(text = "Hide", targetState = false, onClick = onClick)
            Crossfade(
                targetState = boxVisible,
                animationSpec = tween(5000)
            ) { visible ->
                when (visible) {
                    true -> CustomButton(text = "Hide", targetState = false, onClick = onClick, bgColor = Color.Red)
                    false -> CustomButton(text = "Show", targetState = true, onClick = onClick, bgColor = Color.Magenta)
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        /*if (boxVisible) {
            Box(modifier = Modifier
                .size(height = 200.dp, width = 200.dp)
                .background(Color.Blue))
        }*/

        val state = remember { MutableTransitionState(false) }

        AnimatedVisibility(
            //visible = boxVisible,
            //visibleState = state.apply { targetState = true },
            visible = boxVisible,
            //enter = fadeIn(initialAlpha = .5f),
            //enter = scaleIn(),
            //enter = fadeIn() + expandHorizontally(),
            //enter = fadeIn(animationSpec = tween(durationMillis = 5000)),
            //enter = slideInHorizontally(animationSpec = tween(durationMillis = 5000, easing = LinearOutSlowInEasing)),
            //enter = slideInHorizontally(animationSpec = tween(durationMillis = 5000, easing = CubicBezierEasing(0f, 1f, 0.5f, 1f))),
            //enter = fadeIn(animationSpec = repeatable(10, animation = tween(durationMillis = 2000), repeatMode = RepeatMode.Reverse)),
            //exit = slideOutVertically(),
            /*enter = fadeIn(animationSpec = tween(durationMillis = 5500)),
            exit = fadeOut(animationSpec = tween(durationMillis = 5500))*/
            //enter = EnterTransition.None,
            //exit = ExitTransition.None
            enter = fadeIn(animationSpec = tween(durationMillis = 5500)),
            //exit = slideOutVertically()
            exit = fadeOut(animationSpec = tween(durationMillis = 5500))
        ) {
            Row {
                Box(modifier = Modifier
                    .animateEnterExit(
                        enter = fadeIn(animationSpec = tween(durationMillis = 5500)),
                        exit = fadeOut(animationSpec = tween(durationMillis = 5500))
                    )
                    .size(height = 200.dp, width = 200.dp)
                    .background(Color.Blue))
                Spacer(modifier = Modifier.width(20.dp))
                Box(modifier = Modifier
                    .animateEnterExit(
                        enter = slideInVertically(
                            animationSpec = tween(durationMillis = 5500)
                        ),
                        exit = slideOutVertically(
                            animationSpec = tween(durationMillis = 5500)
                        )
                    )
                    .size(width = 150.dp, height = 150.dp)
                    .background(Color.Red)
                )
            }
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