package com.bilibili.di.component;

import com.bilibili.di.module.ActivityModule;
import com.bilibili.di.module.PageModule;
import com.bilibili.di.scope.PerActivity;
import com.bilibili.ui.main.MainActivity;
import com.bilibili.ui.test.activity.ScrollGradientActivity;
import com.bilibili.ui.test.activity.StatusWithPictureActivity;

import dagger.Component;

/**
 * Created by jiayiyang on 17/3/23.
 */

@Component(dependencies = {ApiComponent.class}, modules = {ActivityModule.class, PageModule.class})
@PerActivity
public interface ActivityComponent {
    //Bilibili
    void inject(MainActivity mainActivity);

    //Test
    void inject(StatusWithPictureActivity statusWithPictureActivity);
    void inject(ScrollGradientActivity scrollGradientActivity);

}
