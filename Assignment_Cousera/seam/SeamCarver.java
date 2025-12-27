import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.StdOut;

import java.awt.Color;

public class SeamCarver {
    private Picture picture;
    private int scwidth, scheight;

    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        if (picture == null) throw new IllegalArgumentException("Picture in method is null");
        this.picture = picture;
        this.scwidth = picture.width();
        this.scheight = picture.height();
    }

    // current picture
    public Picture picture() {
        return picture;
    }

    // width of current picture
    public int width() {
        return picture.width();
    }

    // height of current picture
    public int height() {
        return picture.height();
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if (!validate(x, scwidth) || (!validate(y, scheight)))
            throw new IllegalArgumentException("x or y coordition is out of range");

        if (x == 0 || x == scwidth - 1 || y == 0 || y == scheight - 1) return 1000;
        Color center = picture.get(x, y);
        Color left = picture.get(x - 1, y), right = picture.get(x + 1, y),
                top = picture.get(x, y - 1), under = picture.get(x, y + 1);
        double Xenergy = (left.getRed() - right.getRed()) * (left.getRed() - right.getRed()) +
                (left.getGreen() - right.getGreen()) * (left.getGreen() - right.getGreen()) +
                (left.getBlue() - right.getBlue()) * (left.getBlue() - right.getBlue());
        double Yenergy = (top.getRed() - under.getRed()) * (top.getRed() - under.getRed()) +
                (top.getGreen() - under.getGreen()) * ((top.getGreen() - under.getGreen())) +
                (top.getBlue() - under.getBlue()) * (top.getBlue() - under.getBlue());
        return Math.sqrt(Xenergy + Yenergy);
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        double[][] weight = SCUtility.toEnergyMatrix(this);
        return shortestHorizontalPath(weight);
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        double[][] weight = SCUtility.toEnergyMatrix(this);
        return shortestVerticalPath(weight);
    }

    private int[] shortestVerticalPath(double[][] weight) {
        // double[][] weight = SCUtility.toEnergyMatrix(this);
        int width = weight.length, height = weight[0].length;
        // StdOut.println(width + "+" + height);
        double[][] distTo = new double[width][height];
        int[][] edgeTo = new int[width][height];
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                distTo[i][j] = Double.POSITIVE_INFINITY;
        for (int i = 0; i < width; i++)
            distTo[i][0] = weight[i][0];
        for (int j = 1; j < height; j++) {
            if (distTo[0][j] > distTo[0][j - 1] + weight[0][j]) {
                distTo[0][j] = distTo[0][j - 1] + weight[0][j];
                edgeTo[0][j] = calculate(0, j - 1);
            }
            if (width > 1 && distTo[0][j] > distTo[1][j - 1] + weight[0][j]) {
                distTo[0][j] = distTo[1][j - 1] + weight[0][j];
                edgeTo[0][j] = calculate(1, j - 1);
            }
            for (int i = 1; i < width - 1; i++) {
                for (int u = i - 1; u <= i + 1; u++) {
                    if (distTo[i][j] > distTo[u][j - 1] + weight[i][j]) {
                        distTo[i][j] = distTo[u][j - 1] + weight[i][j];
                        edgeTo[i][j] = calculate(u, j - 1);
                    }
                }
            }
            if (distTo[width - 1][j] > distTo[width - 1][j - 1] + weight[width - 1][j]) {
                distTo[width - 1][j] = distTo[width - 1][j - 1] + weight[width - 1][j];
                edgeTo[width - 1][j] = calculate(width - 1, j - 1);
            }
            if (width > 1
                    && distTo[width - 1][j] > distTo[width - 2][j - 1] + weight[width - 1][j]) {
                distTo[width - 1][j] = distTo[width - 2][j - 1] + weight[width - 1][j];
                edgeTo[width - 1][j] = calculate(width - 2, j - 1);
            }
        }
        double mindistance = distTo[0][height - 1];
        int minindex = 0;
        for (int i = 1; i < width; i++) {
            if (mindistance > distTo[i][height - 1]) {
                mindistance = distTo[i][height - 1];
                minindex = i;
            }
        }
        int x = edgeTo[minindex][height - 1];
        int[] verticalSeamLine = new int[height];
        verticalSeamLine[height - 1] = minindex;
        for (int sequence = height - 2; sequence >= 0; sequence--) {
            verticalSeamLine[sequence] = x % width;
            x = edgeTo[x % width][x / width];
        }
        return verticalSeamLine;
    }

    private int[] shortestHorizontalPath(double[][] weight) {
        int width = weight.length, height = weight[0].length;
        double[][] distTo = new double[width][height];
        int[][] edgeTo = new int[width][height];
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                distTo[i][j] = Double.POSITIVE_INFINITY;
        for (int j = 0; j < height; j++)
            distTo[0][j] = weight[0][j];
        for (int i = 1; i < width; i++) {
            if (distTo[i][0] > distTo[i - 1][0] + weight[i][0]) {
                distTo[i][0] = distTo[i - 1][0] + weight[i][0];
                edgeTo[i][0] = calculate(i - 1, 0);
            }
            if (height > 1 && distTo[i][0] > distTo[i - 1][1] + weight[i][0]) {
                distTo[i][0] = distTo[i - 1][1] + weight[i][0];
                edgeTo[i][0] = calculate(i - 1, 1);
            }
            for (int j = 1; j < height - 1; j++) {
                for (int u = j - 1; u <= j + 1; u++) {
                    if (distTo[i][j] > distTo[i - 1][u] + weight[i][j]) {
                        distTo[i][j] = distTo[i - 1][u] + weight[i][j];
                        edgeTo[i][j] = calculate(i - 1, u);
                    }
                }
            }
            if (distTo[i][height - 1] > distTo[i - 1][height - 1] + weight[i][height - 1]) {
                distTo[i][height - 1] = distTo[i - 1][height - 1] + weight[i][height - 1];
                edgeTo[i][height - 1] = calculate(i - 1, height - 1);
            }
            if (height > 1 && distTo[i][height - 1] > distTo[i - 1][height - 2] + weight[i][height
                    - 1]) {
                distTo[i][height - 1] = distTo[i - 1][height - 2] + weight[i][height - 1];
                edgeTo[i][height - 1] = calculate(i - 1, height - 2);
            }
        }
        double mindistance = distTo[width - 1][0];
        int minindex = 0;
        for (int j = 1; j < height; j++) {
            if (mindistance > distTo[width - 1][j]) {
                mindistance = distTo[width - 1][j];
                minindex = j;
            }
        }
        int x = edgeTo[width - 1][minindex];
        int[] horizontalSeamLine = new int[width];
        horizontalSeamLine[width - 1] = minindex;
        for (int sequence = width - 2; sequence >= 0; sequence--) {
            horizontalSeamLine[sequence] = x / width;
            x = edgeTo[x % width][x / width];
        }
        return horizontalSeamLine;
    }

    // remove horizontal seam from current picture

    /****
     * Ta có thể giảm thời gian tính Ma trận sau mỗi lần loại 1 seam bằng cch cập nhật các pixel xung quanh
     * SeamLine đó thôi
     * @param seam
     */
    public void removeHorizontalSeam(int[] seam) {
        if (seam == null)
            throw new IllegalArgumentException("horizontal seam which will be removed is null");
        if (scheight <= 1) throw new IllegalArgumentException(
                "Height of Picture is not satisfied (less | equal to 1)");
        if (!validateArray(seam, scheight, scwidth)) throw new IllegalArgumentException(
                "seam array does not satisfies condition of length or entry's value is not satisfied");
        Picture result = new Picture(scwidth, scheight - 1);
        for (int i = 0; i < scwidth; i++) {
            for (int j = 0; j < seam[i]; j++) {
                result.set(i, j, picture.get(i, j));
            }
            for (int j = seam[i]; j < scheight - 1; j++) {
                picture.set(i, j, picture.get(i, j + 1));
                result.set(i, j, picture.get(i, j + 1));
            }
        }
        picture = result;
        scwidth = width();
        scheight = height();
    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        if (seam == null)
            throw new IllegalArgumentException("vertical seam which will be removed is null");
        if (scwidth <= 1) throw new IllegalArgumentException(
                "Width of Picture is not satisfied (less | equal to 1)");
        if (!validateArray(seam, scwidth, scheight)) throw new IllegalArgumentException(
                "seam array does not satisfies condition of length or entry's value is not satisfied");
        Picture result = new Picture(scwidth - 1, scheight);
        for (int j = 0; j < scheight; j++) {
            for (int i = 0; i < seam[j]; i++) {
                result.set(i, j, picture.get(i, j));
            }
            for (int i = seam[j]; i < scwidth - 1; i++) {
                picture.set(i, j, picture.get(i + 1, j));
                result.set(i, j, picture.get(i + 1, j));
            }
        }
        picture = result;
        scwidth = width();
        scheight = height();
    }

    private boolean validateArray(int[] seam, int maxrange, int maxlength) {
        if (seam.length != maxlength) return false;
        for (int i = 0; i < seam.length - 1; i++) {
            if (!validate(seam[i], maxrange) || Math.abs(seam[i] - seam[i + 1]) > 1) return false;
        }
        return true;
    }

    private boolean validate(int coordition, int maxrange) {
        if (coordition < 0 || coordition >= maxrange)
            return false;
        return true;
    }

    private int calculate(int i, int j) {
        return i + j * scwidth;
    }

    // unit testing (optional)
    public static void main(String[] args) {
        Picture picture1 = new Picture("4x6.png");
        StdOut.println(picture1.width() + " " + picture1.height());
        SeamCarver sc = new SeamCarver(picture1);
        for (int i : sc.findVerticalSeam()) {
            StdOut.print(i + ", ");
        }
        StdOut.println();
    }

}
