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


/**
 * Counters monitor monotonically increasing values. Counters may never be reset to a lesser value. If you
 * need to track a value that goes up and down, use a {@link Gauge}.
 *
 * @author Jon Schneider
 * @deprecated scheduled for removal in 2.0.0, please use {@link io.micrometer.api.instrument.Counter}
 */
@Deprecated
public interface Counter extends io.micrometer.api.instrument.Counter {
    static Builder builder(String name) {
        return new Builder(name);
    }

    /**
     * Fluent builder for counters.
     *
     * @deprecated scheduled for removal in 2.0.0, please use {@link io.micrometer.api.instrument.Counter.Builder}
     */
    @Deprecated
    class Builder extends io.micrometer.api.instrument.Counter.Builder {

        protected Builder(String name) {
            super(name);
        }
    }

    @Override
    Meter.Id getId();
}
