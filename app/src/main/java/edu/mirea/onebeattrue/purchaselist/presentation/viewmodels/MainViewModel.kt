package edu.mirea.onebeattrue.purchaselist.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import edu.mirea.onebeattrue.purchaselist.data.ShopListRepositoryImpl
import edu.mirea.onebeattrue.purchaselist.domain.DeleteShopItemUseCase
import edu.mirea.onebeattrue.purchaselist.domain.EditShopItemUseCase
import edu.mirea.onebeattrue.purchaselist.domain.GetShopListUseCase
import edu.mirea.onebeattrue.purchaselist.domain.ShopItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository =
        ShopListRepositoryImpl(application) // неправильно создавать репозиторий здесь, ну тут уж извините, куда деваться

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    private val scope = CoroutineScope(Dispatchers.IO)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        scope.launch {
            deleteShopItemUseCase.deleteShopItem(shopItem)
        }
    }

    fun changeEnableState(shopItem: ShopItem) {
        scope.launch {
            val newItem = shopItem.copy(enabled = !shopItem.enabled)
            editShopItemUseCase.editShopItem(newItem)
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}