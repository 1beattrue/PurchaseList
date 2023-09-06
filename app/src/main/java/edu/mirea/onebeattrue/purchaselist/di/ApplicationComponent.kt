package edu.mirea.onebeattrue.purchaselist.di

import android.app.Activity
import android.app.Application
import dagger.BindsInstance
import dagger.Component
import edu.mirea.onebeattrue.purchaselist.presentation.views.MainActivity
import edu.mirea.onebeattrue.purchaselist.presentation.views.ShopItemFragment

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment: ShopItemFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}