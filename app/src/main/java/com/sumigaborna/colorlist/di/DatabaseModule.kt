package com.sumigaborna.colorlist.di

import androidx.room.Room
import com.sumigaborna.colorlist.database.ColorListDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single { Room.databaseBuilder(androidContext(), ColorListDatabase::class.java, "ColorsTable")
        .fallbackToDestructiveMigration().build() }
    single { get<ColorListDatabase>().colorDao() }
}