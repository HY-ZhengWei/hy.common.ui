package org.hy.common.ui.event;

import java.util.EventListener;





/**
 * 日期面板(JDatePanel的事件监听器接口
 *
 * @author   ZhengWei(HY)
 * @version  V1.0  2012-03-31
 */
public interface DateChangeListener extends EventListener 
{

	/**
	 * 时间改变之后，触发触发器
	 * 
	 * @param e
	 */
	public void onChangeListener(DateChangeEvent e);
	
	
	
	/**
	 * 在有效单元格上的鼠标双击时，触发触发器
	 * 
	 * @param e
	 */
	public void doubleClickListener(DateChangeEvent e);
	
}
