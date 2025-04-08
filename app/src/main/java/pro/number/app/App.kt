package pro.number.app

import android.app.Application
import pro.number.app.di.DaggerApplicationComponent

class App : Application() {

    val component by lazy {
        DaggerApplicationComponent.create()
    }

}