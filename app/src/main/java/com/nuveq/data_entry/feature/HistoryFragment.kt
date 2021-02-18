package com.nuveq.data_entry.feature

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nuveq.data_entry.R
import com.nuveq.data_entry.common.BaseFragment
import com.nuveq.data_entry.common.DataResource
import com.nuveq.data_entry.databinding.FragmentHistoryBinding
import com.nuveq.data_entry.di.ViewModelProviderFactory
import com.nuveq.data_entry.feature.adapter.HistoryAdapter
import com.nuveq.data_entry.utils.AppUtils
import com.nuveq.data_entry.utils.AppUtils.changeDateFormat
import com.nuveq.data_entry.utils.AppUtils.getCurrentDate
import java.util.*
import javax.inject.Inject

class HistoryFragment : BaseFragment(), HistoryAdapter.Interaction {
    private lateinit var viewModel: ProductViewModel
    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var binding: FragmentHistoryBinding
    private var calendar: Calendar? = null

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    override fun layoutResourceId(): Int {
        return R.layout.fragment_history
    }



    override fun initFragmentComponents() {
        binding = getBinding() as FragmentHistoryBinding
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(ProductViewModel::class.java)
        viewModel.getProductHistory(AppUtils.getCurrentDate())
        historyAdapter = HistoryAdapter(this)
        binding!!.adapter = historyAdapter
        calendar = Calendar.getInstance()

    }

    override fun initFragmentFunctionality() {
    }

    override fun initFragmentListener() {
        viewModel.observeProductHistory().observe(this, Observer { dataResource ->
            if (dataResource != null) {
                when (dataResource.status) {
                    DataResource.DataStatus.LOADING -> {
                        binding!!.refresh.isRefreshing = true
                    }
                    DataResource.DataStatus.ERROR -> {
                        binding!!.refresh.isRefreshing = false
                        //showErrorDialog("Error!", dataResource.message)
                        AppUtils.message(binding!!.root, dataResource.message)
                        historyAdapter.submitList(emptyList())
                    }
                    DataResource.DataStatus.SUCCESS -> {
                        binding!!.refresh.isRefreshing = false
                        historyAdapter.submitList(dataResource.data!!.history!!)
                    }
                }
            }
        })

        binding.refresh.setOnRefreshListener {
            viewModel.getProductHistory(getCurrentDate())
        }

        binding.tvDate.setText(getCurrentDate())
        binding.tvDate.setOnClickListener({ v ->
            showDatePicker(binding.tvDate, calendar!!)
        })

    }

    override fun onItemProductSelected(position: Int) {

    }


    fun showDatePicker(editText: EditText, c: Calendar) {
      var  dpd = DatePickerDialog(activity!!, R.style.DatePickerDialogTheme, OnDateSetListener { datePicker, yy, mm, dd ->
          var mm = mm
          mm= mm +1
          var date=changeDateFormat("$yy/$mm/$dd")
          editText.setText(date)
          viewModel.getProductHistory(date)

      },
              c[Calendar.YEAR],
              c[Calendar.MONTH],
              c[Calendar.DAY_OF_MONTH])
        dpd.show()
    }


}