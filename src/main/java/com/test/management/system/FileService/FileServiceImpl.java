package com.test.management.system.FileService;

import com.test.management.system.entity.Test;
import com.test.management.system.entity.TestStep;
import com.test.management.system.wrappers.TestWrapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {


    @Override
    public File createFeatureFile(TestWrapper testWrapper) {
        try {
            return createTempFileFrom(testWrapper);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }


    private byte[] writeBytes(TestWrapper wrapper) {
        String fileContent = "";
        List<Test> tests = wrapper.getList();
        String annotations = wrapper.getAnnotations();
        if(annotations != null && !annotations.isEmpty()) {
            fileContent +=annotations+"\n";
        }
        String featureName = wrapper.getFeatureName();
        fileContent += "Feature: " + featureName;
        fileContent += "\n" + "\n";
        for (Test t : tests) {
            fileContent += "  Scenario: " + t.getTestName() + "\n";
            List<TestStep> steps = t.getSteps();
            for (TestStep ts : steps) {
                fileContent += "    " + ts.getStepUsecase() + " " + ts.getStep().getStepDescription() + "\n";
            }
            fileContent += "\n";
        }
        return fileContent.getBytes();
    }


    private File createTempFileFrom(TestWrapper testWrapper) throws IOException {
        File f = File.createTempFile("fileName", ".txt");
        f.deleteOnExit();
        try (OutputStream out = new FileOutputStream(f)) {
            out.write(writeBytes(testWrapper));
        }
        return f;
    }
}
