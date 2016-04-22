/*
 * Copyright 2016 Hippo Seven
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.destin.sehaikun;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Interpolator;

public class AnimationUtils {

    public static final Interpolator FAST_SLOW_INTERPOLATOR = new LinearOutSlowInInterpolator();
    public static final Interpolator SLOW_FAST_INTERPOLATOR = new FastOutLinearInInterpolator();
    public static final Interpolator SLOW_FAST_SLOW_INTERPOLATOR = new FastOutSlowInInterpolator();

    public static final int ANIMATION_DURATION_SHORT = 150;
    public static final int ANIMATION_DURATION_MEDIUM = 400;
    public static final int ANIMATION_DURATION_LONG = 800;


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void reveal(View view, int centerX, int centerY, float startRadius, float endRadius, Animator.AnimatorListener listener) {
        Animator animator = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius);
        animator.setDuration(ANIMATION_DURATION_MEDIUM);
        if (listener != null) {
            animator.addListener(listener);
        }
        animator.start();
    }

}