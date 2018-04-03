package br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.graph

import android.app.Activity
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.main.MainActivity
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.main.MainComponent
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by lmiyagi on 11/8/17.
 */
@Module(subcomponents = [(MainComponent::class)])
abstract class BindingModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    abstract fun bindMainActivityInjectorFactory(builder: MainComponent.Builder): AndroidInjector.Factory<out Activity>
}