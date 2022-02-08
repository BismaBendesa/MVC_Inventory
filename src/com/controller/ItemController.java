/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.view.Item;
import java.sql.SQLException;

/**
 *
 * @author Bisma
 */
public interface ItemController {
    public void Save(Item item) throws SQLException;
    public void New(Item item) throws SQLException;
    public void Edit(Item item) throws SQLException;
    public void Delete(Item item) throws SQLException;
    public void SetCategoryCb(Item item) throws SQLException;
    public void Display(Item item) throws SQLException;
}
