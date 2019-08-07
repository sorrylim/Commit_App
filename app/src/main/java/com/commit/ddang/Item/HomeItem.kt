package com.commit.ddang.Item

data class Homefeed(val items: List<Item>)
data class Item(
    val title: String,
    val telephone: String,
    val address: String
)
