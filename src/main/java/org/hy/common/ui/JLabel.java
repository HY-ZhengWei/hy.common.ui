package org.hy.common.ui;

import java.awt.Color;
import java.awt.Cursor;





/**
 * 标签类
 * 为了更好的使用XJava功能。
 * 
 * 设置是否使用链接样式
 *
 * @author   ZhengWei(HY)
 * @version  V1.0  2013-04-29
 */
public class JLabel extends javax.swing.JLabel
{
	private static final long serialVersionUID = 4575271952087673334L;
	
	/** 是否使用链接样式 */
	private boolean hrefStyle = false;
	
	
	
	/**
	 * 设置是否使用链接样式
	 * 
	 * @param i_HrefStyle
	 */
	public void setHrefStyle(boolean i_HrefStyle)
	{
		if ( i_HrefStyle )
		{
			this.setForeground(Color.BLUE);                                   // 设置链接颜色  
			this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));   // 设置鼠标样式  
		}
		else
		{
			this.setForeground(Color.BLACK);
			this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	}
	
	
	
	public boolean isHrefStyle()
	{
		return this.hrefStyle;
	}
	
}
