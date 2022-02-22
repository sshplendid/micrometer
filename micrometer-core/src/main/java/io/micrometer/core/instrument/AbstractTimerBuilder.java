/*
 * Copyright 2020 VMware, Inc.
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

import io.micrometer.core.instrument.distribution.DistributionStatisticConfig;
import io.micrometer.core.instrument.distribution.pause.PauseDetector;
import io.micrometer.core.lang.Nullable;
import org.checkerframework.checker.units.qual.C;

import java.time.Duration;
import java.util.Arrays;

/**
 * Base builder for {@link Timer}.
 *
 * @param <B> builder type
 *
 * @author Jon Schneider
 * @since 1.6.0
 *
 * @deprecated scheduled for removal in 2.0.0, please use {@link io.micrometer.api.instrument.Counter}
 */
@SuppressWarnings("unchecked")
@Deprecated
public abstract class AbstractTimerBuilder<B extends AbstractTimerBuilder<B>> extends io.micrometer.api.instrument.AbstractTimerBuilder<B> {

    protected AbstractTimerBuilder(String name) {
        super(name);
    }
}
