package com.example.android.punkbeer.root

import com.example.android.punkbeer.retrofit.PunkServiceModule
import com.example.android.punkbeer.view.main.MainActivity
import com.example.android.punkbeer.view.main.MainModule
import dagger.Component
import javax.inject.Singleton


/**
 * Created by samsung on 2018-03-11.
 */


@Singleton
@Component(modules = arrayOf( ApplicationModule::class, PunkServiceModule::class, MainModule::class))
interface ApplicationComponent {

    fun inject(target: MainActivity)
    fun inject(target: App)


}
