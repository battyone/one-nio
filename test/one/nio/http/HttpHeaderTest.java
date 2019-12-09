/*
 * Copyright 2019 Odnoklassniki Ltd, Mail.Ru Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package one.nio.http;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for HTTP header processing facilities.
 *
 * @author Vadim Tsesko <incubos@yandex.com>
 */
public class HttpHeaderTest {
    private static final String HEADER = "X-OK-Custom-Header: ";

    private void testHeaderConsumer(final String... values) {
        final Request request = new Request(Request.METHOD_GET, "/", true);
        for (final String value : values) {
            request.addHeader(HEADER + value);
        }

        final List<String> sink = new ArrayList<>(values.length);
        request.consumeHeaders(HEADER, sink::add);
        assertEquals(Arrays.asList(values), sink);
    }

    @Test
    public void consumeEmpty() {
        testHeaderConsumer();
    }

    @Test
    public void consumeSingle() {
        testHeaderConsumer("Value");
    }

    @Test
    public void consumeDouble() {
        testHeaderConsumer("First", "Second");
    }
}
