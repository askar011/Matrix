package com.askar.matrix.report;

import com.askar.matrix.singleton.Matrix;

public class Report {

    private Matrix matrix = Matrix.getInstance();

    public void printMatrix() {

        for (int i = 0; i < matrix.getMatrix().length; i++) {
            for (int j = 0; j < matrix.getMatrix().length; j++) {
                System.out.print(matrix.getMatrix()[i][j].getNumber() + " ");
            }
            System.out.println();
        }
    }

}
