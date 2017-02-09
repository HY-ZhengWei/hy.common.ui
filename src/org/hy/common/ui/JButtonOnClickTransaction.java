package org.hy.common.ui;





/**
 * 按钮点击事务。
 * 
 * 将按钮点击当作一个完整的事务来处理。
 *
 * @author   ZhengWei(HY)
 * @version  V1.0  2012-03-28
 */
public interface JButtonOnClickTransaction extends JCOnClickTransaction
{
	// 此接口已被写成通用接口，原只被用于按钮控件。现在也可以用于菜单控件上。
	// 但，没有简单删除此类的原因是：此接口已被发布，为了老的应用不出错，而保留
	// ZhengWei(HY) 2013-04-28
}
