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

import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import io.micrometer.core.lang.Nullable;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.ToDoubleFunction;
import java.util.function.ToLongFunction;

/**
 * Generator of meters bound to a static global composite registry. For use especially
 * in places where dependency injection of {@link MeterRegistry} is not possible for an instrumented
 * type.
 *
 * @author Jon Schneider
 * @deprecated scheduled for removal in 2.0.0, please use {@link io.micrometer.api.instrument.Metrics}
 */
@Deprecated
public class Metrics extends io.micrometer.api.instrument.Metrics {
}
