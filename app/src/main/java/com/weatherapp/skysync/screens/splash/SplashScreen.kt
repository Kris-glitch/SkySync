package com.weatherapp.skysync.screens.splash



import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.weatherapp.skysync.R
import com.weatherapp.skysync.navigation.Screens
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController) {
val scale = remember {
    androidx.compose.animation.core.Animatable(0f)
}
    LaunchedEffect(key1 = true, block = {
        scale.animateTo(
            targetValue = 0.9f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(8f).getInterpolation(it)
                }
            )
            )
        delay(5000L)

        navController.navigate(Screens.HomeScreen.name)
    })



    Surface(
        modifier = Modifier
            .fillMaxSize()
            .scale(scale.value),
        color = Color(0xFF9FC2DA)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
            ) {

            Image(modifier = Modifier.size(330.dp),
                painter = painterResource(id = R.drawable.splash),
                contentDescription = stringResource(
                    id = R.string.splash_image
                ))

            Text(
                modifier = Modifier.padding(24.dp),
                text = stringResource(id = R.string.slogan),
                fontWeight = FontWeight.Light,
                fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
                fontSize = 24.sp,
                color = Color(0xFF2b3f58),
                textAlign = TextAlign.Center
            )
        }

    }

}