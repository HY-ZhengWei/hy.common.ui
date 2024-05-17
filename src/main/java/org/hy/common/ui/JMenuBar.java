package org.hy.common.ui;





/**
 * 菜单栏。
 * 为了更好的使用XJava功能。
 *
 * @author   ZhengWei(HY)
 * @version  V1.0  2013-04-22
 */
public class JMenuBar extends javax.swing.JMenuBar
{

    private static final long serialVersionUID = -8683938860942892700L;

    
    
    /**
     * 添加菜单大类
     * 
     * @param io_JMenu
     * @return
     */
    public JMenu addMenu(JMenu io_JMenu)
    {
        super.add(io_JMenu);
        return io_JMenu;
    }
    
}
