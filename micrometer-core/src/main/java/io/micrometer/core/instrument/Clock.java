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
 * Used to measure absolute and relative time.
 *
 * @see MockClock for a clock that can be manually advanced for use in tests.
 * @author Jon Schneider
 * @deprecated scheduled for removal in 2.0.0, please use {@link io.micrometer.api.instrument.Clock}
 */
@Deprecated
public interface Clock extends io.micrometer.api.instrument.Clock {

    /**
     * Deprecated - scheduled for removal in 2.0.0. Please use {@link io.micrometer.api.instrument.Clock#SYSTEM}.
     */
    @Deprecated
    Clock SYSTEM = new Clock() {
        @Override
        public long wallTime() {
            return io.micrometer.api.instrument.Clock.SYSTEM.wallTime();
        }

        @Override
        public long monotonicTime() {
            return io.micrometer.api.instrument.Clock.SYSTEM.monotonicTime();
        }
    };
}
