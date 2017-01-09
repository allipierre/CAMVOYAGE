/**
 * 
 */
package com.gluonapplication.views;

/**
 * @author yotti
 *
 */



import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.gluonapplication.views.DatabaseTestPresenter;

public class DBUtils {

    public static void copyDatabase(String pathIni, String pathEnd, String name)  {

        try (InputStream myInput = DatabaseTestPresenter.class.getResourceAsStream(pathIni+name)) {
            String outFileName =  pathEnd + "/" + name;
            try (OutputStream myOutput = new FileOutputStream(outFileName)) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }
                myOutput.flush();

            } catch (IOException ex) {
                System.out.println("Error " + ex);
            }
        } catch (IOException ex) {
            System.out.println("Error " + ex);
        }
    }
}
