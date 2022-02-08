/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.connection.DbCon;
import com.controller.ItemController;
import com.view.Item;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Bisma
 */
public class ItemModel implements ItemController {

    @Override
    public void Save(Item item) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
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

}
