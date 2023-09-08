/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rss.gui;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Admin
 */
public class function {
    
    public void search(javax.swing.JTable tb, String key)
    {
        DefaultTableModel table = (DefaultTableModel)tb.getModel();
        String search = key.toLowerCase();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table);
        tb.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
    }
    
    public void updateTable(javax.swing.JTable tb,Object[] list)
    {
        
        if(list != null)
        {
            DefaultTableModel model = (DefaultTableModel)tb.getModel();
            Object[] lines = list;
            model.setRowCount(0);
            
            Arrays.stream(lines)
                    .forEach(x -> model.addRow(x.toString().split(";")));  
        }
    }
    
    public void updateCustTable(javax.swing.JTable tb,Object[] list)
    {
        if(list != null)
        {
            DefaultTableModel model = (DefaultTableModel)tb.getModel();
            Object[] lines = list;
            model.setRowCount(0);
            
            Arrays.stream(lines)
                    .filter(x -> x.toString().split(";")[2].equals("Customer"))
                    .forEach(x-> model.addRow(x.toString().split(";")));
        }
    }
    
    public void orderTable(javax.swing.JTable tb,Object[] list, String username)
    {
        if(list != null)
        {
            DefaultTableModel model = (DefaultTableModel)tb.getModel();
            Object[] lines = list;
            model.setRowCount(0);
            
            Arrays.stream(lines)
                    .filter(x -> x.toString().split(";")[1].equals(username))
                    .filter(x -> x.toString().split(";")[6].equals("To Ship"))
                    .forEach(x-> model.addRow(x.toString().split(";")));
        }
    }
    
    public void limitedNo (java.awt.event.KeyEvent evt, javax.swing.JTextField txt, int count)
    {
        String no =txt.getText();
        int length = no.length();
        
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <='9')
        {
                if(length < count )
                {
                    txt.setEditable(true);
                }else
                {
                    txt.setEditable(false);
                }
        }else
        {
            if (evt.getKeyChar() == KeyEvent.VK_BACK_SPACE)
            {
                txt.setEditable(true);
            }else
            {
                txt.setEditable(false);
            }
        }
    }
    
    public void allowDigit(java.awt.event.KeyEvent evt)
    {
        char c = evt.getKeyChar();
        
        if (!Character.isDigit(c)){
            evt.consume();
        }
    }
    
}
