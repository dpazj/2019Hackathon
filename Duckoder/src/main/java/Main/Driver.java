/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

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
        ArrayList<Color> test = ef.getRGBValues();        
       
        File file = new File("test.txt");
        EncoderDecoder e = new EncoderDecoder();
        ArrayList<Color> encodedColors = e.encodeColors(test, file);
        //System.out.println(encodedColors.size());
        
    }
}
