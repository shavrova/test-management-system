package com.tms.generator.util;

import com.tms.model.entity.Test;
import com.tms.model.entity.TestStep;
import com.tms.wrapper.TestWrapper;
import org.apache.commons.text.CaseUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;


public class JavaFileUtil {

    private static byte[] writeBytes(TestWrapper wrapper) {
        String fileContent = "";
        List<Test> tests = wrapper.getList();

        String className = wrapper.getFeatureName();
        fileContent += "public class " + className + "Steps {";
        fileContent += "\n\n";

        for (Test t : tests) {
            List<TestStep> steps = t.getSteps();
            for (TestStep ts : steps) {
                fileContent += "    @" + ts.getStepUsecase() + "(\"^" + ts.getStep().getStepDescription() + "$\")" + "\n";
                fileContent += "    public void " +
                        CaseUtils.toCamelCase(ts.getStep().getStepDescription(),false) +
                        "(){\n    }\n\n";
            }
        }
        fileContent += "}\n";
        return fileContent.getBytes();
    }

    public static File createTempJavaFileFrom(TestWrapper testWrapper) throws IOException {
        File f = File.createTempFile("fileName", ".java");
        f.deleteOnExit();
        try (OutputStream out = new FileOutputStream(f)) {
            out.write(writeBytes(testWrapper));
        }
        return f;
    }
}
