package org.hy.common.ui.junit;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.hy.common.ui.event.DateChangeListener;

import org.hy.common.ui.JDatePanel;
import org.hy.common.ui.JTextField;
import org.hy.common.ui.event.DateChangeEvent;





/**
 * 日期面板测试类。
 *
 * @author   ZhengWei(HY)
 * @version  V1.0  2012-03-31
 */
public class JDatePanelTest implements DateChangeListener
{
	private JTextField  text;
	
	
	public JDatePanelTest(JTextField i_Text)
	{
		this.text = i_Text;
	}
	
	public void onChangeListener(DateChangeEvent e) 
	{
		this.text.setText(e.getDate().getFull());
	}
	
	
	public static void main(String [] args) throws Exception
	{	
		JDatePanel v_JDatePanel = new JDatePanel();
		
		JFrame     v_Frame = new JFrame();
		JTextField v_Text  = new JTextField();
		
		
		v_Text.setBackground(Color.ORANGE);
		
		v_JDatePanel.addDateChangeListener(new JDatePanelTest(v_Text));
		
		v_Frame.getContentPane().setLayout(new BorderLayout());
		v_Frame.getContentPane().add(v_JDatePanel ,BorderLayout.CENTER);
		v_Frame.getContentPane().add(v_Text       ,BorderLayout.SOUTH);
		
		v_Frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		v_Frame.setSize(800, 600);
		v_Frame.setVisible(true);
	}
	
	
	public void doubleClickListener(DateChangeEvent e) 
	{
		System.out.println(e.getDate());
	}
	
}
