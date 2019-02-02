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
    
    int width;
    int height;

    public Decoder3() {

        BufferedImage img = null;

        try {

            img = ImageIO.read(new File("encoded.png"));

            width = img.getWidth();
            height = img.getHeight();
            
            imageColor = new Color[height][width];

            for (int i = 0; i < height; i++) 
            {

                for (int j = 0; j < width; j++) {

                    imageColor[i][j] = new Color(img.getRGB(j, i),true);
                    
                }

            }
            

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
	           		System.out.print(letter + " ");
	           		
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

        String byteColourRed = "";
        String byteColourGreen = "";
        String byteColourBlue = "";
        int index = 7;
        int rgb = 1;

        for (int i = 0; i < height; i++) {

            for (int j = 0; j < width; j++) {

                switch (index) {
                    case 1:
                        byteColourRed += intToBinary(imageColor[i][j].getRed()).charAt(index - 1);
                        byteColourRed += intToBinary(imageColor[i][j].getRed()).charAt(index);
                        
                        break;

                    case 2:
                        byteColourGreen += intToBinary(imageColor[i][j].getGreen()).charAt(index - 1);
                        byteColourGreen += intToBinary(imageColor[i][j].getGreen()).charAt(index);
                        
                        break;

                    case 3:
                        byteColourBlue += intToBinary(imageColor[i][j].getBlue()).charAt(index - 1);
                        byteColourBlue += intToBinary(imageColor[i][j].getBlue()).charAt(index);  
                        rgb = 0;
                        break;
                }

            }

            rgb++;

        }

    }

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub

        Decoder3 n = new Decoder3();
        n.decodeText();
        //InputStream is = getClass().getClassLoader().getResourceAsStream("samp.wav");


    }

}
