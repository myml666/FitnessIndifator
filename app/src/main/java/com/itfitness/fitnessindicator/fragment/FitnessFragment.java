package com.itfitness.fitnessindicator.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itfitness.fitnessindicator.R;

/**
 * @ProjectName: FitnessIndifator
 * @Package: com.itfitness.fitnessindicator.fragment
 * @ClassName: FitnessFragment
 * @Description: java类作用描述
 * @Author: 作者名 itfitness
 * @CreateDate: 2020/5/9 17:59
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/5/9 17:59
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class FitnessFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_fitness, container, false);
        return inflate;
    }
}
