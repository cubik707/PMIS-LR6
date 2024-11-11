package com.example.labs5plus.utils

sealed class DrawerEvents {
    data class OnItemClick(val title: String, val index: Int): DrawerEvents()
}