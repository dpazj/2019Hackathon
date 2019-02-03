package Main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import static sun.security.krb5.Confounder.bytes;

public class Decoder {

    private Color[][] imageColor;
    private Color[][] encodedColor;
    private BufferedImage encodedImg;
    ArrayList<ArrayList<Color>> encodedExam;

    int width;
    int height;

    public Decoder() {

        BufferedImage encodedImg = null;

        try {

            encodedImg = ImageIO.read(new File("encoded.png"));

            width = encodedImg.getWidth();
            height = encodedImg.getHeight();

            encodedExam = new ArrayList<>();

            for (int i = 0; i < width; i++) {
                ArrayList<Color> tmp = new ArrayList<>();
                for (int j = 0; j < height; j++) {
                    Color c = new Color(encodedImg.getRGB(i, j),true);
                    // System.out.println(c.getRed() + " " + c.getGreen() + " " + c.getBlue());
                    tmp.add(c);
                }
                encodedExam.add(tmp);

            }

        } catch (IOException e) {

        }

    }

    public int decodeText() throws FileNotFoundException, IOException {

        int endOfFile = 0;
        char[] prevChars = new char[6];
        String prev = "";
        int counter = 0;
        int y = -1;
        int x = 0;
        byte red, green, blue, alpha;
        
               
        
        ArrayList<Byte> bytes = new ArrayList<>();
        while(!(prev.equals('\0' + '\0' + '\0' + '\0' + '\0' + '\0')) && x < encodedExam.size() - 1){
            y++;
            red = (byte) (encodedExam.get(x).get(y).getRed() & 0b11);
            green = (byte) (encodedExam.get(x).get(y).getGreen() & 0b11);
            blue = (byte) (encodedExam.get(x).get(y).getBlue() & 0b11);
            alpha = (byte) (encodedExam.get(x).get(y).getAlpha() & 0b11);
            
            //System.out.println(red + " " + green +" "+ blue +" "+ alpha);
 
            endOfFile = ((red << 6) | (green << 4) | (blue << 2) | alpha);
            //System.out.print((char)endOfFile);
            if(y == encodedExam.get(x).size() -1){x++;y=-1;}
            bytes.add((byte)endOfFile);
            prevChars[counter] = (char) endOfFile;
            prev="";
            for(char c : prevChars){
                prev+= "" + c;
            }
            
            counter++;
            if(counter == 6){
                counter = 0;
            }
        }
        
        for(int i=0; i<6; i++){
            bytes.remove(bytes.size() - 1);
        }
        
        byte[] byteArray = new byte[bytes.size()];
        for(int i=0; i<bytes.size(); i++){
            byteArray[i] = bytes.get(i);
        }
        
        FileOutputStream fs = new FileOutputStream("demo.gif");

        fs.write(byteArray);
        fs.flush();

        return 0;
    }
}
