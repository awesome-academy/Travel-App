package com.sunasterisk.travelapp.ui.base

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.sunasterisk.travelapp.R
import com.sunasterisk.travelapp.utils.createProgressDialog
import com.sunasterisk.travelapp.utils.showSnackBar
import com.sunasterisk.travelapp.utils.showToast

abstract class BaseMVPActivity<V : BaseContract.View<T>, T : BaseContract.Presenter<V>> :
    BaseActivity(), BaseContract.View<T> {

    protected abstract val presenter: T

    private var progressDialog: Dialog? = null

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        (this as? V)?.let { presenter.onAttach(it) }
        super.onCreate(savedInstanceState)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        this.intent = intent
    }

    override fun showProgressDialog() {
        progressDialog = createProgressDialog()
    }

    override fun dismissProgressDialog() {
        progressDialog?.dismiss()
    }

    override fun showToastMessage(message: String?) {
        if (!message.isNullOrEmpty()) {
            baseContext.showToast(message)
        }
    }

    override fun showToastMessage(stringResourceId: Int) {
        showToastMessage(getString(stringResourceId))
    }

    override fun showSnackBarMessage(message: String?) {
        if (!message.isNullOrEmpty()) {
            showSnackBarMessage(message)
        }
    }

    override fun showSnackBarMessage(stringResourceId: Int) {
        showSnackBarMessage(getString(stringResourceId))
    }

    override fun onError(message: String?) {
        val msg = message ?: getString(R.string.error_default_message)
        if (this is View) {
            showSnackBar(msg, Snackbar.LENGTH_SHORT)
        }
    }

    override fun onError(resId: Int) {
        onError(getString(resId))
    }

    override fun onStop() {
        super.onStop()
        dismissProgressDialog()
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissProgressDialog()
    }
}
