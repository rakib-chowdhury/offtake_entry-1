package com.nuveq.data_entry.feature.auth

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nuveq.data_entry.R
import com.nuveq.data_entry.common.BaseFragment
import com.nuveq.data_entry.common.DataResource
import com.nuveq.data_entry.databinding.FragmentChangePassBinding
import com.nuveq.data_entry.di.ViewModelProviderFactory
import com.nuveq.data_entry.model.auth.ChangePassPost
import com.nuveq.data_entry.utils.AppUtils
import javax.inject.Inject

class ChangePassFragment : BaseFragment() {
    private lateinit var binding: FragmentChangePassBinding
    private lateinit var viewModel: AuthViewModel

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
    override fun layoutResourceId(): Int {
        return R.layout.fragment_change_pass
    }

    override fun initFragmentComponents() {
        binding = getBinding() as FragmentChangePassBinding
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(AuthViewModel::class.java)

        binding.btnLogin.curveButtonText.text = getString(R.string.change)
    }

    override fun initFragmentFunctionality() {

    }

    override fun initFragmentListener() {
        viewModel.observeChangeUser().observe(this, Observer { dataResource ->
            if (dataResource != null) {
                when (dataResource.status) {
                    DataResource.DataStatus.LOADING -> {
                        showProgressDialog()
                    }
                    DataResource.DataStatus.ERROR -> {
                        hideProgressDialog()
                        //showErrorDialog("Error!", dataResource.message)
                        AppUtils.message(binding!!.root, dataResource.message)
                    }
                    DataResource.DataStatus.SUCCESS -> {
                        hideProgressDialog()
                        AppUtils.successMessage(binding!!.root, dataResource.data!!.message!!)

                    }
                }
            }
        })

        binding.btnLogin.btnLogin.setOnClickListener {
            if (!isValid()) {
                return@setOnClickListener
            }

            val changePost = ChangePassPost()
            changePost.password = binding.etOldPass.text.toString()
            changePost.newPassword = binding.etNewPass.text.toString()
            changePost.confirmPassword = binding.etConfirmPass.text.toString()
            viewModel.changePass(changePost)
        }
    }


    fun isValid(): Boolean {
        if (binding!!.etOldPass.text.toString().equals("")) {
            binding!!.etOldPass.setError("please input old password")
            return false;
        } else if (binding!!.etNewPass.text.toString().equals("")) {
            binding!!.etNewPass.setError("please input new password")
            return false;
        } else if (binding!!.etConfirmPass.text.toString().equals("")) {
            binding!!.etConfirmPass.setError("please input confirm password")
            return false;
        } else if (!binding!!.etConfirmPass.text.toString().equals(binding!!.etNewPass.text.toString())) {
            AppUtils.message(binding.root, "Confirm password is not matched!")
            return false;
        }
        return true
    }
}