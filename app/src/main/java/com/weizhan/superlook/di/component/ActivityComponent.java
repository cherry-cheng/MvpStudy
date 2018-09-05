package com.weizhan.superlook.di.component;

import com.weizhan.superlook.di.module.ActivityModule;
import com.weizhan.superlook.di.module.PageModule;
import com.weizhan.superlook.di.scope.PerActivity;
import com.weizhan.superlook.ui.main.MainActivity;
import com.weizhan.superlook.ui.test.activity.ScrollGradientActivity;
import com.weizhan.superlook.ui.test.activity.StatusWithPictureActivity;

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
