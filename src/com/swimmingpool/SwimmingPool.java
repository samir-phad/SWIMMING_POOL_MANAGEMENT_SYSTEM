package com.swimmingpool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SwimmingPool implements SwimmingPoolInterface{
	
	
	Scanner scanner = new Scanner(System.in);
	DBConnection db = new DBConnection();
	Connection connection = DBConnection.getConnection();

	@Override
	public void createDatabase() {
		System.out.println("Enter database name :- ");
		String query = "create database + "+scanner.next()+"";
		try(
				Statement statement = connection.createStatement();
				
				){
			statement.execute(query);
			System.out.println("Database Created Successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void createTable() {
		String sql = "Create table if not exists swimming_pool(" + " member_id int primary key auto_Increment,"
					+ "member_name varchar(100)," + "age INT," + "gender varchar(10)," + "phone varchar(15),"
					+ " swimming_plan varchar(50)," + "trainer_name varchar(100)," + "session_date date," 
					+ "fee double," + "attendence varchar(20)" +")";
		try(
				Statement statement = connection.createStatement();
				
				){
			statement.executeUpdate(sql);
			System.out.println("Table created successfully!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error while creating table.");
		}
	}

	@Override
	public void droptable() {
		
		
	}

	@Override
	public void dropdatabase() {
		
		
	}

	@Override
	public void insertMember() {
		
		
	}

	@Override
	public void displayMember() {
		
		
	}

	@Override
	public void searchMember() {
		
		
	}

	@Override
	public void updateMember() {
		
		
	}

	@Override
	public void deleteMember() {
		
		
	}

}
