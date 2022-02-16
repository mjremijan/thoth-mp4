package org.ferris.mp4.main;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.ferris.mp4.title.TitleChanger;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 * 
 * TEST
 */
public class PlexTVShowFileNameToTitle {
    public static void main(String[] args) throws Exception {
        File dir = new File("D:\\Desktop\\DuckTales");
        List<File> files = null;
        
        files = Arrays.asList(
            dir.listFiles(f -> f.isFile() && f.getName().endsWith(".mp4"))
        );
        files.forEach (f -> {
            System.out.printf("Renaming: %s%n", f.getName());
            
            // Chip 'n Dale Rescue Rangers - s02e04 - Rescue Rangers to the Rescue Part Four -17.mp4
            String name
                = f.getName();
            int lastDashIndex
                = name.lastIndexOf("-");
            if (lastDashIndex > 0) {
                String prefix = name.substring(0, lastDashIndex).trim();
                File dest = new File(dir, prefix + ".mp4");
                System.out.printf("Rename to: \"%s\"\n", dest.getName());
                f.renameTo(dest);
            }
        });
        
        files = Arrays.asList(
            dir.listFiles(f -> f.isFile() && f.getName().endsWith(".mp4"))
        );
        files.forEach(f -> {
            System.out.printf("Setting titles: %s%n", f.getName());
            // Paw Patrol - s01e11 - Pups Pit Crew.mp4
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
