package com.endrjudev.data

import android.content.Context
import androidx.room.Room
import com.endrjudev.data.database.GitHubDatabase
import com.endrjudev.data.network.NetworkDataSource
import com.endrjudev.domain.Repository
import org.koin.dsl.module

object DataDI {
    val dataModule = module {
        single<Repository> { GitHubRepository(get(), get()) }
        single { NetworkDataSource() }
        single { createDatabase(get()) }
    }

    private fun createDatabase(context: Context) = Room.databaseBuilder(
        context,
        GitHubDatabase::class.java, "github-database"
    ).build()
}