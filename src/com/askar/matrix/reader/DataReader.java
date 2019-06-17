package com.askar.matrix.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class DataReader {

    private Scanner in;

    public int readDataFromFile(String filePath) {
        File input = new File(filePath);
        int temp = 0;
        try {
            in = new Scanner(input);
            in.useLocale(Locale.US);
            if (in.hasNextInt()){
                temp = in.nextInt();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            in.close();
        }

        return temp;
    }
}
