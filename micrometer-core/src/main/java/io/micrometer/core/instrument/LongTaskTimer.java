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

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.distribution.DistributionStatisticConfig;
import io.micrometer.core.instrument.distribution.HistogramSupport;
import io.micrometer.core.lang.Nullable;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * A long task timer is used to track the total duration of all in-flight long-running tasks and the number of
 * such tasks.
 *
 * @author Jon Schneider
 * @deprecated scheduled for removal in 2.0.0, please use {@link io.micrometer.api.instrument.LongTaskTimer}
 */
@Deprecated
public interface LongTaskTimer extends io.micrometer.api.instrument.LongTaskTimer {
    static Builder builder(String name) {
        return new Builder(name);
    }

    /**
     * Create a timer builder from a {@link Timed} annotation.
     *
     * @param timed The annotation instance to base a new timer on.
     * @return This builder.
     */
    static Builder builder(Timed timed) {
        if (!timed.longTask()) {
            throw new IllegalArgumentException("Cannot build a long task timer from a @Timed annotation that is not marked as a long task");
        }

        if (timed.value().isEmpty()) {
            throw new IllegalArgumentException("Long tasks instrumented with @Timed require the value attribute to be non-empty");
        }

        return (Builder) new Builder(timed.value())
                .tags(timed.extraTags())
                .publishPercentileHistogram(timed.histogram())
                .description(timed.description().isEmpty() ? null : timed.description());
    }

    /**
     * Deprecated - scheduled for removal in 2.0.0. Please use {@link io.micrometer.api.instrument.LongTaskTimer.Sample}.
     */
    @Deprecated
    abstract class Sample extends io.micrometer.api.instrument.LongTaskTimer.Sample {

    }

    /**
     * Fluent builder for long task timers.
     * @deprecated scheduled for removal in 2.0.0, please use {@link io.micrometer.api.instrument.LongTaskTimer.Builder}
     */
    @Deprecated
    class Builder extends io.micrometer.api.instrument.LongTaskTimer.Builder {

        protected Builder(String name) {
            super(name);
        }
    }

    @Override
    Meter.Id getId();
}
