package io.skalogs.skaetl.rules.metrics.udaf;

/*-
 * #%L
 * metric-api
 * %%
 * Copyright (C) 2017 - 2018 SkaLogs
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CountFunctionTest {

    @Test
    public void shouldReturnZeroWhenNoValue() {
        CountFunction countFunction =new CountFunction();
        assertThat(countFunction.compute()).isEqualTo(0);
    }

    @Test
    public void shouldReturnCount() {
        CountFunction countFunction =new CountFunction();
        countFunction.addValue(JsonNodeFactory.instance.numberNode(100));
        countFunction.addValue(JsonNodeFactory.instance.numberNode(3));
        assertThat(countFunction.compute()).isEqualTo(2);
    }
}
