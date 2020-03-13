package com.test.management.system;

import com.test.management.system.controller.rest.StepsRestController;
import com.test.management.system.controller.rest.TestRestController;
import com.test.management.system.controller.ui.*;
import com.test.management.system.controller.user.UserRegistrationController;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TestManagementSystemApplicationTests {

    @Autowired
    private MainController mainController;
    @Autowired
    private StepsRestController stepsRestController;
    @Autowired
    private TestRestController testRestController;
    @Autowired
    private AdminController adminController;
    @Autowired
    private CategoryController categoryController;
    @Autowired
    private StepController stepController;
    @Autowired
    private TestController testController;
    @Autowired
    private UserRegistrationController userRegistrationController;

    @Tag("SMOKE")
    @Test
    void contextLoads() {
        assertThat(mainController).isNotNull();
        assertThat(stepsRestController).isNotNull();
        assertThat(testRestController).isNotNull();
        assertThat(adminController).isNotNull();
        assertThat(categoryController).isNotNull();
        assertThat(stepController).isNotNull();
        assertThat(testController).isNotNull();
        assertThat(userRegistrationController).isNotNull();
    }

}
