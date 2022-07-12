package com.roadster.roam.basesetup.ui.main.base

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.fragment.findNavController
import com.roadster.roam.basesetup.network.BaseError

abstract class BaseDialogFragment : AppCompatDialogFragment() {

    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected val navController
        get() = findNavController()

    protected val commonNetworkErrorObserver: (BaseError) -> Unit = { error ->
        (activity as? BaseActivity<*>)?.handleCommonNetworkErrors(error)
    }

    protected fun <T : ViewModel> obtainViewModel(vmClass: Class<T>, owner: ViewModelStoreOwner = this) =
        ViewModelProvider(owner, viewModelFactory).get(vmClass)

    fun showToast(message: CharSequence) =
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

    public fun hideKeyboard() =
        (requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(requireView().windowToken, 0)

    protected fun navigateSafe(@IdRes destinationId: Int, @IdRes actionId: Int? = null) {
        if (navController.currentDestination?.id != destinationId) {
            navController.navigate(actionId ?: destinationId)
        }
    }
}