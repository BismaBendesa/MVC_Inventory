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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Bisma
 */
public class ItemModel implements ItemController {

    @Override
    public void Save(Item item) throws SQLException {
       String itemId  = item.itemIdField.getText();
       String itemName = item.itemNameField.getText();
       String category = item.categoryCb.getSelectedItem().toString();
       String brand = item.brandField.getText();
       String description = item.descriptionField.getText();
        int stock = Integer.parseInt(item.stockField.getText());
      float price = Float.parseFloat(item.priceField.getText());
       
       String sql = "INSERT INTO `item`(`item_id`,`item_name`, `category`, `brand`, `description`, `stock`, `price`) VALUES (?,?,?,?,?,?,?)";
       PreparedStatement ps;
       
       try{
           ps = DbCon.getcon().prepareStatement(sql);
           ps.setString(1, itemId);
           ps.setString(2, itemName);
           ps.setString(3, category);
           ps.setString(4, brand);
           ps.setString(5, description);
           ps.setInt(6, stock);
           ps.setFloat(7, price);
           ps.executeUpdate();
           
           if (ps.executeUpdate() != 0){
               JOptionPane.showMessageDialog(null, "Data Successfully Added");
           } else {
            JOptionPane.showMessageDialog(null, "not good");
           }
       } catch(Exception e){
          
       } finally{
           New(item);
           Display(item);
       }
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
        try{
            Connection con = DbCon.getcon();
            String sql = "UPDATE item SET item_name=?, category=?, brand=?, description=?, stock=?, price=?" + " WHERE item_id=?";
            PreparedStatement prepare = con.prepareStatement(sql);
            prepare.setString(7, item.itemIdField.getText());
            prepare.setString(1, item.itemNameField.getText());
            prepare.setString(2, (String) item.categoryCb.getSelectedItem());
            prepare.setString(3, item.brandField.getText());
            prepare.setString(4, item.descriptionField.getText());
            prepare.setString(5, item.stockField.getText());
            prepare.setString(6, item.priceField.getText().replace("$", ""));
            prepare.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Successfully Edited");
            prepare.close();
        } catch(Exception e){
            System.out.println(e);
        } finally{
            Display(item);
            New(item);
        }
    }

    @Override
    public void Delete(Item item) throws SQLException {
       try{
            Connection con = DbCon.getcon();
            String sql = "DELETE FROM item WHERE item_id=?";
            PreparedStatement prepare = con.prepareStatement(sql);
            prepare.setString(1, item.itemIdField.getText());
            prepare.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Successfully Deleted");
            prepare.close();
        } catch (Exception e){
            System.out.println(e);
        } finally{
            Display(item);
            New(item);
        }
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

    @Override
    public void TableClicked(Item item) throws SQLException {
        try{
            int pilih = item.inventoryTable.getSelectedRow();
            if (pilih == -1){
                return;
            }
            item.itemIdField.setText(item.tblmodel.getValueAt(pilih, 0).toString());
            item.itemNameField.setText(item.tblmodel.getValueAt(pilih, 1).toString());
            item.categoryCb.setSelectedItem(item.tblmodel.getValueAt(pilih, 2).toString());
            item.brandField.setText(item.tblmodel.getValueAt(pilih, 3).toString());
            item.descriptionField.setText(item.tblmodel.getValueAt(pilih, 4).toString());
            item.stockField.setText(item.tblmodel.getValueAt(pilih, 5).toString());
            item.priceField.setText(item.tblmodel.getValueAt(pilih, 6).toString());
        } catch (Exception e) {
        
        }
        
    }

}
