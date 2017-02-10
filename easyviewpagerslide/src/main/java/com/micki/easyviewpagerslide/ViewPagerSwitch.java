package com.micki.easyviewpagerslide;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by micki on 2017/2/9.
 * view pager switch
 */

public class ViewPagerSwitch {
    private Context context;

    private ViewPager viewPager;
    private TextView[] textViews;
    // Tab页面列表
    private List<View> listViews;
    // 动画图片
    private ImageView cursor;

    // 动画图片偏移量
    private int offset = 0;
    // 当前页卡编号
    private int cursorIndex = 0;
    // 动画图片宽度
    private int bitmapWidth;
    private int viewWidth;
    private int screenWidth;



    /**
     * @param context
     * @param viewPager   传入ViewPager
     * @param textViews   头标
     * @param listViews   子页View
     * @param cursor      动画游标
     * @param screenWidth 屏幕宽度
     */
    public ViewPagerSwitch(Context context, ViewPager viewPager, TextView[] textViews, List<View> listViews, ImageView cursor, int screenWidth) {
        this.context = context;
        this.viewPager = viewPager;
        this.cursor = cursor;
        this.textViews = textViews;
        this.listViews = listViews;
        this.screenWidth = screenWidth;
        initViewPager();
    }

    private void initViewPager() {
        viewWidth = screenWidth / textViews.length;
        initImageViewCursor();
        // viewPager滑动事件
        viewPager.addOnPageChangeListener(new PageChangeListener());

        for (int i = 0; i < textViews.length; i++) {
            textViews[i].setOnClickListener(new BannerOnClickLister(i));
        }

        viewPager.setAdapter(new ViewPagerAdapter(listViews));
        // 默认打开第一个页卡
        viewPager.setCurrentItem(0);

    }

    // 初始化动画
    private void initImageViewCursor() {
        // 获取图片宽度
        bitmapWidth = cursor.getMeasuredWidth();
        Log.d("ZMJ","b = "+bitmapWidth);
        // 计算偏移量
        offset = (viewWidth - bitmapWidth) / 2;
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        cursor.setImageMatrix(matrix);
    }

    //  ViewPager点击事件，添加动画
    private class PageChangeListener implements ViewPager.OnPageChangeListener {
        // 三个距离的动画偏移量
        int first = viewWidth;
        int second = first * 2;

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            Animation animation = null;
            switch (position) {
                case 0:

                    if (cursorIndex == 1) {
                        animation = new TranslateAnimation(first, 0, 0, 0);
                    } else if (cursorIndex == 2) {
                        animation = new TranslateAnimation(second, 0, 0, 0);
                    } /*else if (cursorIndex == 3) {
                        animation = new TranslateAnimation(third, 0, 0, 0);
                    }*/
                    break;
                case 1:

                    if (cursorIndex == 0) {
                        animation = new TranslateAnimation(offset, first, 0, 0);
                    } else if (cursorIndex == 2) {
                        animation = new TranslateAnimation(second, first, 0, 0);
                    } /*else if (cursorIndex == 3) {
                        animation = new TranslateAnimation(third, first, 0, 0);
                    }*/
                    break;
                case 2:

                    if (cursorIndex == 0) {
                        animation = new TranslateAnimation(offset, second, 0, 0);
                    } else if (cursorIndex == 1) {
                        animation = new TranslateAnimation(first, second, 0, 0);
                    } /*else if (cursorIndex == 3) {
                        animation = new TranslateAnimation(third, second, 0, 0);
                    }*/
                    break;
                /*case 3:

                    if (cursorIndex == 0) {
                        animation = new TranslateAnimation(0, third, 0, 0);
                    } else if (cursorIndex == 1) {
                        animation = new TranslateAnimation(first, third, 0, 0);
                    } else if (cursorIndex == 2) {
                        animation = new TranslateAnimation(second, third, 0, 0);
                    }
                    break;*/
            }
            cursorIndex = position;
            animation.setFillEnabled(true);
            animation.setFillAfter(true);
            animation.setDuration(300);
            cursor.startAnimation(animation);
            changeTextColor(cursorIndex);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    //  页卡点击事件
    private class BannerOnClickLister implements View.OnClickListener {

        private int index = 0;

        public BannerOnClickLister(int index) {
            this.index = index;
        }

        @Override
        public void onClick(View v) {
            viewPager.setCurrentItem(index);
            changeTextColor(index);
        }
    }

    // viewPager适配器
    private class ViewPagerAdapter extends PagerAdapter {

        private List<View> listViews;

        public ViewPagerAdapter(List<View> listViews) {
            this.listViews = listViews;
        }

        @Override
        public int getCount() {
            return listViews.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((ViewPager) container).addView(listViews.get(position));
            return listViews.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView(listViews.get(position));
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    // 改变字体颜色
    private void changeTextColor(int currentPage) {
        int count = textViews.length;
        for (int index = 0; index < count; index++) {
            if (index == currentPage) {
                textViews[index].setTextColor(ContextCompat.getColor(context, R.color.selected_color));
            } else {
                textViews[index].setTextColor(ContextCompat.getColor(context, R.color.default_color));
            }
        }
    }
}
