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
    int width;
    int height;
    
    
    EncodingFile(File file){
        setEncodingFile(file);
    }
    
    public void setEncodingFile(File file){
        try{
            this.encodingFile = ImageIO.read(file);
        }catch(IOException e){
            
        }
        this.width = this.encodingFile.getWidth();
        this.height = this.encodingFile.getHeight();
        return;

    }
    
    
    public ArrayList<Color> getRGBValues(){
        ArrayList<Color> rgbArr = new ArrayList<>();
        Color c;
        for(int i=0; i < width;i++){
            for(int j = 0; j < height; j++){
                c = new Color(this.encodingFile.getRGB(i, j));
                rgbArr.add(c);
            }
        }
        return rgbArr; 
    }
    
    public BufferedImage createEncodedFile(ArrayList<Color> newRgb){
        BufferedImage img = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        int j = -1;
        for(int i = 0; i < newRgb.size(); i++){
                if(i % width == 0){j++;}
                img.setRGB(i,j,newRgb.get(i).getRGB());
        }
        
        return img;
    }
    
    
}
