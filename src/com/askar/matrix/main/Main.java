package com.askar.matrix.main;

import com.askar.matrix.entity.Changer;
import com.askar.matrix.report.Report;

import java.util.concurrent.CyclicBarrier;

public class Main {
    private static final int NUMBER_OF_THREADS = 5;

    public static void main(String[] args) throws InterruptedException {
        Report report = new Report();
        CyclicBarrier barrier = new CyclicBarrier(NUMBER_OF_THREADS);

        new Thread(new Changer("1", 0, barrier)).start();
        new Thread(new Changer("2", 1, barrier)).start();
        new Thread(new Changer("3", 2, barrier)).start();
        new Thread(new Changer("4", 3, barrier)).start();
        new Thread(new Changer("5", 4, barrier)).start();


        Thread.sleep(1000);
        report.printMatrix();
    }
}
