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
                    Color c = new Color(encodedImg.getRGB(i, j));
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
        int counter = 0;
        byte red, green, blue;
        ArrayList<Byte> decodedTwoBits = new ArrayList<>();
        System.out.print("width:" + encodedExam.size() + "Height: " + encodedExam.get(0).size());
        for (int i = 0; i < encodedExam.size(); i++) {
            for (int j = 0; j < encodedExam.get(0).size(); j++) {
                red = (byte) (encodedExam.get(i).get(j).getRed() & 0b11);
                green = (byte) (encodedExam.get(i).get(j).getGreen() & 0b11);
                blue = (byte) (encodedExam.get(i).get(j).getBlue() & 0b11);
                //System.out.println(red +" "+green+" "+blue);
                decodedTwoBits.add(red);
                decodedTwoBits.add(green);
                decodedTwoBits.add(blue);
            }
        }
        System.out.println("hellooo: size: ");
        System.out.println(decodedTwoBits.size());
        boolean ass = true;
        while(endOfFile != 26){
            int a,b,c,d;
            
            a = decodedTwoBits.get(counter);
            counter++;
            b = decodedTwoBits.get(counter);
            counter++;
            c = decodedTwoBits.get(counter);
            counter++;
            d = decodedTwoBits.get(counter);
            endOfFile = ((a << 6) | (b << 4) | (c << 2) | d);
            System.out.print((char)endOfFile);
            counter++;
        }

        return 0;
    }
}
