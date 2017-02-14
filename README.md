
#easyViewPagerSlide

![](https://github.com/micki-zhou/easyViewPagerSlide/blob/master/easyViewPager.gif)

#Compile

```groovy
compile 'me.micki:easyViewPagerSlide:1.3.0'
```

#Usage
```java
ViewPagerSwitch
    .getInstance() // must first
    .init(this)
    .addViewPager(viewPager) // add viewPager 添加viewPager
    .addTabs(tabs) // add tabs 添加标签
    .addChildViews(views) // add childViews 添加子页面
    .addCursor(cursor) // add cursor 添加游标
    .setSelectedColor(R.color.colorAccent) // setting seleted tab color 设置选中tab时的颜色
    .setUnSelectedColor(R.color.colorPrimary) // setting unSeleted tab color 设置未选中tab时的颜色
    .build(); // must last
```
#In XML
```xml
<LinearLayout
     android:id="@+id/linearLayout_banner"
     android:layout_width="match_parent"
     android:layout_height="wrap_content">

         <TextView
             android:id="@+id/tv_first"
             android:layout_width="match_parent"
             android:layout_height="match_parent"
             android:layout_weight="1.0"
             android:gravity="center"
             android:paddingBottom="10dp"
             android:paddingTop="10dp"
             android:text="first"
             android:textColor="@color/selected_color"
             android:textSize="15sp" />

         <TextView
             android:id="@+id/tv_second"
             android:layout_width="match_parent"
             android:layout_height="match_parent"
             android:layout_weight="1.0"
             android:gravity="center"
             android:text="second"
             android:textColor="@color/default_color"
             android:textSize="15sp" />


         <TextView
             android:id="@+id/tv_third"
             android:layout_width="match_parent"
             android:layout_height="match_parent"
             android:layout_weight="1.0"
             android:gravity="center"
             android:text="third"
             android:textColor="@color/default_color"
             android:textSize="15sp" />
</LinearLayout>

<ImageView
        android:id="@+id/imageView_cursor"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@+id/linearLayout_banner"
        android:scaleType="matrix"
        android:src="@drawable/img_cursor" />

<android.support.v4.view.ViewPager
     android:id="@+id/viewPager"
     android:layout_width="wrap_content"
     android:layout_height="wrap_content"
     android:layout_below="@+id/imageView_cursor"
     android:flipInterval="30"
     android:persistentDrawingCache="animation">

</android.support.v4.view.ViewPager>
```
#In Activity or Fragment
```java
TextView textView1 = (TextView) findViewById(R.id.tv_first);
TextView textView2 = (TextView) findViewById(R.id.tv_second);
TextView textView3 = (TextView) findViewById(R.id.tv_third);

// tabs
TextView[] tabs = {textView1, textView2, textView3};

// child views
View view1 = View.inflate(this, R.layout.view_1, null);
View view2 = View.inflate(this, R.layout.view_2, null);
View view3 = View.inflate(this, R.layout.view_3, null);

List<View> views = new ArrayList<>();
    views.add(view1);
    views.add(view2);
    views.add(view3);

ImageView cursor = (ImageView) findViewById(R.id.imageView_cursor);

ViewPagerSwitch
    .getInstance() // must first
    .init(this)
    .addViewPager(viewPager)
    .addTabs(tabs)
    .addChildViews(views)
    .addCursor(cursor)
    .setSelectedColor(R.color.colorAccent)
    .setUnSelectedColor(R.color.colorPrimary)
    .build(); // must last
```
