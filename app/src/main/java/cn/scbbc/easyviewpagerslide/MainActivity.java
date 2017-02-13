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
        ImageView imageView = (ImageView) findViewById(R.id.imageView_cursor);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);

        TextView[] textViews = {textView1, textView2, textView3};

        View allOrderView = View.inflate(this, R.layout.view_1, null);
        View payView = View.inflate(this, R.layout.view_2, null);
        View serviceView = View.inflate(this, R.layout.view_3, null);

        List<View> listViews = new ArrayList<>();
        listViews.add(allOrderView);
        listViews.add(payView);
        listViews.add(serviceView);

        ViewPagerSwitch
                .getInstance()
                .init(this)
                .addViewPager(viewPager)
                .addTitles(textViews)
                .addChildViews(listViews)
                .build();

    }

}
