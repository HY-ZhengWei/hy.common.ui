package org.hy.common.ui;

import java.awt.event.ActionEvent;





/**
 * 按钮点击事务。
 * 
 * 将按钮点击当作一个完整的事务来处理。
 *
 * @author   ZhengWei(HY)
 * @version  V1.0  2013-04-28
 */
public interface JCOnClickTransaction
{
	
	/**
	 * 事务之前
	 * 
	 * @param e
	 */
	public void transactionBefore(ActionEvent e); 

	
	/**
	 * 点击按钮的事务
	 * 
	 * @param e
	 */
	public void onClick(ActionEvent e);
	
	
	/**
	 * 事务之后
	 * 
	 * @param e
	 */
	public void transactionAfter(ActionEvent e);
	
}
