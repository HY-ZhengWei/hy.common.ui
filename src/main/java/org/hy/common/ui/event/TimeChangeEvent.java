package org.hy.common.ui.event;

import java.util.EventObject;





/**
 * 时刻面板(JTimePanel)的事件
 *
 * @author   ZhengWei(HY)
 * @version  V1.0  2012-04-01
 */
public class TimeChangeEvent extends EventObject  
{	
	private static final long serialVersionUID = -3012434385667331775L;
	
	private int hour;
	
	private int minute;
	
	private int secord;
	
	
	
	public TimeChangeEvent(Object i_Source ,int i_Hour ,int i_Minute ,int i_Secord) 
	{
		super(i_Source);
		
		this.hour   = i_Hour;
		this.minute = i_Minute;
		this.secord = i_Secord;
	}
	
	
	public int getHour()
	{
		return this.hour;
	}
	
	
	public int getMinute()
	{
		return this.minute;
	}
	
	
	public int getSecord()
	{
		return this.secord;
	}

}
