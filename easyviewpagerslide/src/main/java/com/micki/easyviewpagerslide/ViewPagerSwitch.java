package com.micki.easyviewpagerslide;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
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

    private static final String TAG = ViewPagerSwitch.class.getSimpleName();
    private Context context;

    private ViewPager viewPager;
    private TextView[] tabs;
    // child view
    private List<View> views;

    private ImageView cursor;

    // offset
    private int offset = 0;
    // cursor page
    private int cursorIndex = 0;

    private int bitmapWidth;
    private int viewWidth;
    private int screenWidth;
    private int unSelectedColor = 0;
    private int selectedColor = 0;


    private ViewPagerSwitch() {

    }

    public static ViewPagerSwitch getInstance() {
        return new ViewPagerSwitch();
    }

    public ViewPagerSwitch init(Context context) {
        this.context = context;
        return this;
    }

    public ViewPagerSwitch setUnSelectedColor(int unSelectedColor) {
        this.unSelectedColor = unSelectedColor;
        return this;
    }

    public ViewPagerSwitch setSelectedColor(int selectedColor) {
        this.selectedColor = selectedColor;
        return this;
    }

    public ViewPagerSwitch addViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
        return this;
    }

    public ViewPagerSwitch addTabs(TextView[] tabs) {
        this.tabs = tabs;
        return this;
    }

    public ViewPagerSwitch addChildViews(List<View> views) {
        this.views = views;
        return this;
    }

    public ViewPagerSwitch addCursor(ImageView cursor) {
        this.cursor = cursor;
        return this;
    }

    public void build() {
        initView();
    }

    private void initView() {
        this.screenWidth = getScreenWidth();
        check();
    }

    private void check() {
        if (viewPager == null) {
            Log.e(TAG, "未添加viewPager");
            return;
        }
        if (tabs == null || tabs.length <= 0) {
            Log.e(TAG, "未添加tabs");
            return;
        }
        if (views == null || views.size() <= 0) {
            Log.e(TAG, "未添加childViews");
            return;
        }
        initViewPager();
    }

    private int getScreenWidth() {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    private void initViewPager() {
        viewWidth = screenWidth / tabs.length;
        getCursorWidth();
        // viewPage add listener
        viewPager.addOnPageChangeListener(new PageChangeListener());

        for (int i = 0; i < tabs.length; i++) {
            tabs[i].setOnClickListener(new BannerOnClickLister(i));
        }

        viewPager.setAdapter(new ViewPagerAdapter(views));
        // default open first view
        viewPager.setCurrentItem(0);
        changeTextColor(0);
    }

    private void getCursorWidth() {
        if (cursor == null) {
            return;
        }
        ViewTreeObserver vto = cursor.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                cursor.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                bitmapWidth = cursor.getWidth();
                initImageViewCursor();
            }
        });
    }

    // init cursor
    private void initImageViewCursor() {
        // 计算偏移量
        offset = (viewWidth - bitmapWidth) / 2;
        cursor.setPadding(offset, 0, 0, 0);
    }

    /**
     * page change listener
     */
    private class PageChangeListener implements ViewPager.OnPageChangeListener {

        int first = viewWidth;
        int second = first * 2;
        int third = first * 3;
        int four = first * 4;

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
                    } else if (cursorIndex == 3) {
                        animation = new TranslateAnimation(third, 0, 0, 0);
                    } else if (cursorIndex == 4) {
                        animation = new TranslateAnimation(four, 0, 0, 0);
                    }
                    break;
                case 1:

                    if (cursorIndex == 0) {
                        animation = new TranslateAnimation(offset, first, 0, 0);
                    } else if (cursorIndex == 2) {
                        animation = new TranslateAnimation(second, first, 0, 0);
                    } else if (cursorIndex == 3) {
                        animation = new TranslateAnimation(third, first, 0, 0);
                    } else if (cursorIndex == 4) {
                        animation = new TranslateAnimation(four, first, 0, 0);
                    }
                    break;
                case 2:

                    if (cursorIndex == 0) {
                        animation = new TranslateAnimation(offset, second, 0, 0);
                    } else if (cursorIndex == 1) {
                        animation = new TranslateAnimation(first, second, 0, 0);
                    } else if (cursorIndex == 3) {
                        animation = new TranslateAnimation(third, second, 0, 0);
                    } else if (cursorIndex == 4) {
                        animation = new TranslateAnimation(four, second, 0, 0);
                    }
                    break;
                case 3:

                    if (cursorIndex == 0) {
                        animation = new TranslateAnimation(0, third, 0, 0);
                    } else if (cursorIndex == 1) {
                        animation = new TranslateAnimation(first, third, 0, 0);
                    } else if (cursorIndex == 2) {
                        animation = new TranslateAnimation(second, third, 0, 0);
                    } else if (cursorIndex == 4) {
                        animation = new TranslateAnimation(four, third, 0, 0);
                    }
                    break;
                case 4:

                    if (cursorIndex == 0) {
                        animation = new TranslateAnimation(0, four, 0, 0);
                    } else if (cursorIndex == 1) {
                        animation = new TranslateAnimation(first, four, 0, 0);
                    } else if (cursorIndex == 2) {
                        animation = new TranslateAnimation(second, four, 0, 0);
                    } else if (cursorIndex == 3) {
                        animation = new TranslateAnimation(third, four, 0, 0);
                    }
                    break;
            }
            cursorIndex = position;
            animation.setFillEnabled(true);
            animation.setFillAfter(true);
            animation.setDuration(300);
            if (cursor != null)
                cursor.startAnimation(animation);
            changeTextColor(cursorIndex);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    /**
     * page click
     */
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

    /**
     * view pager adapter
     */
    private class ViewPagerAdapter extends PagerAdapter {

        private List<View> views;

        public ViewPagerAdapter(List<View> views) {
            this.views = views;
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((ViewPager) container).addView(views.get(position));
            return views.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView(views.get(position));
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    /**
     * @param currentPage selected page
     */
    private void changeTextColor(int currentPage) {
        int count = tabs.length;
        for (int index = 0; index < count; index++) {
            if (index == currentPage) {
                if (selectedColor != 0)
                    tabs[index].setTextColor(ContextCompat.getColor(context, selectedColor));
                else
                    tabs[index].setTextColor(ContextCompat.getColor(context, R.color.selected_color));

            } else {
                if (unSelectedColor != 0)
                    tabs[index].setTextColor(ContextCompat.getColor(context, unSelectedColor));
                else
                    tabs[index].setTextColor(ContextCompat.getColor(context, R.color.unselected_color));
            }
        }
    }
}
