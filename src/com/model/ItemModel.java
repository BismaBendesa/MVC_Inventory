/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.connection.DbCon;
import com.controller.ItemController;
import com.view.Item;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Bisma
 */
public class ItemModel implements ItemController {

    @Override
    public void Save(Item item) throws SQLException {
       item.itemIdField.getText();
       item.itemNameField.getText();
       item.categoryCb.getSelectedItem().toString();
       item.brandField.getText();
       item.descriptionField.getText();
       item.stockField.getText();
       item.priceField.getText();
       
       String sql = "";
    }

    @Override
    public void New(Item item) throws SQLException {
       item.itemIdField.setText("");
       item.itemNameField.setText("");
       item.brandField.setText("");
       item.descriptionField.setText("");
       item.stockField.setText("");
       item.priceField.setText("");
    }

    @Override
    public void Edit(Item item) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Delete(Item item) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void SetCategoryCb(Item item) throws SQLException {
        String sql = "SELECT * FROM `category`";
        ResultSet rs;
        try{
            rs = DbCon.getcon().createStatement().executeQuery(sql);
            while(rs.next()){
                item.categoryCb.addItem(rs.getString("category_name"));
            }
        } catch(Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void Display(Item item) throws SQLException {
        item.tblmodel.getDataVector().removeAllElements();
        item.tblmodel.fireTableDataChanged();
        try{
            Connection con = DbCon.getcon();
            Statement stt = con.createStatement();
            String sql = "SELECT * FROM `item` ORDER BY `item_id` asc";
            ResultSet res = stt.executeQuery(sql);
            while (res.next()){
                Object[] ob = new Object[8];
                ob[0] = res.getString("item_id");
                ob[1] = res.getString("item_name");
                ob[2] = res.getString("category");
                ob[3] = res.getString("brand");
                ob[4] = res.getString("description");
                ob[5] = res.getString("stock");
                ob[6] = "$" + res.getString("price");
                item.tblmodel.addRow(ob);
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }

}
