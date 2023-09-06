package edu.mirea.onebeattrue.purchaselist.di

import android.app.Application
import dagger.Binds
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck
import edu.mirea.onebeattrue.purchaselist.data.AppDatabase
import edu.mirea.onebeattrue.purchaselist.data.ShopListMapper
import edu.mirea.onebeattrue.purchaselist.data.ShopListRepositoryImpl
import edu.mirea.onebeattrue.purchaselist.domain.ShopListRepository
import edu.mirea.onebeattrue.purchaselist.presentation.PurchaseListApplication

@DisableInstallInCheck
@Module
interface DataModule {
    @ApplicationScope
    @Binds
    fun bindShopListRepository(impl: ShopListRepositoryImpl): ShopListRepository

    companion object {
        @ApplicationScope
        @Provides
        fun provideShopListDao(
            application: Application
        ) = AppDatabase.getInstance(application).shopListDao()
    }
}