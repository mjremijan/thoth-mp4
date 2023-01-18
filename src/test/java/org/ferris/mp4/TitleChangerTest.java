package org.ferris.mp4;

import java.io.File;
import java.io.IOException;
import org.ferris.mp4.metadata.MetadataChanger;
import org.junit.Test;

/**
 *  *
 *  * @author Michael Remijan mjremijan@yahoo.com @mjremijan  
 */
public class TitleChangerTest {

    @Test
    public void testMe() throws IOException {
        // setup
        File mp4
            = new File("src/test/resources/flag.mp4");

        MetadataChanger titleChanger
            = new MetadataChanger(mp4);

        // action
        titleChanger.set(String.format("junit %d", System.currentTimeMillis()));
    }
}
