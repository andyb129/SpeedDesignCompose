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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.model.Task
import com.example.androiddevchallenge.ui.theme.taupe100
import dev.chrisbanes.accompanist.coil.CoilImage
import uk.co.barbuzz.adoptapup.repo.TaskRepo

@Composable
fun MainScreen() {
    val taskCollections1: List<Task> = TaskRepo().getCollection1Tasks()
    val taskCollections2: List<Task> = TaskRepo().getCollection2Tasks()
    val taskBody: List<Task> = TaskRepo().getBodyTasks()
    val taskMind: List<Task> = TaskRepo().getMindTasks()

    var search by remember { mutableStateOf("") }

    Scaffold(
        //Modifier.navigationBarsPadding(),
        bottomBar = { MainBottomNavigation() },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {  },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = null,
                    tint = MaterialTheme.colors.background
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
    ) {
        Column(
            Modifier
                .fillMaxHeight()
                .background(MaterialTheme.colors.background)
                .padding(start = 16.dp)
        ) {
            Spacer(Modifier.height(56.dp))
            EditText(
                hint = "Search",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(end = 16.dp)
                    .focusable(enabled = true),
                onValueChange = { search = it },
                vectorImageId = R.drawable.ic_search_24px,
                tint = MaterialTheme.colors.onSurface
            )
            Spacer(Modifier.height(24.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "FAVOURITE COLLECTIONS",
                textAlign = TextAlign.Left,
                style = MaterialTheme.typography.h2
            )
            Spacer(Modifier.height(8.dp))
            LazyRow(content = {
                items(taskCollections1) { task ->
                    TaskCollectionListItem(task)
                }
            })
            Spacer(Modifier.height(8.dp))
            LazyRow(content = {
                items(taskCollections2) { task ->
                    TaskCollectionListItem(task)
                }
            })
            Spacer(Modifier.height(32.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "ALIGN YOUR BODY",
                textAlign = TextAlign.Left,
                style = MaterialTheme.typography.h2
            )
            Spacer(Modifier.height(8.dp))
            LazyRow(content = {
                items(taskBody) { task ->
                    TaskBodyListItem(task)
                }
            })
            Spacer(Modifier.height(32.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "ALIGN YOUR MIND",
                textAlign = TextAlign.Left,
                style = MaterialTheme.typography.h2
            )
            Spacer(Modifier.height(8.dp))
            LazyRow(content = {
                items(taskMind) { task ->
                    TaskBodyListItem(task)
                }
            })
        }
    }
}

@Composable
fun TaskCollectionListItem(task: Task) {
    Row(
        Modifier
            .width(192.dp)
            .height(56.dp)
            .padding(end = 8.dp)
            .background(MaterialTheme.colors.surface)
            .clip(RoundedCornerShape(4.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier
                .width(56.dp)
                .height(56.dp)
        ) {
            CoilImage(
                data = task.imageUrl,
                fadeIn = true,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                loading = {
                    Box(Modifier.fillMaxSize()) {
                        Image(
                            modifier = Modifier
                                .size(36.dp)
                                .align(Alignment.Center),
                            painter = painterResource(id = R.drawable.ic_spa_24px),
                            contentDescription = null
                        )
                    }
                }
            )
        }
        Spacer(Modifier.width(16.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = task.title,
            textAlign = TextAlign.Left,
            style = MaterialTheme.typography.h3
        )
    }
}

@Composable
fun TaskBodyListItem(task: Task) {
    Column(
        Modifier.padding(end = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            Modifier
                .width(88.dp)
                .height(88.dp)
                .clip(CircleShape)
        ) {
            CoilImage(
                data = task.imageUrl,
                fadeIn = true,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                loading = {
                    Box(Modifier.fillMaxSize()) {
                        Image(
                            modifier = Modifier
                                .size(36.dp)
                                .align(Alignment.Center),
                            painter = painterResource(id = R.drawable.ic_spa_24px),
                            contentDescription = null
                        )
                    }
                }
            )
        }
        Spacer(Modifier.height(8.dp))
        Row(
            Modifier.height(24.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = task.title,
                textAlign = TextAlign.Left,
                style = MaterialTheme.typography.h3
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}

@Preview(showBackground = true)
@Composable
fun TaskCollectionListItemPreview() {
    TaskCollectionListItem(
        Task(
            "title here",
            "https://www.pexels.com/photo/photo-of-green-leaves-3571551/",
            0
        )
    )
}
