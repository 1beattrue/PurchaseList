package edu.mirea.onebeattrue.purchaselist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import edu.mirea.onebeattrue.purchaselist.R
import edu.mirea.onebeattrue.purchaselist.databinding.ActivityMainBinding
import edu.mirea.onebeattrue.purchaselist.domain.ShopItem

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel
    private lateinit var llShopListView: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        llShopListView = binding.llShopList

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this) {
            showList(it)
        }
    }

    private fun showList(list: List<ShopItem>) {
        llShopListView.removeAllViews()
        for (shopItem in list) {
            val layoutId =
                if (shopItem.enabled) R.layout.item_shop_enabled else R.layout.item_shop_disabled
            val view = LayoutInflater.from(this).inflate(layoutId, llShopListView, false)

            val tvName = view.findViewById<TextView>(R.id.tv_name)
            val tvCount = view.findViewById<TextView>(R.id.tv_count)

            tvName.text = shopItem.name
            tvCount.text = shopItem.count.toString()

            view.setOnLongClickListener {
                viewModel.editShopItem(shopItem)
                true
            }

            llShopListView.addView(view)
        }
    }
}