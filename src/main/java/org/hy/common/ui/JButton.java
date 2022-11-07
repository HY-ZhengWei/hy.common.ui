package org.hy.common.ui;

import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.Icon;





/**
 * 按钮。
 * 
 * 可设置特殊功能有：
 * 1. 设置有点击事务功能的按钮。即可以实现点击动作由一个线程来执行，就能实现按钮点击下变不可用，但执行完成后又可用的功能。
 *
 * @author   ZhengWei(HY)
 * @version  V1.0  2012-03-28
 */
public class JButton extends javax.swing.JButton implements JCOnClick
{

	private static final long serialVersionUID = -8257259180684346032L;
	
	
	/** 是否允许有按钮有点击事务的功能。这个属性有类内部决定，不可修改。 */
	private boolean                   isAllowOnClickTransaction;
	
	/** 按钮的事务接口对象。这个接口对象由具体的使用者来实现。不实现也可以，那就是一个普通的按钮。 
	 *  启用事务功能，就是用 setOnClickTransaction()方法实例化此属性
	 */
	private JCOnClickTransaction      onClickTransaction;
	
	/** 如果按钮启用了事务功能，就自己启用这个点击事件。 */
	private JCOnClickListener         onClickListener;
	
	
	
	public JButton() 
	{
		this(null, null);
	}
	
	    

	public JButton(Icon icon) 
	{
		this(null, icon);
	}
	
	    

	public JButton(String text) 
    {
        this(text, null);
    }
	
	    

    public JButton(Action a) 
    {
        super(a);
        this.isAllowOnClickTransaction = false;
    }
    


    public JButton(String text, Icon icon) 
    {
        super(text ,icon);
        this.isAllowOnClickTransaction = true;
    }
    
    
    
    public void addActionListener(ActionListener l)
    {
    	super.addActionListener(l);
    	this.isAllowOnClickTransaction = false;
    }
    
    
	
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
	


	public boolean isAllowOnClickTransaction()
	{
		return this.isAllowOnClickTransaction;
	}
	
}
