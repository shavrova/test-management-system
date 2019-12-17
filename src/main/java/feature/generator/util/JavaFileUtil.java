package feature.generator.util;

import com.test.management.system.entity.Test;
import com.test.management.system.entity.TestStep;
import com.test.management.system.util.wrapper.TestWrapper;
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
