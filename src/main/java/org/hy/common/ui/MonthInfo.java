package org.hy.common.ui;





/**
 * 月份的信息类。主用于 org.hy.common.JPanel
 *
 * @author   ZhengWei(HY)
 * @version  V1.0  2012-03-30
 */
public class MonthInfo
{
    public  static final MonthInfo [] $MONTH_CN = {new MonthInfo(1  ,"　一月")
                                                  ,new MonthInfo(2  ,"　二月")
                                                  ,new MonthInfo(3  ,"　三月")
                                                  ,new MonthInfo(4  ,"　四月")
                                                  ,new MonthInfo(5  ,"　五月")
                                                  ,new MonthInfo(6  ,"　六月")
                                                  ,new MonthInfo(7  ,"　七月")
                                                  ,new MonthInfo(8  ,"　八月")
                                                  ,new MonthInfo(9  ,"　九月")
                                                  ,new MonthInfo(10 ,"　十月")
                                                  ,new MonthInfo(11 ,"十一月")
                                                  ,new MonthInfo(12 ,"十二月")
                                                };
    
    
    private int    month;
    
    private String monthName;

    
    
    public MonthInfo(int i_Month ,String i_MonthName)
    {
        this.month     = i_Month;
        this.monthName = i_MonthName;
    }
    
    
    public int getMonth()
    {
        return month;
    }
    
    
    public String getMonthName()
    {
        return monthName;
    }
    
    
    @Override
    public String toString()
    {
        return this.monthName;
    }
    
}