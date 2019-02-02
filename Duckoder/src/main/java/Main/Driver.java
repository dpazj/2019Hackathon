/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author joeriemersma
 */
public class Driver {
    
    public static void main(String[] args){
        
        File f = new File("monalisa.png");

        System.out.print(f.isFile());
        EncodingFile ef = new EncodingFile(f);
        ArrayList<ArrayList<Color>> test = ef.getRGBValues();
        createFile(ef.createEncodedFile(test));
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
