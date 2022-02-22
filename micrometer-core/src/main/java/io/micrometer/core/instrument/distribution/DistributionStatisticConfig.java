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
package io.micrometer.core.instrument.distribution;

import io.micrometer.api.instrument.Timer;
import io.micrometer.api.lang.Nullable;
import io.micrometer.core.instrument.internal.Mergeable;

import java.time.Duration;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.stream.LongStream;

/**
 * Configures the distribution statistics that emanate from meters like {@link io.micrometer.core.instrument.Timer}
 * and {@link io.micrometer.core.instrument.DistributionSummary}.
 * <p>
 * These statistics include max, percentiles, percentile histograms, and SLA violations.
 * <p>
 * Many distribution statistics are decayed to give greater weight to recent samples.
 *
 * @author Jon Schneider
 * @deprecated scheduled for removal in 2.0.0, please use {@link io.micrometer.api.instrument.Counter}
 */
@Deprecated
public class DistributionStatisticConfig extends io.micrometer.api.instrument.distribution.DistributionStatisticConfig {
    public static final DistributionStatisticConfig DEFAULT = (DistributionStatisticConfig) DistributionStatisticConfig.builder()
            .percentilesHistogram(false)
            .percentilePrecision(1)
            .minimumExpectedValue(1.0)
            .maximumExpectedValue(Double.POSITIVE_INFINITY)
            .expiry(Duration.ofMinutes(2))
            .bufferLength(3)
            .build();

    public static final DistributionStatisticConfig NONE = (DistributionStatisticConfig) builder().build();


    public static DistributionStatisticConfig.Builder builder() {
        return new DistributionStatisticConfig.Builder();
    }


    /**
     * Deprecated - scheduled for removal in 2.0.0. Please use {@link io.micrometer.api.instrument.distribution.DistributionStatisticConfig}.
     */
    @Deprecated
    public static class Builder extends io.micrometer.api.instrument.distribution.DistributionStatisticConfig.Builder {

    }
}
