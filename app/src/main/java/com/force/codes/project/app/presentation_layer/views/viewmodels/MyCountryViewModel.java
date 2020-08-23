/*
 * Created by Force Porquillo on 8/1/20 6:16 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 8/1/20 6:16 PM
 */

package com.force.codes.project.app.presentation_layer.views.viewmodels;

import androidx.lifecycle.MutableLiveData;
import com.force.codes.project.app.data_layer.model.country.CountryDetails;
import com.force.codes.project.app.data_layer.repositories.interfaces.MyCountryRepository;
import com.force.codes.project.app.presentation_layer.views.base.BaseViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;
import timber.log.Timber;

public class MyCountryViewModel extends BaseViewModel {
  private final MyCountryRepository repository;
  private final MutableLiveData<CountryDetails> liveData;
  private final MutableLiveData<String> stringLiveData;

  @Inject
  public MyCountryViewModel(MyCountryRepository repository) {
    this.repository = repository;
    liveData = new MutableLiveData<>();
    stringLiveData = new MutableLiveData<>();
  }

  public MutableLiveData<CountryDetails> getCountryData(String country) {
    Disposable disposable = repository.getCountryDetails(country)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(liveData::setValue, Timber::e);

    addToUnsubscribed(disposable);
    return liveData;
  }

  public MutableLiveData<String> getStringLiveData() {
    return stringLiveData;
  }

  public MutableLiveData<CountryDetails> getLiveData() {
    return liveData;
  }

  public MutableLiveData<String> getPrimarySelected() {
    Disposable disposable = repository.getCountry()
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(stringLiveData::setValue, Timber::e);

    addToUnsubscribed(disposable);
    return stringLiveData;
  }
}
