package com.example.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<viewBinding : ViewBinding>(
    private val bindingInflater: (
        layoutInflater: LayoutInflater,
        parent: ViewGroup?,
        attachToRoot: Boolean
    ) -> viewBinding
) : Fragment() {

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
