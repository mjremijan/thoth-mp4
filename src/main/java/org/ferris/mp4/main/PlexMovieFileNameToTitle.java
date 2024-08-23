package org.ferris.mp4.main;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class PlexMovieFileNameToTitle {
    public static void main(String[] args) {
        File dir = new File("D:\\Videos\\TV Shows\\Kids\\DreamWorks Dragons (2012)\\Season 01");
        List<File> files
            = Arrays.asList(
                dir.listFiles(f -> f.isFile() && f.getName().endsWith(".mp4"))
            );
        files.forEach(f -> {
            //System.out.printf("Processing: %s%n", f.getName());
            // Finding Neverland (2004)
            String title
                = f.getName().substring(0, f.getName().indexOf("(") - 1).trim();

            try {
                System.out.printf("Set title: \"%s\"%n", title);
                //new TitleChanger(f).set(title);
            } catch (Exception ex) {
               ex.printStackTrace();
            }
        });
        System.out.printf("DONE%n");
    }
}
