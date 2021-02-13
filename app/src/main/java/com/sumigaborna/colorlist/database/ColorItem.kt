package com.sumigaborna.colorlist.database

import android.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ColorsTable")
data class ColorItem(
    @PrimaryKey val id: Int,
    val color: Int = getColorByDivision(id),
)

private fun getColorByDivision(colorId: Int): Int {
    return if (colorId % 3 == 0 && colorId % 5 == 0) Color.YELLOW
    else if (colorId % 3 == 0) Color.RED
    else if (colorId % 5 == 0) Color.BLUE
    else Color.GREEN
}