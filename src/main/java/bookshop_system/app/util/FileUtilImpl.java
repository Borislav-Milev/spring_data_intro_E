package bookshop_system.app.util;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class FileUtilImpl implements FileUtil {

    @Override
    public Set<String> readFileContent(String filePath) {
        File file = new File(filePath);
        Set<String> result = new LinkedHashSet<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while (true){
                line = reader.readLine();
                if(line == null) break;
                if(line.equals("")) continue;
                result.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
