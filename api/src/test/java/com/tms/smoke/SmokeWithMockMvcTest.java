//package com.tms.smoke;
//
//
//import static org.hamcrest.Matchers.containsString;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//import com.tms.controller.ui.TestController;
//import com.tms.service.StepService;
//import com.tms.service.TestService;
//import com.tms.service.user.UserService;
//import org.junit.jupiter.api.Test;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class SmokeWithMockMvcTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//    @MockBean
//    private TestService testService;
//    @MockBean
//    private StepService stepService;
//    @MockBean
//    private UserService userService;
//    @MockBean
//    private TestController testController;
//
//    @Test
//    public void whenGetLoginUrl_thenPageShouldBeReturned() throws Exception {
//        mockMvc.perform(get("/login")).andDo(print()).andExpect(status().isOk())
//                .andExpect(content().string(containsString("Login page")));
//    }
//
//    @Test
//    public void whenGetRegisterUrl_thenPageShouldBeReturned() throws Exception {
//        mockMvc.perform(get("/registration")).andDo(print()).andExpect(status().isOk())
//                .andExpect(content().string(containsString("Registration")));
//    }
//
//    @WithMockUser(value = "spring")
//    @Test
//    public void whenGetMyTestsUrl_thenPageShouldBeReturned() throws Exception {
//        mockMvc.perform(get("/user")).andDo(print())
//                .andExpect(redirectedUrl("myTests"))
//                .andExpect(status().isFound())
//                .andExpect(content().string(containsString("Add new test quickly")));
//    }
//}
