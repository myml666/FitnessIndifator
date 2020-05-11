package com.itfitness.fitnessindicator.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.itfitness.fitnessindicator.R;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * @ProjectName: FitnessIndifator
 * @Package: com.itfitness.fitnessindicator.widget
 * @ClassName: FitnessIndicator
 * @Description: java类作用描述
 * @Author: 作者名 itfitness
 * @CreateDate: 2020/5/9 16:27
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/5/9 16:27
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class FitnessIndicator extends FrameLayout {

    private LinearLayout linearLayout;
    private ImageView imageView;
    private List<String> indicatorDatas;//指示器的文字
    private boolean mIsLayout = false;//是否布局完
    private Class itemViewClazz;
    private HorizontalScrollView horizontalScrollView;
    private ViewPager viewPager;

    public FitnessIndicator(@NonNull Context context) {
        this(context,null);
    }

    public FitnessIndicator(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FitnessIndicator(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setVisibility(INVISIBLE);
        init();

    }
    private void init() {
        horizontalScrollView = new HorizontalScrollView(getContext());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        horizontalScrollView.setLayoutParams(layoutParams);
        horizontalScrollView.setOverScrollMode(OVER_SCROLL_NEVER);
        horizontalScrollView.setHorizontalScrollBarEnabled(false);

        linearLayout = new LinearLayout(getContext());
        HorizontalScrollView.LayoutParams layoutParamsOne = new HorizontalScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(layoutParamsOne);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        imageView = new ImageView(getContext());
        FrameLayout.LayoutParams layoutParamsTwo = new FrameLayout.LayoutParams(ConvertUtils.dp2px(15), ConvertUtils.dp2px(15));
        layoutParamsTwo.gravity = Gravity.BOTTOM;
        imageView.setLayoutParams(layoutParamsTwo);
        imageView.setImageResource(R.mipmap.ic_launcher);
        horizontalScrollView.addView(linearLayout);
        addView(horizontalScrollView);
        addView(imageView);
        post(new Runnable() {
            @Override
            public void run() {
                mIsLayout = true;
                if(indicatorDatas != null && indicatorDatas.size()>0){
                    initDatas2View();
                    updateItemStatus(0);
                }
            }
        });
    }

    /**
     * 绑定ViewPager
     * @param viewPager
     */
    public void bindViewPager(ViewPager viewPager){
        this.viewPager = viewPager;
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LogUtils.eTag("滑动",positionOffset,position);
                if(mIsLayout){
//                    int nextPos = position+1;
//                    if(nextPos<indicatorDatas.size()-1){
//                        View current = linearLayout.getChildAt(position);
//                        View next = linearLayout.getChildAt(nextPos);
//
//                        float v1 = (float) current.getWidth() / 2 + (float)imageView.getMeasuredWidth()/2;
//                        float v = (float) current.getWidth() / 2 + (float) next.getWidth() / 2;
//                        LogUtils.eTag("滑动2",current.getWidth(),next.getWidth());
//                        imageView.setTranslationX(v1+positionOffset*v);
//                    }
                }
            }

            @Override
            public void onPageSelected(int position) {
                updateItemStatus(position);
                int preWidth = 0;//indicator前面的宽度
                for(int i = 0 ; i < position+1 ; i++){
                    View childAt = linearLayout.getChildAt(i);
                    int width = childAt.getWidth();
                    if(i == position){
                        preWidth+=width/2;
                    }else {
                        preWidth+=width;
                    }
                }
                int left = preWidth - imageView.getWidth()/2;
                imageView.setTranslationX(left);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        if(mIsLayout){
            updateItemStatus(0);
        }
    }

    /**
     * 更新Item的状态
     */
    private void updateItemStatus(int selectPos) {
        for(int  i = 0 ; i < linearLayout.getChildCount() ; i++){
            View child = linearLayout.getChildAt(i);
            if(child instanceof ItemViewImpl){
                ItemViewImpl itemImpl = (ItemViewImpl) child;
                if(selectPos == i){
                    itemImpl.setSelect(true);
                }else {
                    itemImpl.setSelect(false);
                }
            }
        }
    }

    private void initDatas2View() {
        linearLayout.removeAllViews();
        for(int i = 0 ; i < indicatorDatas.size() ; i ++ ){
            final View itemView = getClazzView(itemViewClazz);
            if(itemView instanceof ItemViewImpl){
                ItemViewImpl itemImpl = (ItemViewImpl) itemView;
                itemImpl.setItemText(indicatorDatas.get(i));
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            itemView.setPadding(ConvertUtils.dp2px(15),0,ConvertUtils.dp2px(15),0);
//            layoutParams.leftMargin = ConvertUtils.dp2px(15);
//            layoutParams.rightMargin = ConvertUtils.dp2px(15);
            itemView.setLayoutParams(layoutParams);
            if(i == 0){
                itemView.post(new Runnable() {
                    @Override
                    public void run() {
                        float measuredWidth = (float)itemView.getMeasuredWidth();
                        float measuredWidthImg = (float)imageView.getMeasuredWidth();
                        LogUtils.eTag("宽度",measuredWidth);
                        imageView.setTranslationX(measuredWidth/2-measuredWidthImg/2);
                        setVisibility(VISIBLE);
                    }
                });
            }
            linearLayout.addView(itemView);
        }
    }

    /**
     * 设置数据
     * @param datas
     * @param itemViewClazz
     */
    public void setDatas(List<String> datas , Class itemViewClazz){
        this.indicatorDatas = datas;
        this.itemViewClazz = itemViewClazz;
        if(mIsLayout){
            initDatas2View();
        }
    }
    /**
     * 通过反射实例化View
     * @param clazz
     */
    private View getClazzView(Class clazz){
        View itemView = null;
        try {
            Constructor c = clazz.getConstructor(Context.class);//获取有参构造
            itemView = (View) c.newInstance(getContext());    //通过有参构造创建对象
        }catch (Exception e){
        }
        return itemView;
    }
    public interface ItemViewImpl{
        void setSelect(boolean isSelect);
        void setItemText(String text);
    }
}
