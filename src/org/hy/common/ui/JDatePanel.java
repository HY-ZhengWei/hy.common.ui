package org.hy.common.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EventObject;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerListModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.hy.common.ui.event.DateChangeListener;

import org.hy.common.Date;
import org.hy.common.Help;
import org.hy.common.ui.event.DateChangeEvent;





/**
 * 日期面板(YYYY-MM-DD)。
 *
 * @author   ZhengWei(HY)
 * @version  V1.0  2012-03-29
 */
public final class JDatePanel extends JPanel
{
	private static final long serialVersionUID = -7494487215355052974L;
	
	/** 天份标题的背景色 */
	private static final Color $COLOR_TITLE_BG                 = new Color(120 ,150 ,255);
	
	/** 天份标题的背景色 */
	private static final Color $COLOR_TITLE_FG                 = new Color(230 ,230 ,230);
	
	/** 选中天份背景色 */
	private static final Color $COLOR_DAY_SELECT_BG            = new Color(90 ,120 ,255);
	
	/** 选中天份前景色 */
	private static final Color $COLOR_DAY_SELECT_FG            = new Color(255 ,255 ,255);
	
	/** 选中假日、星期六、星期天的前景色 */
	private static final Color $COLOR_DAY_SELECT_HOLIDAY_FG    = new Color(255 ,128 ,128);
	
	/** 假日、星期六、星期天的前景色 */
	private static final Color $COLOR_HOLIDAY_FG               = new Color(255 ,0   ,0);
	
	/** 标记"今天"的边框色 */
	private static final Color $COLOR_TODAY_BORDER             = new Color(255 ,0   ,0);
	
	private static final String [] $WEEK_CN = {"日"
		                                      ,"一"
		                                      ,"二"
		                                      ,"三"
		                                      ,"四"
		                                      ,"五"
		                                      ,"六"
	                                          };
	
	/** 日期。控件统一对外提供的值 */
	private Date                            value;
	
	/** 顶部面板。放置月份与年份的控件 */
	private JPanel                          jpTop;
	
	/** 月份控件 */
	private JComboBox<?>                    jcMonth;
	
	/** 年份控件 */
	private JSpinner                        jsYear;
	
	/** 天份面板 */
	private JScrollPane                     jsPaneDay;
	
	/** 天份面板 */
	private JTable                          jtDay;
	
	/** 
	 * 是否允许事件。内部参数不对外开放。
	 * 当由内部发起的，对控件的改变时，即不发起事件。 
	 */
	private boolean                         isAllowEvent;
	
	/** 选中单元格后的字体 */
	private Font                            $FONT_SELECT;
	
	/** 之前选择的单元格行索引。下标从0开始 */
	private int                             oldRowIndex;
	
	/** 之前选择的单元格列索引。下标从0开始 */
	private int                             oldColIndex;
	
	/** 上次鼠标的点击时间 */
	private long                            oldMouseOnClickTime;
	
	/** 自定义事件的监听器集合 */
	private Collection<DateChangeListener>  listeners;
	
	
	
	/**
	 * 获取月份数据信息
	 * 
	 * @return
	 */
	public static final MonthInfo [] GetMonth()
	{
		return MonthInfo.$MONTH_CN;
	}
	
	
	
	/**
	 * 获取星期数据信息
	 * 
	 * @return
	 */
	public static final String [] GetWeek()
	{
		return $WEEK_CN;
	}
	
	
	
	public JDatePanel() 
	{
		super();
		
		this.init();
		this.setValue(new Date());
	}
	
	
	
	public JDatePanel(java.util.Date i_Date) 
	{
		this(new Date(i_Date));
	}
	
	
	
	public JDatePanel(Date i_Date) 
	{
		super();
		
		this.init();
		this.setValue(i_Date);
	}

	
	
	public JDatePanel(boolean isDoubleBuffered) 
	{
		super(isDoubleBuffered);
		
		this.init();
		this.setValue(new Date());
	}

	
	
	public JDatePanel(LayoutManager layout) 
	{
		super(layout);
		
		this.init();
		this.setValue(new Date());
	}
	
	
	
	public JDatePanel(LayoutManager layout, boolean isDoubleBuffered) 
	{
		super(layout, isDoubleBuffered);
		
		this.init();
		this.setValue(new Date());
	}
	
	
	
	/**
	 * 注册事件
	 * 
	 * @param e
	 */
	public void addDateChangeListener(DateChangeListener e)
	{
		if ( this.listeners == null )
		{
			this.listeners = new HashSet<DateChangeListener>();
		}
		
		this.listeners.add(e);
	}
	
	
	
	/**
	 * 移除事件
	 * 
	 * @param e
	 */
	public void removeDateChangeListener(DateChangeListener e)
	{
		if ( this.listeners == null )
		{
			return;
		}
		
		this.listeners.remove(e);
	}
	
	
	
	/**
	 * 触发日期值改变事件
	 * 
	 * @param i_Date
	 */
	protected void fireDateChange(Date i_Date)
	{
		if ( this.listeners == null )
		{
			return;
		}
		
		DateChangeEvent v_Event = new DateChangeEvent(this ,new Date(i_Date));
		
		notifyListeners(v_Event);
	}
	
	
	
	/**
	 * 在有效单元格上的鼠标双击时，触发触发器
	 * 
	 * @param i_Date
	 */
	protected void fireDoubleClick(Date i_Date)
	{
		if ( this.listeners == null )
		{
			return;
		}
		
		DateChangeEvent v_Event = new DateChangeEvent(this ,new Date(i_Date));
		
		notifyDoubleClickListeners(v_Event);
	}
	
	
	
	/**
	 * 通知所有注册事件监听的对象
	 * 
	 * @param i_Event
	 */
	private void notifyListeners(DateChangeEvent i_Event)
	{
		Iterator<DateChangeListener> v_Iter = this.listeners.iterator();

		while ( v_Iter.hasNext() ) 
		{
			DateChangeListener v_Listener = v_Iter.next();

			v_Listener.onChangeListener(i_Event);
		}
	}
	
	
	
	/**
	 * 通知所有注册事件监听的对象
	 * 
	 * @param i_Event
	 */
	private void notifyDoubleClickListeners(DateChangeEvent i_Event)
	{
		Iterator<DateChangeListener> v_Iter = this.listeners.iterator();

		while ( v_Iter.hasNext() ) 
		{
			DateChangeListener v_Listener = v_Iter.next();

			v_Listener.doubleClickListener(i_Event);
		}
	}
	
	
	
	/**
	 * 初始化
	 */
	private void init()
	{
		this.jpTop        = new JPanel();
		this.jcMonth      = new JComboBox<Object>(GetMonth());
		this.jsYear       = new JSpinner();
		this.jsPaneDay    = new JScrollPane();
		this.jtDay        = new JTable();
		
		
		Font v_MonthFont = new Font(this.jcMonth.getFont().getFontName() ,0 ,12);
		Font v_YearFont  = new Font(this.jsYear .getFont().getFontName() ,0 ,12);
		this.jcMonth.setFont(v_MonthFont);
		this.jsYear.setFont(v_YearFont);
		
		
		
		List<String> v_YearList = new ArrayList<String>();
		for (int v_Index=1980; v_Index<=2100; v_Index++)
		{
			v_YearList.add(String.valueOf(v_Index));
		}
		
		SpinnerListModel v_YearModel = new SpinnerListModel(v_YearList);
		this.jsYear.setModel(v_YearModel);
		
		
		this.jsPaneDay.getViewport().setBackground(Color.WHITE);
		
		
		this.jtDay.setShowGrid(false);
		this.jtDay.setRowSelectionAllowed(false);
		this.jtDay.setColumnSelectionAllowed(false);
		this.jtDay.setCellSelectionEnabled(true);
		this.jtDay.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		
		// 布局
		int v_MyWidth = 180;
		this.jpTop.setLayout(null);
		this.jpTop.setBounds(0 ,0 ,v_MyWidth ,20);
		
		this.jcMonth.setBounds(0  ,0 ,70 ,17);
		this.jsYear .setBounds(this.jpTop.getWidth() - 70 ,0 ,70 ,18);
		
		this.jpTop.add(this.jcMonth);
		this.jpTop.add(this.jsYear);
		
		
		this.jsPaneDay.getViewport().add(this.jtDay);
		this.jsPaneDay.setBounds(0 ,this.jpTop.getHeight() ,v_MyWidth ,119);
		
		
		this.setLayout(null);
		this.setSize(v_MyWidth ,this.jpTop.getHeight() + this.jsPaneDay.getHeight() + 2);
		
		this.add(this.jpTop);
		this.add(this.jsPaneDay);
		
		
		
		// 事件
		this.jcMonth.addItemListener(  new MonthListener());
		this.jsYear .addChangeListener(new YearListener());
		this.jtDay  .addMouseListener( new DayCellRenderer());
		
		
		
		$FONT_SELECT = new Font(this.getFont().getFontName() ,1 ,this.getFont().getSize());
	}
	
	
	
	/**
	 * 获取时间
	 * 
	 * @return
	 */
	public Date getValue()
	{
		return new Date(this.value);
	}
	
	
	
	/**
	 * 设置月份
	 * 
	 * @param i_Month
	 */
	protected void setMonth(int i_Month)
	{
		Date v_NewValue = new Date(this.value);
		
		v_NewValue.setMonth(i_Month);
		
		this.setValue(v_NewValue);
	}
	
	
	
	/**
	 * 设置年份
	 * 
	 * @param i_Year
	 */
	protected void setYear(int i_Year)
	{
		Date v_NewValue = new Date(this.value);
		
		v_NewValue.setYear(i_Year);
		
		this.setValue(v_NewValue);
	}
	
	
	
	/**
	 * 设置天份
	 * 
	 * @param i_Day
	 */
	protected void setDay(int i_Day)
	{
		Date v_NewValue = new Date(this.value);
		
		v_NewValue.setDate(i_Day);
		
		this.setValue(v_NewValue);
	}
	
	
	
	/**
	 * 设置时间
	 * 
	 * @param i_Value  java.util.Date
	 */
	public void setValue(java.util.Date i_Value)
	{
		this.setValue(new Date(i_Value));
	}
	
	
	
	/**
	 * 设置时间
	 * 
	 * @param i_Value  org.hy.common.Date
	 */
	public void setValue(Date i_Value)
	{
		if ( i_Value == null )
		{
			return;
		}
		
		boolean v_IsFireEvent = (this.value == null) || !this.value.equals(i_Value); 
		this.isAllowEvent     = false;
		this.value            = i_Value.getFirstTimeOfDay();
		
		
		// 设置月份控件中的选择值
		this.jcMonth.setSelectedIndex(this.value.getMonth() - 1);
		
		// 设置年份控件中的选择值
		this.jsYear.setValue(String.valueOf(this.value.getYear()));
		
		
		
		DefaultTableModel v_DayModel = new DefaultTableModel();
		// 生成日期的列标题
		for (int v_Index=0; v_Index<GetWeek().length; v_Index++)
		{
			v_DayModel.addColumn(GetWeek()[v_Index].toString());
		}
		
		int         v_Today           = this.value.getDay();
		int         v_TodayRowIndex   = 0;
		int         v_TodayColIndex   = 0;
		Date        v_FirstDayOfMonth = this.value.getFirstDayOfMonth();
		Date        v_LastDayOfMonth  = this.value.getLastDayOfMonth(); 
		int         v_FirstDayWeek    = v_FirstDayOfMonth.getWeek(Date.WEEK_FIRST_EN);
		int         v_LastDay         = v_LastDayOfMonth.getDay(); 
		String [][] v_DayArr          = new String[6][7];
		
		// 生成一个月份所有日期的数组组合及位置
		for (int v_ColIndex=1; v_ColIndex<=7; v_ColIndex++)
		{
			for (int v_RowIndex=1; v_RowIndex<=6; v_RowIndex++)
			{
				int v_CellIndex = (v_RowIndex - 1) * 7 + v_ColIndex;
				
				if ( v_FirstDayWeek <= v_CellIndex && v_CellIndex - v_FirstDayWeek < v_LastDay )
				{
					int v_Day = v_CellIndex - v_FirstDayWeek + 1;
					
					if ( v_Day == v_Today )
					{
						v_TodayRowIndex = v_RowIndex - 1;
						v_TodayColIndex = v_ColIndex - 1;
					}
					
					if ( v_Day < 10 )
					{
						v_DayArr[v_RowIndex - 1][v_ColIndex - 1] = "    " + String.valueOf(v_Day);
					}
					else
					{
						v_DayArr[v_RowIndex - 1][v_ColIndex - 1] = "  "   + String.valueOf(v_Day);
					}
				}
				else
				{
					v_DayArr[v_RowIndex - 1][v_ColIndex - 1] = "";
				}
			}
		}
		
		// 将日期数组按行添加到JTable中
		for (int v_RowIndex=1; v_RowIndex<=6; v_RowIndex++)
		{
			v_DayModel.addRow(v_DayArr[v_RowIndex - 1]);
		}
		
		
		this.jtDay.setModel(v_DayModel);
		
		
		// 设置单元格的编辑器与显示控件
		for (int i_ColIndex=0; i_ColIndex<7; i_ColIndex++)
		{
			TableColumn v_TableColumn = this.jtDay.getColumnModel().getColumn(i_ColIndex);
			
			v_TableColumn.setCellRenderer(new DayCellRenderer());
			v_TableColumn.setCellEditor(new DayCellEditor(new JTextField()));
		}
		
		
		// 设置标题的渲染器
		this.jtDay.getTableHeader().setDefaultRenderer(new DayTitleCellRenderer());
		this.jtDay.getTableHeader().setResizingAllowed(false);    // 不允许改变列宽
		this.jtDay.getTableHeader().setReorderingAllowed(false);  // 不允许移动列
		
		
		this.isAllowEvent = true;
		

		this.jtDay.changeSelection(v_TodayRowIndex, v_TodayColIndex, false, false);
		
		
		// 触发日期值改变事件
		if ( v_IsFireEvent )
		{
			this.fireDateChange(this.value);
		}
	}
	
	
	
	/**
	 * 月份改变的事件
	 */
	class MonthListener implements ItemListener
	{
		public void itemStateChanged(ItemEvent e) 
		{
			if ( e.getStateChange() == ItemEvent.SELECTED )
			{
				MonthInfo v_NewMonth = ((MonthInfo)e.getItem());
				
				if ( isAllowEvent )
				{
					setMonth(v_NewMonth.getMonth());
				}
			}
		}
		
	}
	
	
	
	/**
	 * 年份改变的事件
	 */
	class YearListener implements ChangeListener
	{
		public void stateChanged(ChangeEvent e) 
		{
			int v_NewYear = Integer.parseInt(jsYear.getValue().toString());
			
			if ( isAllowEvent )
			{
				setYear(v_NewYear);
			}
		}	
	}
	
	
	
	/**
	 * 标题栏的单元格渲染器
	 */
	class DayTitleCellRenderer extends DefaultTableCellRenderer
	{
		private static final long serialVersionUID = -7620740998910967018L;
		
		
		public Component getTableCellRendererComponent(JTable  i_Table
										              ,Object  i_Value
										              ,boolean i_IsSelected
										              ,boolean i_HasFocus
										              ,int     i_Row
										              ,int     i_Column)
		{
			this.setBackground($COLOR_TITLE_BG);
			this.setForeground($COLOR_TITLE_FG);
			this.setHorizontalAlignment(SwingConstants.CENTER);
			return super.getTableCellRendererComponent(i_Table ,i_Value ,i_IsSelected ,i_HasFocus ,i_Row ,i_Column);
		}
		
	}
	
	
	
	/**
	 * 单元格渲染器及相关事件
	 */
	class DayCellRenderer extends DefaultTableCellRenderer implements MouseListener
	{	
		private static final long serialVersionUID = -3943588426730900723L;
		
		
		public DayCellRenderer()
		{
			
		}
		
		
		public Component getTableCellRendererComponent(JTable  i_Table
										              ,Object  i_Value
										              ,boolean i_IsSelected
										              ,boolean i_HasFocus
										              ,int     i_Row
										              ,int     i_Column)
		{

			if ( i_Value == null || Help.isNull(i_Value.toString()) ) 
			{
				return null;
			}
			
			// 星期六、星期天设置文字为红色
			if ( i_Column == 0 || i_Column == 6 )
			{
				this.setBackground(Color.WHITE);
				this.setForeground($COLOR_HOLIDAY_FG);
				this.setFont(i_Table.getFont());
			}

			// 选中的文字样式
			if ( i_IsSelected || i_HasFocus )
			{
				this.setBackground($COLOR_DAY_SELECT_BG);
				
				if ( i_Column == 0 || i_Column == 6 )
				{
					this.setForeground($COLOR_DAY_SELECT_HOLIDAY_FG);
				}
				else
				{
					this.setForeground($COLOR_DAY_SELECT_FG);
				}
				this.setFont($FONT_SELECT);
				
				oldRowIndex = i_Row;
				oldColIndex = i_Column;
			}
			// 其它的文字样式
			else
			{
				if ( 0 < i_Column && i_Column < 6 )
				{
					this.setBackground(Color.WHITE);
					this.setForeground(Color.BLACK);
					this.setFont(i_Table.getFont());
				}
			}
			
			// 设置"今天"的样式
			Date   v_Today = new Date();
			String v_Value = i_Value.toString().trim();
			if ( v_Value.equals(String.valueOf(v_Today.getDay()))
			  && ((MonthInfo)jcMonth.getSelectedItem()).getMonth() == v_Today.getMonth()
			  && jsYear.getValue().toString().equals(String.valueOf(v_Today.getYear())) )
			{
				this.setBorder(new LineBorder($COLOR_TODAY_BORDER));
			}
			else
			{
				this.setBorder(null);
			}
			 
			
			this.setValue(i_Value);
			
			return this;
		}


		public void mouseReleased(MouseEvent e) 
		{
			Object v_Cell = jtDay.getValueAt(oldRowIndex ,oldColIndex);
			
			if ( v_Cell == null )
			{
				return;
			}
			else if ( Help.isNull(v_Cell.toString()) )
			{
				return;
			}
			
			setDay(Integer.parseInt(v_Cell.toString().trim()));
		}


		public void mouseClicked(MouseEvent e) 
		{
			int v_RowIndex = jtDay.rowAtPoint(e.getPoint());
			int v_ColIndex = jtDay.columnAtPoint(e.getPoint());
			
			Object v_Cell = jtDay.getValueAt(v_RowIndex ,v_ColIndex);
			
			if ( v_Cell == null )
			{
				return;
			}
			else if ( Help.isNull(v_Cell.toString()) )
			{
				return;
			}
			
			
			long v_OnClickTime = System.currentTimeMillis();
			if ( v_OnClickTime - oldMouseOnClickTime <= 300 )
			{
				// 触发鼠标在单元格上的双击事件。双击时间差为 300 毫秒。
				fireDoubleClick(value);
			}
			oldMouseOnClickTime = v_OnClickTime;
		}


		public void mouseEntered(MouseEvent e) 
		{
			//System.out.println("mouseEntered");
		}


		public void mouseExited(MouseEvent e) 
		{
			//System.out.println("mouseExited");
		}


		public void mousePressed(MouseEvent e) 
		{
			//System.out.println("mousePressed");
		}
		
	}
	
	
	
	/**
	 * 单元格编辑及相关事件
	 */
	class DayCellEditor extends DefaultCellEditor
	{
		private static final long serialVersionUID = -6348831629700136963L;
		
		private JTextField  cellObject;
		
		
		
		public DayCellEditor(JTextField i_CellObject) 
		{
			super(i_CellObject);
			
			this.cellObject = i_CellObject;
			this.cellObject.setEditable(false);
			this.cellObject.setBorder(null);
			this.cellObject.setDragEnabled(false);
		}
		
		
		public Component getTableCellEditorComponent(JTable  i_Table
		                ,Object  i_Value
		                ,boolean i_IsSelected
		                ,int     i_Row
		                ,int     i_Column) 
		{
			if ( i_Value == null ) 
			{
				return null;
			}
			
			this.cellObject.setText(i_Value.toString());
			
			return (Component)this.cellObject;
		}
		
		
		public Object getCellEditorValue() 
		{
			return this.cellObject.getText();
		}
		
		
		public boolean isCellEditable(EventObject anEvent) 
		{
			return true;
		}
		
		
		public boolean shouldSelectCell(EventObject anEvent) 
		{
			if ( this.cellObject == null || Help.isNull(this.cellObject.getText()) )
			{
				this.fireEditingCanceled();
				return false;
			}
			
			//this.cellObject.setBackground($COLOR_DAY_SELECT);
			//this.cellObject.setFont($FONT_SELECT);
			
			// 这条语句是关键中的关键(提示:重绘)
			setDay(Integer.parseInt(this.cellObject.getText().trim()));
			
			return true;
		}

	}
	
}

