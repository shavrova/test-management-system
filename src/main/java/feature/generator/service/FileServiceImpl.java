package feature.generator.service;

import com.test.management.system.entity.Test;
import com.test.management.system.entity.TestStep;
import com.test.management.system.util.wrapper.TestWrapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import static feature.generator.util.FeatureFileUtil.createTempFeatureFileFrom;
import static feature.generator.util.JavaFileUtil.createTempJavaFileFrom;

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
