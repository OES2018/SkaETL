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

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;

public class SumFunction extends AggregateFunction<JsonNode, Double> {
    @Getter
    private Double sum = 0d;

    @Override
    public AggregateFunction addValue(JsonNode value) {
        double doubleValue = value.doubleValue();
        sum += doubleValue;
        return this;
    }

    @Override
    public Double compute() {
        return sum;
    }

    @Override
    public AggregateFunction<JsonNode, Double> merge(AggregateFunction<JsonNode, Double> newValue) {
        sum += newValue.compute();
        return this;
    }
}
