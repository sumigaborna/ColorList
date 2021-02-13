package com.sumigaborna.colorlist.di

import com.sumigaborna.colorlist.ui.adapters.ColorsAdapter
import com.sumigaborna.colorlist.ui.fragments.ColorItemListener
import org.koin.dsl.module

val adaptersModule = module {
    factory { (colorItemListener:ColorItemListener)->ColorsAdapter(colorItemListener) }
}