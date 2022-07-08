package com.roadster.roam.basesetup.ui.main.base

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding
import com.roadster.roam.basesetup.R
import com.roadster.roam.basesetup.extensions.UNDEFINED_INT
import com.roadster.roam.basesetup.network.BaseError
import com.roadster.roam.basesetup.network.NetworkError

abstract class BaseActivity<VIEW_BINDING:ViewBinding> : AppCompatActivity {

    @get:LayoutRes
    abstract val layoutId: Int

    lateinit var binding:VIEW_BINDING

    abstract val graphId: Int

    abstract val navHostFragmentId: Int

    val navController: NavController by lazy {
        Navigation.findNavController(this, navHostFragmentId)
    }

    protected val commonNetworkErrorObserver: (BaseError) -> Unit =
        { handleCommonNetworkErrors(it) }

    constructor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setWindowBackground()
        if (layoutId != UNDEFINED_INT) {
            binding = DataBindingUtil.setContentView(this, layoutId)
            setContentView(binding.root)
        }

        var bundle = bundleOf()

        (supportFragmentManager.findFragmentById(navHostFragmentId) as? NavHostFragment)?.apply {
            val navGraph = navController.navInflater.inflate(graphId).apply {
                setStartDestination(R.id.exampleFragment)
            }
            navController.setGraph(navGraph, bundle)
        }
    }

    protected open fun setWindowBackground() {}

    fun setMainWindowBackground() = setWindowBackgroundDrawable()

    protected fun setWindowBackgroundDrawable(
        drawable: Drawable? = ContextCompat.getDrawable(
            this,
            R.drawable.window_bg_primary
        )
    ) {
        window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = ContextCompat.getColor(
                this@BaseActivity,
                android.R.color.transparent
            )
            drawable?.let {
                setBackgroundDrawable(it)
            }
        }
    }

    protected fun setWindowBackgroundDrawableRes(@DrawableRes drawableRes: Int = R.drawable.window_bg_primary) {
        window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = ContextCompat.getColor(
                this@BaseActivity,
                android.R.color.transparent
            )
            ContextCompat.getDrawable(this@BaseActivity, drawableRes)?.let {
                setBackgroundDrawable(it)
            }
        }
    }

    fun handleCommonNetworkErrors(cause: BaseError) {
        when (cause) {
            is NetworkError.Unauthorized -> {}
            is NetworkError.Connection -> handleConnectionError()
            is NetworkError.ConnectionTimeout -> handleConnectionTimeoutError()
            is NetworkError.ServerInternalError,
            is NetworkError.ServerTemporaryUnavailable,
            is NetworkError.ServerMaintenance -> showServerErrorDialog(cause)
        }
    }

    private fun showServerErrorDialog(error: BaseError) =
        showInfoMessage(error.message ?: getString(R.string.error_message_common))

    private fun handleConnectionTimeoutError() =
        showInfoMessage(getString(R.string.network_error_connection_timeout))

    private fun handleConnectionError() =
        showInfoMessage(getString(R.string.network_error_connection))

    fun showInfoMessage(message: CharSequence) = showInfoMsg(message)

    abstract fun showInfoMsg(message:CharSequence?)
}