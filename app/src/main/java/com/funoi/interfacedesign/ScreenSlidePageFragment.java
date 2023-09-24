package com.funoi.interfacedesign;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;


public class ScreenSlidePageFragment extends Fragment {
    private int image;

    public ScreenSlidePageFragment(){this.image=R.layout.fragment_screen_slide_page;}
    public ScreenSlidePageFragment(int image){
        this.image=image;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (ViewGroup) inflater.inflate(
                this.image, container, false);
    }
}
