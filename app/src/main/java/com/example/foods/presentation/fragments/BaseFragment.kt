package com.example.foods.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.foods.R
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment

abstract class BaseFragment<viewBinding : ViewBinding>(
    private val bindingInflater: (
        layoutInflater: LayoutInflater,
        parent: ViewGroup?,
        attachToRoot: Boolean
    ) -> viewBinding
) : DaggerFragment() {

    private var _binding: viewBinding? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater, container, false)
        return _binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    protected fun showRetrySnackBar(view: View, message: String, listener: () -> Unit) {
        Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.retry_label) { listener.invoke() }
            .show()
    }
}
