package br.ufscar.rcms.scorecard.rest.controller;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

public class SampleControllerTest {

    private final MockMvc mockMvc = standaloneSetup(new ProducaoController()).build();

    @Test
    public void getSampleTest() throws Exception {
        // TODO PEDRO
//        mockMvc.perform(get("/samples/1")).andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(jsonPath("$[0].description").value("XPTO"));

    }

}