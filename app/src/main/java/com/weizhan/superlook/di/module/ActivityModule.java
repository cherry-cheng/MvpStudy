package com.weizhan.superlook.di.module;

import android.content.Context;


import com.weizhan.superlook.di.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jiayiyang on 17/3/25.
 */

@Module
public class ActivityModule {

    private Context context;

    public ActivityModule(Context context){
        this.context = context;
    }

    @Provides
    @PerActivity
    Context provideContext(){
        return context;
    }
}
