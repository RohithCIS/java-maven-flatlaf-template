package com.vit.billing;

import javax.swing.*;
import com.formdev.flatlaf.*;

import com.vit.billing.pages.Login;
import com.vit.billing.utils.Database;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }

        Database db = new Database();

        Login login = new Login(db);
    }
}
