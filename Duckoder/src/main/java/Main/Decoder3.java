package Main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;

public class Decoder3 {

    private Color[][] imageColor;
    private Color[][] encodedColor;
    private BufferedImage img;
    private BufferedImage encodedImg;
    
    int width;
    int height;

    public Decoder3() {

        BufferedImage img = null;

        try {

            img = ImageIO.read(new File("encoded.png"));
            encodedImg = img;

            width = img.getWidth();
            height = img.getHeight();
            
            imageColor = new Color[height][width];

            for (int i = 0; i < height; i++) 
            {

                for (int j = 0; j < width; j++) {

                    imageColor[i][j] = new Color(img.getRGB(j, i),true);
                    
                }

            }
            
            encodedColor = new Color[height/4][width/4];
            

        } catch (IOException e) {

        }
        
        

    }

    public int decodeBinary(String n) {

        int num = 0;
        int j = 7;

        for (int i = 1; i < 200; i *= 2) {

            num += Character.getNumericValue(n.charAt(j)) * i;
            j--;
        }

        return num;

    }

    public String intToBinary(int n) {

        String binary = "";

        for (int i = 128; i > 0; i /= 2) 
        {

            if (n - i >= 0) {
                binary += "1";
                n -=i;

            } else {

                binary += "0";

            }

            if (i == 1) {

                i--;
            }
        }

        return binary;
    }

    public int decodeText()
	{
		
		String byteLetter = "";
		int rgb = 1;

	 	for (int i = 0; i <height; i++)
	   	{

	 		for (int j = 0; j < width; j++)
	 		{
	 			
	 			switch(rgb) 
	 			{
	 			
	 				case 1: byteLetter += intToBinary(imageColor[i][j].getRed()).charAt(6);
	 						byteLetter += intToBinary(imageColor[i][j].getRed()).charAt(7);;
	 						break;
	 						
	 				case 2: byteLetter += intToBinary(imageColor[i][j].getGreen()).charAt(6);
							byteLetter += intToBinary(imageColor[i][j].getGreen()).charAt(7);;
							break;
							
	 				case 3: byteLetter += intToBinary(imageColor[i][j].getBlue()).charAt(6);
							byteLetter += intToBinary(imageColor[i][j].getBlue()).charAt(7);;
							rgb = 0;
							break;
	 			
	 			}
	 			
	 			rgb++;
	 			
	 			if (byteLetter.length() == 8)
	 			{	 
	           		
	 				String letter = Character.toString((char)decodeBinary(byteLetter));
	 				
	 				System.out.print(letter);
	 				
	           		
	           		if(letter.equals(".")) 
	           		{

	           			System.out.println();
	           			
	           		}
	           		
	           		if(letter.equals(('\0')))
	           		{
	           			
	           			return 1;
	           			
	           		}
	           		
	           		byteLetter = "";           		
	           		
	          	}

	       	}

	   	}
	 	
	 	return 0;
	 	 
	}

    public void decodeImage() {

        int byteColourRed = 0;
        int byteColourGreen = 0;
        int byteColourBlue = 0;
        
        String byteValue = "";
        
        int rgb = 1;

        for (int i = 0; i < 400; i++) 
        {

            for (int j = 0; j < 400; j++) {

                switch (rgb) {
                    case 1:
                    	byteValue += intToBinary(imageColor[i][j].getRed()).charAt(6);
                    	byteValue += intToBinary(imageColor[i][j].getRed()).charAt(7);
                        
                        break;

                    case 2:
                    	byteValue += intToBinary(imageColor[i][j].getGreen()).charAt(6);
                    	byteValue += intToBinary(imageColor[i][j].getGreen()).charAt(7);
                        
                        break;

                    case 3:
                    	byteValue += intToBinary(imageColor[i][j].getBlue()).charAt(6);
                    	byteValue += intToBinary(imageColor[i][j].getBlue()).charAt(7);  
                        rgb = 0;
                        break;
                }
            
	            if (byteValue.length() == 8)
	 			{	 	
	            	
	 				int temp = decodeBinary(byteValue);
	           		
	           		if(byteValue.equals(('\0')))
	           		{
	
	           			System.out.println();
	           			
	           		}
	           		
	           		switch (rgb) {
		                case 1:
		                	byteColourRed= decodeBinary(byteValue);
		                	byteValue = "";
		                    break;
		
		                case 2:
		                	byteColourGreen = decodeBinary(byteValue);
		                	byteValue = "";  
		                    break;
		
		                case 0:
		                	byteColourBlue = decodeBinary(byteValue);  
		                	byteValue = "";
		                    rgb = 0;
		                    break;
	           		}
	           		
	           		encodedColor[i/2][j/2] = new Color(255,255,255);//byteColourRed,byteColourGreen,byteColourBlue);
	           		encodedImg.setRGB(j, i, encodedColor[i/2][j/2].getRGB());
	           		        		          		
	          	}
	           
	            rgb++;
            }
            
            
        }
        System.out.println("DOME");
        try {
            // retrieve image
            BufferedImage bi = encodedImg;
            File outputfile = new File("encodedImage.png");
            ImageIO.write(bi, "png", outputfile);
        } catch (IOException e) {

        }
        
    }

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub

        Decoder3 n = new Decoder3();
        n.decodeText();
        //n.decodeImage();


    }

}
