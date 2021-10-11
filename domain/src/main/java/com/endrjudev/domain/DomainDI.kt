package com.endrjudev.domain

import com.endrjudev.domain.usecase.GetRepositoriesUseCase
import com.endrjudev.domain.usecase.GetRepositoryUseCase
import org.koin.dsl.module

object DomainDI {
    val domainModule = module {
        factory { GetRepositoriesUseCase(get()) }
        factory { GetRepositoryUseCase(get()) }
    }
}