package edu.mirea.onebeattrue.purchaselist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.mirea.onebeattrue.purchaselist.data.ShopListRepositoryImpl
import edu.mirea.onebeattrue.purchaselist.domain.DeleteShopItemUseCase
import edu.mirea.onebeattrue.purchaselist.domain.EditShopItemUseCase
import edu.mirea.onebeattrue.purchaselist.domain.GetShopListUseCase
import edu.mirea.onebeattrue.purchaselist.domain.ShopItem

class MainViewModel : ViewModel() {

    private val repository =
        ShopListRepositoryImpl // неправильно создавать репозиторий здесь, ну тут уж извините, куда деваться

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = MutableLiveData<List<ShopItem>>()

    fun getShopList() {
        val list = getShopListUseCase.getShopList()
        shopList.value = list
    }

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
        getShopList()
    }

    fun editShopItem(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
        getShopList()
    }
}