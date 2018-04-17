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
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
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
        BufferedImage image = new BufferedImage(width, height,     BufferedImage.TYPE_INT_ARGB);
        WritableRaster raster = (WritableRaster) image.getData();
        raster.setPixels(0,0,width,height,pixels);
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
    public double[] open(String url) {
        BufferedImage imagenL = null;
        imagenL = openBF(url);

        int H = imagenL.getHeight();
        int W = imagenL.getWidth();
        int size = W * H;
        double[] vector = new double[size * 3];
        int[] vectorRGB = new int[size];
        imagenL.getRGB(0, 0, W, H, vectorRGB, 0, W);

        for (int i = 0; i < size; i++) {
            Color c = new Color(vectorRGB[i]);
            vector[i] = c.getRed();
            vector[i + size] = c.getGreen();
            vector[i + size * 2] = c.getBlue();
        }
        
        return vector;
    }
    
    public void save(Image img)
    {
        

    }
    public static void main(String[] args) {

        ImageReader ir = new ImageReader();
        Clock reloj = new Clock();
        reloj.start();
        double[] imagenL = ir.open("src/main/resources/imagenes/DJI_0036_5x31.jpg");
        reloj.stop();
        reloj.printTime("open");
        
        for (int i = 0; i < 120; i++) {
            System.out.printf("%.1f\t",imagenL[i]);
        }
        System.out.println("");
        for (int i = 10000; i < 10120; i++) {
            System.out.printf("%.1f\t",imagenL[i]);
        }
        System.out.println("");
        for (int i = 20000; i < 20120; i++) {
            System.out.printf("%.1f\t",imagenL[i]);
        }
        
    }
}
