/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author joeriemersma
 */
public class Driver {

  /* public static void main(String[] args) throws FileNotFoundException, IOException {
        encode();
        decode();
 
    } */
    public String encodePath;
    public String secrectFilePath;
    public Driver(){
    }
    
    
    public BufferedImage encode() throws FileNotFoundException, IOException{
        File f = new File("monalisa.png");

        //System.out.print(f.isFile());
        EncodingFile ef = new EncodingFile(f);
        ef.setEncodingFile(f);

        ArrayList<ArrayList<Color>> test = ef.getRGBValues();

        File file = new File("test.txt");
        Encoder e = new Encoder();
        //

        ArrayList<ArrayList<Color>> encodedColors = e.encodeColors(test, file);
        System.out.println("X: "+ encodedColors.size() + " Y: " + encodedColors.get(0).size());
        BufferedImage bi = ef.createEncodedFile(encodedColors);
        createFile(bi);
        //compareFile(ef.getEncodingFile(),bi);
        //
        return bi;
    }
    public  void decode() throws IOException{ 
        Decoder d = new Decoder();
        d.decodeText();
    }

    public void compareFile(BufferedImage original, BufferedImage changed) {
        for (int i = 0; i < 20; i++) {
            Color c = new Color(original.getRGB(0, i),true);
            Color c1 = new Color(changed.getRGB(0, i),true);
            System.out.println("Original: " + c.getRed() + " " + c.getGreen() + " " + c.getBlue());
            System.out.println("Changed: " + c1.getRed() + " " + c1.getGreen() + " " + c1.getBlue());
            System.out.println(" ");
        }
    }

    

    public void createFile(BufferedImage bf) {

        File file = new File("encoded.png");
        try {

            ImageIO.write(bf, "png", file);
        } catch (Exception e) {
            //Should do some error control
        }

    }
    
}
