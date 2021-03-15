/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.example.androiddevchallenge.R

@Composable
fun LoginScreen(navController: NavHostController?) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val backgroundResId = if (MaterialTheme.colors.isLight) {
        R.drawable.ic_light_login
    } else {
        R.drawable.ic_dark_login
    }

    Box(Modifier.background(MaterialTheme.colors.background)) {
        Image(
            painterResource(id = backgroundResId),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().fillMaxHeight()
        )
        Column(
            modifier = Modifier.fillMaxHeight().padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "LOG IN",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h1
            )
            Spacer(Modifier.height(32.dp))

            EditText(
                hint = "Email address",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .focusable(enabled = true),
                onValueChange = { password = it }
            )
            Spacer(Modifier.height(8.dp))

            EditText(
                hint = "Password",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .focusable(enabled = true),
                onValueChange = { password = it }
            )
            Spacer(Modifier.height(8.dp))

            Button(
                onClick = { navController?.navigate(NAV_MAIN) },
                Modifier
                    .fillMaxWidth()
                    .height(72.dp)
                    .clip((RoundedCornerShape(16.dp)))
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "SIGN UP",
                    style = MaterialTheme.typography.body1
                )
            }
            Spacer(Modifier.height(16.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Don't have an account? Sign up",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body1
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(null)
}
