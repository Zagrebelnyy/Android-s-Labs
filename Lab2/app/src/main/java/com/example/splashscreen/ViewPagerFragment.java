package com.example.splashscreen;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class ViewPagerFragment extends Fragment {
    private Context context;
    private List<ListItem> listItems;
    private ViewPager2 viewPager2;
    int position;
    public ViewPagerFragment(){

    }
    public ViewPagerFragment(Context context, List<ListItem> listItems, int position) {
        this.position = position;
        this.context = context;
        this.listItems = listItems;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);
        viewPager2 = (ViewPager2) view.findViewById(R.id.view_pager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(context, listItems);
        viewPager2.postDelayed(new Runnable() {
            @Override
            public void run() {
                viewPager2.setCurrentItem(position);
            }
        }, 100);
        viewPager2.setAdapter(viewPagerAdapter);
        return view;
    }
}
