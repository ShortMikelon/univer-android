@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

package com.github.shortmikelon.firstlab.screens.home

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.github.shortmikelon.firstlab.R
import com.github.shortmikelon.firstlab.screens.home.entities.MenuItem

@Composable
fun HomeScreen(modifier: Modifier) {
    var menuExpanded by remember { mutableStateOf(false) }

    var extensionMenuExpanded by remember { mutableStateOf(false) }

    var contextMenuExpanded by remember { mutableStateOf(false) }

    var homeScreenText by remember { mutableStateOf("Нынче ветрено и волны с перехлестом.\n" +
            "Скоро осень, все изменится в округе.\n" +
            "Смена красок этих трогательней, Постум,\n" +
            "чем наряда перемена у подруги.\n" +
            "Дева тешит до известного предела —\n" +
            "дальше локтя не пойдешь или колена.\n" +
            "Сколь же радостней прекрасное вне тела:\n" +
            "ни объятья невозможны, ни измена!") }
    var homeTextColor by remember { mutableStateOf(Color.Black) }
    var homeTextSize by remember { mutableIntStateOf(20) }

    val context = LocalContext.current

    val menuItems = listOf(
        MenuItem(
            text = "Reset",
            onClick = {
                homeTextSize = 20
                homeTextColor = Color.Black
            }
        ),
        MenuItem(
            text = "Increase text size",
            onClick = {
                homeTextSize += 2
            }
        ),
        MenuItem(
            text = "Decrease text size",
            onClick = {
                if (homeTextSize > 2) homeTextSize -= 2
            }
        )
    )

    val extensionMenu = listOf(
        MenuItem(
            text = "Set red color",
            onClick = {
                homeTextColor = Color.Red
            }
        ),
        MenuItem(
            text = "Set yellow color",
            onClick = {
                homeTextColor = Color.Yellow
            }
        ),
        MenuItem(
            text = "Set green color",
            onClick = {
                homeTextColor = Color.Green
            }
        )
    )

    Column {
        HomeScreenTopAppBar(onMenuClicked = { menuExpanded = true })

        HomeScreenDropdownMenu(
            expanded = menuExpanded,
            items = menuItems,
            extensionMenuItems = extensionMenu,
            onDismissRequest = { menuExpanded = false },
            onItemClick = { item ->
                Toast.makeText(context, item.text, Toast.LENGTH_SHORT).show()
                item.onClick()
            }
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = homeScreenText,
                fontSize = homeTextSize.sp,
                color = homeTextColor,
                modifier = Modifier
                    .align(Alignment.Center)
                    .combinedClickable(
                        onClick = { },
                        onLongClick = {
                            contextMenuExpanded = true
                        }
                    )
            )
        }

        HomeContextMenu(
            expanded = contextMenuExpanded,
            items = menuItems,
            extensionMenuItems = extensionMenu,
            onDismissRequest = { contextMenuExpanded = false },
            onItemClick = { item ->
                Toast.makeText(context, item.text, Toast.LENGTH_SHORT).show()
            }
        )
    }
}

@Composable
private fun HomeScreenTopAppBar(
    onMenuClicked: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(
                text = "Daniel",
                color = MaterialTheme.colorScheme.onPrimary,
            )
        },
        actions = {
            IconButton(onClick = onMenuClicked) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Show menu",
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@Composable
private fun HomeScreenDropdownMenu(
    expanded: Boolean,
    items: List<MenuItem>,
    extensionMenuItems: List<MenuItem>,
    onDismissRequest: () -> Unit,
    onItemClick: (MenuItem) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
    )
    {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = onDismissRequest,
        ) {
            items.forEach { item ->
                DropdownMenuItem(text = { Text(text = item.text) }, onClick = { onItemClick(item) })
            }

            Divider()

            extensionMenuItems.forEach { item ->
                DropdownMenuItem(text = { Text(text = item.text) }, onClick = { onItemClick(item) })
            }
        }
    }
}

@Composable
private fun HomeContextMenu(
    expanded: Boolean,
    items: List<MenuItem>,
    extensionMenuItems: List<MenuItem>,
    onDismissRequest: () -> Unit,
    onItemClick: (MenuItem) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
    )
    {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = onDismissRequest,
        ) {
            items.forEach { item ->
                DropdownMenuItem(text = { Text(text = item.text) }, onClick = { onItemClick(item) })
            }

            Divider()

            extensionMenuItems.forEach { item ->
                DropdownMenuItem(text = { Text(text = item.text) }, onClick = { onItemClick(item) })
            }
        }
    }
}