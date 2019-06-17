package com.askar.matrix.entity;

import com.askar.matrix.reader.DataReader;
import com.askar.matrix.singleton.Matrix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Changer implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(Changer.class);
    private DataReader dataReader = new DataReader();
    private String name;
    private Matrix matrix = Matrix.getInstance();
    private String filePath;
    private int changes = 0;
    private CyclicBarrier cyclicBarrier;

    public Changer(String name, int fileNumber, CyclicBarrier cyclicBarrier) {
        this.name = name;
        this.filePath = "data/thread" + fileNumber + ".txt";
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        for (int i = 0; i < matrix.getMatrix().length; i++) {
            for (int j = 0; j < matrix.getMatrix().length; j++) {
                if (changes == 1) {
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        LOGGER.error(e);
                    }
                }
                matrix.getMatrix()[i][j].getLock().lock();
                try {
                    if (i == j && (!matrix.getMatrix()[i][j].getChanged())) {
                        int number = dataReader.readDataFromFile(filePath);
                        matrix.getMatrix()[i][j].setNumber(number);
                        LOGGER.info(name + " thread changed" + " matrix [" + i + "][" + j + "] to:" + number);
                        matrix.getMatrix()[i][j].setChanged(true);
                        changes++;
                    }
                } finally {
                    matrix.getMatrix()[i][j].getLock().unlock();
                }
            }
        }
    }
}
