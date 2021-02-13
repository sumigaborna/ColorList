package com.sumigaborna.colorlist.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ColorListDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addItem(colorItem: ColorItem)

    @Query("SELECT * FROM ColorsTable")
    fun getColorsAscending(): PagingSource<Int, ColorItem>

    @Query("SELECT * FROM ColorsTable WHERE id=:colorId ")
    fun getColorById(colorId: Int): ColorItem

    @Query("SELECT * FROM ColorsTable WHERE id=99999")
    fun isDatabaseInitialized(): ColorItem?

}