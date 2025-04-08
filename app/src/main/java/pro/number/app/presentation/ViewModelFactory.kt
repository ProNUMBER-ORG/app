package pro.number.app.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject constructor(
    private val viewModels: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return viewModels[modelClass]?.get() as T
    }

    companion object {
        fun createForPreview(viewModel: ViewModel): ViewModelFactory =
            ViewModelFactory(
                mapOf(viewModel::class.java to Provider<ViewModel> { viewModel })
            )
    }
}