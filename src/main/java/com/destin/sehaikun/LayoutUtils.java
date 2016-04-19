/*
 * Copyright 2015 Hippo Seven
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
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.view.View;
import android.view.ViewAnimationUtils;

public class LayoutUtils {

    /**
     * dp conversion to pix
     *
     * @param context The context
     * @param dp      The value you want to conversion
     * @return value in pix
     */
    public static int dp2pix(Context context, float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density + 0.5f);
    }

    /**
     * pix conversion to dp
     *
     * @param context The context
     * @param pix     The value you want to conversion
     * @return value in dp
     */
    public static float pix2dp(Context context, int pix) {
        return pix / context.getResources().getDisplayMetrics().density;
    }

    /**
     * sp conversion to pix
     *
     * @param sp The value you want to conversion
     * @return value in pix
     */
    public static int sp2pix(Context context, float sp) {
        return (int) (sp * context.getResources().getDisplayMetrics().scaledDensity + 0.5f);
    }

    /**
     * pix conversion to sp
     *
     * @param pix The value you want to conversion
     * @return value in sp
     */
    public static float pix2sp(Context context, float pix) {
        return pix / context.getResources().getDisplayMetrics().scaledDensity;
    }

    /**
     * 获取状态栏高度
     *
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        java.lang.reflect.Field field = null;
        int x = 0;
        int statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
            return statusBarHeight;
        } catch (Exception e) {
            e.printStackTrace();
            statusBarHeight = LayoutUtils.dp2pix(context, 6);//maybe
        }
        return statusBarHeight;
    }

//    /**
//     * Is this device is table.
//     * For sw600dp is true
//     *
//     * @param context the context
//     * @return true for table
//     */
//    public static boolean isTable(Context context) {
//        return context.getResources().getBoolean(R.bool.is_table);
//    }

    /**
     * CircularReveal animator beta
     *
     * @param view
     */
    public static void showReveal(final View view) {
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            int radius = Math.max(view.getWidth() / 2, view.getHeight() / 2);
            ViewAnimationUtils.createCircularReveal(view, view.getWidth() / 2, view.getHeight() / 2, 0, radius).setDuration(250).start();
        } else {
            view.setScaleX(0);
            view.setScaleY(0);
            view.animate().scaleX(1).scaleY(1);
        }
    }


    public static void hideReveal(final View view) {
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            int radius = Math.max(view.getWidth() / 2, view.getHeight() / 2);
            ViewAnimationUtils.createCircularReveal(view, view.getWidth() / 2, view.getHeight() / 2, radius, 0).setDuration(250).start();
        } else {
            view.animate().scaleX(0).scaleY(0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    view.setScaleX(1);
                    view.setScaleY(1);
                }

            });

        }
    }
}