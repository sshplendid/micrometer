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
package io.micrometer.core.aop;

import io.micrometer.api.instrument.MeterRegistry;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.lang.NonNullApi;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * <p>
 * Aspect responsible for intercepting all methods annotated with the {@link Counted @Counted}
 * annotation and recording a few counter metrics about their execution status.<br>
 * The aspect supports programmatic customizations through constructor-injectable custom logic.
 * </p>
 * <p>
 * You might want to add tags programmatically to the {@link Counter}.<br>
 * In this case, the tags provider function (<code>Function&lt;ProceedingJoinPoint, Iterable&lt;Tag&gt;&gt;</code>) can help.
 * It receives a {@link ProceedingJoinPoint} and returns the {@link Tag}s that will be attached to the {@link Counter}.
 * </p>
 * <p>
 * You might also want to skip the {@link Counter} creation programmatically.<br>
 * One use-case can be having another component in your application that already processes the {@link Counted @Counted} annotation
 * in some cases so that {@code CountedAspect} should not intercept these methods.
 * By using the skip predicate (<code>Predicate&lt;ProceedingJoinPoint&gt;</code>)
 * you can tell the {@code CountedAspect} when not to create a {@link Counter}.
 *
 * Here's a theoretic example to disable {@link Counter} creation for Spring controllers:
 *</p>
 * <pre>
 * &#064;Bean
 * public CountedAspect countedAspect(MeterRegistry meterRegistry) {
 *     return new CountedAspect(meterRegistry, this::skipControllers);
 * }
 *
 * private boolean skipControllers(ProceedingJoinPoint pjp) {
 *     Class&lt;?&gt; targetClass = pjp.getTarget().getClass();
 *     return targetClass.isAnnotationPresent(RestController.class) || targetClass.isAnnotationPresent(Controller.class);
 * }
 * </pre>
 *
 * @author Ali Dehghani
 * @author Jonatan Ivanov
 * @since 1.2.0
 * @see Counted
 * @deprecated scheduled for removal in 2.0.0, please use {@link io.micrometer.api.aop.CountedAspect}
 */
@Deprecated
public class CountedAspect extends io.micrometer.api.aop.CountedAspect {

    public CountedAspect() {
    }

    public CountedAspect(MeterRegistry registry) {
        super(registry);
    }

    public CountedAspect(MeterRegistry registry, Function<ProceedingJoinPoint, Iterable<io.micrometer.api.instrument.Tag>> tagsBasedOnJoinPoint) {
        super(registry, tagsBasedOnJoinPoint);
    }

    public CountedAspect(MeterRegistry registry, Predicate<ProceedingJoinPoint> shouldSkip) {
        super(registry, shouldSkip);
    }

    public CountedAspect(MeterRegistry registry, Function<ProceedingJoinPoint, Iterable<io.micrometer.api.instrument.Tag>> tagsBasedOnJoinPoint, Predicate<ProceedingJoinPoint> shouldSkip) {
        super(registry, tagsBasedOnJoinPoint, shouldSkip);
    }
}
