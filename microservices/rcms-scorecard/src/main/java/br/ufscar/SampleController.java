package br.ufscar;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/samples")
public class SampleController {

    @RequestMapping(value = "/1", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Sample> getSample() {
        return Arrays.asList(new Sample(1, "XPTO"), new Sample(2, "ABC"));
    }

    @RequestMapping(value = "/2", produces = MediaType.APPLICATION_JSON_VALUE)
    public Sample getSample2() {
        return new Sample(1, "XPTO");
    }
}