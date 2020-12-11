import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class server {

    public static void main(String[] args) {
        try {
            
            Registry reg = LocateRegistry.createRegistry(7777);
            
            pictureImpl ci = new pictureImpl();
            reg.bind("RMICalSer", ci);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
