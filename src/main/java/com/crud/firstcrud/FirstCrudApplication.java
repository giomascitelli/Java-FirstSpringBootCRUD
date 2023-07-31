package com.crud.firstcrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class FirstCrudApplication {
    private static List<User> users = new ArrayList<>();
    private static int nextUserId = 1;
    private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		SpringApplication.run(FirstCrudApplication.class, args);
		runProgramLoop();
	}

    private static void runProgramLoop() {
        String choice;

        while (true) {
            printMenu();
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createUser();
                    break;
                case "2":
                    readUsers();
                    break;
                case "3":
                    updateUser();
                    break;
                case "4":
                    deleteUser();
                    break;
                case "0":
                    System.out.println("Exiting program...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n[1] - Create user");
        System.out.println("[2] - Read user database");
        System.out.println("[3] - Update user");
        System.out.println("[4] - Delete user");
        System.out.println("[0] - Exit program");
        System.out.print("\nEnter your choice: ");
    }

    private static void createUser() {
        System.out.print("Enter the user's username: ");
        String username = scanner.nextLine();

		while (!isUsernameAvailable(username)) {
			System.out.println("Username is already taken.");
			System.out.print("Enter the user's username: ");
			username = scanner.nextLine();
		}

        int age;
        while (true) {
            try {
                System.out.print("Enter the user's age: ");
                age = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please input a valid number.");
            }
        }

		String email;
		while (true) {
			System.out.print("Enter the user's email: ");
			email = scanner.nextLine();

			if (email.matches(".+@.+\\.com") && isEmailAvailable(email)) {
				break;
			} else {
				if (!email.matches(".+@.+\\.com")) {
					System.out.println("Invalid email format.");
				} else {
					System.out.println("Email already taken.");
				}
				
			}
		}

        User newUser = new User(nextUserId, username, age, email);
        users.add(newUser);
        nextUserId++;

        System.out.println("User " + "(" + newUser.getId() + ") " + newUser.getUsername() + " created successfully.");
    }

	private static boolean isEmailAvailable(String email) {
		for (User user : users) {
			if (user.getEmail().equals(email)) {
				return false;
			}
		}
		return true;
	}

	private static boolean isUsernameAvailable(String username) {
		for (User user : users) {
			if (user.getUsername().equals(username)) {
				return false;
			}
		}
		return true;
	}

    private static void readUsers() {
        if (!users.isEmpty()) {
            for (User user : users) {
                System.out.println("\nID: " + user.getId() + "\nUsername: " + user.getUsername() + "\nAge: " + user.getAge() + "\nEmail: " + user.getEmail());
            }
        } else {
            System.out.println("No users found.");
        }
    }

    private static void updateUser() {
        System.out.print("Enter the user ID: ");
        int userId = Integer.parseInt(scanner.nextLine());

        for (User user : users) {
            if (user.getId() == userId) {
                System.out.print("Enter the new username (press Enter to skip): ");
                String username = scanner.nextLine();

                int age = -1;
                while (true) {
                    System.out.print("Enter the new age (press Enter to skip): ");
                    String ageInput = scanner.nextLine();

                    if (ageInput.isEmpty()) {
                        break;
                    }

                    try {
                        age = Integer.parseInt(ageInput);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number. Please input a valid number.");
                    }
                }

				String email;
				while (true) {
					System.out.print("Enter the new email (press Enter to skip): ");
					email = scanner.nextLine();
		
					if (email.isEmpty() || email.matches(".+@.+\\.com")) {
						break;
					} else {
						System.out.println("Invalid email format.");
					}
				}

                if (!username.isEmpty()) {
                    user.setUsername(username);
                }

                if (age >= 0) {
                    user.setAge(age);
                }

				if (!email.isEmpty()) {
					user.setEmail(email);
				}

                System.out.println("User updated successfully.");
                return;
            }
        }

        System.out.println("User not found.");
    }

    private static void deleteUser() {
        System.out.print("Enter the user ID: ");
        int userId = Integer.parseInt(scanner.nextLine());

		User userToRemove = null;

		for (User user : users) {
			if (user.getId() == userId) {
				userToRemove = user;
				break;
			}
		}

		if (userToRemove != null) {
			users.remove(userToRemove);
			System.out.println("User deleted successfully.");
		} else {
			System.out.println("User not found.");
		}

    }


}
