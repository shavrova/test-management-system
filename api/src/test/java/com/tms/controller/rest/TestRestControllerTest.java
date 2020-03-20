package com.tms.controller.rest;

import com.tms.model.entity.Category;
import com.tms.model.entity.Step;
import com.tms.model.entity.user.Role;
import com.tms.model.entity.user.User;
import com.tms.service.StepService;
import com.tms.service.TestService;
import com.tms.service.user.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static com.tms.util.JsonUtils.asJsonString;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WithMockUser(value = "spring")
@WebMvcTest(TestRestController.class)
public class TestRestControllerTest {

    private static com.tms.model.entity.Test test;
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
    public static void setup() {
        category = Category.builder().categoryName("Login").build();
        role = Role.builder().name("NEW_ROLE").build();
        user = User.builder()
                .firstName("First Name")
                .lastName("Last Name")
                .email("email@email.com")
                .password("password")
                .roles(Collections.singletonList(role))
                .build();
        step = Step.builder().stepDescription("My step description").build();
        step.setId(1L);
        test = com.tms.model.entity.Test.builder()
                .testName("Name")
                .testDescription("description")
                .createDate(new Date())
                .category(category)
                .user(user)
                .build();
        test.setId(1L);
        test.addStep(step);
    }

    @BeforeEach
    public void setupForEach() {
        given(testService.findAll()).willReturn(Arrays.asList(test));
        given(testService.findById(anyLong())).willReturn(test);
    }

    @Test
    public void whenGetAllTests_thenCreatedTestIsReturnedInArray() throws Exception {
        mvc.perform(get("/api/tests")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].testName", is(test.getTestName())))
                .andExpect(jsonPath("$[0].testDescription", is(test.getTestDescription())))
                .andExpect(jsonPath("$[0].createDate", is(new SimpleDateFormat("dd-MM-yyyy").format(new Date()))))
                .andExpect(jsonPath("$[0].category.categoryName", is(category.getCategoryName())))
                .andExpect(jsonPath("$[0].user.firstName", is(user.getFirstName())))
                .andExpect(jsonPath("$[0].user.lastName", is(user.getLastName())))
                .andExpect(jsonPath("$[0].user.roles[0].name", is(role.getName())));
    }

    @Test
    public void whenGetTestById_thenReturnedTestWithTargetId() throws Exception {
        given(testService.findById(anyLong())).willReturn(test);
        mvc.perform(get("/api/tests/{testId}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is((test.getId().intValue()))));
    }

    @Test
    public void whenPostTest_thenResourceIsCreated() throws Exception {
        mvc.perform(post("/api/tests")
                .content(asJsonString(test))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.testName").exists())
                .andExpect(jsonPath("$.testDescription").exists())
                .andExpect(jsonPath("$.createDate").exists())
                .andExpect(jsonPath("$.category").exists())
                .andExpect(jsonPath("$.user").exists())
                .andExpect(jsonPath("$.steps").exists());
    }

    @Test
    public void whenUpdateTest_thenResourceIsUpdated() throws Exception {
        test.setTestName("new test name");
        test.setTestDescription("new description");
        mvc.perform(put("/api/tests")
                .content(asJsonString(test))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.testName", is(test.getTestName())))
                .andExpect(jsonPath("$.testDescription", is(test.getTestDescription())));
    }

    @Test
    public void whenDeleteTest_thenResourceIsDeleted() throws Exception {
        mvc.perform(delete("/api/tests/{testId}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isGone())
                .andExpect(content().string(containsString("deleted")));
    }

    @Test
    public void whenUnlinkStep_thenStepIsUnlinkedFromTest() throws Exception {
        given(stepService.findById(anyLong())).willReturn(step);
        mvc = MockMvcBuilders.standaloneSetup(new TestRestController(testService, stepService)).build();
        mvc.perform(delete("/api/tests/{testId}/steps/{stepId}", 1, 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("unlinked")));
    }

    @Test
    public void whenLinkStep_thenStepIsLinkedToTest() throws Exception {
        Step s = new Step("new Step");
        s.setId(2L);
        given(stepService.findById(2L)).willReturn(s);
        mvc = MockMvcBuilders.standaloneSetup(new TestRestController(testService, stepService)).build();
        mvc.perform(post("/api/tests/{testId}/steps/{stepId}", 1, 2))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.steps[*].id.stepId", hasItem(2)));
    }
}
