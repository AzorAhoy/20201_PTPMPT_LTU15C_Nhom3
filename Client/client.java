import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.imgcodecs.Imgcodecs;




import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Base64;


public class client {
    public static void main(String[] args) {
        dataHandling dh = new dataHandling();
       System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        String filename = "test.png";
        Mat mat = Imgcodecs.imread(filename);
        if (mat.empty()) {
            System.err.println("Cannot read image: " + filename);
            System.exit(0);
        }


        String data = dh.matToJson(mat);

        try {
            
            Registry reg = LocateRegistry.getRegistry("172.17.0.2", 7777);
            IPicture cal = (IPicture) reg.lookup("RMICalSer"); 
            String stringResult = cal.aaa(data);

            Mat result = dh.matFromJson(stringResult);

            Imgcodecs.imwrite("result_test.jpg", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

