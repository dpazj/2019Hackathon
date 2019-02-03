/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;


/**
 *
 * @author Douglas
 */
public class Encoder {

    int cycle = 0;
    int cycleCount = 0;


    public ArrayList<ArrayList<Color>> encodeColors(ArrayList<ArrayList<Color>> file, File hiddenFile) throws FileNotFoundException, IOException {
        FileInputStream f = new FileInputStream(hiddenFile);

        byte[] hiddenBytes = new byte[(int) hiddenFile.length() + 1];
        f.read(hiddenBytes);
        
        for(byte a : hiddenBytes){
            System.out.print((char) a);
        }
        
        hiddenBytes[(int) hiddenFile.length()] = (byte) 26;
        
        int x = 0;
        int y = -1;
        for(int i = 0; i < hiddenBytes.length; i++){
            y++;
            byte temp = hiddenBytes[i];
            //System.out.println("red: "+ (temp & 0b11) + " " + (file.get(x).get(y).getRed() & 0xFC));
            System.out.println(" ");
            int red = (file.get(x).get(y).getRed() & 0xFC) | ((temp >> 6) & 0b11);
            int green = (file.get(x).get(y).getGreen() & 0xFC) | ((temp >> 4)& 0b11);
            int blue = (file.get(x).get(y).getBlue() & 0xFC) | ((temp >> 2)& 0b11);
            int alpha = (file.get(x).get(y).getAlpha() & 0xFC) | (temp & 0b11);
            System.out.println(red + " " + green +" "+ blue +" "+ alpha);
            Color c = new Color(red,green,blue,alpha);
            c = new Color(c.getRGB(),true);
            file.get(x).set(y, c);
            if(y == file.size() ){x++;y=0;}
            //System.out.println(file.get(x).get(y).getRed() + " " + file.get(x).get(y).getGreen() + " " + file.get(x).get(y).getBlue() + " " + file.get(x).get(y).getAlpha());
        }

        return file;
    }

   

}
