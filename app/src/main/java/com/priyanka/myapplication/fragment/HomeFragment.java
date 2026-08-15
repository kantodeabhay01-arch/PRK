package com.priyanka.myapplication.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.AnimationTypes;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.priyanka.myapplication.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    ImageSlider imageSlider;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        imageSlider = view.findViewById(R.id.isHomeFragmentImageSlider);

        ArrayList<SlideModel> slideModelArrayList = new ArrayList<>();

        slideModelArrayList.add(new SlideModel(R.drawable.eduvoice,"EduVoice India", ScaleTypes.CENTER_CROP));
        slideModelArrayList.add(new SlideModel(R.drawable.eduvoice_1, ScaleTypes.CENTER_CROP));
        slideModelArrayList.add(new SlideModel(R.drawable.eduvoice_2, ScaleTypes.CENTER_CROP));
        slideModelArrayList.add(new SlideModel(R.drawable.eduvoice_3, ScaleTypes.CENTER_CROP));
        slideModelArrayList.add(new SlideModel(R.drawable.eduvoice_4, ScaleTypes.CENTER_CROP));
        slideModelArrayList.add(new SlideModel(R.drawable.eduvoice_5,"STUDENT'S", ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(slideModelArrayList);
        imageSlider.setSlideAnimation(AnimationTypes.CUBE_IN);
        return view;
    }
}