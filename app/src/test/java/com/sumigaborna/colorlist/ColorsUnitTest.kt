package com.sumigaborna.colorlist

import android.graphics.Color
import com.sumigaborna.colorlist.database.ColorItem
import org.junit.Test

import org.junit.Assert.*

class ColorsUnitTest {
    @Test
    fun dividing_index_by_three_returns_red_color() {
        val colorItem = ColorItem(3)
        assertEquals(Color.RED, colorItem.color)
    }

    @Test
    fun dividing_index_by_five_returns_blue_color() {
        val colorItem = ColorItem(5)
        assertEquals(Color.BLUE, colorItem.color)
    }

    @Test
    fun dividing_index_by_three_and_five_returns_yellow_color() {
        val colorItem = ColorItem(15)
        assertEquals(Color.YELLOW, colorItem.color)
    }

    @Test
    fun dividing_gives_remainder_returns_default_color() {
        val colorItem = ColorItem(7)
        assertEquals(Color.GREEN, colorItem.color)
    }
}