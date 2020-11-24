import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class pictureImpl extends UnicastRemoteObject implements IPicture  {


    public pictureImpl() throws RemoteException  {
    }

    public String aaa(String data) throws RemoteException {
        dataHandling dh = new dataHandling();
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat src =  dh.matFromJson(data);

        //Creating an empty matrices to store edges, source, destination
        Mat gray = new Mat(src.rows(), src.cols(), src.type());
        Mat edges = new Mat(src.rows(), src.cols(), src.type());
        Mat dst = new Mat(src.rows(), src.cols(), src.type(), new Scalar(0));
        //Converting the image to Gray
        Imgproc.cvtColor(src, gray, Imgproc.COLOR_RGB2GRAY);
        //Blurring the image
        Imgproc.blur(gray, edges, new Size(3, 3));
        //Detecting the edges
        Imgproc.Canny(edges, edges, 100, 100*3);
        //Copying the detected edges to the destination matrix
        src.copyTo(dst, edges);

        String result = dh.matToJson(dst);
        return result;
    }
}
