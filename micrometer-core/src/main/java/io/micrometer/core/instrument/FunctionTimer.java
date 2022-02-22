/*
 * Copyright 2017 VMware, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micrometer.core.instrument;

import java.util.concurrent.TimeUnit;
import java.util.function.ToDoubleFunction;
import java.util.function.ToLongFunction;

import io.micrometer.api.instrument.Measurement;

/**
 * A timer that tracks two monotonically increasing functions: one representing the count of events and one
 * representing the total time spent in every event.
 *
 * @author Jon Schneider
 * @deprecated scheduled for removal in 2.0.0, please use {@link io.micrometer.api.instrument.FunctionTimer}
 */
@Deprecated
public interface FunctionTimer extends io.micrometer.api.instrument.FunctionTimer {
    static <T> Builder<T> builder(String name, T obj, ToLongFunction<T> countFunction,
            ToDoubleFunction<T> totalTimeFunction,
            TimeUnit totalTimeFunctionUnit) {
        return new Builder<>(name, obj, countFunction, totalTimeFunction, totalTimeFunctionUnit);
    }

    /**
     * Fluent builder for function timer.
     *
     * @param <T> The type of the state object from which the timer values are extracted.
     * @deprecated scheduled for removal in 2.0.0, please use {@link io.micrometer.api.instrument.FunctionTimer.Builder}
     */
    @Deprecated
    class Builder<T> extends io.micrometer.api.instrument.FunctionTimer.Builder<T> {

        protected Builder(String name, T obj, ToLongFunction<T> countFunction, ToDoubleFunction<T> totalTimeFunction, TimeUnit totalTimeFunctionUnit) {
            super(name, obj, countFunction, totalTimeFunction, totalTimeFunctionUnit);
        }
    }

    @Override
    Meter.Id getId();
}
