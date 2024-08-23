package org.ferris.mp4.main;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import org.ferris.mp4.metadata.MetadataChanger;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class PlexTVShowFileNameToTitle {
    public static void main(String[] args) throws Exception {
        withDates();
        //withoutDates();
    }
    
//    private static void withoutDates() throws Exception 
//    {
//        List<File> files = Arrays.asList(
//            new File("D:\\Videos\\TV Shows\\Parents\\Snowpiercer (2020)\\Season 02")
//                .listFiles(f -> f.isFile() && f.getName().endsWith(".mp4"))
//        );                
//        
//        for (int i=0; i<files.size(); i++)
//        {
//            File f = files.get(i);
//            
//            System.out.printf("PROCESSING%n");
//            System.out.printf(" file: %s%n", f.getName());
//            
//            String [] tokens
//                = f.getName().split(" - ");
//            
//            String title;
//            {
//                title = tokens[tokens.length - 1].trim();
//                title = title.substring(0, title.lastIndexOf(".")).trim();
//                System.out.printf(" title: \"%s\"%n%n", title);
//            }
//           
//            try {
//                new MetadataChanger(f).set(
//                      new MetadataChanger.Title(title)   
//                );
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            }
//        }
//        System.out.printf("DONE%n");
//    }
    
    private static void withDates() throws Exception 
    {
        File dir = null;
        List<Calendar> dates = Collections.EMPTY_LIST;
        {
            dir = new File("D:\\Videos\\TV Shows\\Parents\\Snowpiercer (2020)\\Season 02");
           
            int year = 2021;
            dates = Arrays.asList(
                new GregorianCalendar(year, Calendar.JANUARY, 25)
              , new GregorianCalendar(year, Calendar.FEBRUARY, 1)
              , new GregorianCalendar(year, Calendar.FEBRUARY, 8)
              , new GregorianCalendar(year, Calendar.FEBRUARY, 15)
              , new GregorianCalendar(year, Calendar.FEBRUARY, 22)
              , new GregorianCalendar(year, Calendar.MARCH, 1)
              , new GregorianCalendar(year, Calendar.MARCH, 8)
              , new GregorianCalendar(year, Calendar.MARCH, 15)
              , new GregorianCalendar(year, Calendar.MARCH, 29)
              , new GregorianCalendar(year, Calendar.MARCH, 29)
          );
        }
        
        List<File> files = Arrays.asList(
            dir.listFiles(f -> f.isFile() && f.getName().endsWith(".mp4"))
        );
        
        for (int i=0; i<files.size(); i++)
        {
            File f = files.get(i);
            Calendar c = dates.get(i);
            
            System.out.printf("PROCESSING%n");
            System.out.printf(" file: %s%n", f.getName());
            System.out.printf(" year: %d%n", c.get(Calendar.YEAR));

            String [] tokens
                = f.getName().split(" - ");
            
            String title;
            {
                title = tokens[2].trim();
                title = title.substring(0, title.lastIndexOf(".")).trim();
                System.out.printf(" title: \"%s\"%n%n", title);
            }

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
