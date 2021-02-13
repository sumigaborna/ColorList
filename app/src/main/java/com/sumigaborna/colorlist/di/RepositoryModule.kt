package com.sumigaborna.colorlist.di

import com.sumigaborna.colorlist.repository.ColorsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { ColorsRepository(get()) }
}