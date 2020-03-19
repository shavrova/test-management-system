package com.tms.generator.service;

import com.tms.model.entity.Test;
import com.tms.model.entity.TestStep;
import com.tms.wrapper.TestWrapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

import static com.tms.generator.util.FeatureFileUtil.createTempFeatureFileFrom;
import static com.tms.generator.util.JavaFileUtil.createTempJavaFileFrom;

@Service
public class FileServiceImpl implements FileService {


    @Override
    public File createFeatureFile(TestWrapper wrapper) {
        try {
            return createTempFeatureFileFrom(wrapper);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public File createJavaFile(TestWrapper wrapper) {
        try {
            return createTempJavaFileFrom(wrapper);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
