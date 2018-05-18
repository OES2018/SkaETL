package io.skalogs.skaetl.web;

import io.skalogs.skaetl.generator.GeneratorErrorService;
import io.skalogs.skaetl.generator.GeneratorRetryService;
import io.skalogs.skaetl.generator.GeneratorService;
import io.skalogs.skaetl.web.domain.PayloadTopic;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/generator")
@AllArgsConstructor
public class GeneratorController {

    private final GeneratorService generatorService;
    private final GeneratorErrorService generatorErrorService;
    private final GeneratorRetryService generatorRetryService;

    @ResponseStatus(CREATED)
    @PutMapping("/inputTopic")
    public void inputTopic(@Valid @RequestBody PayloadTopic payload) {
        generatorService.createRandom(payload.getNbElemBySlot(), payload.getNbSlot());
    }

    @ResponseStatus(CREATED)
    @PutMapping("/inputTopicNetwork")
    public void inputTopicNetwork(@Valid @RequestBody PayloadTopic payload) {
        generatorService.createRandomNetwork(payload.getNbElemBySlot());
    }

    @ResponseStatus(CREATED)
    @PutMapping(value = "/inputTopicApacheAsJSON", produces = MediaType.APPLICATION_JSON_VALUE)
    public void inputTopicApacheAsJSON(@Valid @RequestBody PayloadTopic payload) {
        generatorService.createApacheAsJSON(payload.getNbElemBySlot(), payload.getNbSlot());
    }

    @ResponseStatus(CREATED)
    @PutMapping(value = "/inputTopicApacheAsTEXT", produces = MediaType.TEXT_PLAIN_VALUE)
    public void inputTopicApacheAsTEXT(@Valid @RequestBody PayloadTopic payload) {
        generatorService.createApacheAsText(payload.getNbElemBySlot(), payload.getNbSlot());
    }

    @ResponseStatus(CREATED)
    @PutMapping("/inputErrorTopic")
    public void inputErrorTopic(@Valid @RequestBody PayloadTopic payload) {
        generatorErrorService.createRandom(payload.getNbElemBySlot());
    }

    @ResponseStatus(CREATED)
    @PutMapping("/inputRetryTopic")
    public void inputRetryTopic(@Valid @RequestBody PayloadTopic payload) {
        generatorRetryService.createRandom(payload.getNbElemBySlot(), payload.getNbSlot());
    }
}