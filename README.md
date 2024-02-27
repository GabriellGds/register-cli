# Register Cli

This is my first project in Java, I did it to learn the basic concepts of the language.

### Objective:
1. To train and understand Object-Oriented Programming in Java.
2. To practice using Streams, Lambda expressions, and Exception handling in Java.


### How It Works
The Register CLI is a command-line interface application that allows users to manage a user registry. When you run the application, 
it prompts you to enter the directory path where the user data is or will be saved. Then, it displays a menu with options to register a user, list all registered users, search for a user by email or name, delete a user, or exit the application.

During the user registration process, the application performs some validations:

- It checks if the email contains the “@” symbol.

- It verifies that the user is over 18 years old.

- It ensures that the height is a number with decimals.

- It prevents the registration of a user with an email that already exists in the registry.

The application uses Java I/O to read and write user data to files. Each user has a separate file named after their name. 
When a user is added, a new file is created. When a user is deleted, their file is deleted.



