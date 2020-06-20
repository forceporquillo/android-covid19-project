package com.force.codes.project.app.presentation_layer.views.fragments.mapview;

/*
 * Created by Force Porquillo on 6/2/20 12:50 PM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/2/20 6:24 AM
 *
 */

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.force.codes.project.app.R;
import com.force.codes.project.app.presentation_layer.views.fragments.BaseFragment;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;


public class MapFragment extends BaseFragment implements OnMapReadyCallback{

    @BindView(R.id.map_view)
    MapView mapView;

    private Unbinder unbinder;
    private View view;

    public MapFragment(){
        // Required empty public constructor
    }

    public static MapFragment newInstance(){
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_map, container, false);
        unbinder = ButterKnife.bind(this, view);

        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        try{
            MapsInitializer.initialize(view.getContext());
        } catch(Exception e){
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap){
        try{
            googleMap.setMapStyle(MapStyleOptions
                    .loadRawResourceStyle(view.getContext(), R.raw.map_style_milk));
        } catch(Resources.NotFoundException e){
            e.printStackTrace();
        }

        googleMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        mapView.onDestroy();

        if(mapView != null){
            mapView = null;
        }

        unbinder.unbind();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    @Override
    public void onStop(){
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory(){
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onStart(){
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onPause(){
        super.onPause();
        mapView.onPause();
    }
}
