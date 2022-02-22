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
 * Track the sample distribution of events. An example would be the response sizes for requests
 * hitting an http server.
 *
 * @author Jon Schneider
 * @deprecated scheduled for removal in 2.0.0, please use {@link io.micrometer.api.instrument.DistributionSummary}
 */
@Deprecated
public interface DistributionSummary extends io.micrometer.api.instrument.DistributionSummary {

    static Builder builder(String name) {
        return new Builder(name);
    }

    /**
     * Fluent builder for distribution summaries.
     *
     * @deprecated scheduled for removal in 2.0.0, please use {@link io.micrometer.api.instrument.DistributionSummary.Builder}
     */
    @Deprecated
    class Builder extends io.micrometer.api.instrument.DistributionSummary.Builder {

        protected Builder(String name) {
            super(name);
        }
    }

    @Override
    Meter.Id getId();
}
