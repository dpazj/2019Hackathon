import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;

public class Decoder3{
 
	private Color[][] imageColor;
	private Color[][] encodedColor;
	private BufferedImage img;
	int width;
	int height;
    
	public Decoder3()
	{
		
		BufferedImage img = null;
		
		try 
		{
		       
		   img = ImageIO.read(new File("monalisa.png"));
		   
		   width = img.getWidth();
		   height = img.getHeight();
		   imageColor = new Color[height][width];
		   
		   for (int i = 0; i <height; i++)
		   {

			   for (int j = 0; j < width; j++)
			   {
					
				   imageColor[i][j] = new Color(img.getRGB(j, i), true);
					
			   }
				
		   }
		    	
		}
		catch (IOException e) 
		{
		    	
		    	
		}

	}	

	public int decodeBinary(String n)
	{
		
		int num = 0;
		int j = 7;
	
		for (int i = 1; i < 200; i *= 2)
		{
			
			num += Character.getNumericValue(n.charAt(j))*i;
			j--;
		}
		
		return num;

	}
	
	public String intToBinary(int n) 
	{
		
		String binary = "";
		
		for(int i = 128;i>0;i/=2) 
		{
			
			if(n - i >= 0) 
			{
				binary += "1";
				
			}
			else
			{
				
				binary += "0";
				
			}	
			
			if(i == 1)
			{
				
				i--;
			}
		}
		
		return binary;
	}

	public void decodeText() throws IOException
	{
		
		String byteLetter = "";
		int index = 7;
		int rgb = 1;

	 	for (int i = 0; i <height; i++)
	   	{

	 		for (int j = 0; j < width; j++)
	 		{
	 			switch(rgb) 
	 			{
	 			
	 				case 1: byteLetter += intToBinary(imageColor[i][j].getRed()).charAt(index-1);
	 						byteLetter += intToBinary(imageColor[i][j].getRed()).charAt(index);;
	 						break;
	 						
	 				case 2: byteLetter += intToBinary(imageColor[i][j].getGreen()).charAt(index-1);
							byteLetter += intToBinary(imageColor[i][j].getGreen()).charAt(index);;
							break;
							
	 				case 3: byteLetter += intToBinary(imageColor[i][j].getBlue()).charAt(index-1);
							byteLetter += intToBinary(imageColor[i][j].getBlue()).charAt(index);;
							rgb = 0;
							break;
	 			
	 			}

	 			if (byteLetter.length() == 8)
	 			{	 
	           		
	 				String letter = Character.toString((char)decodeBinary(byteLetter));
	           		System.out.print(letter);
	           		usingFileWriters(letter);
	           		if(letter.equals(".")) 
	           		{

	           			System.out.println();
	           			
	           		}
	           		
	           		byteLetter = "";           		
	           		
	          	}
	 			
	 			index -= 2;
	 			if(index == 1){index = 7;}
	 			rgb++;
	 			
	       	}

	   	}
	 	 
	}
	
	public static void usingFileWriters(String n) throws IOException
	{
		try(FileWriter fw = new FileWriter("myfile.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{

			    out.print(n);
			    
			    if(n.equals(".")){
			    	out.println();}
			    //more code
			} catch (IOException e) {
			    //exception handling left as an exercise for the reader
			}
	}
	
	public void decodeImage() 
	{
		
		String byteColourRed = "";
		String byteColourGreen = "";
		String byteColourBlue = "";
		int index = 7;
		int rgb = 1;
		
	 	for (int i = 0; i <height; i++)
	   	{

	 		for (int j = 0; j < width; j++)
	 		{
	 			
	 			switch(index) 
	 			{
	 				case 7: byteColourRed += intToBinary(imageColor[i][j].getRed()).charAt(index-1);
	 						byteColourRed += intToBinary(imageColor[i][j].getRed()).charAt(index);;
							break;
	 				
	 				case 2: byteColourGreen += intToBinary(imageColor[i][j].getGreen()).charAt(index-1);
	 				byteColourGreen += intToBinary(imageColor[i][j].getGreen()).charAt(index);;
							break;
					
	 				case 3: byteColourBlue += intToBinary(imageColor[i][j].getBlue()).charAt(index-1);
	 						byteColourBlue += intToBinary(imageColor[i][j].getBlue()).charAt(index);;
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
		//n.readImageColour();

		n.decodeText();
		//n.decodeImage();

		
	}

}
