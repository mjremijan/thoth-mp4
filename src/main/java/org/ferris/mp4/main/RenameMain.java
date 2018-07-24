package org.ferris.mp4.main;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class RenameMain {
    public static void main(String[] args) throws Exception {
        File dir = new File("C:\\Users\\Michael\\Desktop\\Season 04");
        List<File> files
            = Arrays.asList(
                dir.listFiles(f -> f.isFile() && f.getName().endsWith(".mp4"))
            );
        files.forEach(f -> {
            System.out.printf("Processing: %s%n", f.getName());

            String newName = f.getName().substring(0, f.getName().lastIndexOf("-")).trim();
            newName += ".mp4";

            System.out.printf("Set new name: \"%s\"%n", newName);
                f.renameTo(new File(f.getParentFile(), newName));
        });
        System.out.printf("DONE%n");
    }
}
