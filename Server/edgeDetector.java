import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class edgeDetector {

    public  BufferedImage edgeDectect(BufferedImage img)  {



        BufferedImage image = img;

       image = transposedHBlur(image);

        int x = image.getWidth();
        int y = image.getHeight();


        int[][] edgeColors = new int[x][y];
        int maxGradient = -1;

        for (int i = 1; i < x - 1; i++) {
            for (int j = 1; j < y - 1; j++) {

                int val00 = getGrayScale(image.getRGB(i - 1, j - 1));
                int val01 = getGrayScale(image.getRGB(i - 1, j));
                int val02 = getGrayScale(image.getRGB(i - 1, j + 1));

                int val10 = getGrayScale(image.getRGB(i, j - 1));
                int val11 = getGrayScale(image.getRGB(i, j));
                int val12 = getGrayScale(image.getRGB(i, j + 1));

                int val20 = getGrayScale(image.getRGB(i + 1, j - 1));
                int val21 = getGrayScale(image.getRGB(i + 1, j));
                int val22 = getGrayScale(image.getRGB(i + 1, j + 1));

                int gx = ((-1 * val00) + (0 * val01) + (1 * val02))
                        + ((-2 * val10) + (0 * val11) + (2 * val12))
                        + ((-1 * val20) + (0 * val21) + (1 * val22));

                int gy =  ((-1 * val00) + (-2 * val01) + (-1 * val02))
                        + ((0 * val10) + (0 * val11) + (0 * val12))
                        + ((1 * val20) + (2 * val21) + (1 * val22));

                double gval = Math.sqrt((gx * gx) + (gy * gy));
                int g = (int) gval;

                if(maxGradient < g) {
                    maxGradient = g;
                }

                edgeColors[i][j] = g;
            }
        }

        double scale = 255.0 / maxGradient;

        for (int i = 1; i < x - 1; i++) {
            for (int j = 1; j < y - 1; j++) {
                int edgeColor = edgeColors[i][j];
                edgeColor = 255 - (int)(edgeColor * scale);
                edgeColor = 0xff000000 | (edgeColor << 16) | (edgeColor << 8) | edgeColor;

                image.setRGB(i, j, edgeColor);
            }
        }

        return image;
    }

    public static int  getGrayScale(int rgb) {
        int r = (rgb >> 16) & 0xff;
        int g = (rgb >> 8) & 0xff;
        int b = (rgb) & 0xff;

        //from https://en.wikipedia.org/wiki/Grayscale, calculating luminance
        int gray = (int)(0.2126 * r + 0.7152 * g + 0.0722 * b);
        //int gray = (r + g + b) / 3;

        return gray;

    }



    public static BufferedImage  transposedHBlur(BufferedImage im) {
        int height = im.getHeight();
        int width = im.getWidth();

        BufferedImage temp =  new BufferedImage(width, height,  BufferedImage.TYPE_INT_RGB);
        double[] k = { 0.00598, 0.060626, 0.241843, 0.383103, 0.241843, 0.060626, 0.00598};
        // horizontal blur, transpose result
        for (int y = 3; y < height - 3; y++) {
            for (int x = 3; x < width - 3; x++) {
                double r = 0, g = 0, b = 0;
                for (int i = 0; i < 7; i++) {
                    int pixel = im.getRGB(x + i - 3, y);
                    b += (pixel & 0xFF) * k[i];
                    g += ((pixel >> 8) & 0xFF) * k[i];
                    r += ((pixel >> 16) & 0xFF) * k[i];
                }
                int p = (int)b + ((int)g << 8) + ((int)r << 16);

                temp.setRGB(x, y, p);
            }
        }
        return temp;
    }

}


