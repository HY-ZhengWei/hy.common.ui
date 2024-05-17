package org.hy.common.ui;

import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;





/**
 * 菜单大类。
 * 为了更好的使用XJava功能。
 *
 * @author   ZhengWei(HY)
 * @version  V1.0  2013-04-22
 */
public class JMenu extends javax.swing.JMenu
{

    private static final long serialVersionUID = 5728459717047052759L;

    
    
    /**
     * 添加菜单项
     * 
     * @param io_JMenuItem
     * @return
     */
    public javax.swing.JMenuItem addMenuItem(javax.swing.JMenuItem io_JMenuItem)
    {
        super.add(io_JMenuItem);
        return io_JMenuItem;
    }
    
    
    
    /**
     * 使 super.setMnemonic()方法有对字符串类型的处理能力
     * 
     * @param i_Key
     */
    public void setMnemonicStr(String i_Key)
    {
        super.setMnemonic(i_Key.charAt(0));
    }
    
    
    
    /**
     * 设置 Ctrl + 某个字符的快捷键
     * 
     * @param i_Key
     */
    public void setCtrlKey(String i_Key)
    {
        super.setAccelerator(KeyStroke.getKeyStroke(i_Key.charAt(0) ,KeyEvent.CTRL_DOWN_MASK ,false));
    }
    
    
    
    /**
     * 设置 Shift + 某个字符的快捷键
     * 
     * @param i_Key
     */
    public void setShiftKey(String i_Key)
    {
        super.setAccelerator(KeyStroke.getKeyStroke(i_Key.charAt(0) ,KeyEvent.SHIFT_DOWN_MASK ,false));
    }
    
    
    
    /**
     * 设置 Alt + 某个字符的快捷键
     * 
     * @param i_Key
     */
    public void setAltKey(String i_Key)
    {
        super.setAccelerator(KeyStroke.getKeyStroke(i_Key.charAt(0) ,KeyEvent.CTRL_DOWN_MASK ,false));
    }

}
