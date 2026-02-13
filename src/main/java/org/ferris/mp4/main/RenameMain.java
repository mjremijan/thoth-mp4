package org.ferris.mp4.main;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class RenameMain {
    public static void main(String[] args) throws Exception {
        File dir = new File("D:\\Videos\\TV Shows\\Parents\\Tron Uprising\\Season 01");
        List<File> files
            = Arrays.asList(
                dir.listFiles(f -> f.isFile() && f.getName().endsWith(".mkv"))
            );
        
        Map<String, String> map = Map.ofEntries(
              Map.entry("0101", "Beck's Beginning|2012-05-20")
            , Map.entry("0102", "The Renegade - Part 1|2012-06-07")
            , Map.entry("0103", "The Renegade - Part 2|2012-06-14")
            , Map.entry("0104", "Blackout|2012-06-21")
            , Map.entry("0105", "Identity|2012-06-28")
            , Map.entry("0106", "Isolated|2012-07-05")
            , Map.entry("0107", "Price of Power|2012-07-12")
            , Map.entry("0108", "The Reward|2012-10-19")
            , Map.entry("0109", "Scars, Part 1|2012-10-26")
            , Map.entry("0110", "Scars, Part 2|2012-11-02")
            , Map.entry("0111", "Grounded|2012-12-03")
            , Map.entry("0112", "We Both Know How This Ends|2012-12-10")
            , Map.entry("0113", "The Stranger|2012-12-17")
            , Map.entry("0114", "Tagged|2012-12-24")
            , Map.entry("0115", "State of Mind|2012-12-31")
            , Map.entry("0116", "Welcome Home|2013-01-07")
            , Map.entry("0117", "Rendezvous|2013-01-14")
            , Map.entry("0118", "No Bounds|2013-01-21")
            , Map.entry("0119", "Terminal|2013-01-28")
        );

        
        files.forEach(f -> {
            System.out.printf("Processing: %s%n", f.getName());
            
            // determine season and episode numbers
            String season, episode;
            {
                Pattern pattern = Pattern.compile("S(\\d{2})E(\\d{2})");
                Matcher matcher = pattern.matcher(f.getName());
                if (matcher.find()) {
                    season = matcher.group(1);  // "01"
                    episode = matcher.group(2); // "13"
                } else {
                    throw new RuntimeException("No regex match on " + f.getName());
                }
            }
            
            // determine title and date
            String title, date, key=season+episode;            
            if (map.containsKey(key)) {
                String[] tokens = map.get(key).split("\\|");
                title = tokens[0];
                date = tokens[1];
            } else {
                throw new RuntimeException("No value found for key " + key);
            }

                
            // generate new name
            String newName = String.format(
                "Tron Uprising (%s) - s%se%s - %s.mkv"
                , date, season, episode, title
            );

            // rename the file
            System.out.printf("Set new name: \"%s\"%n", newName);
            f.renameTo(new File(f.getParentFile(), newName));
        });
        System.out.printf("DONE%n");
    }
}
