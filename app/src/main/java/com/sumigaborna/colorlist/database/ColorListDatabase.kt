package com.sumigaborna.colorlist.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ColorItem::class], version = 1, exportSchema = false)
abstract class ColorListDatabase : RoomDatabase() {
    abstract fun colorDao(): ColorListDao
}