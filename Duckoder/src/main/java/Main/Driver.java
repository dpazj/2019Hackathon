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
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author joeriemersma
 */
public class Driver {

    public static void main(String[] args) throws FileNotFoundException{

        File f = new File("monalisa.png");

        System.out.print(f.isFile());
        EncodingFile ef = new EncodingFile(f);
        ef.setEncodingFile(f);

        ArrayList<ArrayList<Color>> test = ef.getRGBValues();
        
        
        File file = new File("test.txt");
        EncoderDecoder e = new EncoderDecoder();
        
        ArrayList<ArrayList<Color>> encodedColors = e.encodeColors(test, file);
        System.out.println("X: "+ encodedColors.size() + " Y: " + encodedColors.get(0).size());
        createFile(ef.createEncodedFile(encodedColors));
        
    }

    public static void createFile(BufferedImage bf){

        File file = new File("encoded.png");
        try{

            ImageIO.write(bf, "png", file);
        }catch(Exception e){
            //Should do some error control
        }

    }
}
