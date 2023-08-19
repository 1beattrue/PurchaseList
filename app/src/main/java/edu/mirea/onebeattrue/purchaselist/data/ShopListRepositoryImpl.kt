package edu.mirea.onebeattrue.purchaselist.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import edu.mirea.onebeattrue.purchaselist.domain.ShopItem
import edu.mirea.onebeattrue.purchaselist.domain.ShopListRepository

class ShopListRepositoryImpl(
    application: Application
) : ShopListRepository {

    private val shopListDao = AppDatabase.getInstance(application).shopListDao()
    private val mapper = ShopListMapper()

    override fun addShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopListDao.deleteShopItem(shopItem.id)
    }

    override fun editShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem)) // Room перезаписывает элементы с одинаковыми id
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        val dbModel = shopListDao.getShopItem(shopItemId)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return MediatorLiveData<List<ShopItem>>().apply {
            // этот класс позволяет перехватывать события из другой LiveData и каким то образом на них реагировать
            // (например преобразовать в другой тип)
            addSource(shopListDao.getShopList()) {
                value = mapper.mapListDbModelToListEntity(it)
            }
        }
    }
}