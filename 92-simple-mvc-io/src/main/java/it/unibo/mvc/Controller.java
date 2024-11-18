package it.unibo.mvc;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {
    private static final String SEP = System.getProperty("file.separator");
    private static final String PATH = System.getProperty("user.home") + SEP + "output.txt";

    private File myFile;

    /**
     * 
     * @param path
     */
    public Controller(final String path) {
        myFile = new File(path);
    }

    /**
     * to do.
     */
    public Controller() {
        myFile = new File(PATH);
    }

    /**
     * 
     * @param path
     */
    public void setMyFile(final String path) {
        myFile = new File(path);
    }

    /**
     * 
     * @return my file path.
     */
    public String getPathMyFile() {
        return myFile.getAbsolutePath();
    }

    /**
     * 
     * @return my file.
     */
    public File getFile() {

        return myFile;
    }

    /**
     * 
     * @param text path for my file.
     */
    public void writeOnFile(final String text) {

        try (
                DataOutputStream dbstrem = new DataOutputStream(
                        new BufferedOutputStream(
                                new FileOutputStream(myFile)))) {
            dbstrem.writeUTF(text);
        } catch (IOException e) {
            System.out.println("Errore"); // NOPMD suppressed as it is a false positive
        }
    }

}
