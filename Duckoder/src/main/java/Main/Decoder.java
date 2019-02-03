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
    private BufferedImage img;
    private BufferedImage encodedImg;
    ArrayList<ArrayList<Color>> encodedExam;

    int width;
    int height;

    public Decoder() {

        BufferedImage img = null;

        try {

            img = ImageIO.read(new File("encoded.png"));
            encodedImg = img;

            width = img.getWidth();
            height = img.getHeight();

            encodedExam = new ArrayList<>();

            for (int i = 0; i < width; i++) {
                ArrayList<Color> tmp = new ArrayList<>();
                for (int j = 0; j < height; j++) {
                    Color c = new Color(img.getRGB(i, j));
                    // System.out.println(c.getRed() + " " + c.getGreen() + " " + c.getBlue());
                    tmp.add(c);
                }
                encodedExam.add(tmp);

            }

        } catch (IOException e) {

        }

    }

    public int decodeText() {
        
        byte endOfFile = 0;
        int counter = 0;
        int red,green,blue;
        ArrayList<Byte> decodedTwoBits = new ArrayList<>();
        ArrayList<Byte> decodedBytes = new ArrayList<>();

        for(int i = 0; i < width; i++){
            for(int j = 0;j < height; j++){
                red = encodedExam.get(i).get(j).getRed() & 0b11;
                green = encodedExam.get(i).get(j).getGreen() & 0b11;
                blue = encodedExam.get(i).get(j).getBlue() & 0b11;
                //System.out.println(red +" "+green+" "+blue);
                decodedTwoBits.add((byte)red);
                decodedTwoBits.add((byte)green);
                decodedTwoBits.add((byte)blue);
            }
        }
        System.out.println("hellooo");
        /*
        while(endOfFile != 25){
            int a,b,c,d;
            a = decodedTwoBits.get(counter);
            counter++;
            b = decodedTwoBits.get(counter);
            counter++;
            c = decodedTwoBits.get(counter);
            counter++;
            d = decodedTwoBits.get(counter);
            endOfFile = (byte) ((a << 6) & (b << 4) & (c << 2) & d);
            System.out.println(endOfFile);
            counter++;
        }*/

        return 0;
    }
}
