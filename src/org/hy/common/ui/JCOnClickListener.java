package org.hy.common.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;





/**
 * 设置有点击事务功能的按钮或菜单。
 * 
 * 即可以实现点击动作由一个线程来执行，就能实现按钮或菜单点击下变不可用，但执行完成后又可用的功能。
 * 
 * 防止点击动作执行过长造成界面无响应的问题
 *
 * @author   ZhengWei(HY)
 * @version  V1.0  2013-04-28
 */
public class JCOnClickListener implements ActionListener
{
	private JCOnClick jcOnClick;
	
	
	
	public JCOnClickListener(JCOnClick i_JCOnClick)
	{
		this.jcOnClick = i_JCOnClick;
	}
	
	
	public void actionPerformed(ActionEvent e) 
	{
		if ( this.jcOnClick != null
		  && this.jcOnClick.isAllowOnClickTransaction()
		  && this.jcOnClick.getOnClickTransaction() != null )
		{
			try
			{
				this.jcOnClick.getOnClickTransaction().transactionBefore(e);
			}
			catch (Exception exce)
			{
				// Nothing.
			}
			
			
			try
			{
				Thread v_TD = new Thread(new JCOnClickTD(this.jcOnClick.getOnClickTransaction() ,e));
				
				v_TD.start();
			}
			catch (Exception exce)
			{
				// Nothing.
			}
		}
	}
	
	
	
	class JCOnClickTD implements Runnable
	{
		private JCOnClickTransaction      onClickTrans;
		
		private ActionEvent               actionEvent;
		
		
		
		public JCOnClickTD(JCOnClickTransaction i_OnClickTrans ,ActionEvent i_ActionEvent)
		{
			this.onClickTrans = i_OnClickTrans;
			this.actionEvent  = i_ActionEvent;
		}

		
		public void run() 
		{
			try
			{
				this.onClickTrans.onClick(this.actionEvent);
			}
			catch (Exception exce)
			{
				// Nothing.
			}
			
			try
			{
				this.onClickTrans.transactionAfter(this.actionEvent);
			}
			catch (Exception exce)
			{
				// Nothing.
			}
		}
		
	}
	
}
