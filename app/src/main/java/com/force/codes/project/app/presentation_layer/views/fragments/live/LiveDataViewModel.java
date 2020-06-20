package com.force.codes.project.app.presentation_layer.views.fragments.live;

/*
 * Created by Force Porquillo on 6/3/20 10:47 PM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/3/20 10:47 PM
 *
 */

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.force.codes.project.app.data_layer.repositories.live.LiveOverviewRepository;
import com.force.codes.project.app.data_layer.model.WorldData;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class LiveDataViewModel extends ViewModel{
    public MutableLiveData<WorldData> liveData = new MutableLiveData<>();

    private LiveOverviewRepository repository;

    public LiveDataViewModel(LiveOverviewRepository repository){
        this.repository = repository;
    }

    public LiveData<WorldData> getDataFromNetwork(){
        return LiveDataReactiveStreams.fromPublisher(
                repository.getWorldDataFromNetwork()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext(list -> liveData.setValue(list))
                        .doOnError(Timber::e));

    }
}
