package com.itfitness.fitnessindicator;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.itfitness.fitnessindicator.adapter.FitnessFragmentPagerAdapter;
import com.itfitness.fitnessindicator.fragment.FitnessFragment;
import com.itfitness.fitnessindicator.widget.FitnessIndicator;
import com.itfitness.fitnessindicator.widget.FitnessIndicatorItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private FitnessIndicator fid;
    private ViewPager vp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fid = (FitnessIndicator) findViewById(R.id.fid);
        vp = (ViewPager) findViewById(R.id.vp);
        initDatas();
    }

    private void initDatas() {
        ArrayList<String> datas = new ArrayList<>();
        ArrayList<Fragment> fragments = new ArrayList<>();
        for(int i = 0 ; i < 10 ; i ++){
            datas.add("类目"+i);
            fragments.add(new FitnessFragment());
        }
        fid.setDatas(datas, FitnessIndicatorItem.class);
        vp.setAdapter(new FitnessFragmentPagerAdapter(getSupportFragmentManager(),fragments));
        fid.bindViewPager(vp);
    }
}
