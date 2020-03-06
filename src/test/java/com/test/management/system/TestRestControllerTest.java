package com.test.management.system;

import com.test.management.system.controller.rest.TestRestController;
import com.test.management.system.entity.Category;
import com.test.management.system.entity.Step;
import com.test.management.system.entity.Test;
import com.test.management.system.entity.user.Role;
import com.test.management.system.entity.user.User;
import com.test.management.system.service.StepService;
import com.test.management.system.service.TestService;
import com.test.management.system.service.user.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TestRestController.class)
public class TestRestControllerTest {

    private static Test test;
    private static Category category;
    private static User user;
    private static Step step;
    private static Role role;

    @Autowired
    private MockMvc mvc;
    @MockBean
    private TestService testService;
    @MockBean
    private StepService stepService;
    @MockBean
    private UserService userService;

    @BeforeAll
    public static void setup() throws ParseException {
        category = new Category("Login");
        role = new Role("new role");
        user = new User("First Name", "Last Name", "email@email.com", "password", Arrays.asList(role));
        step = new Step("My step description");

        test = new Test("Name", "description", new Date(), category, user);
        test.setId(5L);
        test.addStep(step);
    }


    @BeforeAll
    public void setupForEach() {
        given(testService.findAll()).willReturn(Arrays.asList(test));
    }

    @WithMockUser(value = "spring")
    @org.junit.jupiter.api.Test
    public void givenTestCreated_thenReturnedTest() throws Exception {
        mvc.perform(get("/api/tests")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].testName", is(test.getTestName())))
                .andExpect(jsonPath("$[0].testDescription", is(test.getTestDescription())));
    }

    @WithMockUser(value = "spring")
    @org.junit.jupiter.api.Test
    public void givenTestCreated_thenReturnedCreatedDate() throws Exception {
        mvc.perform(get("/api/tests")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].createDate", is(new SimpleDateFormat("dd-MM-yyyy").format(new Date()))));
    }

    @WithMockUser(value = "spring")
    @org.junit.jupiter.api.Test
    public void givenTestCreated_thenReturnedCategory() throws Exception {
        mvc.perform(get("/api/tests")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].category.categoryName", is(category.getCategoryName())));
    }

    @WithMockUser(value = "spring")
    @org.junit.jupiter.api.Test
    public void givenTestEndpoint_thenReturnsUser() throws Exception {
        mvc.perform(get("/api/tests")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].user.firstName", is(user.getFirstName())))
                .andExpect(jsonPath("$[0].user.lastName", is(user.getLastName())))
                .andExpect(jsonPath("$[0].user.roles[0].name", is(role.getName())));
    }

    @WithMockUser(value = "spring")
    @org.junit.jupiter.api.Test
    public void getTestById() throws Exception {
        given(testService.findById(5L)).willReturn(test);
        mvc.perform(get("/api/tests/{testId}", 5)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("testName", is(test.getTestName())))
                .andExpect(jsonPath("testDescription", is(test.getTestDescription())));
    }
}
