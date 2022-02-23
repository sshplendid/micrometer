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
package io.micrometer.core.instrument.binder.cache;

import com.github.benmanes.caffeine.cache.*;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import io.micrometer.core.instrument.*;
import io.micrometer.core.lang.NonNullApi;
import io.micrometer.core.lang.NonNullFields;
import io.micrometer.core.lang.Nullable;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.ToLongFunction;

/**
 * Collect metrics from Caffeine's {@link com.github.benmanes.caffeine.cache.Cache}. {@link CaffeineStatsCounter} is an
 * alternative that can collect more detailed statistics.
 * <p>
 * Note that {@code recordStats()} is required to gather non-zero statistics:
 * <pre>{@code
 * Cache<String, String> cache = Caffeine.newBuilder().recordStats().build();
 * CaffeineCacheMetrics.monitor(registry, cache, "mycache", "region", "test");
 * }</pre>
 *
 * @author Clint Checketts
 * @see CaffeineStatsCounter
 * @deprecated scheduled for removal in 2.0.0, please use {@link io.micrometer.binder.cache.CaffeineCacheMetrics}
 */
@NonNullApi
@NonNullFields
@Deprecated
public class CaffeineCacheMetrics<K, V, C extends Cache<K, V>> extends io.micrometer.binder.cache.CaffeineCacheMetrics<K, V, C> {

    /**
     * Creates a new {@link CaffeineCacheMetrics} instance.
     *  @param cache     The cache to be instrumented. You must call {@link Caffeine#recordStats()} prior to building the cache
     *                  for metrics to be recorded.
     * @param cacheName Will be used to tag metrics with "cache".
     * @param tags      tags to apply to all recorded metrics.
     */
    public CaffeineCacheMetrics(C cache, String cacheName, Iterable<Tag> tags) {
        super(cache, cacheName, tags);
    }

    @Override
    protected Long size() {
        return super.size();
    }

    @Override
    protected long hitCount() {
        return super.hitCount();
    }

    @Override
    protected Long missCount() {
        return super.missCount();
    }

    @Override
    protected Long evictionCount() {
        return super.evictionCount();
    }

    @Override
    protected long putCount() {
        return super.putCount();
    }
}
