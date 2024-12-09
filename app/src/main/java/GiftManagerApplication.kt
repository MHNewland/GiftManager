package com.mnewland.giftmanager

import android.app.Application
import com.mnewland.giftmanager.data.AppContainer
import com.mnewland.giftmanager.data.AppDataContainer

class GiftManagerApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}