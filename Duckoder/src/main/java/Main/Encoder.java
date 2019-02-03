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

    private boolean getBitSet(byte b, int pointer) {
        // 0 1 2 3 4 5 6 7

        if ((b & (1 << (pointer - 1))) >= 1) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<ArrayList<Color>> encodeColors(ArrayList<ArrayList<Color>> file, File hiddenFile) throws FileNotFoundException, IOException {
        FileInputStream f = new FileInputStream(hiddenFile);

        byte[] hiddenBytes = new byte[(int) hiddenFile.length() + 1];
        f.read(hiddenBytes);
        
        hiddenBytes[(int) hiddenFile.length()] = (byte) 26;
        System.out.println("Hidden Bytes Length: " + hiddenBytes.length);
        ArrayList<Integer> twoBits = new ArrayList<>();
        for(byte b: hiddenBytes){
            int a,b1,c,d;
            d = b & 0b11; c = (b >> 2) & 0b11; b1 = (b >> 4) & 0b11; a = (b >> 6) & 0b11;
            twoBits.add(a);
            twoBits.add(b1);
            twoBits.add(c);
            twoBits.add(d);
            //System.out.println(b+" "+a + " " + b1 + " " + c + " " + d);
        }
        
        while(twoBits.size() % 3 != 0){twoBits.add(0);}
       // System.out.print(twoBits.size() % 3);
        int x = 0;
        int y = -1;
        
        for(int i = 0; i < twoBits.size(); i+=3){
            y++;
            int red = (file.get(x).get(y).getRed() & 0xFC) | twoBits.get(i);
            int green = (file.get(x).get(y).getGreen() & 0xFC) | twoBits.get(i + 1);
            int blue = (file.get(x).get(y).getBlue() & 0xFC) | twoBits.get(i + 2);
          //  System.out.println(red + " " + green + " " + blue);
            Color c = new Color(red,green,blue);
            //System.out.println(c.getRed() + " " + c.getGreen() + " " + c.getBlue());
            file.get(x).set(y, c);
            //System.out.println(file.get(x).get(y).getRed() + " " + file.get(x).get(y).getGreen() + " " + file.get(x).get(y).getBlue());
           // System.out.println(x +" " + y);
           // System.out.println(" ");
            if(y == file.size() - 1){x++;y=0;}
        }
        
        
        System.out.print("Done");
       
        
        
        return file;
    }

   

}
