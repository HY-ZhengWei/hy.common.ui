package org.hy.common.ui.event;

import java.util.EventListener;





/**
 * 时刻面板(JTimePanel)的事件监听器接口
 *
 * @author   ZhengWei(HY)
 * @version  V1.0  2012-04-01
 */
public interface TimeChangeListener extends EventListener 
{
	
	/**
	 * 时刻改变之后，触发触发器
	 * 
	 * @param e
	 */
	public void onChangeListener(TimeChangeEvent e);
	
}
