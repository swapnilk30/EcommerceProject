package com.shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Admin {
	
	 static Scanner sc = new Scanner(System.in);

	    public static void adminRights() {
	        System.out.println("As an Admin you have few rights");
	        System.out.println();
	        System.out.println("Press 1 to check quantity of any product");
	        System.out.println("Press 2 to see registered user list");
	        System.out.println("Press 3 to check user history for product purchase");
	        int right = sc.nextInt();
	        switch (right) {
	            case 1: {
	                showQuantity();
	                break;
	            }
	            case 2: {
	                showAllRegisteredUsers();
	                break;
	            }
	            case 3: {
	                showUserHistory();
	                break;
	            }
	        }

	        System.out.println("Press 1 if you want to perform any more actions, else press any key to exit");
	        int i = sc.nextInt();
	        if (i==1){
	            adminRights();
	        }
	        else {
	            System.out.println("Thank you for using us, have a great day");
	        }

	    }

	    public static void showUserHistory() {
	        sc.nextLine();
	        System.out.println("Enter username");
	        String username = sc.nextLine();
	        try {
	            //Connection
	            Connection connection = ConnectionProvider.createConnection();
	            //Query
	            String q = "select * from " + username + "";

	            Statement statement = connection.createStatement();
	            ResultSet resultSet = statement.executeQuery(q);

	            while (resultSet.next()) {
	                int product_id = resultSet.getInt(1);
	                String name = resultSet.getString(2);
	                int price = resultSet.getInt(3);
	                int quantity = resultSet.getInt(4);

	                System.out.println("Product Id : " + product_id);
	                System.out.println("Name : " + name);
	                System.out.println("Price : " + price);
	                System.out.println("Quantity : " + quantity);
	                System.out.println("--------------------------------------");
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    public static void showAllRegisteredUsers() {
	        try {
	            //Connection
	            Connection connection = ConnectionProvider.createConnection();
	            //Query
	            String q = "select * from users";

	            Statement statement = connection.createStatement();
	            ResultSet resultSet = statement.executeQuery(q);

	            while (resultSet.next()) {
	                String username = resultSet.getString(1);
	                String password = resultSet.getString(2);
	                String address = resultSet.getString(3);
	                String phoneNumber = resultSet.getString(4);
	                String fullName = resultSet.getString(5);
	                String emailId = resultSet.getString(6);


	                System.out.println("UserName : " + username);
	                System.out.println("Password : " + password);
	                System.out.println("Address : " + address);
	                System.out.println("Phone Number : " + phoneNumber);
	                System.out.println("Full Name : " + fullName);
	                System.out.println("Email Id : " + emailId);
	                System.out.println("--------------------------------------");
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    public static void showQuantity() {
	        Scanner sc = new Scanner(System.in);
	        System.out.println("Enter Product Id");
	        int n = sc.nextInt();
	        try {
	            //Connection
	            Connection connection = ConnectionProvider.createConnection();

	            //Query
	            String q = "select p_quantity from product where p_id = " + n + "";

	            //Statement

	            Statement statement = connection.createStatement();
	            ResultSet resultSet = statement.executeQuery(q);

	            while (resultSet.next()) {
	                int quantity = resultSet.getInt(1);

	                System.out.println("Quantity : " + quantity);
	                System.out.println("--------------------------------------");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    public static void showAllProducts() {
	        try {
	            //Connection
	            Connection connection = ConnectionProvider.createConnection();
	            //Query
	            String q = "select * from product";

	            Statement statement = connection.createStatement();
	            ResultSet resultSet = statement.executeQuery(q);

	            while (resultSet.next()) {
	                int product_id = resultSet.getInt(1);
	                String name = resultSet.getString(4);
	                int price = resultSet.getInt(3);
	                String description = resultSet.getString(2);
	                int quantity = resultSet.getInt(5);

	                System.out.println("Product Id : " + product_id);
	                System.out.println("Name : " + name);
	                System.out.println("Price : " + price);
	                System.out.println("Description : " + description);
	                System.out.println("Quantity : " + quantity);
	                System.out.println("--------------------------------------");
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    public static boolean insertProductsToDb(ProductDetails product) {

	        boolean f = false;

	        try {
	            //Connection
	            Connection connection = ConnectionProvider.createConnection();
	            //Query
	            String q = "insert into product(p_id,p_name,p_price,description,quantity) values(?, ?, ?, ?, ?)";

	            //Adding Values
	            PreparedStatement statement = connection.prepareStatement(q);

	            //Set the value of parameter
	            statement.setInt(1, product.getProduct_id());
	            statement.setString(2, product.getName());
	            statement.setInt(3, product.getPrice());
	            statement.setString(4, product.getDescription());
	            statement.setInt(5, product.getQuantity());

	            //Execute
	            statement.executeUpdate();
	            f = true;

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return f;
	    }
}
