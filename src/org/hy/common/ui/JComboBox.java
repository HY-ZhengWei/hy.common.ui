package org.hy.common.ui;

import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ComboBoxModel;





/**
 * 下拉列表框。
 * 
 * 可设置特殊功能有：
 * 1. 能实现类似于 <html> 中 <select> 标签的设置属性的方便功能。
 * 2. 设置有点击事务功能的按钮。即可以实现点击动作由一个线程来执行，就能实现按钮点击下变不可用，但执行完成后又可用的功能。
 *
 * @author   ZhengWei(HY)
 * @version  V1.0  2012-11-20
 */
public class JComboBox extends javax.swing.JComboBox<Object> implements JCOnClick
{
	
	private static final long serialVersionUID = -7279380869567339056L;
	
	
	/** 是否允许有按钮有点击事务的功能。这个属性有类内部决定，不可修改。 */
	private boolean                   isAllowOnClickTransaction;
	
	/** 按钮的事务接口对象。这个接口对象由具体的使用者来实现。不实现也可以，那就是一个普通的按钮。 
	 *  启用事务功能，就是用 setOnClickTransaction()方法实例化此属性
	 */
	private JCOnClickTransaction      onClickTransaction;
	
	/** 如果按钮启用了事务功能，就自己启用这个点击事件。 */
	private JCOnClickListener         onClickListener;
	
	
	
	public JComboBox() 
	{
        super();
        this.isAllowOnClickTransaction = true;
    }
	
	
	
	public JComboBox(ComboBoxModel<Object> aModel) 
	{
        super(aModel);
        this.isAllowOnClickTransaction = true;
    }
	
	
	
	public JComboBox(final Object items[]) 
	{
		super(items);
		this.isAllowOnClickTransaction = true;
    }
	
	
	public JComboBox(Vector<Object> items) 
	{
		super(items);
		this.isAllowOnClickTransaction = true;
    }
	
	
	
    public void addActionListener(ActionListener l)
    {
    	super.addActionListener(l);
    	this.isAllowOnClickTransaction = false;
    }
    
    
	
	public JCOnClickTransaction getOnClickTransaction() 
	{
		return onClickTransaction;
	}
	

	
	public void setOnClickTransaction(JCOnClickTransaction i_OnClickTransaction) 
	{
		if ( this.isAllowOnClickTransaction )
		{
			if ( this.onClickTransaction == null && i_OnClickTransaction != null )
			{
				if ( this.onClickListener == null )
				{
					this.onClickListener = new JCOnClickListener(this);
					
					super.addActionListener(this.onClickListener);
				}
				else 
				{
					super.removeActionListener(this.onClickListener);
					super.addActionListener(this.onClickListener);
				}
			}
			else if ( this.onClickTransaction != null && i_OnClickTransaction == null )
			{
				if ( this.onClickListener != null )
				{
					super.removeActionListener(this.onClickListener);
					this.onClickListener = null;
				}
			}
			
			this.onClickTransaction = i_OnClickTransaction;
		}
		else
		{
			this.onClickTransaction = null;
		}
	}
	


	public boolean isAllowOnClickTransaction()
	{
		return this.isAllowOnClickTransaction;
	}
	
	
	
	/**
	 * 添加列表项
	 * 
	 * @param i_ID
	 * @param i_Value
	 */
	public void addItem(String i_ID ,String i_Value)
	{
		this.addItem(new JComboBox_ItemInfo(i_ID ,i_Value));
	}
	
	
	
	/**
	 * 获取列表项的显示值
	 * 
	 * @param i_Index
	 */
	public String getItemValue(int i_Index)
	{
		Object v_Item = this.getItemAt(i_Index);
		
		if ( v_Item != null )
		{
			if ( v_Item instanceof JComboBox_ItemInfo )
			{
				return ((JComboBox_ItemInfo)v_Item).getValue();
			}
			else
			{
				return v_Item.toString();
			}
		}
		else
		{
			return null;
		}
	}
	
	
	
	/**
	 * 获取列表项的ID值
	 * 
	 * @param i_Index
	 */
	public Object getItemID(int i_Index)
	{
		Object v_Item = this.getItemAt(i_Index);
		
		if ( v_Item != null )
		{
			if ( v_Item instanceof JComboBox_ItemInfo )
			{
				return ((JComboBox_ItemInfo)v_Item).getId();
			}
			else
			{
				return v_Item.toString();
			}
		}
		else
		{
			return null;
		}
	}
	
	
	
	/**
	 * 根据列表选项信息类的ID设置选中的项
	 * 
	 * @param i_ItemID
	 */
	public void setSelectedItemByID(String i_ItemID)
	{
		if ( i_ItemID == null )
		{
			return;
		}
		
		this.setSelectedItem(new JComboBox_ItemInfo(i_ItemID ,""));
	}
	
}





/**
 * 下拉列表框的列表选项信息类
 *
 * @author   ZhengWei(HY)
 * @version  V1.0  2012-11-20
 */
class JComboBox_ItemInfo implements Comparable<JComboBox_ItemInfo>
{
	/** ID值 */
	private String id;
	
	/** 用于前台显示 */
	private String value;
	
	
	
	public JComboBox_ItemInfo(String i_ID ,String i_Value)
	{
		this.id    = i_ID;
		this.value = i_Value;
	}
	
	
	
	public String getId() 
	{
		return id;
	}
	
	
	
	public String getValue()
	{
		return value;
	}
	
	
	
	public int compareTo(JComboBox_ItemInfo i_Other) 
	{
		if ( i_Other == null )
		{
			return 1;
		}
		else
		{
			if ( this.id == null )
			{
				return -1;
			}
			else
			{
				return this.id.compareTo(i_Other.getId());
			}
		}
	}
	
	
	
	public boolean equals(Object i_Other)
	{
		if ( i_Other == null )
		{
			return false;
		}
		else if ( this == i_Other )
		{
			return true;
		}
		else if ( i_Other instanceof JComboBox_ItemInfo )
		{
			JComboBox_ItemInfo v_OtherItem = (JComboBox_ItemInfo)i_Other;
			
			if ( this.id == null )
			{
				return false;
			}
			else 
			{
				return this.id.equals(v_OtherItem.getId());
			}
		}
		else
		{
			return false;
		}
	}
	
	
	
	public String toString()
	{
		return this.value;
	}
	
}
