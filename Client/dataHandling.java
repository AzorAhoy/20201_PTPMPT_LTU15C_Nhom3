import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import sun.rmi.runtime.Log;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class dataHandling {


    // convert BufferedImage to byte []
    public static String toByteArray(BufferedImage bi, String format)
            throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, format, baos);
        byte[] bytes = baos.toByteArray();
        String dataString = new String(Base64.encode(bytes));
        return dataString;

    }

    // convert byte[] to BufferedImage
    public static BufferedImage toBufferedImage(String string)
            throws IOException {
        byte[] data = new byte[0];
        try {
            data = Base64.class.newInstance().decode(string);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        InputStream is = new ByteArrayInputStream(data);
        BufferedImage bi = ImageIO.read(is);
        return bi;

    }
}
