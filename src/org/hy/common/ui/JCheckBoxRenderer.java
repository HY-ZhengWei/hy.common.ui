package org.hy.common.ui;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;





/**
 * JTable中的复选框的实现
 *
 * @author   ZhengWei(HY)
 * @version  V1.0  2012-03-27
 */
public class JCheckBoxRenderer implements TableCellRenderer
{
	private static final JCheckBox $JCB_TRUE  = new JCheckBox();
	
	private static final JCheckBox $JCB_FALSE = new JCheckBox();
	
	
	
	static 
	{
		$JCB_TRUE .setSelected(true);
		$JCB_FALSE.setSelected(false);
	}
	
	
	
	public Component getTableCellRendererComponent(JTable  i_Table
			                                      ,Object  i_Value
	                                              ,boolean i_IsSelected
	                                              ,boolean i_HasFocus
	                                              ,int     i_Row
	                                              ,int     i_Column) 
	{
		if ( i_Value == null )
		{
			return $JCB_FALSE;
		}
		else if ( i_Value instanceof JCheckBox )
		{
			return (Component)i_Value;
		}
		else if ( i_Value instanceof Boolean )
		{
			if ( ((Boolean)i_Value).booleanValue() )
			{
				return $JCB_TRUE;
			}
			else
			{
				return $JCB_FALSE;
			}
		}
		else
		{
			return $JCB_FALSE;
		}
	}

}
