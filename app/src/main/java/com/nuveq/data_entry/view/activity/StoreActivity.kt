package com.nuveq.data_entry.view.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nuveq.data_entry.R
import com.nuveq.data_entry.appdata.AppConstants
import com.nuveq.data_entry.common.BaseActivity
import com.nuveq.data_entry.common.DataResource
import com.nuveq.data_entry.databinding.ActivityStoreBinding
import com.nuveq.data_entry.di.ViewModelProviderFactory
import com.nuveq.data_entry.feature.adapter.StoreAdapter
import com.nuveq.data_entry.feature.StoreViewModel
import com.nuveq.data_entry.model.store.Store

import com.nuveq.data_entry.utils.AppUtils
import javax.inject.Inject

class StoreActivity : BaseActivity(), StoreAdapter.Interaction {
    lateinit var binding: ActivityStoreBinding
    private var adapter: StoreAdapter? = null
    private lateinit var viewModel: StoreViewModel

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
    override val layoutResourceFile: Int
        get() = R.layout.activity_store

    override fun initComponent() {
        binding = getBinding() as ActivityStoreBinding
        initToolbar()
        setToolbarTitle(getString(R.string.store))
        enableBackButton()
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(StoreViewModel::class.java)
        adapter = StoreAdapter(this)


    }

    override fun initFunctionality() {
        viewModel.getStoreData()
    }

    override fun initListener() {
        viewModel.observeStoreList().observe(this, Observer { dataResource ->
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
                        adapter!!.submitList(dataResource.data!!.stores!!)
                        binding!!.adapter = adapter
                    }
                }
            }
        })
    }

    override fun onItemSelected(item: Store) {
        val bundle = Bundle()
        bundle.putString(AppConstants.STORE_ID, item.storeId.toString())
        startActivity( DataEntryActivity::class.java, false,bundle)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
//            onBackPressed();
//            Snackbar.make(floatingbar,"Clicked",Snackbar.LENGTH_SHORT).show();
            //startActivity(new Intent(HomeActivity.this, HistoryActivity.class));
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}