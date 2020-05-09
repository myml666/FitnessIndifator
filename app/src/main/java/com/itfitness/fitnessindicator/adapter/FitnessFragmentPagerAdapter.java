package com.itfitness.fitnessindicator.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @ProjectName: FitnessIndifator
 * @Package: com.itfitness.fitnessindicator.adapter
 * @ClassName: FitnessFragmentPagerAdapter
 * @Description: java类作用描述
 * @Author: 作者名 itfitness
 * @CreateDate: 2020/5/9 17:57
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/5/9 17:57
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class FitnessFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragment;

    public FitnessFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragment) {
        super(fm);
        this.fragment=fragment;
    }

    @Override
    public Fragment getItem(int position) {
        return fragment.get(position);
    }

    @Override
    public int getCount() {
        return fragment.size();
    }
}
