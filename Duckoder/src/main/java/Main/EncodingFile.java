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
        System.out.println("Height:" + this.height + " Width: " + this.width);
        return;

    }
    
    
    public ArrayList<ArrayList<Color>> getRGBValues(){
        ArrayList<ArrayList<Color>> grid = new ArrayList<>();
        ArrayList<Color> row = null;
        Color c;
        
        for(int i=0; i < width;i++){
            row = new ArrayList<>();
            for(int j=0; j < height; j++){
                c = new Color(this.encodingFile.getRGB(i, j),true);
                row.add(c);
            }
            grid.add(row);
            
        }
        return grid; 
    }
    
    public BufferedImage createEncodedFile(ArrayList<ArrayList<Color>> newRgb){
        
        BufferedImage img = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        
        System.out.print("outer " + newRgb.size() + " inner " + newRgb.get(0).size());
        
        for(int i = 0; i < newRgb.size(); i++){
            for(int j = 0; j < newRgb.get(i).size(); j++){
                
                img.setRGB(i,j,newRgb.get(i).get(j).getRGB());
                
            }    
        }
        return img;
    }
    
    public BufferedImage getEncodingFile(){
        return this.encodingFile;
    }
        
    
}
