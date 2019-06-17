package com.askar.matrix.entity;

import com.askar.matrix.reader.DataReader;
import com.askar.matrix.singleton.Matrix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Changer implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(Changer.class);
    private DataReader dataReader = new DataReader();
    private String name;
    private Matrix matrix = Matrix.getInstance();
    private boolean run = true;
    private String filePath;
    private int c = 0;
    private Semaphore semaphore;

    public Changer(String name, int fileNumber, Semaphore semaphore) {
        this.name = name;
        this.semaphore = semaphore;
        this.filePath = "data/thread" + fileNumber + ".txt";
    }

    @Override
    public void run() {
        while (run) {
            for (int i = 0; i < matrix.getMatrix().length; i++) {
                for (int j = 0; j < matrix.getMatrix().length; j++) {
                    try {
                        semaphore.acquire();
                    } catch (InterruptedException e) {
                        LOGGER.error(e);
                    }
                    if (c < 2 && i == j && (!matrix.getMatrix()[i][j].getChanged())){
                        int number = dataReader.readDataFromFile(filePath);
                        matrix.getMatrix()[i][j].setNumber(number);
                        LOGGER.info(name + " thread changed" + " matrix [" + i + "][" + j + "] to:" + number);
                        matrix.getMatrix()[i][j].setChanged(true);
                        c++;
                    } else{
                        run = false;
                    }
                    semaphore.release();
                }
            }
        }
    }
}
