/*
 * Copyright 2022 VMware, Inc.
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
package io.micrometer.api.instrument.observation;

import java.io.IOException;
import java.util.function.Consumer;

import io.micrometer.api.instrument.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link ObservationTextPublisher}.
 *
 * @author Jonatan Ivanov
 */
class ObservationTextPublisherTests {
    private static final String CONTEXT_TOSTRING = "name='testName', contextualName='testContextualName', error='java.io.IOException: simulated', lowCardinalityTags=[lcTag='foo'], highCardinalityTags=[hcTag='bar'], map=[contextKey='contextValue']";
    private final TestConsumer consumer = new TestConsumer();
    private final ObservationHandler<Observation.Context> publisher = new ObservationTextPublisher(consumer);

    @Test
    void onStartShouldPublishStartEvent() {
        publisher.onStart(createTestContext());
        assertThat(consumer.toString()).isEqualTo("START - " + CONTEXT_TOSTRING);
    }

    @Test
    void onScopeOpenedShouldPublishOpenEvent() {
        publisher.onScopeOpened(createTestContext());
        assertThat(consumer.toString()).isEqualTo("OPEN - " + CONTEXT_TOSTRING);
    }

    @Test
    void onErrorShouldPublishErrorEvent() {
        publisher.onError(createTestContext());
        assertThat(consumer.toString()).isEqualTo("ERROR - " + CONTEXT_TOSTRING);
    }

    @Test
    void onScopeClosedShouldPublishCloseEvent() {
        publisher.onScopeClosed(createTestContext());
        assertThat(consumer.toString()).isEqualTo("CLOSE - " + CONTEXT_TOSTRING);
    }

    @Test
    void onStopClosedShouldPublishCloseEvent() {
        publisher.onStop(createTestContext());
        assertThat(consumer.toString()).isEqualTo("STOP - " + CONTEXT_TOSTRING);
    }

    @Test
    void shouldSupportAnyContextByDefault() {
        assertThat(publisher.supportsContext(null)).isTrue();
        assertThat(publisher.supportsContext(new Observation.Context())).isTrue();
        assertThat(publisher.supportsContext(createTestContext())).isTrue();
    }

    @Test
    void shouldSupportContextEnabledByThePredicate() {
        ObservationHandler<Observation.Context> publisher = new ObservationTextPublisher(consumer, context -> "testName".equals(context.getName()));
        assertThat(publisher.supportsContext(new Observation.Context())).isFalse();
        assertThat(publisher.supportsContext(createTestContext())).isTrue();
    }

    private Observation.Context createTestContext() {
        Observation.Context context = new Observation.Context()
                .setName("testName")
                .setContextualName("testContextualName")
                .setError(new IOException("simulated"));
        context.addLowCardinalityTag(Tag.of("lcTag", "foo"));
        context.addHighCardinalityTag(Tag.of("hcTag", "bar"));
        context.put("contextKey", "contextValue");

        return context;
    }

    static class TestConsumer implements Consumer<String> {
        private final StringBuilder stringBuilder = new StringBuilder();

        @Override
        public void accept(String text) {
            stringBuilder.append(text).append("\n");
        }

        @Override
        public String toString() {
            return stringBuilder.toString().trim();
        }
    }
}
