package Algorithms.Stanford;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by kashishtayal on 12/20/16.
 */
public final class FIleReaderUtils {
    public static List<String> fileReader(URL path) throws FileNotFoundException {
        File file = new File(path.getFile());
        Scanner scanner = new Scanner(file);
        List<String> lines = new ArrayList<>();
        while(scanner.hasNextLine()){
            lines.add(scanner.nextLine());
        }
        return lines;
    }
}
