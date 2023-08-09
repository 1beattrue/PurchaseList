package edu.mirea.onebeattrue.purchaselist.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.mirea.onebeattrue.purchaselist.R
import edu.mirea.onebeattrue.purchaselist.databinding.ActivityShopItemBinding
import edu.mirea.onebeattrue.purchaselist.domain.ShopItem

class ShopItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShopItemBinding

    private var screenMode = MODE_UNKNOWN
    private var shopItemId = ShopItem.UNDEFINED_ID
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        parseIntent()

        if (savedInstanceState == null)
            launchRightMode()
    }

    private fun launchRightMode() {
        val fragment = when (screenMode) {
            MODE_ADD -> ShopItemFragment.newInstanceAddItem()
            MODE_EDIT -> ShopItemFragment.newInstanceEditItem(shopItemId)
            else -> throw RuntimeException("Unknown screen mode: $screenMode")
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.shop_item_container, fragment) // добавляем фрагмент в контейнер
            .commit()                                // запускаем транзакцию
    }

    private fun parseIntent() {
        if (!intent.hasExtra(EXTRA_SCREEN_MODE))
            throw RuntimeException("Param screen mode is absent")

        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if (mode != MODE_EDIT && mode != MODE_ADD)
            throw RuntimeException("Unknown screen mode: $mode")

        screenMode = mode

        if (screenMode == MODE_EDIT) {
            if (!intent.hasExtra(EXTRA_SHOP_ITEM_ID))
                throw RuntimeException("Param shop item id is absent")

            shopItemId = intent.getIntExtra(EXTRA_SHOP_ITEM_ID, ShopItem.UNDEFINED_ID)
        }
    }

    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_SHOP_ITEM_ID = "extra_shop_item_id"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"

        private const val MODE_UNKNOWN = ""

        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditItem(context: Context, shopItemId: Int): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_SHOP_ITEM_ID, shopItemId)
            return intent
        }
    }
}