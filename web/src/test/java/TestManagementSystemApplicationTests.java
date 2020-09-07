import com.tms.TestManagementSystemApplication;
import com.tms.controller.ui.*;
import com.tms.controller.user.UserRegistrationController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = TestManagementSystemApplication.class)
class TestManagementSystemApplicationTests {

    @Autowired
    private MainController mainController;
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
        Assertions.assertThat(mainController).isNotNull();
        Assertions.assertThat(adminController).isNotNull();
        Assertions.assertThat(categoryController).isNotNull();
        Assertions.assertThat(stepController).isNotNull();
        Assertions.assertThat(testController).isNotNull();
        Assertions.assertThat(userRegistrationController).isNotNull();
    }

}
