/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package helpers;

import java.io.IOException;
import java.util.*;
import java.io.*;
import java.lang.*;

/**
 *
 * @author Admin
 */
public class StoreHelper
{
    public static String saveToTempFile(java.io.InputStream in) throws java.io.IOException
    {
        UUID uuid = UUID.randomUUID();
        File tempFile = File.createTempFile(uuid.toString(), ".tmp");
        java.io.OutputStream outFile = new java.io.FileOutputStream(tempFile);

        int countBuffer = 30000;
        byte[] buffer  = new byte[countBuffer];

        int readedBytes = 0;

        while ((readedBytes = in.read(buffer)) >= 0)
        {
            outFile.write(buffer, 0,readedBytes);
        }

        outFile.flush();
        outFile.close();

        return tempFile.getName();

    }
    public static java.io.InputStream getTempFile(String uuid) throws java.io.FileNotFoundException
    {
        String tempDir = System.getProperty("java.io.tmpdir");
        File tempFile = new java.io.File(tempDir+uuid.toString());
        return new java.io.FileInputStream(tempFile);
    }

}
