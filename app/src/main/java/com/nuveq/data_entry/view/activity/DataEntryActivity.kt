package com.nuveq.data_entry.view.activity

import android.app.Dialog
import android.view.MenuItem
import android.view.Window
import android.widget.EditText
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nuveq.data_entry.R
import com.nuveq.data_entry.appdata.AppConstants
import com.nuveq.data_entry.common.BaseActivity
import com.nuveq.data_entry.common.DataResource
import com.nuveq.data_entry.databinding.ActivityDataEntryBinding
import com.nuveq.data_entry.di.ViewModelProviderFactory
import com.nuveq.data_entry.feature.adapter.ProductAdapter
import com.nuveq.data_entry.feature.adapter.ProductCatAdapter
import com.nuveq.data_entry.feature.ProductViewModel
import com.nuveq.data_entry.model.data_post.DataEntryPost
import com.nuveq.data_entry.model.porduct_data.Product
import com.nuveq.data_entry.utils.AppUtils
import com.nuveq.data_entry.utils.NumberValidation
import jrizani.jrspinner.JRSpinner
import javax.inject.Inject


class DataEntryActivity : BaseActivity(), ProductCatAdapter.Interaction, ProductAdapter.Interaction {

    private lateinit var binding: ActivityDataEntryBinding
    lateinit var dialog: Dialog
    private var catAdapter: ProductCatAdapter? = null
    private var productAdapter: ProductAdapter? = null
    private lateinit var retProductList: ArrayList<Product>

    private lateinit var postProductList: ArrayList<com.nuveq.data_entry.model.data_post.Product>
    private lateinit var viewModel: ProductViewModel

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
    override val layoutResourceFile: Int
        get() = R.layout.activity_data_entry


    override fun initComponent() {
        binding = getBinding() as ActivityDataEntryBinding
        postProductList = ArrayList()
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(ProductViewModel::class.java)
        initToolbar()
        setToolbarTitle(getString(R.string.data_entry))
        enableBackButton()
        catAdapter = ProductCatAdapter(this)
        productAdapter = ProductAdapter(this)
        binding.adapterProduct = productAdapter


    }

    override fun initFunctionality() {
        viewModel.getProductData()

        viewModel.observeProductList().observe(this, Observer { dataResource ->
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
                        retProductList = ArrayList<Product>()
                        retProductList.addAll(dataResource.data!!.products!!)
                        catAdapter!!.submitList(retProductList)
                        binding!!.adapter = catAdapter
                    }
                }
            }
        })

        viewModel.observePost().observe(this, Observer { dataResource ->
            if (dataResource != null) {
                when (dataResource.status) {
                    DataResource.DataStatus.LOADING -> {
                        showProgressDialog()
                    }
                    DataResource.DataStatus.ERROR -> {
                        hideProgressDialog()
                        showErrorAlertDialog("Error!", dataResource.message)
                        AppUtils.message(binding!!.root, dataResource.message)
                    }
                    DataResource.DataStatus.SUCCESS -> {
                        hideProgressDialog()
                        showFinishAlertDialog("Success!",dataResource.data!!.message!!)

                        showToast(dataResource.data!!.message!!)
                        AppUtils.message(binding!!.root, dataResource.data.message)
                        postProductList.clear()
                        productAdapter!!.submitList(postProductList)

                    }
                }
            }
        })

        NumberValidation.checkMobileNumber(binding.etPhone)

    }

    override fun initListener() {
        binding.btnSave.setOnClickListener {
            if (!isMainFormValid()) {
                return@setOnClickListener
            }
            val dataEntryPost = DataEntryPost()
            dataEntryPost.customerName = binding.etName.text.toString()
            dataEntryPost.livingAddress = binding.etArea.text.toString()
            dataEntryPost.customerPhone = binding.etPhone.text.toString()
            dataEntryPost.storeId = intent.extras!!.getString(AppConstants.STORE_ID)
            dataEntryPost.androidVersion = ""
            dataEntryPost.device = ""
            dataEntryPost.latitude = ""
            dataEntryPost.longitude = ""
            dataEntryPost.products = postProductList

            viewModel.postProductData(dataEntryPost)
        }

    }


    private fun showMyDialog(position: Int) {
        dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.product_dialuge)
        val skuListName = arrayOfNulls<String>(retProductList.get(position).skus!!.size)
        val skuListId = arrayOfNulls<String>(retProductList.get(position).skus!!.size)
        val product = com.nuveq.data_entry.model.data_post.Product()
        var cardView = dialog.findViewById<CardView>(R.id.btnCross)
        var btnSave = dialog.findViewById<CardView>(R.id.btn_add_to_list)
        var etBrand = dialog.findViewById<EditText>(R.id.et_brand)
        var spnSku = dialog.findViewById<JRSpinner>(R.id.spn_sku)
        var etQuantity = dialog.findViewById<EditText>(R.id.et_quantity)

        for (i in 0 until retProductList.get(position).skus!!.size) {
            try {
                skuListName[i] = retProductList.get(position).skus!!.get(i).name
                skuListId[i] = retProductList.get(position).skus!!.get(i).id
            } catch (e: Exception) {
            }
        }


        spnSku.setItems(skuListName)
        spnSku.setMultiple(false)
        etBrand.setText(retProductList.get(position).name)
        spnSku.setOnItemClickListener(JRSpinner.OnItemClickListener { position ->
            product.skuId = Integer.parseInt(skuListId[position])
            product.skuName = skuListName[position]
        })

        cardView.setOnClickListener {
            dialog.dismiss()
        }

        btnSave.setOnClickListener {

            if (!isValid(etBrand, etQuantity)) {
                AppUtils.message(etQuantity, getString(R.string.fill_up))
                return@setOnClickListener
            }
            product.productName = etBrand.text.toString()
            product.quantity = Integer.parseInt(etQuantity.text.toString())
            postProductList.add(product)

            showToast(getString(R.string.added_list))
            dialog.dismiss()
            productAdapter!!.submitList(postProductList)
        }

        dialog.show()
    }


    private fun isValid(etBrand: EditText, etQ: EditText): Boolean {
        if (etBrand.text.toString().equals("")) {
            return false
        } else if (etQ.text.toString().equals("")) {
            return false
        }
        return true
    }

    private fun isMainFormValid(): Boolean {
        if (postProductList.isEmpty()) {
            AppUtils.message(binding!!.root, getString(R.string.empty_cart))
            return false
        } else if (binding.etName.text.toString().equals("")) {
            AppUtils.message(binding!!.root, getString(R.string.enter_con_name))

            return false
        } else if (binding.etArea.text.toString().equals("")) {
            AppUtils.message(binding!!.root, getString(R.string.enter_area))

            return false
        } else if (binding.etPhone.text.toString().equals("")) {
            AppUtils.message(binding!!.root, getString(R.string.enter_phone))

            return false
        }

       else if (!NumberValidation.isValid(binding.etPhone.text.toString())) {
            AppUtils.message(binding!!.root,getString(R.string.valid_phone))
            return false

        }
        return true
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

    override fun onItemSelected(position: Int) {
        showMyDialog(position)
    }

    override fun onItemProductSelected(position: Int) {
        postProductList.removeAt(position)
        productAdapter!!.notifyItemRemoved(position)
    }


}