package com.example.diceroller
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diceroller.ui.theme.DiceRollerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = getColor(R.color.purple_200)
        window.navigationBarColor = getColor(R.color.purple_200)
        setContent {
            DiceRollerTheme {
                // A surface container using the 'background' color from the theme
                DiceWithButtonAndImage()
            }
        }
    }
}

@Preview(showBackground = true,
    )
@Composable
fun DiceRollerApp() {
    DiceWithButtonAndImage()
}

@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier.fillMaxSize(1f)){
    var result by remember{ mutableStateOf(1) }
    var imageResource = when(result){
        1 -> painterResource(id = R.drawable.dice_1)
        2 -> painterResource(id = R.drawable.dice_2)
        3 -> painterResource(id = R.drawable.dice_3)
        4 -> painterResource(id = R.drawable.dice_4)
        5 -> painterResource(id = R.drawable.dice_5)
        else -> painterResource(id = R.drawable.dice_6)
    }
    Box {
        Image(painter = painterResource(R.drawable.dice_roller_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            alpha = 0.3f)
        Column(
            modifier = modifier.verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = imageResource,
                contentDescription = result.toString()
            )
            Spacer(modifier = Modifier.height(80.dp))
            FloatingActionButton(onClick = {result = (1..6).random()},
                Modifier.width(150.dp),
                containerColor = Color(220, 157, 231, 255)
                ) {
                Text(text = stringResource(id = R.string.roll))
            }
        }
    }
}