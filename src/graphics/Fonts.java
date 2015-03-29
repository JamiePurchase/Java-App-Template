/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;
import java.awt.Font;

public class Fonts
{

	public static Font getFont(String style)
	{
		if(style=="Standard"){return new Font("Courier", Font.BOLD, 18);}
		return new Font("MV Boli", Font.BOLD, 26);
	}
}