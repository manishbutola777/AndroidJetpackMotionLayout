package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateOffset
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.example.myapplication.ui.theme.MotionLayoutComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MotionLayoutComposeTheme {
                Column {
                    var progress by remember {
                        mutableStateOf(0f)
                    }
                    ProfileHeader(progress = progress)
                    Spacer(modifier = Modifier.height(32.dp))
                    Slider(
                        value = progress,
                        onValueChange = {
                            progress = it
                        },
                        modifier = Modifier.padding(horizontal = 32.dp)
                   )
                }
            }
        }
    }
}


@OptIn(ExperimentalMotionApi::class)
@Composable
fun ProfileHeader(progress: Float ) {
    var progress1 by remember {
        mutableStateOf(0f)
    }
    var isClicked by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val motionScene = remember {
        context.resources
            .openRawResource(R.raw.motion_scene)
            .readBytes()
            .decodeToString()
    }
    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        progress = progress,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            ,

        ) {
        val properties = motionProperties(id = "profile_pic")
        Box(
            modifier = Modifier
                .background(Color.DarkGray)
                .layoutId("box1")
        )
        {


        }
        Icon(
            painter = painterResource(id = R.drawable.box_pic),
            contentDescription = null,
            modifier = Modifier
                .layoutId("profile_pic")
                .clickable {
                    isClicked = !isClicked
                    Log.d("isclicked", isClicked.toString())
                    if (isClicked) {
                         progress1 =1.0f
                    } else {
                        progress1 = 0.0f
                    }

                }

        )




    }
}

