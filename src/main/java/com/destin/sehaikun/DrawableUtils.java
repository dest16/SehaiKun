/*
 * Copyright (C) 2015 Hippo Seven
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.destin.sehaikun;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class DrawableUtils {

    public static Bitmap getBitmapFromDrawable(Drawable drawable) {
        if (drawable == null) {
            return null;
        }

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        try {
            Bitmap bitmap;

            if (drawable instanceof ColorDrawable) {
                bitmap = Bitmap.createBitmap(2, 2, Bitmap.Config.ARGB_8888);
            } else {
                bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

/*copy of 23.1.1 android.support.v7.widget.DrawableUtils*/

    private static final String TAG = "DrawableUtils";

    public static final Rect INSETS_NONE = new Rect();

    private static Class<?> sInsetsClazz;

    static {
        if (Build.VERSION.SDK_INT >= 18) {
            try {
                sInsetsClazz = Class.forName("android.graphics.Insets");
            } catch (ClassNotFoundException e) {
                // Oh well...
            }
        }
    }

    private DrawableUtils() {
    }

    /**
     * Allows us to get the optical insets for a {@link Drawable}. Since this is hidden we need to
     * use reflection. Since the {@code Insets} class is hidden also, we return a Rect instead.
     */
    public static Rect getOpticalBounds(Drawable drawable) {
        if (sInsetsClazz != null) {
            try {
                // If the Drawable is wrapped, we need to manually unwrap it and process
                // the wrapped drawable.
                drawable = DrawableCompat.unwrap(drawable);

                final Method getOpticalInsetsMethod = drawable.getClass()
                        .getMethod("getOpticalInsets");
                final Object insets = getOpticalInsetsMethod.invoke(drawable);

                if (insets != null) {
                    // If the drawable has some optical insets, let's copy them into a Rect
                    final Rect result = new Rect();

                    for (Field field : sInsetsClazz.getFields()) {
                        switch (field.getName()) {
                            case "left":
                                result.left = field.getInt(insets);
                                break;
                            case "top":
                                result.top = field.getInt(insets);
                                break;
                            case "right":
                                result.right = field.getInt(insets);
                                break;
                            case "bottom":
                                result.bottom = field.getInt(insets);
                                break;
                        }
                    }
                    return result;
                }
            } catch (Exception e) {
                // Eugh, we hit some kind of reflection issue...
                Log.e(TAG, "Couldn't obtain the optical insets. Ignoring.");
            }
        }

        // If we reach here, either we're running on a device pre-v18, the Drawable didn't have
        // any optical insets, or a reflection issue, so we'll just return an empty rect
        return INSETS_NONE;
    }

}