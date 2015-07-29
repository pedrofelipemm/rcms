package br.ufscar.rcms.scorecard.rest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import br.ufscar.rcms.scorecard.rest.controller.ProducaoController;

public class SampleControllerTest {

    private final MockMvc mockMvc = standaloneSetup(new ProducaoController()).build();

    @Test
    public void getSampleTest() throws Exception {

//        mockMvc.perform(get("/samples/1")).andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(jsonPath("$[0].description").value("XPTO"));

    }

}