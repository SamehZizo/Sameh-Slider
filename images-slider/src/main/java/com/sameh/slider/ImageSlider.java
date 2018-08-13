package com.sameh.slider;

import android.app.Activity;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.sameh.slider.adapters.ViewPagerAdapter;

public class ImageSlider {

    private LinearLayout sliderDotsPanel;
    private int dotsCount;
    private ImageView[] dots;
    private Activity mContext;
    private int time;

    public ImageSlider(Activity context, ViewPagerAdapter viewPagerAdapter, LinearLayout sliderDotsPanel,int time){
        this.dotsCount = viewPagerAdapter.getCount();
        this.dots = new ImageView[dotsCount];
        this.mContext = context;
        this.sliderDotsPanel = sliderDotsPanel;
        this.time = time;
    }

    public void Go(final ViewPager viewPager){

        final Handler handler = new Handler();

        for(int i = 0; i < dotsCount; i++){

            dots[i] = new ImageView(mContext);
            dots[i].setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotsPanel.addView(dots[i], params);
            //sliderDotsPanel.setVisibility(visibility);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i = 0; i< dotsCount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.nonactive_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager.setCurrentItem(0, true);

        new Thread() {
            @Override
            public void run() {
                while (true){
                    try {
                        mContext.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int value = viewPager.getCurrentItem();
                                if (value < dotsCount -1 ){
                                    value++;
                                    viewPager.setCurrentItem(value, true);
                                }
                                else {
                                    value = 0;
                                    viewPager.setCurrentItem(value, true);
                                }

                            }
                        });

                        Thread.sleep(time);
                    }catch (Exception e){

                    }

                }
            }
        }.start();

    }

}
