package com.swimmingpool;

import java.util.Scanner;

public class SwimmingPoolMain {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
        boolean running = true;

        while (running) {
		SwimmingPoolInterface  swimmingPoolInterface = new SwimmingPool();
		System.out.println("================================================\n      SWIMMING POOL MANAGEMENT SYSTEM \n================================================  ");
		System.out.println("1. Create Database. \n2. Create Table. \n3. Insert Member. \n4. Display All Member. \n5. Search Member."
				+ "			\n6. Update Member. \n7. Delete Member. \n8. Exit. \n9. Drop Table. \n10. Drop Database");
		System.out.println("Enter number to exicute Specific Task.");
		int choice = scanner.nextInt();
		  switch (choice) {

          case 1:
        	  swimmingPoolInterface.createDatabase();
              break;

          case 2:
        	  swimmingPoolInterface.createTable();
              break;

          case 3:
        	  swimmingPoolInterface.insertMember();
              break;

          case 4:
        	  swimmingPoolInterface.displayMembers();
              break;

          case 5:
        	  swimmingPoolInterface.searchMember();
              break;

          case 6:
        	  swimmingPoolInterface.updateMember();
              break;

          case 7:
        	  swimmingPoolInterface.deleteMember();
              break;

          case 8:
              running = false;
              System.out.println("Thank you for using Swimming Pool Management System!");
              break;

          case 9:
        	  swimmingPoolInterface.dropTable();
              break;

          case 10:
        	  swimmingPoolInterface.dropDatabase();
              break;

          default:
              System.out.println("Invalid choice. Please try again.");
		  }
      }

	}

}
