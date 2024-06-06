import java.util.Scanner;

public class TrainApplication {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
    	DataBase.getInstance().readData();
    	start();
    }

    public static void start() {
        AdminFeatures admin = new AdminFeatures();
        UserFeatures user = new UserFeatures();
        System.out.println("Welcome to Train Booking System");
        System.out.println("\n 1.Admin \n 2.User \n 3.Exit");
        System.out.println("Enter your Choice");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                admin.init();
                break;
            case 2:
                user.init();
                break;
            case 3:
            	 DataBase.getInstance().writeData();
            	System.out.println("Thank for using IRTCT Application");
                System.exit(0);
            default:
                System.out.println("Invalid Input");
                break;
        }
    }
}
