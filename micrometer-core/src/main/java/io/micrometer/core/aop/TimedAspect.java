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
import io.micrometer.core.annotation.Incubating;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.LongTaskTimer;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.lang.NonNullApi;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * <p>
 * AspectJ aspect for intercepting types or methods annotated with {@link Timed @Timed}.<br>
 * The aspect supports programmatic customizations through constructor-injectable custom logic.
 * </p>
 * <p>
 * You might want to add tags programmatically to the {@link Timer}.<br>
 * In this case, the tags provider function (<code>Function&lt;ProceedingJoinPoint, Iterable&lt;Tag&gt;&gt;</code>) can help.
 * It receives a {@link ProceedingJoinPoint} and returns the {@link Tag}s that will be attached to the {@link Timer}.
 * </p>
 * <p>
 * You might also want to skip the {@link Timer} creation programmatically.<br>
 * One use-case can be having another component in your application that already processes the {@link Timed @Timed} annotation
 * in some cases so that {@code TimedAspect} should not intercept these methods. E.g.: Spring Boot does this for its controllers.
 * By using the skip predicate (<code>Predicate&lt;ProceedingJoinPoint&gt;</code>)
 * you can tell the {@code TimedAspect} when not to create a {@link Timer}.
 *
 * Here's an example to disable {@link Timer} creation for Spring controllers:
 *</p>
 * <pre>
 * &#064;Bean
 * public TimedAspect timedAspect(MeterRegistry meterRegistry) {
 *     return new TimedAspect(meterRegistry, this::skipControllers);
 * }
 *
 * private boolean skipControllers(ProceedingJoinPoint pjp) {
 *     Class&lt;?&gt; targetClass = pjp.getTarget().getClass();
 *     return targetClass.isAnnotationPresent(RestController.class) || targetClass.isAnnotationPresent(Controller.class);
 * }
 * </pre>
 *
 * @author David J. M. Karlsen
 * @author Jon Schneider
 * @author Johnny Lim
 * @author Nejc Korasa
 * @author Jonatan Ivanov
 * @since 1.0.0
 * @deprecated scheduled for removal in 2.0.0, please use {@link io.micrometer.api.aop.TimedAspect}
 */
@Deprecated
public class TimedAspect extends io.micrometer.api.aop.TimedAspect {
    public TimedAspect() {
        super();
    }

    public TimedAspect(MeterRegistry registry) {
        super(registry);
    }

    public TimedAspect(MeterRegistry registry, Function<ProceedingJoinPoint, Iterable<io.micrometer.api.instrument.Tag>> tagsBasedOnJoinPoint) {
        super(registry, tagsBasedOnJoinPoint);
    }

    public TimedAspect(MeterRegistry registry, Predicate<ProceedingJoinPoint> shouldSkip) {
        super(registry, shouldSkip);
    }

    public TimedAspect(MeterRegistry registry, Function<ProceedingJoinPoint, Iterable<io.micrometer.api.instrument.Tag>> tagsBasedOnJoinPoint, Predicate<ProceedingJoinPoint> shouldSkip) {
        super(registry, tagsBasedOnJoinPoint, shouldSkip);
    }
}
