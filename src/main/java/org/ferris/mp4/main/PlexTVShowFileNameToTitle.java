package org.ferris.mp4.main;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.ferris.mp4.title.TitleChanger;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class PlexTVShowFileNameToTitle {
    public static void main(String[] args) throws Exception {
        File dir = new File("C:\\Users\\Michael\\Videos\\TV Shows\\Parents\\Homeland\\Season 05");
        List<File> files
            = Arrays.asList(
                dir.listFiles(f -> f.isFile() && f.getName().endsWith(".mp4"))
            );
        files.forEach(f -> {
            System.out.printf("Processing: %s%n", f.getName());
            // Paw Patrol - s01e11 - Pups Pit Crew
            String [] tokens
                = f.getName().split(" - ");
            String title
                = tokens[2].trim();
            title = title.substring(0, title.lastIndexOf(".")).trim();

            System.out.printf("Set title: \"%s\"%n", title);
            try {
                new TitleChanger(f).set(title);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        System.out.printf("DONE%n");
    }
}
