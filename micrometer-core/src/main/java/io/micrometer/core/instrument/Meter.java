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

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import io.micrometer.api.instrument.Tag;

/**
 * A named and dimensioned producer of one or more measurements.
 *
 * @author Jon Schneider
 * @deprecated scheduled for removal in 2.0.0, please use {@link io.micrometer.api.instrument.Meter}
 */
public interface Meter extends io.micrometer.api.instrument.Meter {
    /**
     * Deprecated - scheduled for removal in 2.0.0. Please use {@link io.micrometer.api.instrument.Meter.Builder}.
     */
    @Deprecated
    static Builder builder(String name, Type type, Iterable<Measurement> measurements) {
        return new Builder(name, io.micrometer.api.instrument.Meter.Type.valueOf(type.name()),
                StreamSupport.stream(measurements.spliterator(), false).map(m -> (io.micrometer.api.instrument.Measurement) m).collect(Collectors.toList()));
    }

    /**
     * Custom meters may emit metrics like one of these types without implementing
     * the corresponding interface. For example, a heisen-counter like structure
     * will emit the same metric as a {@link Counter} but does not have the same
     * increment-driven API.
     *
     * @deprecated scheduled for removal in 2.0.0, please use {@link io.micrometer.api.instrument.Meter.Type}
     */
    @Deprecated
    enum Type {
        COUNTER,
        GAUGE,
        LONG_TASK_TIMER,
        TIMER,
        DISTRIBUTION_SUMMARY,
        OTHER;
    }

    /**
     * A meter is uniquely identified by its combination of name and tags.
     *
     * @deprecated scheduled for removal in 2.0.0, please use {@link io.micrometer.api.instrument.Meter.Id}
     */
    @Deprecated
    class Id extends io.micrometer.api.instrument.Meter.Id {

        protected Id(String name, Tags tags, String baseUnit, String description, io.micrometer.api.instrument.Meter.Type type, io.micrometer.api.instrument.Meter.Id syntheticAssociation) {
            super(name, tags, baseUnit, description, type, syntheticAssociation);
        }

        public Id(String name, io.micrometer.api.instrument.Tags tags, String baseUnit, String description, io.micrometer.api.instrument.Meter.Type type) {
            super(name, tags, baseUnit, description, type);
        }

        @Override
        public Meter.Id withTag(Tag tag) {
            return (Id) super.withTag(tag);
        }
    }

    /**
     * Fluent builder for custom meters.
     *
     * @deprecated scheduled for removal in 2.0.0, please use {@link io.micrometer.api.instrument.Meter.Builder}
     */
    @Deprecated
    class Builder extends io.micrometer.api.instrument.Meter.Builder {

        protected Builder(String name, io.micrometer.api.instrument.Meter.Type type, Iterable<io.micrometer.api.instrument.Measurement> measurements) {
            super(name, type, measurements);
        }
    }

    @Override
    Meter.Id getId();
}
