@file:OptIn(ExperimentalMaterial3Api::class)

package com.atitienei_daniel.feature_messages

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForwardIos
import androidx.compose.material.icons.rounded.OpenInNew
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.atitienei_daniel.core_design.JetMessagesTheme

@Composable
fun MessagesScreen() {

    val chatListState = rememberLazyListState()

    val isSearchBarVisible by remember {
        derivedStateOf { chatListState.firstVisibleItemIndex == 0 }
    }

    val isTopBarTitleVisible by remember {
        derivedStateOf { chatListState.firstVisibleItemIndex > 0 }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    AnimatedVisibility(
                        visible = isTopBarTitleVisible,
                        enter = fadeIn(tween(200)),
                        exit = fadeOut(tween(200))
                    ) {
                        Text(
                            text = "Messages",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                },
                navigationIcon = {
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "Edit")
                    }
                },
                actions = {
                    IconButton(
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.OpenInNew,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            state = chatListState,
            contentPadding = PaddingValues(
                top = innerPadding.calculateTopPadding() + 5.dp,
                bottom = innerPadding.calculateBottomPadding() + 15.dp,
            )
        ) {
            item {
                ListHeader(isTopBarTitleVisible)
            }
            item {
                SearchBar(isSearchBarVisible)
            }
            items(10) {
                ChatItem()
            }
        }
    }
}

@Composable
private fun ListHeader(isTopBarTitleVisible: Boolean) {
    AnimatedVisibility(
        visible = !isTopBarTitleVisible,
        enter = fadeIn(tween(200)),
        exit = fadeOut(tween(200))
    ) {
        Text(
            text = "Messages",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 20.dp, bottom = 15.dp)
        )
    }
}

@Composable
private fun SearchBar(isSearchBarVisible: Boolean) {
    Box(modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)) {
        AnimatedVisibility(
            visible = isSearchBarVisible,
            enter = fadeIn(tween(200)),
            exit = fadeOut(tween(200))
        ) {
            TextField(
                value = "",
                onValueChange = {},
                placeholder = { Text(text = "Search") },
                leadingIcon = {
                    Icon(imageVector = Icons.Rounded.Search, contentDescription = null)
                },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun ChatItem() {
    ListItem(
        leadingContent = {
            Image(
                painter = painterResource(id = R.drawable.profile_picture),
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        },
        headlineText = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Daniel", fontWeight = FontWeight.Bold)

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "10:22", style = MaterialTheme.typography.labelMedium)
                    Spacer(modifier = Modifier.width(2.dp))
                    Icon(
                        imageVector = Icons.Rounded.ArrowForwardIos,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        },
        supportingText = {
            Text(
                text = "Hello",
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun MessagesScreenPreview() {
    JetMessagesTheme {
        MessagesScreen()
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun MessagesScreenPreviewDark() {
    JetMessagesTheme {
        MessagesScreen()
    }
}