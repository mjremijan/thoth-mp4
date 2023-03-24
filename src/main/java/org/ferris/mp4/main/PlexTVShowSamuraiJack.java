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
public class PlexTVShowSamuraiJack {
    public static void main(String[] args) throws Exception {
        File dir;
        List<Calendar> dates;
//        {
//            dir = new File("D:\\Videos\\TV Shows\\Parents\\Samurai Jack\\Season 01");
//           
//            int year = 2001;
//            dates = new ArrayList<Calendar>() {{
//                add(new GregorianCalendar(year, GregorianCalendar.AUGUST, 10));
//                add(new GregorianCalendar(year, GregorianCalendar.AUGUST, 10));
//                add(new GregorianCalendar(year, GregorianCalendar.AUGUST, 10));
//                add(new GregorianCalendar(year, GregorianCalendar.AUGUST, 13));
//                add(new GregorianCalendar(year, GregorianCalendar.AUGUST, 27));
//                add(new GregorianCalendar(year, GregorianCalendar.NOVEMBER, 19));
//                add(new GregorianCalendar(year, GregorianCalendar.AUGUST, 20));
//                add(new GregorianCalendar(year, GregorianCalendar.OCTOBER, 15));
//                add(new GregorianCalendar(year, GregorianCalendar.SEPTEMBER, 3));
//                add(new GregorianCalendar(year, GregorianCalendar.OCTOBER, 12));
//                add(new GregorianCalendar(year, GregorianCalendar.OCTOBER, 29));
//                add(new GregorianCalendar(year, GregorianCalendar.NOVEMBER, 26));
//                add(new GregorianCalendar(year, GregorianCalendar.DECEMBER, 3));
//            }};
//        }
        {
            dir = new File("D:\\Videos\\TV Shows\\Parents\\Samurai Jack\\Season 02");
           
            int year = 2002;
            dates = new ArrayList<Calendar>() {{
                add(new GregorianCalendar(year, GregorianCalendar.MARCH, 1));
                add(new GregorianCalendar(year, GregorianCalendar.MARCH, 8));
                add(new GregorianCalendar(year, GregorianCalendar.MARCH, 15));
                add(new GregorianCalendar(year, GregorianCalendar.MARCH, 22));
                add(new GregorianCalendar(year, GregorianCalendar.MARCH, 29));
                add(new GregorianCalendar(year, GregorianCalendar.APRIL, 5));
                add(new GregorianCalendar(year, GregorianCalendar.APRIL, 12));
                add(new GregorianCalendar(year, GregorianCalendar.SEPTEMBER, 6));
                add(new GregorianCalendar(year, GregorianCalendar.SEPTEMBER, 13));
                add(new GregorianCalendar(year, GregorianCalendar.SEPTEMBER, 20));
                add(new GregorianCalendar(year, GregorianCalendar.SEPTEMBER, 27));
                add(new GregorianCalendar(year, GregorianCalendar.OCTOBER, 4));
                add(new GregorianCalendar(year, GregorianCalendar.OCTOBER, 11));
            }};
        }
//        {
//            dir = new File("D:\\Videos\\TV Shows\\Parents\\Samurai Jack\\Season 03");
//           
//            int year = 2002;
//            dates = new ArrayList<Calendar>() {{
//                add(new GregorianCalendar(year, GregorianCalendar.OCTOBER, 18));
//                add(new GregorianCalendar(year, GregorianCalendar.NOVEMBER, 1));
//                add(new GregorianCalendar(year, GregorianCalendar.NOVEMBER, 8));
//                add(new GregorianCalendar(year, GregorianCalendar.OCTOBER, 25));
//                add(new GregorianCalendar(year, GregorianCalendar.NOVEMBER, 22));
//                add(new GregorianCalendar(year, GregorianCalendar.APRIL, 26));
//                add(new GregorianCalendar(year, GregorianCalendar.MAY, 3));
//                add(new GregorianCalendar(year, GregorianCalendar.MAY, 10));
//                add(new GregorianCalendar(year, GregorianCalendar.MAY, 17));
//                add(new GregorianCalendar(year, GregorianCalendar.MAY, 31));
//                add(new GregorianCalendar(year, GregorianCalendar.AUGUST, 16));
//                add(new GregorianCalendar(year, GregorianCalendar.AUGUST, 16));
//                add(new GregorianCalendar(year, GregorianCalendar.JUNE, 7));
//            }};
//        }
        
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
