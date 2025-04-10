package pro.number.app.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import pro.number.data.di.DataModule
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(context: Context) : Builder

        fun build() : ApplicationComponent
    }

}