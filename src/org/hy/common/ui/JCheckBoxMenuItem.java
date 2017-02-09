package org.hy.common.ui;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.KeyStroke;





/**
 * 菜单项复选类。
 * 为了更好的使用XJava功能。
 * 
 * 可设置特殊功能有：（这个功能没有实现 ，下面的代码是假的。待以后用时，再真实实现）
 * 1. 设置有点击事务功能的按钮。即可以实现点击动作由一个线程来执行，就能实现按钮点击下变不可用，但执行完成后又可用的功能。
 *
 * @author   ZhengWei(HY)
 * @version  V1.0  2013-04-28
 */
public class JCheckBoxMenuItem extends javax.swing.JCheckBoxMenuItem implements JCOnClick
{
	
	private static final long serialVersionUID = -1444897190143557236L;
	
	/** 是否允许有按钮有点击事务的功能。这个属性有类内部决定，不可修改。 */
	private boolean                   isAllowOnClickTransaction;
	
	/** 按钮的事务接口对象。这个接口对象由具体的使用者来实现。不实现也可以，那就是一个普通的按钮。 
	 *  启用事务功能，就是用 setOnClickTransaction()方法实例化此属性
	 */
	private JCOnClickTransaction      onClickTransaction;
	
	/** 如果按钮启用了事务功能，就自己启用这个点击事件。 */
	private JCOnClickListener         onClickListener;
	
	
	
    public void addActionListener(ActionListener l)
    {
    	super.addActionListener(l);
    	this.isAllowOnClickTransaction = false;
    }
    
    
	
	/**
	 * 按钮的事务接口对象。这个接口对象由具体的使用者来实现。不实现也可以，那就是一个普通的按钮。 
	 *  启用事务功能，就是用 setOnClickTransaction()方法实例化此属性
	 *  
	 * @return
	 */
	public JCOnClickTransaction getOnClickTransaction() 
	{
		return onClickTransaction;
	}
	
	
	
	public void setOnClickTransaction(JCOnClickTransaction i_OnClickTransaction) 
	{
		if ( this.isAllowOnClickTransaction )
		{
			if ( this.onClickTransaction == null && i_OnClickTransaction != null )
			{
				if ( this.onClickListener == null )
				{
					this.onClickListener = new JCOnClickListener(this);
					
					super.addActionListener(this.onClickListener);
				}
				else 
				{
					super.removeActionListener(this.onClickListener);
					super.addActionListener(this.onClickListener);
				}
			}
			else if ( this.onClickTransaction != null && i_OnClickTransaction == null )
			{
				if ( this.onClickListener != null )
				{
					super.removeActionListener(this.onClickListener);
					this.onClickListener = null;
				}
			}
			
			this.onClickTransaction = i_OnClickTransaction;
		}
		else
		{
			this.onClickTransaction = null;
		}
	}


	
	/**
	 * 是否允许有按钮有点击事务的功能。这个属性有类内部决定，不可修改。
	 * 
	 * @return
	 */
	public boolean isAllowOnClickTransaction()
	{
		return this.isAllowOnClickTransaction;
	}
	
	
	
	/**
	 * 使 super.setMnemonic()方法有对字符串类型的处理能力
	 * 
	 * @param i_Key
	 */
	public void setMnemonicStr(String i_Key)
	{
		super.setMnemonic(i_Key.charAt(0));
	}
	
	
	
	/**
	 * 设置 Ctrl + 某个字符的快捷键
	 * 
	 * @param i_Key
	 */
	public void setCtrlKey(String i_Key)
	{
		super.setAccelerator(KeyStroke.getKeyStroke(i_Key.charAt(0) ,KeyEvent.CTRL_MASK ,false));
	}
	
	
	
	/**
	 * 设置 Shift + 某个字符的快捷键
	 * 
	 * @param i_Key
	 */
	public void setShiftKey(String i_Key)
	{
		super.setAccelerator(KeyStroke.getKeyStroke(i_Key.charAt(0) ,KeyEvent.SHIFT_MASK ,false));
	}
	
	
	
	/**
	 * 设置 Alt + 某个字符的快捷键
	 * 
	 * @param i_Key
	 */
	public void setAltKey(String i_Key)
	{
		super.setAccelerator(KeyStroke.getKeyStroke(i_Key.charAt(0) ,KeyEvent.ALT_MASK ,false));
	}
	
	
	
	/**
	 * 按图片路径设置菜单图片
	 * 
	 * @param i_IconPath
	 * @throws Exception
	 */
	public void setIconPath(String i_IconPath) throws Exception
	{
		ImageIcon v_Image = new ImageIcon(new URL(i_IconPath));
		
		v_Image.setImage(v_Image.getImage().getScaledInstance(17 ,17 ,1));
		
		super.setIcon(v_Image);
	}
	
}
