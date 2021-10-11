package com.endrjudev.presentation

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object PresentationDI {
    val presentationModule = module {
        viewModel { MainViewModel(get()) }
        viewModel { params -> DetailViewModel(get(), params.get()) }
    }
}