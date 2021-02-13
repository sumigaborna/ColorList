package com.sumigaborna.colorlist.di

import com.sumigaborna.colorlist.ui.ColorsViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ColorsViewModel(androidApplication(),get()) }
}