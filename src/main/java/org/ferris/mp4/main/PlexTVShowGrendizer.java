package org.ferris.mp4.main;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.ferris.mp4.metadata.MetadataChanger;

/**
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class PlexTVShowGrendizer {

    public static void main(String[] args) throws Exception {
        File dir
            = new File("D:\\Videos\\Edits\\UFO Robo Grendizer\\Season 01");

        List<File> files = Arrays.asList(
                dir.listFiles(f -> f.isFile() && f.getName().endsWith(".mp4"))
        );

        for (int i = 0; i < files.size(); i++) {
            File f = files.get(i);

            System.out.printf("PROCESSING%n");
            System.out.printf(" File: %s%n", f.getName());

            // UFO Robo Grendizer (1975-10-05) - s01e01 - Koji Kabuto and Duke Fleed [兜甲児とデュークフリード]
            String fileName = f.getName();
            
            // (1975-10-05)
            Calendar c = getCalendar(fileName);
            System.out.printf(" Calendar: \"%s\"%n", c);
            
            // Koji Kabuto and Duke Fleed
            String titleInEnglish = getTitleInEnglish(fileName);
            System.out.printf(" Title in English: \"%s\"%n", titleInEnglish);
            
            try {
                new MetadataChanger(f).set(
                      new MetadataChanger.Title(titleInEnglish)
                    , new MetadataChanger.Year(c.getTime())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        System.out.printf("DONE%n");
    }
    
    private static Calendar getCalendar(String fileName) {
        // UFO Robo Grendizer (1975-10-05) - s01e01 - Koji Kabuto and Duke Fleed [兜甲児とデュークフリード]
        int srt = fileName.indexOf("(");
        int end = fileName.indexOf(")", srt);
        String[] yyyymmdd = fileName.substring(srt+1, end).split("-");
        
        return new GregorianCalendar(
              Integer.parseInt(yyyymmdd[0])
            , Integer.parseInt(yyyymmdd[1]) - 1
            , Integer.parseInt(yyyymmdd[2])
        );
    }
    
    private static String getTitleInEnglish(String fileName) {
        // UFO Robo Grendizer (1975-10-05) - s01e01 - Koji Kabuto and Duke Fleed [兜甲児とデュークフリード]
        return fileName.split(" - ")[2].split("\\[")[0].trim();
    }
}
