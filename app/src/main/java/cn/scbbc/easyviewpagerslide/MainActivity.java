package cn.scbbc.easyviewpagerslide;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.micki.easyviewpagerslide.ViewPagerSwitch;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView1 = (TextView) findViewById(R.id.tv_first);
        TextView textView2 = (TextView) findViewById(R.id.tv_second);
        TextView textView3 = (TextView) findViewById(R.id.tv_third);
        TextView textView4 = (TextView) findViewById(R.id.tv_four);
        TextView textView5 = (TextView) findViewById(R.id.tv_five);


        ImageView imageView = (ImageView) findViewById(R.id.imageView_cursor);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);

        TextView[] textViews = {textView1, textView2, textView3, textView4, textView5};

        View view1 = View.inflate(this, R.layout.view_1, null);
        View view2 = View.inflate(this, R.layout.view_2, null);
        View view3 = View.inflate(this, R.layout.view_3, null);
        View view4 = View.inflate(this, R.layout.view_4, null);
        View view5 = View.inflate(this, R.layout.view_5, null);

        List<View> listViews = new ArrayList<>();
        listViews.add(view1);
        listViews.add(view2);
        listViews.add(view3);
        listViews.add(view4);
        listViews.add(view5);

        ViewPagerSwitch
                .getInstance()
                .init(this)
                .addViewPager(viewPager)
                .addTabs(textViews)
                .addChildViews(listViews)
                .setSelectedColor(R.color.colorAccent1)
                .setUnSelectedColor(R.color.colorAccent2)
                .addCursor(imageView)
                .build();

    }

}
