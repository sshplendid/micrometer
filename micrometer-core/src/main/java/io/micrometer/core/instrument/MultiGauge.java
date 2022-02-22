/*
 * Copyright 2018 VMware, Inc.
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

import io.micrometer.api.instrument.Meter;
import io.micrometer.api.instrument.MeterRegistry;
import io.micrometer.core.annotation.Incubating;
import io.micrometer.core.lang.Nullable;

import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.toSet;

/**
 * @author Jon Schneider
 * @since 1.1.0
 * @deprecated scheduled for removal in 2.0.0, please use {@link io.micrometer.api.instrument.MultiGauge}
 */
@Incubating(since = "1.1.0")
public class MultiGauge extends io.micrometer.api.instrument.MultiGauge {

    protected MultiGauge(MeterRegistry registry, Meter.Id commonId) {
        super(registry, commonId);
    }

    /**
     * @param name The gauge's name.
     * @return A new gauge builder.
     */
    public static Builder builder(String name) {
        return new Builder(name);
    }

    /**
     * Deprecated - scheduled for removal in 2.0.0. Please use {@link io.micrometer.api.instrument.MultiGauge.Row}.
     */
    @SuppressWarnings("unchecked")
    @Deprecated
    public static class Row<T> extends io.micrometer.api.instrument.MultiGauge.Row<T> {

        protected Row(io.micrometer.api.instrument.Tags uniqueTags, T obj, ToDoubleFunction<T> valueFunction) {
            super(uniqueTags, obj, valueFunction);
        }

        /**
         * Deprecated - scheduled for removal in 2.0.0. Please use {@link io.micrometer.api.instrument.MultiGauge.Row}.
         */
        @Deprecated
        public static <T> Row<T> of(Tags uniqueTags, T obj, ToDoubleFunction<T> valueFunction) {
            return new Row<>(uniqueTags, obj, valueFunction);
        }

        /**
         * Deprecated - scheduled for removal in 2.0.0. Please use {@link io.micrometer.api.instrument.MultiGauge.Row}.
         */
        @Deprecated
        public static Row<Number> of(Tags uniqueTags, Number number) {
            return new Row<>(uniqueTags, number, Number::doubleValue);
        }

        /**
         * Deprecated - scheduled for removal in 2.0.0. Please use {@link io.micrometer.api.instrument.MultiGauge.Row}.
         */
        @Deprecated
        public static Row<Supplier<Number>> of(Tags uniqueTags, Supplier<Number> valueFunction) {
            return new Row<>(uniqueTags, valueFunction, f -> {
                Number value = valueFunction.get();
                return value == null ? Double.NaN : value.doubleValue();
            });
        }
    }

    /**
     * Fluent builder for multi-gauges.
     */
    public static class Builder extends io.micrometer.api.instrument.MultiGauge.Builder {
        protected Builder(String name) {
            super(name);
        }

        // TODO: Override all methods :scream:
    }
}
