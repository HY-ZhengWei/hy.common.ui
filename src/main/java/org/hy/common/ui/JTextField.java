package org.hy.common.ui;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import org.hy.common.Date;





/**
 * 文本框。
 * 
 * 可设置特殊功能有：
 * 1. 有"只能输入数字"的限制功能
 * 2. 有"只能输入字母"的限制功能
 * 3. 有"只能输入汉字"的限制功能
 * 4. 有"只能输入符号"的限制功能
 * 5. 附加"能输入指定符号"的限制功能
 * 6. 有"只能输入日期(YYYY-MM-DD)"的限制功能
 * 7. 有"只能输入时刻(HH24:MI:SS)"的限制功能
 * 8. 有"只能输入时间(YYYY-MM-DD HH24:MI:SS)"的限制功能
 * 9. 开启时间控件的强大功能
 * 等等输入限制功能
 *
 * @author   ZhengWei(HY)
 * @version  V1.0  2012-03-26
 */
public class JTextField extends javax.swing.JTextField
{

	private static final long serialVersionUID = 290278395442040531L;
	
	/** 是否允许输入数字 */
	private boolean isAllowNumber              = true;
	
	/** 是否允许英文的小写字母 */
	private boolean isAllowEnglish_LowerCase   = true;
	
	/** 是否允许英文的大写字母 */
	private boolean isAllowEnglish_UpperCase   = true;
	
	/** 是否允许英文的标点符号 */
	private boolean isAllowEnglist_Punctuation = true;

	/** 是否允许汉字 */
	private boolean isAllowChinese             = true;
	
	/** 是大字符长度 */
	private int     maxLength                  = Integer.MAX_VALUE;
	
	/** 如果只能输入数字，则表示最小的数值 */
	private double  minValue                   = Integer.MIN_VALUE;
	
	/** 如果只能输入数字，则表示最大的数值 */
	private double  maxValue                   = Integer.MAX_VALUE;
	
	/** 附加允许输入的字符。即在上面的基础上之，还能允许输入的字符 */
	private char [] appendAllowCharArr         = new char[0];
	
	/** 时间控件 */
	private JDateTime jDateTime;
	
	/** 如果开启时间控件功能，则自动注册此监听器 */
	private TurnOnDateTimeListener turnOnDateTimeListener;
	
	
	
	public JTextField()
	{
		super();
		this.addEvent();
	}
	
	
	public JTextField(String text)
	{
		super(text);
		this.addEvent();
	}
	
	
	public JTextField(int columns)
	{
		super(columns);
		this.addEvent();
	}
	
	
	public JTextField(String text, int columns)
	{
		super(text ,columns);
		this.addEvent();
	}
	
	
	public JTextField(Document doc, String text, int columns)
	{
		super(doc ,text ,columns);
		this.addEvent();
	}
	
	
	/**
	 * 只能输入数字
	 */
	public void setOnlyNumber()
	{
		this.setAllowNumber(true);
		this.setAllowEnglish_LowerCase(false);
		this.setAllowEnglish_UpperCase(false);
		this.setAllowEnglist_Punctuation(false);
		this.setAllowChinese(false);
		
		this.appendAllowCharArr = new char[0];
	}
	
	
	/**
	 * 只能输入数字、点、负号
	 */
	public void setOnlyDouble()
	{
		this.setAllowNumber(true);
		this.setAllowEnglish_LowerCase(false);
		this.setAllowEnglish_UpperCase(false);
		this.setAllowEnglist_Punctuation(false);
		this.setAllowChinese(false);
		
		this.appendAllowCharArr = new char[]{'.' ,'-'};
	}
	
	
	/**
	 * 只能输入日期(YYYY-MM-DD)
	 */
	public void setOnlyDate()
	{
		this.setAllowNumber(true);
		this.setAllowEnglish_LowerCase(false);
		this.setAllowEnglish_UpperCase(false);
		this.setAllowEnglist_Punctuation(false);
		this.setAllowChinese(false);
		
		this.appendAllowCharArr = new char[]{'-'};
	}
	
	
	/**
	 * 只能输入时刻(HH24:MI:SS)
	 */
	public void setOnlyTime()
	{
		this.setAllowNumber(true);
		this.setAllowEnglish_LowerCase(false);
		this.setAllowEnglish_UpperCase(false);
		this.setAllowEnglist_Punctuation(false);
		this.setAllowChinese(false);
		
		this.appendAllowCharArr = new char[]{':'};
	}
	
	
	/**
	 * 只能输入时间(YYYY-MM-DD HH24:MI:SS)
	 */
	public void setOnlyDateTime()
	{
		this.setAllowNumber(true);
		this.setAllowEnglish_LowerCase(false);
		this.setAllowEnglish_UpperCase(false);
		this.setAllowEnglist_Punctuation(false);
		this.setAllowChinese(false);
		
		this.appendAllowCharArr = new char[]{'-' ,' ' ,':'};
	}
	
	
	/**
	 * 只能输入字母
	 */
	public void setOnlyEnglish()
	{
		this.setAllowNumber(false);
		this.setAllowEnglish_LowerCase(true);
		this.setAllowEnglish_UpperCase(true);
		this.setAllowEnglist_Punctuation(false);
		this.setAllowChinese(false);
		
		this.appendAllowCharArr = new char[0];
	}
	
	
	/**
	 * 只能输入数字+字母
	 */
	public void setOnlyNumberEnglish()
	{
		this.setAllowNumber(true);
		this.setAllowEnglish_LowerCase(true);
		this.setAllowEnglish_UpperCase(true);
		this.setAllowEnglist_Punctuation(false);
		this.setAllowChinese(false);
		
		this.appendAllowCharArr = new char[0];
	}
	
	
	/**
	 * 允许所有输入
	 */
	public void setAllowAll()
	{
		this.setAllowNumber(true);
		this.setAllowEnglish_LowerCase(true);
		this.setAllowEnglish_UpperCase(true);
		this.setAllowEnglist_Punctuation(true);
		this.setAllowChinese(true);
		
		this.appendAllowCharArr = new char[0];
	}
	
	
	/**
	 * 设置附加的允许输入的字符
	 * @param i_CharArr
	 */
	public void setAppendAllowChar(char [] i_CharArr)
	{
		if ( i_CharArr == null )
		{
			this.appendAllowCharArr = new char[0];
		}
		
		this.appendAllowCharArr = i_CharArr;
	}
	
	
	public char [] getAppendAllowChar()
	{
		return this.appendAllowCharArr;
	}
	
	
	/**
	 * 开启时间控件功能
	 */
	public void turnOnDateTime()
	{
		if ( this.turnOnDateTimeListener != null )
		{
			this.removeMouseListener(this.turnOnDateTimeListener);
			this.turnOnDateTimeListener = null;
		}
		
		this.jDateTime              = new JDateTime();
		this.turnOnDateTimeListener = new TurnOnDateTimeListener(this.jDateTime ,this);
		
		this.addMouseListener(this.turnOnDateTimeListener);
		this.jDateTime.addActionListener(this.turnOnDateTimeListener);
	}
	
	
	/**
	 * 关闭时间控件功能
	 */
	public void turnOffDateTime()
	{
		if ( this.turnOnDateTimeListener != null )
		{
			this.removeMouseListener(this.turnOnDateTimeListener);
			this.turnOnDateTimeListener = null;
		}
		
		this.jDateTime = null;
	}
	
	
	/**
	 * 添加输入事件
	 */
	private void addEvent()
	{
		MyEventListener v_MyEventListener = new MyEventListener(this); 
		this.addKeyListener(v_MyEventListener);
		
//		this.pDocument = new PlainDocument();
//		this.setDocument(this.pDocument);
//		this.pDocument.addDocumentListener(v_MyEventListener);
	}
	
	
	public boolean isAllowChinese() 
	{
		return isAllowChinese;
	}


	public void setAllowChinese(boolean isAllowChinese) 
	{
		this.isAllowChinese = isAllowChinese;
	}


	public boolean isAllowEnglish_LowerCase() 
	{
		return isAllowEnglish_LowerCase;
	}


	public void setAllowEnglish_LowerCase(boolean isAllowEnglish_LowerCase) 
	{
		this.isAllowEnglish_LowerCase = isAllowEnglish_LowerCase;
	}
	
	
	public boolean isAllowEnglish_UpperCase() 
	{
		return isAllowEnglish_UpperCase;
	}


	public void setAllowEnglish_UpperCase(boolean isAllowEnglish_UpperCase) 
	{
		this.isAllowEnglish_UpperCase = isAllowEnglish_UpperCase;
	}
	
	
	public boolean isAllowEnglist_Punctuation() 
	{
		return isAllowEnglist_Punctuation;
	}


	public void setAllowEnglist_Punctuation(boolean isAllowEnglist_Punctuation) 
	{
		this.isAllowEnglist_Punctuation = isAllowEnglist_Punctuation;
	}


	public boolean isAllowNumber() 
	{
		return isAllowNumber;
	}


	public void setAllowNumber(boolean isAllowNumber) 
	{
		this.isAllowNumber = isAllowNumber;
	}
	
	
	public int getMaxLength() 
	{
		return maxLength;
	}


	public void setMaxLength(int maxLength) 
	{
		this.maxLength = maxLength;
	}


	public double getMaxValue() 
	{
		return maxValue;
	}


	public void setMaxValue(double maxValue) 
	{
		this.maxValue = maxValue;
	}


	public double getMinValue() 
	{
		return minValue;
	}


	public void setMinValue(double minValue) 
	{
		this.minValue = minValue;
	}
	
		
	
	/**
	 * 键盘事件。主要用于限制不合法的输入
	 */
	class MyEventListener implements KeyListener ,DocumentListener
	{

		private org.hy.common.ui.JTextField jText;

		
		
		public MyEventListener(org.hy.common.ui.JTextField i_JText)
		{
			this.jText = i_JText;
		}
		
		
		public void keyPressed(KeyEvent e) 
		{
			
		}

		public void keyReleased(KeyEvent e) 
		{

		}

		public void keyTyped(KeyEvent e) 
		{
			// 判断文本框长度
			if ( this.jText.getText().length() >= this.jText.getMaxLength() )
			{
				e.setKeyChar('\0');
				return;
			}
			
			
			// 附加允许的字符
			for (int v_Index=0; v_Index<appendAllowCharArr.length; v_Index++)
			{
				if ( e.getKeyChar() == appendAllowCharArr[v_Index] )
				{
					return;
				}
			}
			
			
			// 数字
			if ( '0' <= e.getKeyChar() && e.getKeyChar() <= '9' )
			{
				if ( !this.jText.isAllowNumber() )
				{
					e.setKeyChar('\0');
				}
			}
			// 大写字母
			else if ( 'A' <= e.getKeyChar() && e.getKeyChar() <= 'Z' )
			{
				if ( !this.jText.isAllowEnglish_UpperCase() )
				{
					e.setKeyChar('\0');
				}
			}
			// 小写字母
			else if ( 'a' <= e.getKeyChar() && e.getKeyChar() <= 'z' )
			{
				if ( !this.jText.isAllowEnglish_LowerCase() )
				{
					e.setKeyChar('\0');
				}
			}
			// 标点符号
			else if ( e.getKeyChar() <= '□' )
			{
				if ( !this.jText.isAllowEnglist_Punctuation() )
				{
					e.setKeyChar('\0');
				}
			}
			// 汉字
			else if ( e.getKeyChar() > '□' )
			{
				if ( !this.jText.isAllowChinese() )
				{
					e.setKeyChar('\0');
				}
			}
		}


		public void changedUpdate(DocumentEvent e) 
		{
		}


		public void insertUpdate(DocumentEvent e) 
		{
			/*
			System.out.println(this.jText.getText());
			if (  this.jText.isAllowNumber()
			  && !this.jText.isAllowChinese()
			  && !this.jText.isAllowEnglish_LowerCase() 
			  && !this.jText.isAllowEnglish_UpperCase()
			  && !this.jText.isAllowEnglist_Punctuation())
			{
				if ( this.jText.getText().length() > 0 )
				{
					double v_Double = Double.parseDouble(this.jText.getText());
					
					if ( this.jText.getMinValue() > v_Double )
					{
						return;
					}
					else if ( v_Double > this.jText.getMaxValue() )
					{
						this.jText.setText("1");
						return;
					}
				}
			}
			*/
		}


		public void removeUpdate(DocumentEvent e) 
		{
		}
		
	}
	
	
	
	/**
	 * 实现JDateTime时间控件功能
	 */
	class TurnOnDateTimeListener implements MouseListener ,ActionListener
	{
		private JDateTime   jdt;
		
		private JTextField  father;
		
		private boolean     isVisible;
		
		
		public TurnOnDateTimeListener(JDateTime i_JDateTime ,JTextField i_JTextField)
		{
			this.jdt       = i_JDateTime;
			this.father    = i_JTextField;
			this.isVisible = false;
		}
		
		
		public void actionPerformed(ActionEvent e) 
		{
			if ( this.father.isEnabled() )
			{
				this.father.setText(this.jdt.getValue().getFull());
				this.father.setFocusable(true);
			}
		}


		public void mouseClicked(MouseEvent e) 
		{
			if ( !this.father.isEnabled() )
			{
				return;
			}
			
			Date v_Date = this.jdt.getValue().setDate(this.father.getText());
			
			if ( v_Date == null )
			{
				this.jdt.setValue(new Date());
			}
			else
			{
				this.jdt.setValue(v_Date);
			}
			
			Point v_Point = this.father.getLocationOnScreen();
			
			v_Point.setLocation(v_Point.getX() + 1 ,v_Point.getY() + this.father.getHeight());
			
			this.jdt.setLocation(v_Point);
			this.isVisible = !this.isVisible;
			this.jdt.setVisible(this.isVisible);
		}


		public void mouseEntered(MouseEvent e) 
		{

		}


		public void mouseExited(MouseEvent e) 
		{

		}


		public void mousePressed(MouseEvent e) 
		{

		}


		public void mouseReleased(MouseEvent e) 
		{

		}
		
	}

}
