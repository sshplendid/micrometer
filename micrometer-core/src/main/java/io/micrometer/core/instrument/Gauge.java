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

import io.micrometer.core.annotation.Incubating;
import io.micrometer.core.instrument.distribution.HistogramGauges;
import io.micrometer.core.lang.Nullable;

import java.util.Collections;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;

/**
 * A gauge tracks a value that may go up or down. The value that is published for gauges is
 * an instantaneous sample of the gauge at publishing time.
 *
 * @author Jon Schneider
 * @deprecated scheduled for removal in 2.0.0, please use {@link io.micrometer.api.instrument.Gauge}
 */
@Deprecated
public interface Gauge extends io.micrometer.api.instrument.Gauge {
    /**
     * @param name The gauge's name.
     * @param obj  An object with some state or function which the gauge's instantaneous value
     *             is determined from.
     * @param f    A function that yields a double value for the gauge, based on the state of
     *             {@code obj}.
     * @param <T>  The type of object to gauge.
     * @return A new gauge builder.
     */
    static <T> Builder<T> builder(String name, @Nullable T obj, ToDoubleFunction<T> f) {
        return new Builder<>(name, obj, f);
    }

    /**
     * A convenience method for building a gauge from a supplying function, holding a strong
     * reference to this function.
     *
     * @param name The gauge's name.
     * @param f    A function that yields a double value for the gauge.
     * @return A new gauge builder.
     * @since 1.1.0
     */
    @Incubating(since = "1.1.0")
    static Builder<Supplier<Number>> builder(String name, Supplier<Number> f) {
        return (Builder<Supplier<Number>>) new Builder<>(name, f, f2 -> {
            Number val = f2.get();
            return val == null ? Double.NaN : val.doubleValue();
        }).strongReference(true);
    }

    /**
     * Fluent builder for gauges.
     *
     * @param <T> The type of the state object from which the gauge value is extracted.
     * @deprecated scheduled for removal in 2.0.0, please use {@link io.micrometer.api.instrument.Gauge.Builder}
     */
    @Deprecated
    class Builder<T> extends io.micrometer.api.instrument.Gauge.Builder<T> {

        protected Builder(String name, T obj, ToDoubleFunction<T> f) {
            super(name, obj, f);
        }


    }

    @Override
    Meter.Id getId();
}
