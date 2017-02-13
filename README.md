
#easyViewPagerSlide

#Compile

     compile 'com.micki.easyViewPagerSlide:1.1.0'

#Usage

    `ViewPagerSwitch
        .getInstance() // must first
        .init(context)
        .addViewPager(viewPager)
        .addTitles(textViews)
        .addChildViews(listViews)
        .build(); // must last`

#In XML

         `<LinearLayout
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

         <android.support.v4.view.ViewPager
             android:id="@+id/viewPager"
             android:layout_width="wrap_content"
             android:layout_height="wrap_content"
             android:layout_below="@+id/linearLayout_banner"
             android:flipInterval="30"
             android:persistentDrawingCache="animation">

         </android.support.v4.view.ViewPager>`

#In Activity or Fragment

         `TextView textView1 = (TextView) findViewById(R.id.tv_first);
          TextView textView2 = (TextView) findViewById(R.id.tv_second);
          TextView textView3 = (TextView) findViewById(R.id.tv_third);

          TextView[] textViews = {textView1, textView2, textView3};//titles

          // child views
          View view1 = View.inflate(this, R.layout.view_1, null);
          View view2 = View.inflate(this, R.layout.view_2, null);
          View view3 = View.inflate(this, R.layout.view_3, null);

          List<View> views = new ArrayList<>();
          views.add(view1);
          views.add(view2);
          views.add(view3);

          ViewPagerSwitch
                   .getInstance() // must first
                   .init(context)
                   .addViewPager(viewPager)
                   .addTitles(textViews)
                   .addChildViews(listViews)
                   .build(); // must last`

