/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;


import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author joeriemersma
 */
public class EncodingFile {
    BufferedImage encodingFile = null;
    
    
    EncodingFile(File file){
        setEncodingFile(file);
    }
    
    public void setEncodingFile(File file){
        try{
            encodingFile = ImageIO.read(file);
        }catch(IOException e){
            
        }

    }
    
    private ArrayList<Color> getRGBValues(){
        ArrayList<Color> rgbArr = new ArrayList<>();
        Color c;
        for(int i=0; i < this.encodingFile.getHeight();i++){
            for(int j = 0; j < this.encodingFile.getWidth(); j++){
                c = new Color(this.encodingFile.getRGB(i, j));
                rgbArr.add(c);
            }
        }
        return rgbArr; 
    }
    
    
}
