import java.util.Scanner;

class Admin {
    public String[] books = new String[100];
    public int bookCount = 0;
}

class Librarian extends Admin {
    public String[] issuedBooks = new String[100];
    public int issuedCount = 0;

    String[] studentNames = new String[100];
    String[] sapIds = new String[100];
    String[] rollNumbers = new String[100];
    int[] borrowedBooks = new int[100];
    double[] duePayments = new double[100];
    int studentCount = 0;

    public void addBook(String bookName) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].equals(bookName)) {
                System.out.println("The book already exists.");
                return;
            }
        }
        books[bookCount++] = bookName;
        System.out.println("Book added: " + bookName);
    }

    public void viewBooks() {
        if (bookCount == 0) {
            System.out.println("No books available.");
        } else {
            System.out.println("Books in the library:");
            for (int i = 0; i < bookCount; i++) {
                System.out.println("- " + books[i]);
            }
        }
    }

    public void deleteBook(String bookName) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].equals(bookName)) {
                for (int j = i; j < bookCount - 1; j++) {
                    books[j] = books[j + 1];
                }
                bookCount--;
                System.out.println("Book deleted: " + bookName);
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void issueBooks(String bookName) {
        boolean exists = false;
        for (int i = 0; i < bookCount; i++) {
            if (books[i].equals(bookName)) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            System.out.println("Book does not exist in the library.");
            return;
        }

        for (int i = 0; i < issuedCount; i++) {
            if (issuedBooks[i].equals(bookName)) {
                System.out.println("Book already borrowed.");
                return;
            }
        }

        issuedBooks[issuedCount++] = bookName;
        System.out.println("Book issued successfully: " + bookName);
    }

    public void viewIssuedBooks() {
        if (issuedCount == 0) {
            System.out.println("No books have been issued.");
        } else {
            System.out.println("Issued Books:");
            for (int i = 0; i < issuedCount; i++) {
                System.out.println("- " + issuedBooks[i]);
            }
        }
    }

    public void manageStudentDetails(int option, Scanner input) {
        switch (option) {
            case 0:
                addStudent(input);
                break;
            case 1:
                removeStudent(input);
                break;
            case 2:
                viewStudents();
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    public void addStudent(Scanner input) {
        System.out.print("Enter student name: ");
        studentNames[studentCount] = input.nextLine();
        System.out.print("Enter SAP ID: ");
        sapIds[studentCount] = input.nextLine();
        System.out.print("Enter Roll Number: ");
        rollNumbers[studentCount] = input.nextLine();
        System.out.print("Enter number of borrowed books: ");
        borrowedBooks[studentCount] = input.nextInt();
        System.out.print("Enter due payment amount: ");
        duePayments[studentCount] = input.nextDouble();
        input.nextLine(); // consume newline
        studentCount++;
        System.out.println("Student added successfully.");
    }

    public void removeStudent(Scanner input) {
        System.out.print("Enter SAP ID of student to remove: ");
        String sapIdToRemove = input.nextLine();
        for (int i = 0; i < studentCount; i++) {
            if (sapIds[i].equals(sapIdToRemove)) {
                for (int j = i; j < studentCount - 1; j++) {
                    studentNames[j] = studentNames[j + 1];
                    sapIds[j] = sapIds[j + 1];
                    rollNumbers[j] = rollNumbers[j + 1];
                    borrowedBooks[j] = borrowedBooks[j + 1];
                    duePayments[j] = duePayments[j + 1];
                }
                studentCount--;
                System.out.println("Student removed.");
                return;
            }
        }
        System.out.println("Student with SAP ID not found.");
    }

    public void viewStudents() {
        if (studentCount == 0) {
            System.out.println("No students found.");
        } else {
            System.out.println("Student Records:");
            for (int i = 0; i < studentCount; i++) {
                System.out.println("Name: " + studentNames[i]);
                System.out.println("SAP ID: " + sapIds[i]);
                System.out.println("Roll Number: " + rollNumbers[i]);
                System.out.println("Borrowed Books: " + borrowedBooks[i]);
                System.out.println("Due Payment: Rs. " + duePayments[i]);
                System.out.println("------------------------");
            }
        }
    }

    public void viewOverdueBooksAndNotifyStudents() {
        boolean found = false;
        System.out.println("Checking for students with overdue books or pending dues...");
        for (int i = 0; i < studentCount; i++) {
            if (borrowedBooks[i] > 3 || duePayments[i] > 0) {
                found = true;
                System.out.println("Overdue Alert:");
                System.out.println("Name: " + studentNames[i]);
                System.out.println("SAP ID: " + sapIds[i]);
                System.out.println("Borrowed Books: " + borrowedBooks[i]);
                System.out.println("Due Payment: Rs. " + duePayments[i]);
                System.out.println("Please return books or clear dues immediately.");
                System.out.println("----------------------------");
            }
        }
        if (!found) {
            System.out.println("No overdue books or dues found.");
        }
    }
}
class Student extends Librarian{
    
}
public class Test {
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        Librarian librarian = new Librarian();

        System.out.println("Enter 1 to enter the librarian section");
        int a = input.nextInt();
        input.nextLine(); 

        if (a == 1) {
            System.out.println("In the librarian section");
            System.out.println("Enter 0 to add/view/delete books");
            System.out.println("Enter 1 to issue books to students");
            System.out.println("Enter 2 to view issued books");
            System.out.println("Enter 3 to manage student records");
            System.out.println("Enter 4 to view overdue books and notify students");
            System.out.println("Enter 5 to logout");
            int c = input.nextInt();
            input.nextLine(); 

            switch (c) {
                case 0:
                    System.out.println("Enter 0 to add, 1 to view, 2 to delete");
                    int action = input.nextInt();
                    input.nextLine();
                    if (action == 0) {
                        System.out.print("Enter book name to add: ");
                        String bookToAdd = input.nextLine();
                        librarian.addBook(bookToAdd);
                    } else if (action == 1) {
                        librarian.viewBooks();
                    } else if (action == 2) {
                        System.out.print("Enter book name to delete: ");
                        String bookToDelete = input.nextLine();
                        librarian.deleteBook(bookToDelete);
                    }
                    break;

                case 1:
                    System.out.print("Enter book name to issue: ");
                    String bookToIssue = input.nextLine();
                    librarian.issueBooks(bookToIssue);
                    break;

                case 2:
                    librarian.viewIssuedBooks();
                    break;

                case 3:
                    System.out.println("Enter 0 to add student");
                    System.out.println("Enter 1 to remove student");
                    System.out.println("Enter 2 to view students");
                    int sOption = input.nextInt();
                    input.nextLine();
                    librarian.manageStudentDetails(sOption, input);
                    break;

                case 4:
                    librarian.viewOverdueBooksAndNotifyStudents();
                    break;

                case 5:
                    System.out.println("Logout");
                    break;
            }
        }

        input.close();
    }
}
