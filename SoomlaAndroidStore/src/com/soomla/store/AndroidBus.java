/*
 * Copyright (C) 2012 Soomla Inc.
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
package com.soomla.store;

import android.os.Handler;
import android.os.Looper;
import com.squareup.otto.Bus;

/**
 * This class represents an event bus, allowing separate parts of your application to communicate.
 *
 * AndroidBus extends Bus
 */
public class AndroidBus extends Bus {

    /**
     * Posts events to let subscribers know that a change that has occurred
     *
     * @param event an instance of any class may be published on the bus
     */
    @Override
    public void post(final Object event) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.post(event);
        } else {
            mainThread.post(new Runnable() {
                @Override
                public void run() {
                    post(event);
                }
            });
        }
    }

    /**
     * Registers to the event bus, in order to receive events.
     *
     * @param object
     */
    @Override
    public void register(final Object object) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.register(object);
        } else {
            mainThread.post(new Runnable() {
                @Override
                public void run() {
                    register(object);
                }
            });
        }
    }

    /**
     * Unregisters to the event bus, in order to STOP receiving events.
     *
     * @param object
     */
    @Override
    public void unregister(final Object object) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.unregister(object);
        } else {
            mainThread.post(new Runnable() {
                @Override
                public void run() {
                    unregister(object);
                }
            });
        }
    }


    /** Private Members */

    private final Handler mainThread = new Handler(Looper.getMainLooper());
}
