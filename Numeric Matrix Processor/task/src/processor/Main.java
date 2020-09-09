package processor;

import java.util.Scanner;

public class Main {

    public static double[][] mulConst(double[][] arr, double c) { //multiplication const
        double[][] newArr = new double[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                newArr[i][j] = arr[i][j] * c;
            }
        }
        return newArr;
    }

    public static void add(double[][] arr, double[][] arr2) { //addition
        System.out.println("The addition result is:");
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + arr2[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static double[][] arrayFill (Scanner sc, int a, int b) {
        double [][] arr = new double[a][b];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = sc.nextDouble();
            }
        }
        return arr;
    }

    public static void mul(double[][] arr, double[][] arr2, int row1, int col1, int col2) {
        double[][] arr3 = new double[row1][col2];
        System.out.println("The multiplication result is:");
        for(int i = 0; i < row1; i++) {
            for (int j = 0; j < col2; j++) {
                for (int k = 0; k < col1; k++) {
                    arr3[i][j] += arr[i][k] * arr2[k][j];
                }
                System.out.print(arr3[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static double[][] transposeMain(double[][] arr) {
        double[][] transposed = new double[arr.length][arr[0].length];
                for (int i = 0; i < arr.length; i++) {
                    for (int j = 0; j < arr[0].length; j++) {
                        transposed[i][j] = arr[j][i];
                    }
                }
        return transposed;
    }

    public static double[][] transposeSide(double[][] arr) {
        double[][] transposed;
        transposed = transposeMain(transposeVer(transposeHor(arr)));
        return transposed;
    }

    public static double[][] transposeVer(double[][] arr) {
        double[][] transposed = new double[arr.length][arr[0].length];
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0, k = arr.length - 1; j < arr[0].length; j++, k--) {
                    transposed[i][j] = arr[i][k];
                }
            }
        return transposed;
    }


    public static double[][] transposeHor(double[][] arr) {
        double[][] transposed = new double[arr.length][arr[0].length];
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[0].length; j++) {
                    transposed[(arr.length-1)-i][j] = arr[i][j];
                }
            }
        return transposed;
        }

        public static void printMatrix(double[][] arr) {
            System.out.println("The result is:");
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[0].length; j++) {
                    System.out.printf("%6.2f ", arr[i][j]);
                }
                System.out.println();
            }
        }

    static void getCofactor(double[][] mat, double[][] temp, int p, int q, int n) {
        int i = 0, j = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (row != p && col != q) {
                    temp[i][j++] = mat[row][col];
                    if (j == n - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }

    public static double getMatrixDet(double[][]arr) {
            double det = 0;
            if (arr.length == 2) {
                return arr[0][0] * arr[1][1] - arr[0][1] * arr[1][0];
            }
            double[][] newArr = new double[arr.length-1][arr[0].length-1];
            double sign = 1.0;
            for (int i = 0; i < arr[0].length; i++) {
                getCofactor(arr, newArr, 0, i, arr.length);
                det += sign * arr[0][i] * getMatrixDet(newArr);
                sign = -sign;
            }
            return det;
        }

        public static double[][] inverseMatrix(double[][] matrix) {
            double[][] coMass = new double[matrix.length][matrix[0].length];
            double[][] newArr = new double[matrix.length-1][matrix[0].length-1];
            for (int i = 0; i < coMass.length; i++) {
                for (int j = 0; j < coMass[0].length; j++) {
                    getCofactor(matrix, newArr, i, j, matrix.length);
                    coMass[i][j] = Math.pow(-1, 2+i+j) * getMatrixDet(newArr);
                }
            }
            double det = getMatrixDet(matrix);
            return mulConst(transposeMain(coMass) ,1 / det);
        }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("1. Add matrices\n" +
                    "2. Multiply matrix to a constant\n" +
                    "3. Multiply matrices\n" +
                    "4. Transpose matrix\n" +
                    "5. Calculate a determinant\n" +
                    "6. Inverse matrix\n" +
                    "0. Exit\n" +
                    "Your choice: ");
            int choice = sc.nextInt();
            if (choice != 0) {
                double[][] arr1;
                int row;
                int col;
                int row2;
                int col2;
                double[][] arr2;
                switch (choice) {
                    case 1:
                        System.out.println("Enter size of first matrix: ");
                        row = sc.nextInt();
                        col = sc.nextInt();
                        System.out.println("Enter first matrix: ");
                        arr1 = arrayFill(sc, row, col);

                        System.out.println("Enter size of second matrix: ");
                        row2 = sc.nextInt();
                        col2 = sc.nextInt();
                        System.out.println("Enter second matrix: ");
                        arr2 = arrayFill(sc, row2, col2);
                        add(arr1, arr2);
                        break;
                    case 2:
                        System.out.println("Enter size of matrix: ");
                        row = sc.nextInt();
                        col = sc.nextInt();
                        System.out.println("Enter matrix: ");
                        arr1 = arrayFill(sc, row, col);

                        System.out.println("Enter constant: ");
                        int constant = sc.nextInt();
                        System.out.println("The multiplication result is:");
                        printMatrix(mulConst(arr1, constant));
                        break;
                    case 3:
                        System.out.println("Enter size of first matrix: ");
                        row = sc.nextInt();
                        col = sc.nextInt();
                        System.out.println("Enter first matrix: ");
                        arr1 = arrayFill(sc, row, col);

                        System.out.println("Enter size of second matrix: ");
                        row2 = sc.nextInt();
                        col2 = sc.nextInt();
                        System.out.println("Enter second matrix: ");
                        arr2 = arrayFill(sc, row2, col2);
                        if (col == row2)
                            mul(arr1, arr2, row, col, col2);
                        break;
                    case 4:
                        System.out.print("1. Main diagonal\n" +
                                "2. Side diagonal\n" +
                                "3. Vertical line\n" +
                                "4. Horizontal line\n" +
                                "Your choice: ");
                        int transType = sc.nextInt();
                        System.out.println("Enter matrix size: ");
                        row = sc.nextInt();
                        col = sc.nextInt();
                        System.out.println("Enter matrix: ");
                        arr1 = arrayFill(sc, row, col);
                        switch (transType) {
                            case 1:
                                printMatrix(transposeMain(arr1));
                                break;
                            case 2:
                                printMatrix(transposeSide(arr1));
                                break;
                            case 3:
                                printMatrix(transposeVer(arr1));
                                break;
                            case 4:
                                printMatrix(transposeHor(arr1));
                        }
                        break;
                    case 5:
                        System.out.print("Enter matrix size: ");
                        row = sc.nextInt();
                        col = sc.nextInt();
                        System.out.println("Enter matrix: ");
                        arr1 = arrayFill(sc, row, col);
                        if (row == col) {
                            System.out.println("The result is:");
                            System.out.println(getMatrixDet(arr1) +"\n");
                        } else
                            System.out.println("Should be square!");
                        break;
                    case 6:
                        System.out.print("Enter matrix size: ");
                        row = sc.nextInt();
                        col = sc.nextInt();
                        System.out.println("Enter matrix: ");
                        arr1 = arrayFill(sc, row, col);
                        if (row == col) {
                            printMatrix(inverseMatrix(arr1));
                            System.out.println();
                        } else
                            System.out.println("Should be square!");
                }
            } else return;
        }
    }
}
