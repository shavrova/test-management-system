import com.tms.model.entity.Category;
import com.tms.model.entity.Step;
import com.tms.model.entity.user.Role;
import com.tms.model.entity.user.User;
import com.tms.repository.TestRepository;
import com.tms.service.TestServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestServiceTest {

    @Mock
    TestRepository testRepository;


    @InjectMocks
    TestServiceImpl testService;


    private com.tms.model.entity.Test test;
    private Category category;
    private User user;
    private Step step;
    private Role role;


    @BeforeEach
    void setUp() {
        category = Category.builder().categoryName("Login").build();
        role = Role.builder().name("NEW_ROLE").build();
        user = User.builder()
                .firstName("First Name")
                .lastName("Last Name")
                .email("email@email.com")
                .password("password")
                .roles(Collections.singletonList(role))
                .build();
        user.setId(1L);
        step = Step.builder().stepDescription("My step description").build();
        step.setId(10L);
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

    @Test
    void findAll() {
        List<com.tms.model.entity.Test> returnedTests = new ArrayList<>();
        returnedTests.add(test);
        returnedTests.add(test);

        when(testRepository.findAll()).thenReturn(returnedTests);

        List<com.tms.model.entity.Test> tests = testService.findAll();

        assertNotNull(tests);
        assertEquals(2, tests.size());
    }

    @Test
    void findById() {
        when(testRepository.findById(anyLong())).thenReturn(Optional.of(test));
        com.tms.model.entity.Test test = testService.findById(1L);
        assertNotNull(test);
    }

    @Test
    void save() {
        when(testRepository.save(any())).thenReturn(test);
        com.tms.model.entity.Test savedTest = testService.save(test);
        assertNotNull(savedTest);
        verify(testRepository).save(any());
    }

    @Test
    void deleteById() {
        testService.deleteById(1L);
        verify(testRepository).deleteById(anyLong());
    }

    @Test
    void getSteps() {
        when(testRepository.getOne(any())).thenReturn(test);
        List<Step> returnedSteps = testService.getSteps(1L);
        assertNotNull(returnedSteps);
    }

    @Test
    void getUserTests() {
        List<com.tms.model.entity.Test> returnedTests = new ArrayList<>();
        returnedTests.add(test);
        returnedTests.add(test);
        when(testRepository.findAll()).thenReturn(returnedTests);
        List<com.tms.model.entity.Test> tests = testService.getUserTests(1L);
        assertEquals(tests.size(), 2);
    }

    @Test
    void getDeletedUserTests() {
        List<com.tms.model.entity.Test> returnedTests = new ArrayList<>();
        test.setUser(null);
        returnedTests.add(test);
        when(testRepository.findAll()).thenReturn(returnedTests);

        List<com.tms.model.entity.Test> tests = testService.getDeletedUserTests();
        assertEquals(tests.size(), 1);
    }
}


