package com.tms.generator.util;

import com.tms.model.entity.Test;
import com.tms.model.entity.TestStep;
import com.tms.wrapper.TestWrapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class FeatureFileUtil {


    private static byte[] writeBytes(TestWrapper wrapper) {
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


    public static File createTempFeatureFileFrom(TestWrapper testWrapper) throws IOException {
        File f = File.createTempFile("fileName", ".feature");
        f.deleteOnExit();
        try (OutputStream out = new FileOutputStream(f)) {
            out.write(writeBytes(testWrapper));
        }
        return f;
    }
}
