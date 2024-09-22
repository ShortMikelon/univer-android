package com.github.shortmikelon.firstlab.screens.home.entities

data class MenuItem(
    val text: String,
    val onClick: () -> Unit = {  }
)
