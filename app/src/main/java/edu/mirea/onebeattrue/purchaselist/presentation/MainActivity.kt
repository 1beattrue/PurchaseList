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

    private lateinit var adapter: ShopListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this) {
            adapter.shopList = it
        }
    }

    private fun setupRecyclerView() {
        val rvShopList = binding.rvShopList
        adapter = ShopListAdapter()
        rvShopList.adapter = adapter
    }
}