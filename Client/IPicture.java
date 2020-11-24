import org.opencv.core.Mat;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IPicture extends Remote {
    public String aaa(String data) throws RemoteException;
}
