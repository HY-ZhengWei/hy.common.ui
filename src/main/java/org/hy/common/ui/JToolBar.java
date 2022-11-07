package org.hy.common.ui;

import java.awt.Component;





/**
 * 工具栏类。
 * 为了更好的使用XJava功能。
 *
 * @author   ZhengWei(HY)
 * @version  V1.0  2013-04-22
 */
public class JToolBar extends javax.swing.JToolBar 
{

	private static final long serialVersionUID = -8531222771153906942L;

	
	
	public Component addComponent(Component io_Component) 
	{
        super.addImpl(io_Component, null, -1);
        return io_Component;
    }
	
}
