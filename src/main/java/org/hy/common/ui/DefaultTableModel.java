package org.hy.common.ui;

import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import org.hy.common.Help;





/**
 * 防止单元格被编辑。
 * 
 * 当某个单元处于编辑状态时，再删除此行时（DefaultTableModel.removeRow(x)）就会出错。
 * 而防止此错误发生的最简单方法：就是不允许单元格被编辑。
 * 
 * 1. 如果不特殊设置，默认所有列都 "不能" 被编辑
 * 2. 如果不特殊设置，默认所有行都 "可以" 被编辑
 *
 * @author   ZhengWei(HY)
 * @version  V1.0  2013-04-30
 */
public class DefaultTableModel extends javax.swing.table.DefaultTableModel
{
	private static final long serialVersionUID = -813538790140264635L;
	
	/** 保存哪些列能被编辑 */
	private Map<Integer ,Boolean> allowEditCols;
	
	/** 保存哪些行拒绝被编辑 */
	private Map<Integer ,Boolean> rejectEditRows;
	
	
	
	public DefaultTableModel()
	{
		super();
	}
	
	
	
	public DefaultTableModel(int rowCount, int columnCount)
	{
		super(rowCount ,columnCount);
	}
	
	
	
	public DefaultTableModel(Vector<String> columnNames, int rowCount)
	{
		super(columnNames ,rowCount);
	}
	
	
	
	public DefaultTableModel(Object[] columnNames, int rowCount)
	{
		super(columnNames ,rowCount);
	}
	
	
	
	public DefaultTableModel(Vector<?> data, Vector<String> columnNames)
	{
		super(data ,columnNames);
	}
	
	
	
	public DefaultTableModel(Object[][] data, Object[] columnNames)
	{
		super(data ,columnNames);
	}
	
	
	
	/**
	 * 设置列否能被编辑
	 * 
	 * 如果不特殊设置，默认所有列都 "不能" 被编辑
	 * 
	 * @param i_ColumnIndex  列号
	 * @param i_IsAllow      是否能被编辑
	 */
	public void setColEditIsAllow(int i_ColumnIndex ,boolean i_IsAllow)
	{
		if ( i_ColumnIndex < 0  )
		{
			return;
		}
		
		if ( Help.isNull(this.allowEditCols) )
		{
			this.allowEditCols = new Hashtable<Integer ,Boolean>();
		}
		
		Integer v_ColIndex = Integer.valueOf(i_ColumnIndex);
		if ( this.allowEditCols.containsKey(v_ColIndex) )
		{
			this.allowEditCols.remove(v_ColIndex);
		}
		this.allowEditCols.put(v_ColIndex ,Boolean.valueOf(i_IsAllow));
	}
	
	
	
	/**
	 * 设置行是否被拒绝编辑
	 * 
	 * 如果不特殊设置，默认所有行都 "可以" 被编辑
	 * 
	 * @param i_RowIndex     行号
	 * @param i_IsReject     是否被拒绝编辑
	 */
	public void setRowEditIsReject(int i_ColumnIndex ,boolean i_IsReject)
	{
		if ( i_ColumnIndex < 0  )
		{
			return;
		}
		
		if ( Help.isNull(this.rejectEditRows) )
		{
			this.rejectEditRows = new Hashtable<Integer ,Boolean>();
		}
		
		Integer v_ColIndex = Integer.valueOf(i_ColumnIndex);
		if ( this.rejectEditRows.containsKey(v_ColIndex) )
		{
			this.rejectEditRows.remove(v_ColIndex);
		}
		this.rejectEditRows.put(v_ColIndex ,Boolean.valueOf(i_IsReject));
	}
	
	
	
	/**
	 * 判断单元格是否允许被统编辑
	 * 
	 * 如果不特殊设置，默认所有列都 "不能" 被编辑
	 * 如果不特殊设置，默认所有行都 "可以" 被编辑
	 */
	public boolean isCellEditable(int i_RowIndex ,int i_ColumnIndex) 
	{
		if ( Help.isNull(this.allowEditCols) )
		{
			return false;
		}
		
		if ( this.allowEditCols.containsKey(Integer.valueOf(i_ColumnIndex)) )
		{
			if ( this.allowEditCols.get(Integer.valueOf(i_ColumnIndex)).booleanValue() )
			{
				if ( Help.isNull(this.rejectEditRows) )
				{
					return true;
				}
				else if ( this.rejectEditRows.containsKey(Integer.valueOf(i_RowIndex)) )
				{
					return !(this.rejectEditRows.get(Integer.valueOf(i_ColumnIndex)).booleanValue());
				}
				else
				{
					return true;
				}
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	
	
	/**
	 * 清除所有列级编辑设置
	 */
	public void clearColEditAllow()
	{
		if ( !Help.isNull(this.allowEditCols) )
		{
			this.allowEditCols.clear();
		}
	}
	
	
	
	/**
	 * 清除所有行级编辑设置
	 */
	public void clearRowEditReject()
	{
		if ( !Help.isNull(this.rejectEditRows) )
		{
			this.rejectEditRows.clear();
		}
	}
	
	
	
	/*
    ZhengWei(HY) Del 2016-07-30
    不能实现这个方法。首先JDK中的Hashtable、ArrayList中也没有实现此方法。
    它会在元素还有用，但集合对象本身没有用时，释放元素对象
    
    一些与finalize相关的方法，由于一些致命的缺陷，已经被废弃了
	protected void finalize() throws Throwable 
	{
		this.clearColEditAllow();
		this.clearRowEditReject();
		
		super.finalize();
	}
	*/
	
}
