package org.hy.common.ui.junit;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.hy.common.ui.JTextField;





/**
 * 时间文本框的测试类。
 *
 * @author   ZhengWei(HY)
 * @version  V1.0  2012-04-02
 */
public class JTextFieldTest 
{

	public static void main(String [] args)
	{
		JFrame     v_Frame        = new JFrame(); 
		JTextField v_TextDateTime = new JTextField("2012-04-02 22:59:59");
		
		v_TextDateTime.setOnlyDateTime();
		v_TextDateTime.turnOnDateTime();
		
		v_Frame.getContentPane().setLayout(new BorderLayout());
		v_Frame.getContentPane().add(v_TextDateTime ,BorderLayout.NORTH);
		
		v_Frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		v_Frame.setSize(800, 600);
		v_Frame.setVisible(true);
	}
	
}
