package edu.mirea.onebeattrue.purchaselist.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.mirea.onebeattrue.purchaselist.databinding.ActivityShopItemBinding

class ShopItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShopItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopItemBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}