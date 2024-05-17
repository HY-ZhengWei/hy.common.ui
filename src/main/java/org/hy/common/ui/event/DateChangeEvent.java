package org.hy.common.ui.event;

import java.util.EventObject;

import org.hy.common.Date;





/**
 * 日期面板(JDatePanel)的事件
 *
 * @author   ZhengWei(HY)
 * @version  V1.0  2012-03-31
 */
public class DateChangeEvent extends EventObject
{
    private static final long serialVersionUID = -3846263791732474905L;
    
    private Date   date;
    
    
    
    public DateChangeEvent(Object i_Source ,Date i_Date)
    {
        super(i_Source);
        
        this.date = i_Date;
    }
    
    
    public Date getDate()
    {
        return this.date;
    }
    
}
