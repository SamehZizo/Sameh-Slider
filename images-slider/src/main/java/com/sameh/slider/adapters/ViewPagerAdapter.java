package com.sameh.slider.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.sameh.slider.R;
import java.util.ArrayList;

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private Integer [] images;
    private String [] stringImages;
    private ArrayList<String> arrayList;

    public ViewPagerAdapter(Context context , String [] stringImages){
        this.context = context;
        this.stringImages = stringImages;
        this.images = new Integer[1];
        this.images[0] = 0;
        this.arrayList = new ArrayList<>();
        this.arrayList.add("no_data");
    }

    public ViewPagerAdapter(Context context,ArrayList<String> arrayList){
        this.context = context;
        this.arrayList = arrayList;
        this.stringImages = new String[1];
        this.stringImages[0] = "no_data";
        this.images = new Integer[1];
        this.images[0] = 0;
    }

    public ViewPagerAdapter(Context context,Integer [] images) {
        this.context = context;
        this.images = images;
        this.stringImages = new String[1];
        this.stringImages[0] = "no_data";
        this.arrayList = new ArrayList<>();
        this.arrayList.add("no_data");
    }

    @Override
    public int getCount() {
        if (!stringImages[0].equals("no_data")){
            return stringImages.length;
        }
        else if (images[0] != 0){
            return images.length;
        }
        else {
            return arrayList.size();
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custem_view_pager, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);

        if (!stringImages[0].equals("no_data")){
            Glide.with(context).load(stringImages[position]).into(imageView);
        }
        else if (images[0] != 0){
            Glide.with(context).load(images[position]).into(imageView);
        }
        else {
            Glide.with(context).load(arrayList.get(position)).into(imageView);
        }

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }
}