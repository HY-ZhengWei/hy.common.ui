package org.hy.common.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.hy.common.ui.event.DateChangeListener;
import org.hy.common.ui.event.TimeChangeListener;

import org.hy.common.Date;
import org.hy.common.ui.event.DateChangeEvent;





/**
 * 时间控件(YYYY-MM-DD HH24:MI:SS)。
 *
 * @author   ZhengWei(HY)
 * @version  V1.0  2012-04-01
 */
public class JDateTime extends JFrame 
{	
	private static final long serialVersionUID = -4796163950489492401L;

	/** 主面板 */
	private JPanel      jpMain;
	
	/** 日期面板 */
	private JDatePanel  jDate;
	
	/** 时刻面板 */
	private JTimePanel  jTime;
	
	/** 今天按钮 */
	private JButton     jbToday;
	
	/** 零点。即零时、零分、零秒 */
	private JButton     jbZero;
	
	/** 确定按钮 */
	private JButton     jbSubmit;
	
	
	
	public JDateTime()
	{
		this(new Date());
	}
	
	
	
	public JDateTime(java.util.Date i_Date)
	{
		super();
		this.init(new Date(i_Date));
	}
	
	
	
	public JDateTime(Date i_Date)
	{
		super();
		this.init(i_Date);
	}
	
	
	
	/**
	 * 初始化
	 */
	private void init(Date i_Date)
	{
		this.jpMain   = new JPanel();
		this.jDate    = new JDatePanel(i_Date);
		this.jTime    = new JTimePanel(i_Date);
		this.jbToday  = new JButton("今天");
		this.jbZero   = new JButton("零点");
		this.jbSubmit = new JButton("确定");
		
		
		this.jpMain.setBorder(new LineBorder(Color.BLACK));
		
		
		this.jbToday .setFont(new Font(this.jDate.getFont().getFontName() ,0 ,12));
		this.jbZero  .setFont(new Font(this.jDate.getFont().getFontName() ,0 ,12));
		this.jbSubmit.setFont(new Font(this.jDate.getFont().getFontName() ,0 ,12));
		
		this.jbToday .setBorder(new LineBorder(Color.LIGHT_GRAY));
		this.jbZero  .setBorder(new LineBorder(Color.LIGHT_GRAY));
		this.jbSubmit.setBorder(new LineBorder(Color.LIGHT_GRAY));
		

		// 整体边距间隔
		int v_SpaceSize = 6;
		this.jDate   .setBounds(v_SpaceSize ,v_SpaceSize ,this.jDate.getWidth() ,this.jDate.getHeight());
		this.jTime   .setBounds(v_SpaceSize ,this.jDate.getHeight() + 2 + v_SpaceSize ,this.jTime.getWidth() ,this.jTime.getHeight());
		this.jbToday .setBounds(v_SpaceSize * 3 + this.jTime.getWidth() ,this.jDate.getHeight() + 6 ,40 ,18);
		this.jbZero  .setBounds(v_SpaceSize * 3 + this.jTime.getWidth() ,this.jbToday.getY() + this.jbToday.getHeight() + 2 ,40 ,18);
		this.jbSubmit.setBounds(this.jbToday.getX() + this.jbToday.getWidth() + v_SpaceSize - 1 ,this.jbToday.getY() ,40 ,this.jbToday.getHeight() + this.jbZero.getHeight() + 2);
		
		
		this.setSize(this.jDate.getWidth() + v_SpaceSize * 2 ,this.jDate.getHeight() + this.jTime.getHeight() + v_SpaceSize * 2);
		
		
		// 不显示窗口的标题
		this.setUndecorated(true);
		
		
		this.jpMain.setLayout(null);
		this.jpMain.add(this.jDate);
		this.jpMain.add(this.jTime);
		this.jpMain.add(this.jbToday);
		this.jpMain.add(this.jbZero);
		this.jpMain.add(this.jbSubmit);
		
		this.getContentPane().add(this.jpMain);
		
		
		// 事件
		this.jbToday .addActionListener(new TodayActionListener(this));
		this.jbZero  .addActionListener(new ZeroActionListener(this));
		this.jbSubmit.addActionListener(new SubmitActionListener(this));
		
		JDateTimeListener v_JDateTimeListener = new JDateTimeListener(this);
		this.jDate.addDateChangeListener(v_JDateTimeListener);
		this.addWindowFocusListener(v_JDateTimeListener);
	}
	
	
	
	/**
	 * 设置时间
	 * 
	 * @param i_Date  java.util.Date
	 */
	public void setValue(java.util.Date i_Date)
	{
		this.jDate.setValue(i_Date);
		this.jTime.setValue(i_Date);
	}
	
	
	
	/**
	 * 设置时间
	 * 
	 * @param i_Date  org.hy.common.Date
	 */
	public void setValue(Date i_Date)
	{
		this.jDate.setValue(i_Date);
		this.jTime.setValue(i_Date);
	}
	
	
	
	/**
	 * 获取时间
	 * 
	 * 会返回一个新的实例。
	 * 
	 * @param i_Date  org.hy.common.Date
	 */
	public Date getValue()
	{
		Date v_Date = this.jDate.getValue();
		
		v_Date.setTime(this.jTime.getHour() ,this.jTime.getMinute() ,this.jTime.getSecond());
		
		return v_Date;
	}
	
	
	
	/**
	 * 注册日期(YYYY-MM-DD)事件
	 * 
	 * @param e
	 */
	public void addDateChangeListener(DateChangeListener e)
	{
		this.jDate.addDateChangeListener(e);
	}
	
	
	
	/**
	 * 注册时刻(HH24:MI:SS)事件
	 * 
	 * @param e
	 */
	public void addTimeChangeListener(TimeChangeListener e)
	{
		this.jTime.addTimeChangeListener(e);
	}
	
	
	
	/**
	 * 注册确定按钮的点击事件
	 * 
	 * @param e
	 */
	public void addActionListener(ActionListener e)
	{
		this.jbSubmit.addActionListener(e);
	}
	
	
	
	/**
	 * 今天按钮的点击事件
	 */
	class TodayActionListener implements ActionListener
	{
		private JDateTime jDateTime;
		
		
		public TodayActionListener(JDateTime i_JDateTime)
		{
			this.jDateTime = i_JDateTime;
		}
		

		public void actionPerformed(ActionEvent e) 
		{
			this.jDateTime.setValue(new Date());
		}
		
	}
	
	
	
	/**
	 * 零点按钮的点击事件
	 */
	class ZeroActionListener implements ActionListener
	{
		private JDateTime jDateTime;
		
		
		public ZeroActionListener(JDateTime i_JDateTime)
		{
			this.jDateTime = i_JDateTime;
		}
		

		public void actionPerformed(ActionEvent e) 
		{
			this.jDateTime.jTime.setValue(0 ,0 ,0);
		}
		
	}
	
	
	
	/**
	 * 确定按钮的点击事件
	 * 当触发时窗口自动隐藏
	 */
	class SubmitActionListener implements ActionListener
	{
		private JDateTime jDateTime;
		
		
		public SubmitActionListener(JDateTime i_JDateTime)
		{
			this.jDateTime = i_JDateTime;
		}
		

		public void actionPerformed(ActionEvent e) 
		{
			this.jDateTime.setVisible(false);
		}
		
	}
	
	
	
	/**
	 * 整个窗口的焦点事件。
	 * 当失去焦点时，窗口自动隐藏
	 * 
	 * 双击有效单元格时，窗口自动隐藏
	 */
	class JDateTimeListener implements WindowFocusListener ,DateChangeListener
	{
		private JDateTime jdt;
		
		
		public JDateTimeListener(JDateTime i_JDateTime)
		{
			this.jdt = i_JDateTime;
		}
		

		public void windowGainedFocus(WindowEvent e) 
		{
			
		}


		public void windowLostFocus(WindowEvent e) 
		{
			this.jdt.setVisible(false);
		}


		public void doubleClickListener(DateChangeEvent e) 
		{
			jbSubmit.doClick();
		}


		public void onChangeListener(DateChangeEvent e) 
		{
		    
		}
		
	}
	
}
