package edu.mirea.onebeattrue.purchaselist.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import edu.mirea.onebeattrue.purchaselist.data.ShopListRepositoryImpl
import edu.mirea.onebeattrue.purchaselist.domain.DeleteShopItemUseCase
import edu.mirea.onebeattrue.purchaselist.domain.EditShopItemUseCase
import edu.mirea.onebeattrue.purchaselist.domain.GetShopListUseCase
import edu.mirea.onebeattrue.purchaselist.domain.ShopItem

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository =
        ShopListRepositoryImpl(application) // неправильно создавать репозиторий здесь, ну тут уж извините, куда деваться

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }
}