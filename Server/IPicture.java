

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IPicture extends Remote {
  
    public String drawPic(String data) throws RemoteException;
}