package com.roadster.roam.basesetup.ui.main.base

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.roadster.roam.basesetup.R
import com.roadster.roam.basesetup.navigation.NavControllerProvider
import com.roadster.roam.basesetup.network.BaseError


abstract class BaseFragment : Fragment() {

    @get:LayoutRes
    abstract val layout: Int

    protected open val commonNetworkErrorObserver: (BaseError) -> Unit = { error ->
        (activity as? BaseActivity<*>)?.handleCommonNetworkErrors(error)
    }

    val navController: NavController
        get() = findNavController()

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(layout, container, false)
        (activity as? NavControllerProvider)?.navController?.let {
            Navigation.setViewNavController(
                view,
                it
            )
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    protected fun showKeyboard() =
        (context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)
            ?.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)

    protected fun hideKeyboard() =
        (context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager)
            ?.hideSoftInputFromWindow(requireView().windowToken, 0)

    fun showSuccessMessage(title: CharSequence, subtitle: CharSequence? = null) =
        showSuccessMsg(title, subtitle)

    fun showErrorMessage(title: CharSequence, subtitle: CharSequence? = null) = showErrorMsg(title, subtitle)

    abstract fun setupViews()

    protected fun setWindowBackgroundColor(@ColorInt color: Int) =
        setWindowBackgroundDrawable(
            GradientDrawable().apply {
                shape = GradientDrawable.RECTANGLE
                setColor(color)
            }
        )

    protected fun setWindowBackgroundDrawable(@DrawableRes drawableRes: Int) =
        setWindowBackgroundDrawable(ContextCompat.getDrawable(requireContext(), drawableRes))

    protected fun setWindowBackgroundDrawable(
        drawable: Drawable? = ContextCompat.getDrawable(
            requireContext(),
            R.drawable.window_bg_primary
        )
    ) {
        requireActivity().window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = ContextCompat.getColor(requireContext(), android.R.color.transparent)
            drawable?.let {
                setBackgroundDrawable(it)
            }
        }
    }

    protected fun navigateSafe(
        @IdRes destinationId: Int,
        @IdRes actionId: Int? = null,
        bundle: Bundle? = null
    ) {
        if (navController.currentDestination?.id != destinationId) {
            navController.navigate(actionId ?: destinationId, bundle)
        }
    }

    abstract fun showErrorMsg(title: CharSequence, subtitle: CharSequence?)
    abstract fun showSuccessMsg(title: CharSequence, subtitle: CharSequence?)
}