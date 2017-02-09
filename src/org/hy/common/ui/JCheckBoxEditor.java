package org.hy.common.ui;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;





/**
 * JTable中的复选框的实现
 *
 * @author   ZhengWei(HY)
 * @version  V1.0  2012-03-27
 */
public class JCheckBoxEditor extends AbstractCellEditor implements TableCellEditor ,ItemListener
{
	private static final long serialVersionUID = 9115068480281679452L; 
	
	private JCheckBox jcheckbox;
	
	
	
	public JCheckBoxEditor() 
	{	
		
	}
	
	
	
	public Component getTableCellEditorComponent(JTable  i_Table
			                                    ,Object  i_Value
			                                    ,boolean i_IsSelected
			                                    ,int     i_Row
			                                    ,int     i_Column) 
	{
		if ( i_Value == null )
		{
			if ( this.jcheckbox == null )
			{
				this.jcheckbox = new JCheckBox();
			}
		}
		else if ( i_Value instanceof JCheckBox )
		{
			this.jcheckbox = (JCheckBox)i_Value;
		}
		else if ( i_Value instanceof Boolean )
		{
			if ( this.jcheckbox == null )
			{
				this.jcheckbox = new JCheckBox();
			}
			
			if ( ((Boolean)i_Value).booleanValue() )
			{
				this.jcheckbox.setSelected(true);
			}
			else
			{
				this.jcheckbox.setSelected(false);
			}
		}
		else
		{
			if ( this.jcheckbox == null )
			{
				this.jcheckbox = new JCheckBox();
			}
			
			this.jcheckbox.setSelected(false);
		}
		
		this.jcheckbox.addItemListener(this);
		
		return (Component)this.jcheckbox;
	}
	
	
	
	public Object getCellEditorValue() 
	{
		this.jcheckbox.removeItemListener(this);
		return Boolean.valueOf(this.jcheckbox.isSelected());
	}
	
	
	
	public void itemStateChanged(ItemEvent e) 
	{
		super.fireEditingStopped();
	}
	
	
	
	/*
    ZhengWei(HY) Del 2016-07-30
    不能实现这个方法。首先JDK中的Hashtable、ArrayList中也没有实现此方法。
    它会在元素还有用，但集合对象本身没有用时，释放元素对象
    
    一些与finalize相关的方法，由于一些致命的缺陷，已经被废弃了
	protected void finalize() throws Throwable 
	{
		this.jcheckbox = null;
		
		super.finalize();
	}
	*/
	
}
