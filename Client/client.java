
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLOutput;
import java.util.Base64;
import java.util.Scanner;


public class client {
    public static void main(String[] args) throws IOException {
        dataHandling dh = new dataHandling();

        // show all the image file in dir
        String currentDirectory = System.getProperty("user.dir");
        String dataDir = currentDirectory.concat("/data");
        System.out.println("data dir: " + dataDir);
        listFile(dataDir, "png","jpg");
        System.out.print("Choose image you want to draw :");
        Scanner sc = new Scanner(System.in);
        String filename = sc.nextLine();

        // spit extension from file name
        String extension = "";
        int i = filename.lastIndexOf('.');
        if (i > 0) {
            extension = filename.substring(i+1);
        }

        File file = new File("data/"+filename);
        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // convert bufferimage into array of byte
        String input = dh.toByteArray(image,extension);


        try {
            // Gọi server đang lắng nghe tại cổng 7777
            Registry reg = LocateRegistry.getRegistry("172.17.0.2", 7777);
            // Lấy đối tượng từ xa
            IPicture cal = (IPicture) reg.lookup("RMICalSer");
            // Gọi phương thức từ xa
            String  output = cal.drawPic(input);
            BufferedImage stringResult = dh.toBufferedImage(output);
            String outputName = "result/" + filename;
            File outputfile = new File(outputName);
            try {
                ImageIO.write(stringResult,extension, outputfile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public static void listFile(String folder, String ext1, String ext2) {
        File dir = new File(folder);

        if (dir.isDirectory() == false) {
            System.out.println("Directory does not exists : " + folder);
            return;
        }
        // list out all the file name and filter by the extension
        String[] list = dir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(ext1)|| name.endsWith(ext2);
            }
        });

        if (list.length == 0) {
            System.out.println("no files end with : " + ext1+ " or "+ext2);
            return;
        }

        for (String file : list) {
            String temp = new StringBuffer("").append(file).toString();
            System.out.println("file : " + temp);
        }
    }




}

