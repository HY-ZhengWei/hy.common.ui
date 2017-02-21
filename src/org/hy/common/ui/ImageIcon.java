package org.hy.common.ui;

import java.net.MalformedURLException;
import java.net.URL;

import org.hy.common.Help;





/**
 * 图标。
 * 为了更好的使用XJava功能。
 *
 * @author   ZhengWei(HY)
 * @version  V1.0  2013-04-22
 */
public class ImageIcon 
{
	
	private String imagePath;
	
	private int    width;
	
	private int    height;
	
	private int    hints;
	
	
	
	/**
	 * 按图片路径设置菜单图片
	 * 
	 * @param i_ImagePath
	 * @throws Exception
	 */
	public void setImagePath(String i_ImagePath)
	{
		this.imagePath = i_ImagePath;
	}
	
	
	
	public void setWidth(int i_Width)
	{
		this.width = i_Width;
	}
	
	
	
	public void setHeight(int i_Height)
	{
		this.height = i_Height;
	}
	
	
	
	public void setHints(int i_Hints)
	{
		if ( i_Hints < 0 )
		{
			this.hints = 0;
		}
		else
		{
			this.hints = i_Hints;
		}
	}
	
	
	
	public int getHeight() 
	{
		return height;
	}



	public int getHints() 
	{
		return hints;
	}



	public String getImagePath() 
	{
		return imagePath;
	}



	public int getWidth() 
	{
		return width;
	}



	public javax.swing.ImageIcon getImage() throws MalformedURLException
	{
		if ( Help.isNull(this.imagePath) )
		{
			return null;
		}
		
		javax.swing.ImageIcon v_Image = new javax.swing.ImageIcon(new URL(this.imagePath));
		
		if ( this.width > 0 && this.height > 0 )
		{
			v_Image.setImage(v_Image.getImage().getScaledInstance(this.width ,this.height ,this.hints));
		}
		
		return v_Image;
	}
	
}
