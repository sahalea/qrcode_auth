package com.sahel.qrauth.util;
import java.io.File;

/**
 * The class FileUtils
 *
 * @author Sahel
 * @version 1.0
 * @since 30 May 2020
 */

public class FileUtils {

    private FileUtils() {
        throw new AssertionError();
    }

    public static boolean createDirectory(String path) {
        File file = new File(path);
        if (!file.exists()) {
            if (file.mkdirs()) {
                return true;
            } else {
                return false;
            }
        } else {
            System.out.println("directory already exists.");
            return true;
        }
    }

}
