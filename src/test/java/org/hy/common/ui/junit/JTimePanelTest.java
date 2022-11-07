package org.hy.common.ui.junit;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.hy.common.ui.event.TimeChangeEvent;
import org.hy.common.ui.event.TimeChangeListener;

import org.hy.common.Date;
import org.hy.common.ui.JTextField;
import org.hy.common.ui.JTimePanel;





/**
 * 时刻面板测试类。
 *
 * @author   ZhengWei(HY)
 * @version  V1.0  2012-04-01
 */
public class JTimePanelTest implements TimeChangeListener 
{
	private JTextField  text;
	
	
	public JTimePanelTest(JTextField i_Text)
	{
		this.text = i_Text;
	}
	
	
	public void onChangeListener(TimeChangeEvent e) 
	{
		this.text.setText("H:" + e.getHour() + "  M:" + e.getMinute() + "  S:" + e.getSecord());
	}
	
	
	public static void main(String [] args)
	{
		JTimePanel v_JTimePanel = new JTimePanel();
		
		JFrame     v_Frame = new JFrame();
		JTextField v_Text  = new JTextField();
		
		v_Text.setBackground(Color.ORANGE);
		
		v_JTimePanel.addTimeChangeListener(new JTimePanelTest(v_Text));
		
		Date v_Now = new Date();
		
		v_JTimePanel.setValue(v_Now);
		
		v_Frame.getContentPane().setLayout(new BorderLayout());
		v_Frame.getContentPane().add(v_JTimePanel ,BorderLayout.CENTER);
		v_Frame.getContentPane().add(v_Text       ,BorderLayout.SOUTH);
		
		v_Frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		v_Frame.setSize(800, 600);
		v_Frame.setVisible(true);
	}
	
}
