package com.example.android.punkbeer.root

import android.app.Application

import com.example.android.punkbeer.retrofit.PunkServiceModule
import com.example.android.punkbeer.root.ApplicationComponent
import com.example.android.punkbeer.root.ApplicationModule
import com.example.android.punkbeer.view.main.MainModule


class App : Application() {

    companion object {

        @JvmStatic lateinit var component: ApplicationComponent

    }

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .punkServiceModule(PunkServiceModule())
                .mainModule(MainModule())
                .build()
        component.inject(this)
    }
}
