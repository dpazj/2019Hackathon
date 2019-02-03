package Main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

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

    public int decodeText() {

        int endOfFile = 0;
        int y = -1;
        int x = 0;
        byte red, green, blue, alpha;
        
               
        
        
        while(endOfFile != 26){
            y++;
            red = (byte) (encodedExam.get(x).get(y).getRed() & 0b11);
            green = (byte) (encodedExam.get(x).get(y).getGreen() & 0b11);
            blue = (byte) (encodedExam.get(x).get(y).getBlue() & 0b11);
            alpha = (byte) (encodedExam.get(x).get(y).getAlpha() & 0b11);
            
            //System.out.println(red + " " + green +" "+ blue +" "+ alpha);
 
            endOfFile = ((red << 6) | (green << 4) | (blue << 2) | alpha);
            System.out.print((char)endOfFile);
            if(y == encodedExam.size() ){x++;y=0;}
            
        }

        return 0;
    }
}
