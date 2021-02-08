package com.nuveq.data_entry.feature

import com.nuveq.data_entry.R
import com.nuveq.data_entry.common.App
import com.nuveq.data_entry.common.BaseFragment
import com.nuveq.data_entry.databinding.FragmentProfileBinding

class ProfileFragment : BaseFragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun layoutResourceId(): Int? {
        return R.layout.fragment_profile
    }

    override fun initFragmentComponents() {
        binding = getBinding() as FragmentProfileBinding
        binding.data = App.instance.getLoginUser()
    }

    override fun initFragmentFunctionality() {}
    override fun initFragmentListener() {}
}