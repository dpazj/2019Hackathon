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
import java.util.ArrayList;
import java.util.BitSet;

/**
 *
 * @author Douglas
 */
public class EncoderDecoder {

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

    public ArrayList<ArrayList<Color>> encodeColors(ArrayList<ArrayList<Color>> file, File hiddenFile) throws FileNotFoundException {
        ArrayList<ArrayList<Color>> encodedColors = new ArrayList<>();
        FileInputStream f = new FileInputStream(hiddenFile);

        byte[] hiddenBytes = new byte[(int)hiddenFile.length()];

        System.out.println("Hidden Bytes Length: " + hiddenBytes.length);

        System.out.println("Encoded File Started");

        for (ArrayList<Color> a : file) {
            encodedColors.add(new ArrayList<>());
        }

        int byteCount = 0;
        
        for (int x = 0; x < file.size(); x++) {
            for (int y = 0; y < file.get(0).size(); y++) {
                
                for(int b=0; b<8; b+=2){
                    Color c = encodeColor(file.get(x).get(y), hiddenBytes[byteCount], b);
                    file.get(x).set(y, c);
                    if(cycle == 0){
                        y++;
                    }
                }
                byteCount++;
                if(byteCount >= hiddenBytes.length){
                    System.out.println("Encoded File Done");
                    return file;
                }
            }
        }
        return file;
    }

    private Color encodeColor(Color c, byte b, int pointer) {
        Color newC = c;
        //pointer = 7 - pointer;

        //Red
        if (cycle == 0) {
            byte red = (byte) c.getRed();
            //System.out.println();
            BitSet redBits = BitSet.valueOf(new byte[]{red});

            if (getBitSet(b, pointer)) {
                red |= 1 << 0;
            } else {
                red &= ~(1 << 0);
            }
            if (getBitSet(b, pointer + 1)) {
                red |= 1 << 1;
            } else {
                red &= ~(1 << 1);
            }

            int green = newC.getGreen();
            int blue = newC.getBlue();

            //System.out.println("Red: " + (red & 0xFF));
            newC = new Color(red & 0xFF, newC.getGreen(), newC.getBlue());
        }

        //Green
        if (cycle == 1) {
            byte green = (byte) c.getGreen();
            BitSet greenBits = BitSet.valueOf(new byte[]{green});

            if (getBitSet(b, pointer)) {
                green |= 1 << 0;
            } else {
                green |= 0 << 0;
            }
            if (getBitSet(b, pointer + 1)) {
                green |= 1 << 1;
            } else {
                green |= 0 << 1;
            }

            //System.out.println("Green: " + (green & 0xFF));
            newC = new Color(newC.getRed(), green & 0xFF, newC.getBlue());
        }

        //Blue
        if (cycle == 2) {
            byte blue = (byte) c.getBlue();
            BitSet blueBits = BitSet.valueOf(new byte[]{blue});

            if (getBitSet(b, pointer)) {
                blue |= 1 << 0;
            } else {
                blue |= 0 << 0;
            }
            if (getBitSet(b, pointer + 1)) {
                blue |= 1 << 1;
            } else {
                blue |= 0 << 1;
            }

            //System.out.println("Blue: " + (blue & 0xFF));
            newC = new Color(newC.getRed(), newC.getGreen(), blue & 0xFF);
        }

        if (cycle >= 2) {
            cycle = 0;
        } else {
            cycle++;
        }

        return newC;
    }

}
