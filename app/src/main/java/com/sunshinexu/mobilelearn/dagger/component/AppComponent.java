/*
 *     (C) Copyright 2019, ForgetSky.
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package com.sunshinexu.mobilelearn.dagger.component;

import com.sunshinexu.mobilelearn.app.MobileLearnApp;
import com.sunshinexu.mobilelearn.dagger.module.AllActivityModule;
import com.sunshinexu.mobilelearn.dagger.module.AppModule;
import com.sunshinexu.mobilelearn.dagger.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,       //第一步添加类，解决每个类需要inject(this),代码重复问题
        AllActivityModule.class,
        AppModule.class,
        HttpModule.class})
public interface AppComponent {
    void inject(MobileLearnApp mobileLearnApp);
}
