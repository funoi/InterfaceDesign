package com.funoi.mapp2;


import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

    /**
     * 要在此演示中显示的页数（向导步骤）。
     */
    private static final int NUM_PAGES = 5;

    /**
     * pager小部件，它处理动画并允许水平滑动以访问向导的前一步和下一步。
     */
    private ViewPager2 viewPager;

    /**
     * pager适配器，它将页面提供给视图pager小部件。
     */
    private FragmentStateAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //给计数器按钮绑定监听事件
        Button counterButton = findViewById(R.id.counter);
        counterListener(counterButton);
        counterLongListener(counterButton);

        //给选择颜色按钮绑定监听事件
        Button colorButton1 = findViewById(R.id.button1);
        Button colorButton2 = findViewById(R.id.button2);
        Button colorButton3 = findViewById(R.id.button3);
        colorButton1.setOnClickListener(colorButtonListener);
        colorButton2.setOnClickListener(colorButtonListener);
        colorButton3.setOnClickListener(colorButtonListener);

        //给viewSwitcher按钮绑定监听事件
        Button viewSwitcherButton1 = findViewById(R.id.prev);
        Button viewSwitcherButton2 = findViewById(R.id.next);
        viewSwitcherListener(viewSwitcherButton1);
        viewSwitcherListener(viewSwitcherButton2);

        //设置图片
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new ScreenSlidePageFragment(R.layout.fragment_screen_slide_page));
        fragments.add(new ScreenSlidePageFragment(R.layout.fragment_screen_slide_page2));
        fragments.add(new ScreenSlidePageFragment(R.layout.fragment_screen_slide_page3));
        fragments.add(new ScreenSlidePageFragment(R.layout.fragment_screen_slide_page4));
        fragments.add(new ScreenSlidePageFragment(R.layout.fragment_screen_slide_page5));

        //实例化ViewPager2和PagerAdapter。
        viewPager = findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(this, fragments);
        viewPager.setAdapter(pagerAdapter);
    }


    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // 如果用户当前正在查看第一步，允许系统处理“后退”按钮。这将对该活动调用finish（）并弹出后堆栈。
            super.onBackPressed();
        } else {
            // 否则，选择上一步。
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    /**
     * 一个简单的pager适配器，按顺序表示5个ScreenSlidePageFragment对象。
     */
    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {

        private List<Fragment> list;

        public ScreenSlidePagerAdapter(FragmentActivity fa, List<Fragment> fragments) {
            super(fa);
            this.list = fragments;
        }

        @Override
        public Fragment createFragment(int position) {
            return list.get(position);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }


    //viewSwitcher按钮监听事件
    private void viewSwitcherListener(Button button) {
        button.setOnClickListener(view -> {
            final ViewSwitcher viewSwitcher = findViewById(R.id.viewSwitcher);
            Animation slide_in_left = AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.slide_in_left);
            Animation slide_out_right = AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.slide_out_right);

            viewSwitcher.setInAnimation(slide_in_left);
            viewSwitcher.setOutAnimation(slide_out_right);

            if (view.getId() == R.id.prev) {
                viewSwitcher.showPrevious();
            } else if (view.getId() == R.id.next) {
                viewSwitcher.showNext();
            }

        });
    }




    //计数器长按事件
    private void counterLongListener(Button counterLongButton) {
        counterLongButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                TextView showCounter = findViewById(R.id.showCounter);
                int i = parseInt(showCounter.getText().toString());
                i += 10;
                showCounter.setText(Integer.toString(i));

                //设置进度条
                ProgressBar progressBar = findViewById(R.id.counterBar);
                progressBar.setProgress(i);

                return true;
            }
        });
    }

    //计数器监听事件
    private void counterListener(Button counterButton) {
        counterButton.setOnClickListener(view -> {
            TextView showCounter = findViewById(R.id.showCounter);
            int i = parseInt(showCounter.getText().toString());
            i += 1;
            showCounter.setText(Integer.toString(i));

            //设置进度条
            ProgressBar progressBar = findViewById(R.id.counterBar);
            progressBar.setProgress(i);
        });
    }


    //选择颜色按钮监听事件
    private final View.OnClickListener colorButtonListener = view -> {
        FrameLayout frameLayout = findViewById(R.id.frame1);
        TextView color1 = findViewById(R.id.color01);
        TextView color2 = findViewById(R.id.color02);
        TextView color3 = findViewById(R.id.color03);
        switch (view.getId()) {
            case R.id.button1:
                frameLayout.removeView(color1);
                frameLayout.addView(color1);
                break;
            case R.id.button2:
                frameLayout.removeView(color2);
                frameLayout.addView(color2);
                break;
            case R.id.button3:
                frameLayout.removeView(color3);
                frameLayout.addView(color3);
                break;
        }
    };
}