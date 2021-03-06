/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Drawing
{
	public static void drawImageOpaque(Graphics g, BufferedImage image, int posX, int posY, float alpha)
	{
		// Set Opacity
		Graphics2D g2D = (Graphics2D) g;
		AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
		g2D.setComposite(composite);
		
		// Draw Image
		g2D.drawImage(image, posX, posY, null);
		
		// Clear Opacity
		composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
		g2D.setComposite(composite);
	}
	
	public static Color getColorRGB(int r, int g, int b)
	{
		float hsb[] = Color.RGBtoHSB(r,g,b,null);
		return Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
	}
	
	public static BufferedImage getImage(String filepath)
	{
		filepath = "C:/Users/Jamie/Documents/NetBeansProjects/APPNAME/SRC/RES" + filepath;
		BufferedImage image = null;
		try
		{
			image = ImageIO.read(new File(filepath));
		}
		catch (IOException e)
		{
			System.out.println(e);
		}
		return image;
	}
}