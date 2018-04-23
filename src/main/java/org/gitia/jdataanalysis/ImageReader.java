/*
 * The MIT License
 *
 * Copyright 2018 Matías Roodschild <mroodschild@gmail.com>.
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
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import org.ejml.simple.SimpleMatrix;
import org.gitia.jdataanalysis.data.stats.Clock;
//import org.nd4j.linalg.api.ndarray.INDArray;

/**
 *
 * @author Alberto Ortega <ortegalbertoi@gmail.com>
 */
public class ImageReader {

    private BufferedImage openBF(String url) {
        BufferedImage imagenL = null;
        try {
            InputStream input = new FileInputStream(url);
            ImageInputStream imageInput = ImageIO.createImageInputStream(input);
            imagenL = ImageIO.read(imageInput);
        } catch (Exception e) {

        }
        return imagenL;
    }

    public BufferedImage getImageFromArray(double[] pixels, int width, int height) throws IOException {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        WritableRaster raster = (WritableRaster) image.getData();
        raster.setPixels(0, 0, width, height, pixels);
        image.setData(raster);
        File f = null;
        f = new File("MyFile.png");

        ImageIO.write(image, "png", f);
        return image;
    }

    /**
     *
     * @param url
     * @return
     */
    public static SimpleMatrix open(String url, String extension) {
        SimpleMatrix matrix = null;
        try {
            List<String> address = listFilesForFolder(url, extension);
            BufferedImage im = ImageIO.read(new File(address.get(0)));
            int sizeImage = im.getHeight() * im.getWidth();
            matrix = new SimpleMatrix(address.size(), 3*sizeImage);
            for (int i = 0; i < address.size(); i++) {
                String imageUrl = address.get(i);
                BufferedImage hugeImage = ImageIO.read(new File(imageUrl));
                int[][] result = convertTo2DWithoutUsingGetRGB(hugeImage);
                SimpleMatrix r = new SimpleMatrix(1, sizeImage);
                SimpleMatrix g = new SimpleMatrix(1, sizeImage);
                SimpleMatrix b = new SimpleMatrix(1, sizeImage);
                getRGB(result, r.getDDRM().getData(), g.getDDRM().getData(), b.getDDRM().getData());
                matrix.setRow(i, 0, r.concatColumns(g, b).getDDRM().getData());
            }
        } catch (IOException ex) {
            Logger.getLogger(ImageReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return matrix;
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

    public static List<String> listFilesForFolder(String folderUrl, String extension) {
        //  System.out.println("folder.list " + folder.list().length);
        List<String> address = new ArrayList<>();
        File folder = new File(folderUrl);
        String temp = "";
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                // System.out.println("Reading files under the folder "+folder.getAbsolutePath());
                listFilesForFolder(fileEntry.getPath(), extension);
            } else {
                if (fileEntry.isFile()) {
                    temp = fileEntry.getName();
                    if ((temp.substring(temp.lastIndexOf('.') + 1, temp.length()).toLowerCase()).equals(extension)) {
                        //System.out.println("File= " + folder.getAbsolutePath() + "\\" + fileEntry.getName());
                        address.add(folder.getAbsolutePath() +"\\" +fileEntry.getName());
                    }
                }

            }
        }
        return address;
    }

    public void save(Image img) {

    }
//    public static void main(String[] args) {
//
//        ImageReader ir = new ImageReader();
//        Clock reloj = new Clock();
//        reloj.start();
//        double[] imagenL = ir.open("src/main/resources/imagenes/DJI_0036_5x31.jpg");
//        reloj.stop();
//        reloj.printTime("open");
//        
//        for (int i = 0; i < 120; i++) {
//            System.out.printf("%.1f\t",imagenL[i]);
//        }
//        System.out.println("");
//        for (int i = 10000; i < 10120; i++) {
//            System.out.printf("%.1f\t",imagenL[i]);
//        }
//        System.out.println("");
//        for (int i = 20000; i < 20120; i++) {
//            System.out.printf("%.1f\t",imagenL[i]);
//        }
//        
//        BufferedImage prueba = null;
//        //BufferedImage prueba = ir.getImageFromArray(imagenL, 100, 100);
//        
//    }
}
