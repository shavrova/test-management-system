package com.tms.generator.service;

import com.tms.wrapper.TestWrapper;

import java.io.File;

public interface FileService {

    File createFeatureFile(TestWrapper wrapper);

    File createJavaFile(TestWrapper wrapper);
}
