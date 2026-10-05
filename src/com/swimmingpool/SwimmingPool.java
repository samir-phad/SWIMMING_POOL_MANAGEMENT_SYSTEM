package com.swimmingpool;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SwimmingPool implements SwimmingPoolInterface {

	Scanner scanner = new Scanner(System.in);

	Connection connection = DBConnection.getConnection();

	String DATABASE_NAME = "swimming_pool";

	@Override
	public void createDatabase() {

		String sql = "CREATE DATABASE " + DATABASE_NAME;

		try (Statement statement = connection.createStatement()) {
			
			statement.executeUpdate(sql);

			System.out.println("Database created successfully!");

		} catch (SQLException e) {

			System.out.println("Error.");
		}
	}

	@Override
        @SuppressWarnings("CallToPrintStackTrace")
	public void createTable() {

	    String useDatabase = "USE " + DATABASE_NAME;

	    String sql = "CREATE TABLE IF NOT EXISTS swimming_pool ("
	            + "member_id INT PRIMARY KEY AUTO_INCREMENT, "
	            + "member_name VARCHAR(100), "
	            + "age INT, "
	            + "gender VARCHAR(10), "
	            + "phone VARCHAR(15), "
	            + "swimming_plan VARCHAR(50), "
	            + "trainer_name VARCHAR(100), "
	            + "session_date DATE, "
	            + "fee DOUBLE, "
	            + "attendance VARCHAR(20)"
	            + ")";

	    try (Statement statement = connection.createStatement()) {

	        statement.execute(useDatabase);
	        statement.executeUpdate(sql);

	        System.out.println("Table created successfully!");

	    } catch (SQLException e) {

	        System.out.println("Error.");
	        e.printStackTrace();
	    }
	}

	@Override
        @SuppressWarnings("CallToPrintStackTrace")
	public void insertMember() {

	    String useDatabase = "USE " + DATABASE_NAME;

	    String sql = "INSERT INTO swimming_pool "
	            + "(member_name, age, gender, phone, swimming_plan, "
	            + "trainer_name, session_date, fee, attendance) "
	            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    try {

	        // Select database
	        try (Statement statement = connection.createStatement()) {
	            statement.execute(useDatabase);
	        }

	        System.out.println("Enter Member Name:");
	        String name = scanner.nextLine();

	        System.out.println("Enter Age:");
	        int age = Integer.parseInt(scanner.nextLine());

	        System.out.println("Enter Gender:");
	        String gender = scanner.nextLine();

	        System.out.println("Enter Phone:");
	        String phone = scanner.nextLine();

	        System.out.println("Enter Swimming Plan:");
	        String plan = scanner.nextLine();

	        System.out.println("Enter Trainer Name:");
	        String trainer = scanner.nextLine();

	        System.out.println("Enter Session Date (YYYY-MM-DD):");
	        Date sessionDate = Date.valueOf(scanner.nextLine());

	        System.out.println("Enter Fee:");
	        double fee = Double.parseDouble(scanner.nextLine());

	        System.out.println("Enter Attendance:");
	        String attendance = scanner.nextLine();

	        try (PreparedStatement preparedStatement =
	                     connection.prepareStatement(sql)) {

	            preparedStatement.setString(1, name);
	            preparedStatement.setInt(2, age);
	            preparedStatement.setString(3, gender);
	            preparedStatement.setString(4, phone);
	            preparedStatement.setString(5, plan);
	            preparedStatement.setString(6, trainer);
	            preparedStatement.setDate(7, sessionDate);
	            preparedStatement.setDouble(8, fee);
	            preparedStatement.setString(9, attendance);

	            int rows = preparedStatement.executeUpdate();

	            if (rows > 0) {
	                System.out.println("Member inserted successfully!");
	            }
	        }

	    } catch (SQLException e) {

	        System.out.println("Error while inserting member.");
	        e.printStackTrace();

	    } catch (IllegalArgumentException e) {

	        System.out.println("Invalid input. Please enter valid values.");
	    }
	}

	@Override
        @SuppressWarnings("CallToPrintStackTrace")
	public void displayMembers() {

		String sql = "SELECT * FROM swimming_pool";

		try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {

			boolean found = false;

			while (resultSet.next()) {

				found = true;

				System.out.println("Member ID : " + resultSet.getInt("member_id"));

				System.out.println("Name : " + resultSet.getString("member_name"));

				System.out.println("Age : " + resultSet.getInt("age"));

				System.out.println("Gender  : " + resultSet.getString("gender"));

				System.out.println("Phone : " + resultSet.getString("phone"));

				System.out.println("Swimming Plan : " + resultSet.getString("swimming_plan"));

				System.out.println("Trainer : " + resultSet.getString("trainer_name"));

				System.out.println("Session Date : " + resultSet.getDate("session_date"));

				System.out.println("Fee : " + resultSet.getDouble("fee"));

				System.out.println("Attendance : " + resultSet.getString("attendance"));

			}

			if (!found) {
				System.out.println("No members found.");
			}

		} catch (SQLException e) {

			System.out.println("Error.");
			e.printStackTrace();
		}
	}

	@Override
        @SuppressWarnings("CallToPrintStackTrace")
	public void searchMember() {

		String sql = """
				SELECT * FROM swimming_pool
				WHERE member_id = ?
				""";

		try {

			System.out.println("Enter Member ID to search:");
			int memberId = Integer.parseInt(scanner.nextLine());

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				preparedStatement.setInt(1, memberId);

				try (ResultSet resultSet = preparedStatement.executeQuery()) {

					if (resultSet.next()) {

						System.out.println("Member ID     : " + resultSet.getInt("member_id"));

						System.out.println("Name          : " + resultSet.getString("member_name"));

						System.out.println("Age           : " + resultSet.getInt("age"));

						System.out.println("Gender        : " + resultSet.getString("gender"));

						System.out.println("Phone         : " + resultSet.getString("phone"));

						System.out.println("Swimming Plan : " + resultSet.getString("swimming_plan"));

						System.out.println("Trainer       : " + resultSet.getString("trainer_name"));

						System.out.println("Session Date  : " + resultSet.getDate("session_date"));

						System.out.println("Fee           : " + resultSet.getDouble("fee"));

						System.out.println("Attendance    : " + resultSet.getString("attendance"));

					} else {

						System.out.println("Member not found.");
					}
				}
			}

		} catch (SQLException e) {

			System.out.println("Error.");
			e.printStackTrace();
		}
	}

	@Override
        @SuppressWarnings("CallToPrintStackTrace")
	public void updateMember() {

		String sql = " UPDATE swimming_pool SET member_name = ?, age = ?, gender = ?, phone = ?, swimming_plan = ?, trainer_name = ?, session_date = ?, fee = ?, attendance = ? WHERE member_id = ?";

		try {

			System.out.println("Enter Member ID to update:");
			int memberId = Integer.parseInt(scanner.nextLine());

			System.out.println("Enter New Member Name:");
			String name = scanner.nextLine();

			System.out.println("Enter New Age:");
			int age = Integer.parseInt(scanner.nextLine());

			System.out.println("Enter New Gender:");
			String gender = scanner.nextLine();

			System.out.println("Enter New Phone:");
			String phone = scanner.nextLine();

			System.out.println("Enter New Swimming Plan:");
			String plan = scanner.nextLine();

			System.out.println("Enter New Trainer Name:");
			String trainer = scanner.nextLine();

			System.out.println("Enter New Session Date (YYYY-MM-DD):");
			Date sessionDate = Date.valueOf(scanner.nextLine());

			System.out.println("Enter New Fee:");
			double fee = Double.parseDouble(scanner.nextLine());

			System.out.println("Enter New Attendance:");
			String attendance = scanner.nextLine();

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				preparedStatement.setString(1, name);
				preparedStatement.setInt(2, age);
				preparedStatement.setString(3, gender);
				preparedStatement.setString(4, phone);
				preparedStatement.setString(5, plan);
				preparedStatement.setString(6, trainer);
				preparedStatement.setDate(7, sessionDate);
				preparedStatement.setDouble(8, fee);
				preparedStatement.setString(9, attendance);
				preparedStatement.setInt(10, memberId);

				int rows = preparedStatement.executeUpdate();

				if (rows > 0) {
					System.out.println("Member updated successfully");
				} else {
					System.out.println("Member not found.");
				}
			}

		} catch (SQLException e) {

			System.out.println("Error.");
			e.printStackTrace();
		}
	}

	@Override
        @SuppressWarnings("CallToPrintStackTrace")
	public void deleteMember() {

		String sql = "Delete From swimming_pool Where member_id = ?";

		try {

			System.out.println("Enter Member ID to delete Member :");
			int memberId = Integer.parseInt(scanner.nextLine());

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				preparedStatement.setInt(1, memberId);

				int rows = preparedStatement.executeUpdate();

				if (rows > 0) {
					System.out.println("Member deleted successfully!");
				} else {
					System.out.println("Member not found.");
				}
			}

		} catch (SQLException e) {

			System.out.println("Error.");
			e.printStackTrace();
		}
	}

	@Override
        @SuppressWarnings("CallToPrintStackTrace")
	public void dropTable() {

		String sql = "drop table swimming_pool";

		try (Statement statement = connection.createStatement()) {

			statement.executeUpdate(sql);

			System.out.println("Table dropped successfully!");

		} catch (SQLException e) {

			System.out.println("Error.");
			e.printStackTrace();
		}
	}

	@Override
        @SuppressWarnings("CallToPrintStackTrace")
	public void dropDatabase() {

		String sql = "drop DATABASE " + DATABASE_NAME;

		try (Statement statement = connection.createStatement()) {

			statement.executeUpdate(sql);

			System.out.println("Database dropped successfully");

		} catch (SQLException e) {

			System.out.println("Error.");
			e.printStackTrace();
		}
	}

    @Override
    public void droptable() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void dropdatabase() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void displayMember() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
