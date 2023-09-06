package edu.mirea.onebeattrue.purchaselist.presentation

import android.app.Application
import edu.mirea.onebeattrue.purchaselist.di.DaggerApplicationComponent

class PurchaseListApplication : Application() {
    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}