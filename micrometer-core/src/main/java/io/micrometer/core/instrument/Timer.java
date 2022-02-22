/*
 * Copyright 2019 VMware, Inc.
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
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.distribution.CountAtBucket;
import io.micrometer.core.instrument.distribution.HistogramSupport;
import io.micrometer.core.instrument.distribution.ValueAtPercentile;
import io.micrometer.core.instrument.distribution.pause.PauseDetector;
import io.micrometer.core.lang.Nullable;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * Timer intended to track of a large number of short running events. Example would be something like
 * an HTTP request. Though "short running" is a bit subjective the assumption is that it should be
 * under a minute.
 *
 * @author Jon Schneider
 * @author Oleksii Bondar
 */
public interface Timer extends io.micrometer.api.instrument.Timer {
    /**
     * Start a timing sample using the {@link Clock#SYSTEM System clock}.
     *
     * @return A timing sample with start time recorded.
     * @since 1.1.0
     */
    static Sample start() {
        return start(Clock.SYSTEM);
    }

    /**
     * Start a timing sample.
     *
     * @param registry A meter registry whose clock is to be used
     * @return A timing sample with start time recorded.
     */
    static Sample start(MeterRegistry registry) {
        return start(registry.config().clock());
    }

    /**
     * Start a timing sample.
     *
     * @param clock a clock to be used
     * @return A timing sample with start time recorded.
     */
    static Sample start(Clock clock) {
        return new Sample(clock);
    }

    static Builder builder(String name) {
        return new Builder(name);
    }

    /**
     * @param registry A meter registry against which the timer will be registered.
     * @param name     The name of the timer.
     * @return A timing builder that automatically records a timing on close.
     * @since 1.6.0
     */
    @Incubating(since = "1.6.0")
    static ResourceSample resource(MeterRegistry registry, String name) {
        return new ResourceSample(registry, name);
    }

    /**
     * Create a timer builder from a {@link Timed} annotation.
     *
     * @param timed       The annotation instance to base a new timer on.
     * @param defaultName A default name to use in the event that the value attribute is empty.
     * @return This builder.
     */
    static Builder builder(Timed timed, String defaultName) {
        if (timed.longTask() && timed.value().isEmpty()) {
            // the user MUST name long task timers, we don't lump them in with regular
            // timers with the same name
            throw new IllegalArgumentException("Long tasks instrumented with @Timed require the value attribute to be non-empty");
        }

        return (Builder) new Builder(timed.value().isEmpty() ? defaultName : timed.value())
                .tags(timed.extraTags())
                .description(timed.description().isEmpty() ? null : timed.description())
                .publishPercentileHistogram(timed.histogram())
                .publishPercentiles(timed.percentiles().length > 0 ? timed.percentiles() : null);
    }

    /**
     * @return The base time unit of the timer to which all published metrics will be scaled
     */
    TimeUnit baseTimeUnit();

    /**
     * Maintains state on the clock's start position for a latency sample. Complete the timing
     * by calling {@link Sample#stop(Timer)}. Note how the {@link Timer} isn't provided until the
     * sample is stopped, allowing you to determine the timer's tags at the last minute.
     */
    class Sample extends io.micrometer.api.instrument.Timer.Sample {

        protected Sample(io.micrometer.api.instrument.Clock clock) {
            super(clock);
        }
    }

    class ResourceSample extends io.micrometer.api.instrument.Timer.ResourceSample {

        protected ResourceSample(io.micrometer.api.instrument.MeterRegistry registry, String name) {
            super(registry, name);
        }

        // TODO: Override all methods :scream:
    }

    /**
     * Fluent builder for timers.
     */
    class Builder extends io.micrometer.api.instrument.Timer.Builder {

        protected Builder(String name) {
            super(name);
        }

        // TODO: Override all methods :scream:
    }

}
