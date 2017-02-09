package org.hy.common.ui.junit;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.hy.common.ui.JButton;
import org.hy.common.ui.JDateTime;





/**
 * 时间控件测试类。
 *
 * @author   ZhengWei(HY)
 * @version  V1.0  2012-04-01
 */
public class JDateTimeTest
{
	
	public static void main(String [] args)
	{
		JFrame    v_Frame    = new JFrame(); 
		JButton   v_Button   = new JButton("设置时间");
        JDateTime v_DateTime = new JDateTime();
		
		v_DateTime.addActionListener(new JDateTimeActionListener(v_DateTime));
		v_Button.addActionListener(new ButtonActionListener(v_DateTime ,v_Button));
		
		v_Frame.getContentPane().setLayout(new BorderLayout());
		v_Frame.getContentPane().add(v_Button ,BorderLayout.NORTH);
		
		v_Frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		v_Frame.setSize(800, 600);
		v_Frame.setVisible(true);
	}
	
}



class ButtonActionListener implements ActionListener
{
	private JDateTime jdt;
	
	private JButton   button;
	
	
	public ButtonActionListener(JDateTime i_JDateTime ,JButton i_Button)
	{
		this.jdt    = i_JDateTime;
		this.button = i_Button;
	}
	

	public void actionPerformed(ActionEvent e) 
	{
		Point v_Point = this.button.getLocationOnScreen();
		
		v_Point.setLocation(v_Point.getX() ,v_Point.getY() + this.button.getHeight());
		
		this.jdt.setLocation(v_Point);
		this.jdt.setVisible(!this.jdt.isVisible());
	}
	
}



class JDateTimeActionListener implements ActionListener
{
	private JDateTime jdt;
	
	
	public JDateTimeActionListener(JDateTime i_JDateTime)
	{
		this.jdt = i_JDateTime;
	}
	

	public void actionPerformed(ActionEvent e) 
	{
		System.out.println(this.jdt.getValue().getFull());
	}
	
}
