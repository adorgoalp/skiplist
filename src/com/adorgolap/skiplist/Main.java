package com.adorgolap.skiplist;

import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static SkipListController controller = new SkipListController();

	public static void main(String[] args) {
		int choice = -1;
		int key;

		do {
			choice = showMenu();
			switch (choice) {
			case 1:
				while (true) {
					System.out.println("Enter key to insert , '-9999' to end inserting");
					key = sc.nextInt();
					if (key == -9999) {
						break;
					}
					controller.skipInsert(key);
					controller.printList();
				}
				break;
			case 2:
				controller.printList();
				break;
			case 3:
				System.out.println("Enter key to get index");
				key = sc.nextInt();
				int index = controller.indexOf(key);
				if (index == -1) {
					System.out.println("Element is not present");
				} else {
					System.out.println("Index of " + key + " is " + index);
				}
				break;
			case 4:
				System.out.println("Bye bye!");
				break;
			case -1:
				System.out.println("Error");
				break;
			default:
				break;
			}
		} while (choice != 4);
	}

	private static int showMenu() {
		System.out.println("==== Skip List ====");
		System.out.println("Enter your operation by #");
		System.out.println("1.Insert");
		System.out.println("2.Print");
		System.out.println("3.IndexOf");
		System.out.println("4.Exit");
		int ch = -1;
		try {
			ch = sc.nextInt();
		} catch (Exception e) {
			System.out.println("Enter vaild choice");
			ch = -1;
		}

		return ch;
	}
}
