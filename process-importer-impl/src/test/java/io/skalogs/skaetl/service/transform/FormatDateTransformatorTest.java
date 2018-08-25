package io.skalogs.skaetl.service.transform;

/*-
 * #%L
 * process-importer-impl
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.skalogs.skaetl.RawDataGen;
import io.skalogs.skaetl.domain.FormatDateValue;
import io.skalogs.skaetl.domain.ParameterTransformation;
import io.skalogs.skaetl.domain.TypeValidation;
import io.skalogs.skaetl.utils.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class FormatDateTransformatorTest {

    @Test
    public void should_Process_Ok() throws Exception {
        FormatDateTransformator formatDateTransformator = new FormatDateTransformator(TypeValidation.FORMAT_DATE);
        RawDataGen rd = RawDataGen.builder().messageSend("2018-01-15").project("project").type("type").build();
        ObjectMapper obj = new ObjectMapper();
        String value = obj.writeValueAsString(rd);
        ObjectNode jsonValue = JSONUtils.getInstance().parseObj(value);

        formatDateTransformator.apply(null,
                ParameterTransformation.builder()
                        .formatDateValue(FormatDateValue.builder()
                                .keyField("messageSend")
                                .srcFormat("yyyy-MM-dd")
                                .targetFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                                .build())
                        .build(),
                jsonValue);
        //FIXME: TIMEZONE ISSUE
        assertThat(jsonValue.path("messageSend").asText()).startsWith("2018-01-15T00:00:00.000");
    }


    @Test
    public void should_Process_Ko() throws Exception {
        FormatDateTransformator formatDateTransformator = new FormatDateTransformator(TypeValidation.FORMAT_DATE);
        RawDataGen rd = RawDataGen.builder().messageSend("2018-01-15").project("project").type("type").build();
        ObjectMapper obj = new ObjectMapper();
        String value = obj.writeValueAsString(rd);
        ObjectNode jsonValue = JSONUtils.getInstance().parseObj(value);

        formatDateTransformator.apply(null,
                ParameterTransformation.builder()
                        .formatDateValue(FormatDateValue.builder()
                                .keyField("toto")
                                .srcFormat("yyyy-MM-dd")
                                .targetFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                                .build())
                        .build(),
                jsonValue);
        assertThat(jsonValue.path("toto").asText()).isEqualTo("");
    }
}
