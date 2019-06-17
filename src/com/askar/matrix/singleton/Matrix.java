package com.askar.matrix.singleton;

import com.askar.matrix.entity.Field;
import com.askar.matrix.reader.DataReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Matrix {

    private static final Logger LOGGER = LogManager.getLogger(Matrix.class);
    private Field[][] matrix;
    private DataReader dataReader = new DataReader();
    private Random random = new Random();
    private static final String ARRAY_SIZE_FILE = "data/size.txt";
    private static final int MAX_ARRAY_NUMBER = 20;
    private static final int MIN_ARRAY_NUMBER = 10;

    private Matrix() {
        initArray();
    }

    private static class SingletonHolder {
        public static final Matrix instance = new Matrix();
    }

    public static Matrix getInstance() {
        return SingletonHolder.instance;
    }

    private void initArray() {
        int n = dataReader.readDataFromFile(ARRAY_SIZE_FILE);
        LOGGER.info("size of matrix" + ":" + n);
        matrix = new Field[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    matrix[i][j] = new Field(0);
                } else {
                    int temp = ThreadLocalRandom.current().nextInt(MIN_ARRAY_NUMBER, MAX_ARRAY_NUMBER + 1);
                    matrix[i][j] = new Field(temp);
                }
            }
        }
    }

    public Field[][] getMatrix() {
        return matrix;
    }

}
