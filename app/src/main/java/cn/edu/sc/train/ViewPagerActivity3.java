package cn.edu.sc.train;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;


import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;

import java.util.ArrayList;

public class ViewPagerActivity3 extends AppCompatActivity {
    private ViewPager viewPager;
    private Button btnFirst,btnSecond;
    private ViewPagerFragmentAdapter adapter;
    private ArrayList arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager3);
        viewPager=findViewById(R.id.viewPager);
        btnFirst=findViewById(R.id.btn1);
        btnSecond=findViewById(R.id.btn2);
        arrayList=new ArrayList<Fragment>();
        FirstFragment rightFragment=new FirstFragment();
        SecondFragment leftFragment=new SecondFragment();
        arrayList.add(rightFragment);
        arrayList.add(leftFragment);
        adapter=new ViewPagerFragmentAdapter(getSupportFragmentManager(),arrayList);
        viewPager.setAdapter(adapter);
        btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0,true);

            }
        });
        btnSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1,true);
            }
        });
    }
}