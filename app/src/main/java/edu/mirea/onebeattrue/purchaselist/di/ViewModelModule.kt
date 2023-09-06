package edu.mirea.onebeattrue.purchaselist.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck
import dagger.multibindings.IntoMap
import edu.mirea.onebeattrue.purchaselist.presentation.viewmodels.MainViewModel
import edu.mirea.onebeattrue.purchaselist.presentation.viewmodels.ShopItemViewModel

@DisableInstallInCheck
@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(impl: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ShopItemViewModel::class)
    fun bindShopItemViewModel(impl: ShopItemViewModel): ViewModel
}