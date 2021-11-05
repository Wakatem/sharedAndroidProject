package com.example.project242.MonetaryRates;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PagerAdapter extends FragmentStateAdapter {

    final private int numberOfTabs = 2;
    private Context context;

    public PagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, Context context) {
        super(fragmentManager, lifecycle);
        this.context = context;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {

        if (position == 0) {
            return new CostsFragment();

        }
        else {
            return new DiscountsFragment();
        }

    }


    @Override
    public int getItemCount() {
        return numberOfTabs;
    }


}
