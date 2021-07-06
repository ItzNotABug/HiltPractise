package com.lazygeniouz.hiltpractise.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * [BaseApp] class that extends [Application].
 *
 * Required for Android **Hilt**,
 * therefore  the class is annotated with @[HiltAndroidApp]
 */

@HiltAndroidApp
class BaseApp : Application()