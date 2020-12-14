

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class pictureImpl extends UnicastRemoteObject implements IPicture  {


    public pictureImpl() throws RemoteException  {
    }

    public String drawPic(String data) throws RemoteException {
        dataHandling dh = new dataHandling();
        invertColor ic = new invertColor();
        edgeDetector ed = new edgeDetector();

        BufferedImage img=null;
        try {
             img = dh.toBufferedImage(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        CannyEdgeDetector detector = new CannyEdgeDetector();
        //set thresdhold of low and high
       // detector.setLowThreshold(1.0f);
       // detector.setHighThreshold(5.0f);

        // split to get the edge
  //      detector.setSourceImage(img);
  //      detector.process();
//        BufferedImage edges = detector.getEdgesImage();
        BufferedImage edges = ed.edgeDectect(img);
        // convert the edge to binaryimage to get better line
     //  BufferedImage invertEdge = ic.invertedColor(edges);
        // get color from origon pic to coloring
        Segmentize sm = new Segmentize(img,40);
        BufferedImage colors = sm.segmentize();

        int x = colors.getWidth();
        int y = colors.getHeight();

        for (int i = 1; i < x - 1; i++) {
            for (int j = 1; j < y - 1; j++) {
                int val = edges.getRGB(i, j);
                int r = (0x00ff0000 & val) >> 16;
                int g = (0x0000ff00 & val) >> 8;
                int b = (0x000000ff & val);
                int m=(r+g+b);
                if(m < 685)
                {
                    colors.setRGB(i, j, 0);
                }

            }
        }
        String result = null;
        try {
            result = dh.toByteArray(colors,"jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Done!");
        return result;
    }


}