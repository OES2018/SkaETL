package io.skalogs.skaetl.web;

/*-
 * #%L
 * skaetl-backend
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

import io.skalogs.skaetl.domain.ConsumerState;
import io.skalogs.skaetl.domain.ProcessMetric;
import io.skalogs.skaetl.service.MetricProcessService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/metric")
@AllArgsConstructor
public class MetricProcessController {

    private final MetricProcessService metricProcessService;

    @ResponseStatus(OK)
    @GetMapping("listProcess")
    public List<ConsumerState> list() {
        return metricProcessService.findAll();
    }

    @GetMapping("findById")
    public ProcessMetric findById(@RequestParam("idProcess") String id) {
        return (ProcessMetric) metricProcessService.findById(id);
    }

    @ResponseStatus(OK)
    @GetMapping("findConsumerState")
    public ConsumerState findConsumerState(@PathParam("idProcess") String idProcess) {
        return metricProcessService.findConsumerState(idProcess);
    }

    @ResponseStatus(OK)
    @GetMapping("init")
    public ProcessMetric init() {
        return metricProcessService.init();
    }

    @ResponseStatus(OK)
    @PostMapping("update")
    public void update(@RequestBody ProcessMetric processMetric) {
        metricProcessService.updateProcess(processMetric);
    }

    @ResponseStatus(OK)
    @GetMapping("activate")
    public void activate(@RequestParam("idProcess") String id) {
        metricProcessService.activateProcess(findById(id));
    }

    @ResponseStatus(OK)
    @GetMapping("deactivate")
    public void deactivate(@RequestParam("idProcess") String id) {
        metricProcessService.deactivateProcess(findById(id));
    }

    @ResponseStatus(OK)
    @GetMapping("scaleup")
    public void scaleup(@PathParam("idProcess") String idProcess) throws Exception {
        metricProcessService.scaleup(idProcess);
    }

    @ResponseStatus(OK)
    @GetMapping("scaledown")
    public void scaledown(@PathParam("idProcess") String idProcess) throws Exception {
        metricProcessService.scaledown(idProcess);
    }

    @ResponseStatus(OK)
    @DeleteMapping("delete")
    public void delete(@RequestParam("idProcess") String id) {
        metricProcessService.deleteProcess(findById(id));
    }
}
