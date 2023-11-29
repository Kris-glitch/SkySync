package com.weatherapp.skysync.widgets

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.weatherapp.skysync.R
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun SearchBar(
    title: String = "Title",
    icon: ImageVector? = null,
    isHomeScreen: Boolean = true,
    elevation: Dp = 0.dp,
    // navController: NavController,
    onButtonClicked: () -> Unit = {},

    ) {

    TopAppBar(
        modifier = Modifier.clip(shape = RoundedCornerShape(corner = CornerSize(12.dp))),
        title = {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.secondary,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
                    fontSize = 15.sp
                    )
            )
        },
        navigationIcon = {
            if (!isHomeScreen) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.back_arrow)
                )
            }
        },
        actions = {

        }

        )


}