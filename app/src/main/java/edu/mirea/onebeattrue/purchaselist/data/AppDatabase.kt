package edu.mirea.onebeattrue.purchaselist.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ShopItemDbModel::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun shopListDao(): ShopListDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        private var LOCK = Any()
        private const val DB_NAME = "shop_item.db"

        fun getInstance(application: Application): AppDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) { // double check (для правильной работы с потоками)
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = db
                return db
            }
        }
    }
}