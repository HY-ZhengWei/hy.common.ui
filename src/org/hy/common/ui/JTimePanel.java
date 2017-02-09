package org.hy.common.ui;

import java.awt.LayoutManager;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.JScrollBar;

import org.hy.common.ui.event.TimeChangeEvent;
import org.hy.common.ui.event.TimeChangeListener;

import org.hy.common.Date;





/**
 * 时刻面板(HH24:MI:SS)。
 *
 * @author   ZhengWei(HY)
 * @version  V1.0  2012-04-01
 */
public final class JTimePanel extends JPanel
{
	private static final long serialVersionUID = -9077361128741026787L;
	
	/** 小时 */
	private int                             hour;
	
	/** 分钟 */
	private int                             minute;
	
	/** 秒 */
	private int                             second;
	
	/** 显示时刻的文本框 */
	private JTextField                      jtTime;
	
	/** 小时调节器 */
	private JScrollBar                      jsHour;
	
	/** 分钟调节器 */
	private JScrollBar                      jsMinute;
	
	/** 秒钟调节器 */
	private JScrollBar                      jsSecond;
	
	/** 
	 * 是否允许事件。内部参数不对外开放。
	 * 当由内部发起的，对控件的改变时，即不发起事件。 
	 */
	private boolean                         isAllowEvent;
	
	/** 自定义事件的监听器集合 */
	private Collection<TimeChangeListener>  listeners;
	
	
	
	public JTimePanel() 
	{
		super();
		
		this.init();
		this.setValue(0 ,0 ,0);
	}
	
	
	
	public JTimePanel(java.util.Date i_Date) 
	{
		this(new Date(i_Date));
	}
	
	
	
	public JTimePanel(Date i_Date) 
	{
		super();
		
		this.init();
		this.setValue(i_Date);
	}
	
	
	
	public JTimePanel(boolean isDoubleBuffered) 
	{
		super(isDoubleBuffered);
		
		this.init();
		this.setValue(0 ,0 ,0);
	}
	
	
	
	public JTimePanel(LayoutManager layout) 
	{
		super(layout);
		
		this.init();
		this.setValue(0 ,0 ,0);
	}
	
	
	
	public JTimePanel(LayoutManager layout, boolean isDoubleBuffered) 
	{
		super(layout, isDoubleBuffered);
		
		this.init();
		this.setValue(0 ,0 ,0);
	}
	
	
	
	/**
	 * 注册事件
	 * 
	 * @param e
	 */
	public void addTimeChangeListener(TimeChangeListener e)
	{
		if ( this.listeners == null )
		{
			this.listeners = new HashSet<TimeChangeListener>();
		}
		
		this.listeners.add(e);
	}
	
	
	
	/**
	 * 移除事件
	 * 
	 * @param e
	 */
	public void removeTimeChangeListener(TimeChangeListener e)
	{
		if ( this.listeners == null )
		{
			return;
		}
		
		this.listeners.remove(e);
	}
	
	
	
	/**
	 * 触发日期值改变事件
	 * 
	 * @param i_Date
	 */
	protected void fireTimeChange()
	{
		if ( this.listeners == null )
		{
			return;
		}
		
		TimeChangeEvent v_Event = new TimeChangeEvent(this ,this.hour ,this.minute ,this.second);
		
		notifyListeners(v_Event);
	}
	
	
	
	/**
	 * 通知所有注册事件监听的对象
	 * 
	 * @param i_Event
	 */
	private void notifyListeners(TimeChangeEvent i_Event)
	{
		Iterator v_Iter = this.listeners.iterator();

		while ( v_Iter.hasNext() ) 
		{
			TimeChangeListener v_Listener = (TimeChangeListener)v_Iter.next();

			v_Listener.onChangeListener(i_Event);
		}
	}
	
	
	
	/**
	 * 初始化
	 */
	private void init()
	{
		this.jtTime   = new JTextField(" 00  :  00  :  00 ");
		this.jsHour   = new JScrollBar();
		this.jsMinute = new JScrollBar();
		this.jsSecond = new JScrollBar();
		
		
		this.jtTime.setEditable(false);
		
		
		this.jsHour  .setMinimum(-1);
		this.jsMinute.setMinimum(-1);
		this.jsSecond.setMinimum(-1);
		
		
		this.jsHour.setMaximum(25);
		this.jsMinute.setMaximum(61);
		this.jsSecond.setMaximum(61);
		
		
		this.jsHour  .setVisibleAmount(1);
		this.jsMinute.setVisibleAmount(1);
		this.jsSecond.setVisibleAmount(1);
		
		
		this.jsHour  .setOrientation(JScrollBar.HORIZONTAL);
		this.jsMinute.setOrientation(JScrollBar.HORIZONTAL);
		this.jsSecond.setOrientation(JScrollBar.HORIZONTAL);
		
		
		
		// 布局
		int v_MyWidth = 82;
		this.jtTime  .setBounds(0  ,0  ,v_MyWidth ,20);
		this.jsHour  .setBounds(2  ,22 ,20 ,14);
		this.jsMinute.setBounds(this.jsHour.getX()   + this.jsHour.getWidth()   + 8 ,this.jsHour.getY() ,this.jsHour.getWidth() ,this.jsHour.getHeight());
		this.jsSecond.setBounds(this.jsMinute.getX() + this.jsMinute.getWidth() + 8 ,this.jsHour.getY() ,this.jsHour.getWidth() ,this.jsHour.getHeight());
		
		
		this.setLayout(null);
		this.setSize(v_MyWidth ,this.jtTime.getHeight() + this.jsHour.getHeight() + 2);
		this.add(this.jtTime);
		this.add(this.jsHour);
		this.add(this.jsMinute);
		this.add(this.jsSecond);
		
		
		
		// 事件
		this.jsHour  .addAdjustmentListener(new HourListener());
		this.jsMinute.addAdjustmentListener(new MinuteListener());
		this.jsSecond.addAdjustmentListener(new SecondListener());
	}
	
	
	
	/**
	 * 设置小时调整器
	 * 
	 * @param i_Hour
	 */
	protected void setHour(int i_Hour)
	{
		this.setValue(i_Hour ,this.minute ,this.second);
	}
	
	
	
	/**
	 * 设置分钟调整器
	 * 
	 * @param i_Hour
	 */
	protected void setMinute(int i_Minute)
	{
		this.setValue(this.hour ,i_Minute ,this.second);
	}
	
	
	
	/**
	 * 设置秒钟调整器
	 * 
	 * @param i_Second
	 */
	protected void setSecond(int i_Second)
	{
		this.setValue(this.hour ,this.minute ,i_Second);
	}
	
	
	
	/**
	 * 设置时刻面板的值
	 * 
	 * @param i_Date  java.util.Date
	 */
	public void setValue(java.util.Date i_Date)
	{
		if ( i_Date == null )
		{
			return;
		}
		
		this.setValue(new Date(i_Date));
	}
	
	
	
	/**
	 * 设置时刻面板的值
	 * 
	 * @param i_Date  org.hy.common.Date
	 */
	public void setValue(Date i_Date)
	{
		if ( i_Date == null )
		{
			return;
		}
		
		this.setValue(i_Date.getHours() ,i_Date.getMinutes() ,i_Date.getSeconds());
	}
	
	
	
	/**
	 * 设置时刻面板的值
	 * 
	 * @param i_Date
	 */
	public void setValue(int i_Hour ,int i_Minute ,int i_Second)
	{
		if ( 0> i_Hour || i_Hour > 23 )
		{
			return;
		}
		
		if ( 0> i_Minute || i_Minute > 59 )
		{
			return;
		}
		
		if ( 0> i_Second || i_Second > 59 )
		{
			return;
		}
		
		
		this.isAllowEvent = false;
		boolean v_IsFireEvent = (this.hour != i_Hour) || (this.minute != i_Minute) || (this.second != i_Second); 
		
		this.hour   = i_Hour;
		this.minute = i_Minute;
		this.second = i_Second;
		
		
        // " 00  :  00  :  00 " 
		StringBuilder v_Buffer = new StringBuilder();
		
		v_Buffer.append(" ");
		if ( this.hour < 10 )
		{
			v_Buffer.append("0");
		}
		v_Buffer.append(this.hour);
		v_Buffer.append("  :  ");
		if ( this.minute < 10 )
		{
			v_Buffer.append("0");
		}
		v_Buffer.append(this.minute);
		v_Buffer.append("  :  ");
		if ( this.second < 10 )
		{
			v_Buffer.append("0");
		}
		v_Buffer.append(this.second);
		v_Buffer.append(" ");
		
		this.jtTime  .setText(v_Buffer.toString());
		this.jsHour  .setValue(i_Hour);
		this.jsMinute.setValue(i_Minute);
		this.jsSecond.setValue(i_Second);
		
		this.isAllowEvent = true;
		
		if ( v_IsFireEvent )
		{
			this.fireTimeChange();
		}
	}
	
	
	
	/**
	 * 获取小时
	 * 
	 * @return
	 */
	public int getHour()
	{
		return this.hour;
	}
	
	
	
	/**
	 * 获取分钟
	 * 
	 * @return
	 */
	public int getMinute()
	{
		return this.minute;
	}
	
	
	
	/**
	 * 获取秒钟
	 * 
	 * @return
	 */
	public int getSecond()
	{
		return this.second;
	}
	
	
	
	/**
	 * 小时调节器的事件
	 */
	class HourListener implements AdjustmentListener
	{
		
		public void adjustmentValueChanged(AdjustmentEvent e)
		{
			if ( isAllowEvent )
			{
				if ( e.getValue() == -1 )
				{
					jsHour.setValue(23);
					return;
				}
				else if ( e.getValue() == 24 )
				{
					jsHour.setValue(0);
					return;
				}
				
				setHour(e.getValue());
			}
		}
		
	}
	
	
	
	/**
	 * 分钟调节器的事件
	 */
	class MinuteListener implements AdjustmentListener
	{
		
		public void adjustmentValueChanged(AdjustmentEvent e)
		{
			if ( isAllowEvent )
			{
				if ( e.getValue() == -1 )
				{
					jsMinute.setValue(59);
					return;
				}
				else if ( e.getValue() == 60 )
				{
					jsMinute.setValue(0);
					return;
				}
				
				setMinute(e.getValue());
			}
		}
		
	}
	
	
	
	/**
	 * 秒钟调节器的事件
	 */
	class SecondListener implements AdjustmentListener
	{
		
		public void adjustmentValueChanged(AdjustmentEvent e)
		{
			if ( isAllowEvent )
			{
				if ( e.getValue() == -1 )
				{
					jsSecond.setValue(59);
					return;
				}
				else if ( e.getValue() == 60 )
				{
					jsSecond.setValue(0);
					return;
				}
				
				setSecond(e.getValue());
			}
		}
		
	}
	
}
