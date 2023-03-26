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
 */
public class PlexTVShowFileNameToTitle {
    public static void main(String[] args) throws Exception {
        File dir;
        List<Calendar> dates;
        {
            dir = new File("D:\\Videos\\TV Shows\\Parents\\Samurai Jack\\Season 05");
           
            int year = 2017;
            dates = new ArrayList<Calendar>() {{
                add(new GregorianCalendar(year, GregorianCalendar.MARCH, 11));
                add(new GregorianCalendar(year, GregorianCalendar.MARCH, 18));
                add(new GregorianCalendar(year, GregorianCalendar.MARCH, 25));
                add(new GregorianCalendar(year, GregorianCalendar.APRIL, 8));
                add(new GregorianCalendar(year, GregorianCalendar.APRIL, 15));
                add(new GregorianCalendar(year, GregorianCalendar.APRIL, 22));
                add(new GregorianCalendar(year, GregorianCalendar.APRIL, 29));
                add(new GregorianCalendar(year, GregorianCalendar.MAY, 6));
                add(new GregorianCalendar(year, GregorianCalendar.MAY, 13));
                add(new GregorianCalendar(year, GregorianCalendar.MAY, 20));
            }};
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
