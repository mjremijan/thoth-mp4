package org.ferris.mp4.main;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import org.ferris.mp4.metadata.MetadataChanger;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class PlexHomeVideoNameToTitle {
    public static void main(String[] args) {
        File dir = new File("D:\\Pictures\\_Staging\\12\\1.keep\\VP\\done");
        List<File> files
            = Arrays.asList(
                dir.listFiles(f -> f.isFile() && f.getName().endsWith(".mp4"))
            );
        files.forEach(f -> {
            //System.out.printf("Processing: %s%n", f.getName());
            // Finding Neverland (2004)
            String title
                = f.getName().substring(0, f.getName().indexOf(".mp4")).trim();

            try {
                System.out.printf("Set title: \"%s\"%n", title);
                new MetadataChanger(f).set(title);
            } catch (Exception ex) {
               ex.printStackTrace();
            }
        });
        System.out.printf("DONE%n");
    }
}
