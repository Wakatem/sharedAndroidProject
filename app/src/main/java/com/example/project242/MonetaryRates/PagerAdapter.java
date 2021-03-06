package com.example.project242.MonetaryRates;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.project242.MonetaryRates.Costs.CostsFragment;
import com.example.project242.MonetaryRates.Discount.DiscountsFragment;

public class PagerAdapter extends FragmentStateAdapter {

    final private int numberOfTabs = 2;

    public PagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
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
