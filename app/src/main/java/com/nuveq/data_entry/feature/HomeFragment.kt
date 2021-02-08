package com.nuveq.data_entry.feature

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nuveq.data_entry.R
import com.nuveq.data_entry.common.BaseFragment
import com.nuveq.data_entry.common.DataResource
import com.nuveq.data_entry.common.FakeDataList.getFakeVocDataList
import com.nuveq.data_entry.databinding.HomeFragmentBinding
import com.nuveq.data_entry.di.ViewModelProviderFactory
import com.nuveq.data_entry.feature.adapter.FakeAdapter
import com.nuveq.data_entry.feature.adapter.FakeAdapter.Interaction
import com.nuveq.data_entry.model.FakeData
import com.nuveq.data_entry.utils.AppUtils
import com.nuveq.data_entry.view.activity.StoreActivity
import javax.inject.Inject

class HomeFragment : BaseFragment(), Interaction {
    private var binding: HomeFragmentBinding? = null
    private var adapter: FakeAdapter? = null
    private lateinit var viewModel: StoreViewModel

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
    override fun layoutResourceId(): Int {
        return R.layout.home_fragment
    }

    override fun initFragmentComponents() {
        binding = getBinding() as HomeFragmentBinding
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(StoreViewModel::class.java)
        viewModel.getSummeryData()

    }

    override fun initFragmentFunctionality() {

    }

    override fun initFragmentListener() {
        viewModel.observeSummery().observe(this, Observer { dataResource ->
            if (dataResource != null) {
                when (dataResource.status) {
                    DataResource.DataStatus.LOADING -> {
                        binding!!.refresh.isRefreshing=true
                    }
                    DataResource.DataStatus.ERROR -> {
                        binding!!.refresh.isRefreshing=false
                        //showErrorDialog("Error!", dataResource.message)
                        AppUtils.message(binding!!.root, dataResource.message)
                    }
                    DataResource.DataStatus.SUCCESS -> {
                        binding!!.refresh.isRefreshing=false
                        adapter = FakeAdapter(this)
                        adapter!!.submitList(getFakeVocDataList(context!!,dataResource.data!!))
                        binding!!.adapter = adapter
                    }
                }
            }
        })

        binding!!.button.setOnClickListener {
            startActivity(activity!!, StoreActivity::class.java, false)
        }

        binding!!.refresh.setOnRefreshListener {
            viewModel.getSummeryData()

        }
    }

    override fun onItemSelected(position: Int, fakeData: FakeData) {

    }


}