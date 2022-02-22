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

import java.util.function.ToDoubleFunction;

import io.micrometer.core.lang.Nullable;

/**
 * A counter that tracks a monotonically increasing function.
 *
 * @author Jon Schneider
 * @deprecated scheduled for removal in 2.0.0, please use {@link io.micrometer.api.instrument.FunctionCounter}
 */
@Deprecated
public interface FunctionCounter extends io.micrometer.api.instrument.FunctionCounter {
    static <T> Builder<T> builder(String name, @Nullable T obj, ToDoubleFunction<T> f) {
        return new Builder<>(name, obj, f);
    }

    /**
     * Fluent builder for function counters.
     *
     * @param <T> The type of the state object from which the counter value is extracted.
     * @deprecated scheduled for removal in 2.0.0, please use {@link io.micrometer.api.instrument.FunctionCounter.Builder}
     */
    @Deprecated
    class Builder<T> extends io.micrometer.api.instrument.FunctionCounter.Builder<T> {

        protected Builder(String name, T obj, ToDoubleFunction<T> f) {
            super(name, obj, f);
        }
    }

    @Override
    Meter.Id getId();
}
