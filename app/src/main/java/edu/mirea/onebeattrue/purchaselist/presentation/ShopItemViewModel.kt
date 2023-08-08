package edu.mirea.onebeattrue.purchaselist.presentation

import android.telephony.mbms.StreamingServiceInfo
import androidx.lifecycle.ViewModel
import edu.mirea.onebeattrue.purchaselist.data.ShopListRepositoryImpl
import edu.mirea.onebeattrue.purchaselist.domain.AddShopItemUseCase
import edu.mirea.onebeattrue.purchaselist.domain.EditShopItemUseCase
import edu.mirea.onebeattrue.purchaselist.domain.GetShopItemUseCase
import edu.mirea.onebeattrue.purchaselist.domain.GetShopListUseCase
import edu.mirea.onebeattrue.purchaselist.domain.ShopItem
import java.lang.Exception

class ShopItemViewModel : ViewModel() {
    private val repository = ShopListRepositoryImpl

    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val getShopItemUseCase = GetShopItemUseCase(repository)

    fun addShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)

        if (fieldsValid) {
            val shopItem = ShopItem(name, count, enabled = true)
            addShopItemUseCase.addShopItem(ShopItem(name, count, true))
        }
    }

    fun editShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)

        if (fieldsValid) {
            // TODO: method is incorrect
            val shopItem = ShopItem(name, count, enabled = true)
            editShopItemUseCase.editShopItem(shopItem)
        }
    }

    fun getShopItem(shopItemId: Int) {
        val item = getShopItemUseCase.getShopItem(shopItemId)
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?): Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun validateInput(name: String, count: Int): Boolean {
        if (name.isBlank()) {
            // TODO: show error input name
            return false
        }
        if (count <= 0) {
            // TODO: show error input count
            return false
        }
        return true
    }
}