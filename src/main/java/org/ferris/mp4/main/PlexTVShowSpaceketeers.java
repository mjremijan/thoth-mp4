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
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class PlexTVShowSpaceketeers {

    public static void main(String[] args) throws Exception {
        File dir;
        List<Calendar> dates;
        {
            dir = new File("D:\\Videos\\Edits\\Spaceketeers");

            int year = 1978;
            dates = new ArrayList<Calendar>() {
                {
                    add(new GregorianCalendar(year, GregorianCalendar.APRIL, 2));
                    add(new GregorianCalendar(year, GregorianCalendar.APRIL, 9));
                    add(new GregorianCalendar(year, GregorianCalendar.APRIL, 16));
                    add(new GregorianCalendar(year, GregorianCalendar.APRIL, 23));
                    add(new GregorianCalendar(year, GregorianCalendar.APRIL, 30));

                    add(new GregorianCalendar(year, GregorianCalendar.MAY, 7));
                    add(new GregorianCalendar(year, GregorianCalendar.MAY, 14));
                    add(new GregorianCalendar(year, GregorianCalendar.MAY, 21));

                    add(new GregorianCalendar(year, GregorianCalendar.JUNE, 11));
                    add(new GregorianCalendar(year, GregorianCalendar.JUNE, 18));
                    add(new GregorianCalendar(year, GregorianCalendar.JUNE, 25));

                    add(new GregorianCalendar(year, GregorianCalendar.JULY, 2));
                    add(new GregorianCalendar(year, GregorianCalendar.JULY, 9));

                    add(new GregorianCalendar(year, GregorianCalendar.AUGUST, 6));

                    add(new GregorianCalendar(year, GregorianCalendar.SEPTEMBER, 10));
                    add(new GregorianCalendar(year, GregorianCalendar.SEPTEMBER, 17));
                    add(new GregorianCalendar(year, GregorianCalendar.SEPTEMBER, 24));

                    add(new GregorianCalendar(year, GregorianCalendar.OCTOBER, 1));
                    add(new GregorianCalendar(year, GregorianCalendar.OCTOBER, 8));
                    add(new GregorianCalendar(year, GregorianCalendar.OCTOBER, 15));

                    add(new GregorianCalendar(year, GregorianCalendar.NOVEMBER, 12));
                    add(new GregorianCalendar(year, GregorianCalendar.NOVEMBER, 19));
                }
            };
        }

        List<File> files = Arrays.asList(
                dir.listFiles(f -> f.isFile() && f.getName().endsWith(".mp4"))
        );

        if (files.size() != dates.size()) {
            throw new RuntimeException("Files list size is not the same as dates list size.");
        }

        for (int i = 0; i < files.size(); i++) {
            File f = files.get(i);
            Calendar c = dates.get(i);

            System.out.printf("PROCESSING%n");
            System.out.printf(" File: %s%n", f.getName());
            System.out.printf(" Year: %d%n", c.get(Calendar.YEAR));

            // Spaceketeers - s01e01 - The Journey to the Great Planet 飛べ!オーロラ姫 (1978-04-02)
            String fileName = f.getName();

            // The Journey to the Great Planet
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
    
    private static String getTitleInEnglish(String fileName) {
        // Spaceketeers - s01e01 - The Journey to the Great Planet 飛べ!オーロラ姫 (1978-04-02)
        String[] tokens
                = fileName.split(" - ")[2].split("\\(");
        String both = tokens[0];

        StringBuilder sp = new StringBuilder();
        for (char c : both.toCharArray()) {
            if (isJapanese(c)) {
                break;
            } else {
                sp.append(c);
            }
        }
        return sp.toString().trim();
    }

    private static boolean isJapanese(char c) {
        return
               (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS)
            || (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A)
            || (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B)
            || (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS)
            || (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS)
            || (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.CJK_RADICALS_SUPPLEMENT)
            || (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION)
            || (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.ENCLOSED_CJK_LETTERS_AND_MONTHS)
            || (Character.UnicodeBlock.of(c)==Character.UnicodeBlock.HIRAGANA)
            || (Character.UnicodeBlock.of(c)==Character.UnicodeBlock.KATAKANA)
        ;
    }
}
