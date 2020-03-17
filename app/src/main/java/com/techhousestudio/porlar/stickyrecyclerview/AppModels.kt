package com.techhousestudio.porlar.stickyrecyclerview

import java.util.*

sealed class Item {
    data class HeaderItem(var date: Date) : Item()
    data class BodyItem(var content: String) : Item()
}