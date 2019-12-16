package feature.generator.service;

import com.test.management.system.util.wrapper.TestWrapper;

import java.io.File;

public interface FileService {

    File createFeatureFile(TestWrapper testWrapper);
}
