package com.test.management.system.FileService;

import com.test.management.system.wrappers.TestWrapper;

import java.io.File;

public interface FileService {

    File createFeatureFile(TestWrapper testWrapper);
}
