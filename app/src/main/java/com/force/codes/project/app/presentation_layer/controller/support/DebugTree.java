/*
 * Created by Force Porquillo on 6/20/20 4:10 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/20/20 4:10 PM
 */

package com.force.codes.project.app.presentation_layer.controller.support;

import android.app.Application;
import android.util.Log;

import com.force.codes.project.app.BuildConfig;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import timber.log.Timber;

public class DebugTree extends Application{

    @Override
    public void onCreate(){
        super.onCreate();

        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }else{
            Timber.plant(new CrashReportingTree());
        }
    }

    private static class CrashReportingTree extends Timber.Tree{

        @Override
        protected void log(int priority, @Nullable String tag, @NotNull String message, @Nullable Throwable t){
            if(priority == Log.VERBOSE || priority == Log.DEBUG){
                return;
            }

            FakeCrashLibrary.log(priority, tag, message);

            if(t != null){
                if(priority == Log.ERROR){
                    FakeCrashLibrary.logError(t);
                }
            }else if(priority == Log.WARN){
                FakeCrashLibrary.logWarning(t);
            }
        }
    }
}
