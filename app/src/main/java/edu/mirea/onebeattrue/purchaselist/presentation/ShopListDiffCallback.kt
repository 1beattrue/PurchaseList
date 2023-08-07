package edu.mirea.onebeattrue.purchaselist.presentation

import androidx.recyclerview.widget.DiffUtil
import edu.mirea.onebeattrue.purchaselist.domain.ShopItem

class ShopListDiffCallback( // класс сравнения списков(чтобы понять, что произошло со списком(удаление элемента, добавление элемента...))
    private val oldList: List<ShopItem>,
    private val newList: List<ShopItem>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition] // сравнение полей (equals() у data переопределен)
    }

}