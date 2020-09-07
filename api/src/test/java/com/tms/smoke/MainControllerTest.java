package com.tms.smoke;

import com.tms.controller.ui.MainController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class MainControllerTest {



    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private MainController mainController;

//    @Autowired
//    private WebApplicationContext webApplicationContext;

//
//    @BeforeEach
//    public void setUp() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//    }

    @Test
    public void contexLoads() throws Exception {
        assertNotNull(mainController);
    }

    @Test
    public void showHomePage_ShouldRenderHomePage() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
               // .andExpect(view().name("login"))
                .andExpect(forwardedUrl("/templates/login.html"));
    }
}
