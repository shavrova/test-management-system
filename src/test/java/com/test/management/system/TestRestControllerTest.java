package com.test.management.system;

import com.test.management.system.entity.Category;
import com.test.management.system.entity.Step;
import com.test.management.system.entity.Test;
import com.test.management.system.controller.rest.TestRestController;
import com.test.management.system.service.StepService;
import com.test.management.system.service.TestService;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TestRestController.class)
public class TestRestControllerTest {

    private static Test test;
    private static Step step;
    @Autowired
    private MockMvc mvc;
    @MockBean
    private TestService testService;
    @MockBean
    private StepService stepService;

    @BeforeAll
    public static void setup() {

        test = new Test("My Test name", "My Test Description", new Category());
        step = new Step("My step description");
        test.setId(1L);
    }


    @org.junit.jupiter.api.Test
    public void getAllTest() throws Exception {
        Set<Test> tests = Stream.of(test).collect(Collectors.toSet());
        given(testService.findAll()).willReturn(new TreeSet<>(tests));
        mvc.perform(get("/api/tests")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].testName", is(test.getTestName())))
                .andExpect(jsonPath("$[0].testDescription", is(test.getTestDescription())));
    }

    @org.junit.jupiter.api.Test
    public void getTestById() throws Exception {
        given(testService.findById(1L)).willReturn(test);
        mvc.perform(get("/api/tests/{testId}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("testName", is(test.getTestName())))
                .andExpect(jsonPath("testDescription", is(test.getTestDescription())));
    }
}
