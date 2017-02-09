package org.hy.common.ui;





/**
 * 此接口需要具体的控件（按钮或菜单）来实现。
 * 
 * 设置有点击事务功能的按钮或菜单。
 * 
 * 即可以实现点击动作由一个线程来执行，就能实现按钮或菜单点击下变不可用，但执行完成后又可用的功能。
 * 
 * 防止点击动作执行过长造成界面无响应的问题
 *
 * @author   ZhengWei(HY)
 * @version  V1.0  2013-04-28
 */
public interface JCOnClick 
{
	
	/**
	 * 是否允许有按钮有点击事务的功能。这个属性有类内部决定，不可修改。
	 * 
	 * @return
	 */
	public boolean isAllowOnClickTransaction();
	
	
	
	/**
	 * 按钮的事务接口对象。这个接口对象由具体的使用者来实现。不实现也可以，那就是一个普通的按钮。 
	 *  启用事务功能，就是用 setOnClickTransaction()方法实例化此属性
	 *  
	 * @return
	 */
	public JCOnClickTransaction getOnClickTransaction();
	
}
