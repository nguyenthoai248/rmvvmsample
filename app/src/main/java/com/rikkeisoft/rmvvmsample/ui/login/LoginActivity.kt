package com.rikkeisoft.rmvvmsample.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.rikkeisoft.rmvvmsample.BR
import com.rikkeisoft.rmvvmsample.MainActivity
import com.rikkeisoft.rmvvmsample.R
import com.rikkeisoft.rmvvmsample.ViewModelProviderFactory
import com.rikkeisoft.rmvvmsample.databinding.ActivityLoginBinding
import com.rikkeisoft.rmvvmsample.ui.base.BaseActivity
import javax.inject.Inject

/**
 * Created by ThoaiNguyen on 2/28/2020.
 */
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(), LoginNavigator {

    @Inject lateinit var factory: ViewModelProviderFactory
    internal var mLoginViewModel: LoginViewModel? = null
    internal var mActivityLoginBinding: ActivityLoginBinding? = null

    fun newIntent(context: Context): Intent {
        return Intent(context, LoginActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityLoginBinding = getViewDataBinding()
        mLoginViewModel?.setNavigator(this)
    }

    override val bindingVariable: Int = BR.viewModel

    override val layoutId: Int = R.layout.activity_login

    override val viewModel: LoginViewModel
        get() = ViewModelProviders.of(this, factory).get(LoginViewModel::class.java)

    override fun handleError(throwable: Throwable) {
        // handle error
    }

    override fun login() {
        val email = mActivityLoginBinding?.etEmail?.text.toString()
        val password = mActivityLoginBinding?.etPassword?.text.toString()
        if (mLoginViewModel?.isEmailAndPasswordValid(email, password) == true) {
            hideKeyboard()
            mLoginViewModel?.login(email, password)
        } else {
            Toast.makeText(this, getString(R.string.invalid_email_password), Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun openMainActivity() {
        val intent = MainActivity().newIntent(this@LoginActivity)
        startActivity(intent)
        finish()
    }
}