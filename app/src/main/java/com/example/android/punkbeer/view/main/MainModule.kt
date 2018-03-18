package com.example.android.punkbeer.view.main

import com.example.android.punkbeer.data.RealmRepository
import com.example.android.punkbeer.retrofit.PunkRepository
import com.example.android.punkbeer.retrofit.Repository
import dagger.Module
import dagger.Provides


/**
 * Created by samsung on 2018-03-11.
 */

@Module
class MainModule{

    @Provides
    fun providePresenter(model:MainMVP.Model):MainMVP.Presenter{
        return MainPresenter(model)
    }


    @Provides
    fun provideModel(punkRepository: PunkRepository, realmRepository: Repository):MainMVP.Model{
        return MainModel(punkRepository,realmRepository)
    }

    @Provides
    fun provideRealmRepository():Repository{
        return RealmRepository()
    }


}
