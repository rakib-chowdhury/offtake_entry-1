package com.nuveq.data_entry.feature.auth

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nuveq.data_entry.R
import com.nuveq.data_entry.common.App
import com.nuveq.data_entry.common.BaseActivity
import com.nuveq.data_entry.common.DataResource
import com.nuveq.data_entry.databinding.ActivityRegistrationBinding
import com.nuveq.data_entry.di.ViewModelProviderFactory
import com.nuveq.data_entry.model.auth.LoginPost
import com.nuveq.data_entry.utils.AppUtils
import com.nuveq.data_entry.view.activity.HomeActivity
import javax.inject.Inject

class RegistrationActivity : BaseActivity() {
    var binding: ActivityRegistrationBinding? = null
    private lateinit var viewModel: AuthViewModel

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
    override val layoutResourceFile: Int
        protected get() = R.layout.activity_registration

    override fun initComponent() {
        binding = getBinding() as ActivityRegistrationBinding

        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(AuthViewModel::class.java)

    }

    override fun initFunctionality() {
        viewModel.getUser().observe(this, Observer { dataResource ->
            if (dataResource != null) {
                when (dataResource.status) {
                    DataResource.DataStatus.LOADING -> {
                        showProgressDialog()
                    }
                    DataResource.DataStatus.ERROR -> {
                        hideProgressDialog()
                        showErrorAlertDialog(getString(R.string.er), dataResource.message)
                        AppUtils.message(binding!!.root, dataResource.message)
                    }
                    DataResource.DataStatus.SUCCESS -> {
                        hideProgressDialog()
                        val intent = Intent(this, HomeActivity::class.java)
                        App.instance.setLoginPref(true, dataResource.data!!)
                        startActivity(intent)
                        finish()
                    }
                }
            }
        })
    }

    override fun initListener() {
        binding!!.btnLogin.btnLogin.setOnClickListener {
            if (!isValid()) {
                return@setOnClickListener
            }
            val loginPost = LoginPost()
            loginPost.userId = binding!!.etUsername.text.toString()
            loginPost.password = binding!!.etPassword.text.toString()
            viewModel.authentication(loginPost)
        }
    }


    fun isValid(): Boolean {
        if (binding!!.etUsername.text.toString().equals("")) {
            showErrorAlertDialog(getString(R.string.er), getString(R.string.input_name))
            return false;
        }
        if (binding!!.etPassword.text.toString().equals("")) {
            showErrorAlertDialog(getString(R.string.er), getString(R.string.input_pass))
            return false;
        }
        return true
    }
}