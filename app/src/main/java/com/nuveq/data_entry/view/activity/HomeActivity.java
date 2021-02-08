package com.nuveq.data_entry.view.activity;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.nuveq.data_entry.R;
import com.nuveq.data_entry.common.App;
import com.nuveq.data_entry.common.BaseActivity;
import com.nuveq.data_entry.databinding.ActivityMainBinding;
import com.nuveq.data_entry.di.ViewModelProviderFactory;
import com.nuveq.data_entry.feature.ProductViewModel;
import com.nuveq.data_entry.feature.auth.ChangePassFragment;
import com.nuveq.data_entry.feature.HistoryFragment;
import com.nuveq.data_entry.feature.HomeFragment;
import com.nuveq.data_entry.feature.ProfileFragment;
import com.nuveq.data_entry.sliding_drawer.menu.DrawerAdapter;
import com.nuveq.data_entry.sliding_drawer.menu.DrawerItem;
import com.nuveq.data_entry.sliding_drawer.menu.SimpleItem;
import com.nuveq.data_entry.sliding_drawer.menu.SpaceItem;
import com.nuveq.data_entry.utils.AppUtils;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.Arrays;
import java.util.Calendar;

import javax.inject.Inject;

public class HomeActivity extends BaseActivity implements DrawerAdapter.OnItemSelectedListener {

    private static final int HOME_FRAGMENT = 0;
    private static final int PROFILE_FRAGMENT = 1;
    private static final int HISTORY_FRAGMENT = 2;
    private static final int CHANGE_PASS_FRAGMENT = 3;
    private static final int POS_LOGOUT = 5;
    private Calendar calendar;

    private String[] screenTitles;
    private Drawable[] screenIcons;
    DatePickerDialog dpd;
    private SlidingRootNav slidingRootNav;
    private CallHistoryApi callHistoryApiInterface;
    private ActivityMainBinding binding;


    @Override
    public void onItemSelected(int position) {
        Fragment selectedScreen = null;

        if (position == HOME_FRAGMENT) {
            selectedScreen = new HomeFragment();
            setToolbarTitle(getString(R.string.home));
            binding.Toolbar.btnCalender.setVisibility(View.GONE);

        } else if (position == PROFILE_FRAGMENT) {
            selectedScreen = new ProfileFragment();
            binding.Toolbar.btnCalender.setVisibility(View.GONE);
            setToolbarTitle(getString(R.string.profile));
        } else if (position == HISTORY_FRAGMENT) {
            selectedScreen = new HistoryFragment();
            // binding.Toolbar.btnCalender.setVisibility(View.VISIBLE);
            setToolbarTitle(getString(R.string.menu_history));
        } else if (position == CHANGE_PASS_FRAGMENT) {
            selectedScreen = new ChangePassFragment();
            binding.Toolbar.btnCalender.setVisibility(View.GONE);
            setToolbarTitle(getString(R.string.change_pass));


        } else if (position == POS_LOGOUT) {
            logout();
            return;
        }
        slidingRootNav.closeMenu();
        showFragment(selectedScreen);
    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    @SuppressWarnings("rawtypes")
    private DrawerItem createItemFor(int position) {
        return new SimpleItem(screenIcons[position], screenTitles[position])
                .withIconTint(color(R.color.colorWhite))
                .withTextTint(color(R.color.colorWhite))
                .withSelectedIconTint(color(R.color.white_shade))
                .withSelectedTextTint(color(R.color.white_shade));
    }

    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.ld_activityScreenTitles);
    }

    private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.ld_activityScreenIcons);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();
        return icons;
    }

    @ColorInt
    private int color(@ColorRes int res) {
        return ContextCompat.getColor(this, res);
    }

    @Override
    protected int getLayoutResourceFile() {
        return R.layout.activity_main;
    }

    @Override
    protected void initComponent() {
        calendar = Calendar.getInstance();

        binding = (ActivityMainBinding) getBinding();

        initToolbar();
        slidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(getToolbar())
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withMenuLayout(R.layout.menu_left_drawer)
                .inject();

        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
                createItemFor(HOME_FRAGMENT).setChecked(true),
                createItemFor(PROFILE_FRAGMENT),
                createItemFor(HISTORY_FRAGMENT),
                createItemFor(CHANGE_PASS_FRAGMENT),
                new SpaceItem(48),
                createItemFor(POS_LOGOUT)));
        adapter.setListener(this);

        RecyclerView list = findViewById(R.id.list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);
        SwitchCompat langSwitch = findViewById(R.id.switch_lang);


        if (App.instance.getLangSwitch()) {
            setLocale("bn");
            langSwitch.setChecked(true);
            langSwitch.setText("bn");
        }

        langSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                App.instance.setLangSwitch(isChecked);
                if (isChecked) {
                    setLocale("bn");
                    langSwitch.setText("bn");
                } else {
                    setLocale("en");
                    langSwitch.setText("en");

                }
            }
        });

        adapter.setSelected(HOME_FRAGMENT);
    }

    @Override
    protected void initFunctionality() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onBackPressed() {
        isExitFromAppDialog();
    }


    public interface CallHistoryApi {
        void call(String date);
    }
}
