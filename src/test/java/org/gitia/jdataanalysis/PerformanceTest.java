/*
 * The MIT License
 *
 * Copyright 2018 orteg.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.gitia.jdataanalysis;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PerformanceTest {

    public static void main(String[] args) throws IOException {

        BufferedImage hugeImage = ImageIO.read(new File("src/main/resources/imagenes/DSC00155.jpg"));

        System.out.println("Testing convertTo2DUsingGetRGB:");
        for (int i = 0; i < 10; i++) {
            long startTime = System.nanoTime();
            int[][] result = convertTo2DUsingGetRGB(hugeImage);
            long endTime = System.nanoTime();
            System.out.println(String.format("%-2d: %s", (i + 1), toString(endTime - startTime)));
        }

        System.out.println("");

        System.out.println("Testing convertTo2DWithoutUsingGetRGB:");
        for (int i = 0; i < 10; i++) {
            long startTime = System.nanoTime();
            int[][] result = convertTo2DWithoutUsingGetRGB(hugeImage);
            long endTime = System.nanoTime();
            System.out.println(String.format("%-2d: %s", (i + 1), toString(endTime - startTime)));
        }
        
        System.out.println("Test getRGB:");
        int[][] result = convertTo2DWithoutUsingGetRGB(hugeImage);
        double[] r = new double[result.length*result[0].length];
        double[] g = new double[result.length*result[0].length];
        double[] b = new double[result.length*result[0].length];
        for (int i = 0; i < 10; i++) {
            long startTime = System.nanoTime();
            getRGB(result, r, g, b);
            long endTime = System.nanoTime();
            System.out.println(String.format("%-2d: %s", (i + 1), toString(endTime - startTime)));
            
        }
    }

    private static int[][] convertTo2DUsingGetRGB(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int[][] result = new int[height][width];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                result[row][col] = image.getRGB(col, row);
            }
        }

        return result;
    }

    private static int[][] convertTo2DWithoutUsingGetRGB(BufferedImage image) {

        final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        final int width = image.getWidth();
        final int height = image.getHeight();
        final boolean hasAlphaChannel = image.getAlphaRaster() != null;

        int[][] result = new int[height][width];
        if (hasAlphaChannel) {
            final int pixelLength = 4;
            for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
                int argb = 0;
                argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
                argb += ((int) pixels[pixel + 1] & 0xff); // blue
                argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
                argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
                result[row][col] = argb;
                col++;

                if (col == width) {
                    col = 0;
                    row++;
                }

            }
        } else {
            final int pixelLength = 3;
            for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
                int argb = 0;
                argb += -16777216; // 255 alpha
                argb += ((int) pixels[pixel] & 0xff); // blue
                argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
                argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
                result[row][col] = argb;

                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        }

        return result;

    }

    public static void getRGB(int[][] image, double[] r, double[] g, double[] b) {

        int pos = 0;
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                r[pos] = (double) (new Color(image[i][j])).getRed();
                g[pos] = (double) (new Color(image[i][j])).getGreen();
                b[pos] = (double) (new Color(image[i][j])).getBlue();
                pos++;
            }

        }

    }

    private static String toString(long nanoSecs) {
        int minutes = (int) (nanoSecs / 60000000000.0);
        int seconds = (int) (nanoSecs / 1000000000.0) - (minutes * 60);
        int millisecs = (int) (((nanoSecs / 1000000000.0) - (seconds + minutes * 60)) * 1000);

        if (minutes == 0 && seconds == 0) {
            return millisecs + "ms";
        } else if (minutes == 0 && millisecs == 0) {
            return seconds + "s";
        } else if (seconds == 0 && millisecs == 0) {
            return minutes + "min";
        } else if (minutes == 0) {
            return seconds + "s " + millisecs + "ms";
        } else if (seconds == 0) {
            return minutes + "min " + millisecs + "ms";
        } else if (millisecs == 0) {
            return minutes + "min " + seconds + "s";
        }

        return minutes + "min " + seconds + "s " + millisecs + "ms";
    }

}
