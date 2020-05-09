package com.itfitness.fitnessindicator.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.itfitness.fitnessindicator.R;

/**
 * @ProjectName: FitnessIndifator
 * @Package: com.itfitness.fitnessindicator.widget
 * @ClassName: FitnessIndicatorItem
 * @Description: java类作用描述
 * @Author: 作者名 itfitness
 * @CreateDate: 2020/5/9 16:38
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/5/9 16:38
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class FitnessIndicatorItem extends FrameLayout implements FitnessIndicator.ItemViewImpl{
    private TextView itemIndicatorTv;
    public FitnessIndicatorItem(@NonNull Context context) {
        this(context,null);
    }

    public FitnessIndicatorItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FitnessIndicatorItem(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context,R.layout.item_indicator,this);
        initView();
    }

    private void initView() {
        itemIndicatorTv = (TextView) findViewById(R.id.item_indicator_tv);
    }

    @Override
    public void setSelect(boolean isSelect) {
        itemIndicatorTv.setTextColor(isSelect? Color.GREEN:Color.BLACK);
    }

    @Override
    public void setItemText(String text) {
        itemIndicatorTv.setText(text);
    }
}
