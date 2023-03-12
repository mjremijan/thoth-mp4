package org.ferris.mp4.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.ferris.mp4.metadata.MetadataChanger;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 * 
 * TEST
 * 
 * TEST 2
 */
public class PlexTVShowFileNameToTitle {
    public static void main(String[] args) throws Exception {
        File dir;
        List<Calendar> dates;
        {
            dir = new File("D:\\Videos\\TV Shows\\Parents\\Samurai Jack\\Season 01");
           
            int year = 2021;
            dates = new ArrayList<Calendar>() {{
                add(new GregorianCalendar(year, GregorianCalendar.AUGUST, 10));
                add(new GregorianCalendar(year, GregorianCalendar.AUGUST, 10));
                add(new GregorianCalendar(year, GregorianCalendar.AUGUST, 10));
                add(new GregorianCalendar(year, GregorianCalendar.AUGUST, 13));
                add(new GregorianCalendar(year, GregorianCalendar.AUGUST, 27));
                add(new GregorianCalendar(year, GregorianCalendar.NOVEMBER, 19));
                add(new GregorianCalendar(year, GregorianCalendar.AUGUST, 20));
                add(new GregorianCalendar(year, GregorianCalendar.OCTOBER, 15));
                add(new GregorianCalendar(year, GregorianCalendar.SEPTEMBER, 3));
                add(new GregorianCalendar(year, GregorianCalendar.OCTOBER, 12));
                add(new GregorianCalendar(year, GregorianCalendar.OCTOBER, 29));
                add(new GregorianCalendar(year, GregorianCalendar.NOVEMBER, 26));
                add(new GregorianCalendar(year, GregorianCalendar.DECEMBER, 3));
            }};
        }
        
        List<File> files = Arrays.asList(
            dir.listFiles(f -> f.isFile() && f.getName().endsWith(".mp4"))
        );
        
                
        
//        files.forEach (f -> {
//            System.out.printf("Renaming: %s%n", f.getName());
//            
//            // Chip 'n Dale Rescue Rangers - s02e04 - Rescue Rangers to the Rescue Part Four -17.mp4
//            String name
//                = f.getName();
//            int lastDashIndex
//                = name.lastIndexOf("-");
//            if (lastDashIndex > 0) {
//                String prefix = name.substring(0, lastDashIndex).trim();
//                File dest = new File(dir, prefix + ".mp4");
//                System.out.printf("Rename to: \"%s\"\n", dest.getName());
//                f.renameTo(dest);
//            }
//        });
        
        
        for (int i=0; i<files.size(); i++)
        {
            File f = files.get(i);
            Calendar c = dates.get(i);
            
            System.out.printf("PROCESSING%n");
            System.out.printf(" file: %s%n", f.getName());
            System.out.printf(" year: %d%n", c.get(Calendar.YEAR));

            String [] tokens
                = f.getName().split(" - ");
            String title
                = tokens[2].trim();
            title = title.substring(0, title.lastIndexOf(".")).trim();
            // Samurai jack
            {
                title = title.substring(
                        title.indexOf(" ") + 1
                );
            }    
            System.out.printf(" title: \"%s\"%n%n", title);
            try {
                new MetadataChanger(f).set(
                      new MetadataChanger.Title(title)
                    , new MetadataChanger.Year(c.getTime())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        System.out.printf("DONE%n");
    }
}
