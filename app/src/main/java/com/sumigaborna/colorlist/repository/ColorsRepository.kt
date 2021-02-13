package com.sumigaborna.colorlist.repository

import com.sumigaborna.colorlist.database.ColorItem
import com.sumigaborna.colorlist.database.ColorListDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ColorsRepository(private val colorListDao: ColorListDao) {
    suspend fun addColors(): Boolean {
        return withContext(Dispatchers.IO) {
            if (colorListDao.isDatabaseInitialized() != null) return@withContext true
            else {
                for (i in 0..99999) {
                    try {
                        colorListDao.addItem(ColorItem(i))
                    } catch (e: Exception) {
                        e.printStackTrace()
                        true
                    }
                }
                true
            }
        }
    }

    fun getColors() = colorListDao.getColorsAscending()

    suspend fun getColorById(colorId: Int): ColorItem {
        return withContext(Dispatchers.IO) {
            return@withContext colorListDao.getColorById(colorId)
        }
    }
}