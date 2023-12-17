package com.weatherapp.skysync.screens.about

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.weatherapp.skysync.R
import com.weatherapp.skysync.widgets.SearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavController) {
    Scaffold(
        topBar = {
            SearchBar(
                navController = navController,
                title = "About",
                elevation = 2.dp,
                enabled = false,
                isHomeScreen = false,
                showSearch = false,
                onBackButtonClicked = {
                    navController.popBackStack()
                },
                onMoreClicked = {},
                onSearchClicked = {},
                onAction = KeyboardActions.Default

            )
        }
    ) {
        Box(modifier = Modifier.padding(it)) {}
        Content()
    }


}

@Composable
fun Content() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Surface(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, bottom = 10.dp, top = 80.dp)
                .fillMaxWidth(),
            color = Color(0xFF9FC2DA)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 8.dp),
                    text = stringResource(id = R.string.about_welcome_title),
                    fontSize = 22.sp,
                    fontFamily = FontFamily(Font(R.font.noto_sans_bold))

                )
                Text(
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 12.dp),
                    textAlign = TextAlign.Justify,
                    text = stringResource(id = R.string.about_welcome),
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.noto_sans_regular))

                )
                Text(
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 8.dp),
                    text = stringResource(id = R.string.about_key_features),
                    fontSize = 22.sp,
                    fontFamily = FontFamily(Font(R.font.noto_sans_bold))

                )
                Text(
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 8.dp),
                    textAlign = TextAlign.Justify,
                    text = stringResource(id = R.string.about_key_features_1),
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.noto_sans_regular))

                )
                Text(
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 8.dp),
                    textAlign = TextAlign.Justify,
                    text = stringResource(id = R.string.about_key_features_2),
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.noto_sans_regular))

                )
                Text(
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 12.dp),
                    textAlign = TextAlign.Justify,
                    text = stringResource(id = R.string.about_key_features_3),
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.noto_sans_regular))

                )
                Text(
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 8.dp),
                    text = stringResource(id = R.string.about_why_title),
                    fontSize = 22.sp,
                    fontFamily = FontFamily(Font(R.font.noto_sans_bold))

                )
                Text(
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 8.dp),
                    textAlign = TextAlign.Justify,
                    text = stringResource(id = R.string.about_why),
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.noto_sans_regular))

                )
            }
        }
    }
}