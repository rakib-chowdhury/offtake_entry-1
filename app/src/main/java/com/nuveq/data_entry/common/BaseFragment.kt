package com.nuveq.data_entry.common

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.nuveq.data_entry.R
import dagger.android.support.DaggerFragment
import java.util.*

abstract class BaseFragment : DaggerFragment() {
   private var binding: ViewDataBinding? = null
    private var progressDialog: ProgressDialog? = null
    private var loadingView: LinearLayout? = null
    private var noDataView: LinearLayout? = null

    // private GPSTracker gps;
    var latitude = 0.0
    var longitude = 0.0

    // Inflate the view for the fragment based on layout XML
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if (App.instance.getLangSwitch()) {
            setLocale("bn")
        }
        binding = DataBindingUtil.inflate(inflater, layoutResourceId()!!, container, false)
        return binding!!.getRoot()
    }

    protected abstract fun layoutResourceId(): Int?
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //getGpsLocation();
        initFragmentComponents()
        initFragmentFunctionality()
        initFragmentListener()
    }

    fun getBinding():ViewDataBinding{
        return binding!!
    }

    open fun setLocale(languageCode: String?) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config: Configuration = resources.getConfiguration()
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.getDisplayMetrics())
    }
    /*

    public void getGpsLocation() {
        gps = new GPSTracker(getActivity());
        if (gps.canGetLocation()) {
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
            // Toast.makeText(this, "lt:" + latitude + "\n" + "lng:" + longitude, Toast.LENGTH_SHORT).show();

        }
    }
*/
    fun toast(msg: String?) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
    }

    protected abstract fun initFragmentComponents()
    protected abstract fun initFragmentFunctionality()
    protected abstract fun initFragmentListener()
    val fragment: Fragment
        get() = this

    fun showToast(msg: String?) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    val isNetworkAvailable: Boolean
        get() {
            val connectivityManager = activity!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            var activeNetworkInfo: NetworkInfo? = null
            if (connectivityManager != null) {
                activeNetworkInfo = connectivityManager.activeNetworkInfo
            }
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }

    /*
        public void showProgressDialog(final String title, final String message) {
        new Runnable() {
                @Override
                public void run() {
                    progressDialog = ProgressDialog.show(getActivity(), title, message, true);
                }
            };
        }
        public void showProgressDialog() {
            new Runnable() {
                @Override
                public void run() {
                    progressDialog = ProgressDialog.show(getActivity(), null, getString(R.strings.please_wait), true);
                }
            };
        }

        public void hideProgressDialog() {
            if (progressDialog != null && progressDialog.isShowing()) {
               new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                    }
                };
            }
        }*/
    fun showAlertDialog(title: String?, message: String?) {
        val builder: AlertDialog.Builder
        builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AlertDialog.Builder(context!!, R.style.DialogTheme)
        } else {
            AlertDialog.Builder(context!!)
        }
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok) { dialog, which -> dialog.dismiss() }
                .setIcon(R.drawable.sync)
                .show()
    }

    fun initLoader() {
        loadingView = activity!!.findViewById<View>(R.id.loadingView) as LinearLayout
        noDataView = activity!!.findViewById<View>(R.id.noDataView) as LinearLayout
    }

    fun showLoader() {
        if (loadingView != null) {
            loadingView!!.visibility = View.VISIBLE
        }
        if (noDataView != null) {
            noDataView!!.visibility = View.GONE
        }
    }

    fun hideLoader() {
        if (loadingView != null) {
            loadingView!!.visibility = View.GONE
        }
        if (noDataView != null) {
            noDataView!!.visibility = View.GONE
        }
    }

    fun showEmptyView() {
        if (loadingView != null) {
            loadingView!!.visibility = View.GONE
        }
        if (noDataView != null) {
            noDataView!!.visibility = View.VISIBLE
        }
    }

    fun startActivity(activity: Activity, cls: Class<*>?, finishSelf: Boolean, bundle: Bundle?) {
        val intent = Intent(activity, cls)
        intent.putExtras(bundle!!)
        startActivity(intent)
        if (finishSelf) {
            activity.finish()
        }
    }

    fun startActivity(activity: Activity, cls: Class<*>?, finishSelf: Boolean) {
        val intent = Intent(activity, cls)
        startActivity(intent)
        if (finishSelf) {
            activity.finish()
        }
    }

    fun setToolbarTitle(title: String?) {
        val toolbar = activity!!.findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.title = title
    }

    fun showProgressDialog() {
        activity!!.runOnUiThread { progressDialog = ProgressDialog.show(activity, null, getString(R.string.please_wait), true) }
    }

    fun hideProgressDialog() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            activity!!.runOnUiThread { progressDialog!!.dismiss() }
        }
    }




}