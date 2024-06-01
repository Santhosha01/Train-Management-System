import java.util.List;
import java.util.Scanner;

public class UserFeatures {
    static Scanner sc = new Scanner(System.in);

    public void init() {
        System.out.println("\n----------------------User Page--------------------------");
        System.out.println("\n 1.Sign Up \n 2.Sign in \n 3.Exit");
        System.out.println("Enter your choice");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                signUp();
                init();
                break;
            case 2:
                String username = signIn();
                if (username != null) {
                    startMenu(username);
                } else {
                    init();
                }
                break;
            case 3:
                System.out.println(
                        "\n------ Thanks you for Using  IRTCT----");
                new TrainApplication().start();
                break;
            default:
                System.out.println("InValid Input");
                init();
        }
    }

    private void startMenu(String username) {
        System.out.println("-----------Welcome to User Page--------------\n");
        System.out.println(" 1. Booking \n 2. Get PNR status \n 3. Booked Tickets \n 4. Cancel Ticket  \n 5. List train  \n 6. Search Train \n 7. Exit");
        System.out.println("Enter your choice");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                booking(username);
                startMenu(username);
                break;
            case 2:
                getPnrStatus(username);
                startMenu(username);
                break;
            case 3:
                viewBookedTickets(username);
                startMenu(username);
                break;
            case 4:
                cancelTicket(username);
                startMenu(username);
                break;
            case 5:
                viewTrainRoutes();
                startMenu(username);
                break;
            case 6:
                searchTrainInit(username);
                startMenu(username);
            case 7:
                System.out.println("Thank you for using IRTCT");
                init();
                break;
            default:
                System.out.println("Invalid input");
                init();
                break;
        }
    }

    private void signUp() {
        User user = new User();
        System.out.println("Enter your username");
        String username = sc.nextLine();
        user.setName(username);
        System.out.println("Enter your password");
        String password = sc.nextLine();
        user.setPassword(password);
        System.out.println("Enter your Phone Number");
        String phoneNumber = sc.nextLine();
        user.setPhoneNumber(phoneNumber);
        System.out.println("Enter your email");
        String email = sc.nextLine();
        user.setEmail(email);
        Credential credential = new Credential();
        credential.setEmail(email);
        credential.setPassword(password);
        createCredentials(credential);
        storeUser(user);
        System.out.println("--------------User Sign Up Successfully--------------");
    }

    public void createCredentials(Credential credentials) {
        DataBase.getInstance().insertCredentials(credentials);
    }

    public void storeUser(User user) {
        DataBase.getInstance().storeUser(user);
    }


    public User getUser(String email) {
        List<User> users = DataBase.getInstance().getUsers();
        for (User s : users) {
            if (s.getEmail().equals(email)) {
                return s;
            }
        }
        return null;
    }

    public Train getTrain(String sLocation, String dLocation) {
        List<Train> trains = DataBase.getInstance().getTrains();
        for (Train t : trains) {
            if ((t.getDeparturelocation().equals(sLocation))) {
                if (t.getArrivallocation().equals(dLocation)) {
                    return t;
                } else {
                    List<String> routes = t.getRoutes();
                    for (String s : routes) {
                        if (s.equals(dLocation)) {
                            return t;
                        }
                    }
                }
            }
        }
        return null;
    }

    public List<String> getTrainRoutes(String trainName) {
        List<Train> trains = DataBase.getInstance().getTrains();
        for (Train t : trains) {
            if (t.getName().equals(trainName)) {
                return t.getRoutes();
            }
        }
        return null;
    }

    public Ticket cancelTicket(User u, int pnr) {
        List<Ticket> tickets = u.getTickets();
        if (!tickets.isEmpty()) {
            for (Ticket ticket : tickets) {
                if (ticket.getPnr() == pnr) {
                    Train t = ticket.getTrain();
                    List<Train> trains = DataBase.getInstance().getTrains();
                    for (Train train : trains) {
                        if (train.getName().equals(t.getName())) {
                            train.setNumberofseats(train.getNumberofseats() + 1);
                            return ticket;
                        }
                    }
                    return null;
                }
            }
        }
        return null;
    }

    public String signIn() {
        System.out.println("Enter your Email Id");
        String email = sc.nextLine();
        System.out.println("Enter your password");
        String password = sc.nextLine();
        if (isValidUser(email, password)) {
            System.out.println("-------------Login Successful----------------");
            return email;
        } else {
            System.out.println("Invalid User Name or Password");
        }
        return null;
    }

    private boolean isValidUser(String email, String password) {
        List<Credential> credentials = DataBase.getInstance().getCredentials();
        for (Credential c : credentials) {
            if ((c.getEmail()).equals(email)) {
                if (c.getPassword().equals(password)) {
                    return true;
                }

            }
        }
        return false;
    }

    private void searchTrainInit(String username) {
        System.out.println("-----------Searching Train-----------");
        System.out.println(" 1. Search By departure Location \n 2. Search by Arrival Location \n 3. Exit");
        System.out.println("Enter your choice");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                searchBydeparture();
                searchTrainInit(username);
                break;
            case 2:
                searchByArrival();
                searchTrainInit(username);
                break;
            case 3:
                startMenu(username);
                break;
        }
    }

    private void searchBydeparture() {
        System.out.println("Enter your Starting location");
        String startLocation = sc.nextLine();
        boolean flag = true;
        List<Train> trains = DataBase.getInstance().getTrains();
        for (Train t : trains) {
            if (t.getDeparturelocation().equals(startLocation)) {
                System.out.println(t.toString());
                flag = false;
            }
        }
        if (flag) {
            System.out.println("Train Not Found");
        }
    }

    private void searchByArrival() {
        System.out.println("Enter your destination location");
        String desLocation = sc.nextLine();
        List<Train> trains = DataBase.getInstance().getTrains();
        boolean flag = true;
        for (Train t : trains) {
            if (t.getArrivallocation().equals(desLocation)) {
                System.out.println(t.toString());
                flag = false;
            }
        }
        if (flag) {
            System.out.println("Train not found");
        }
    }


    private void getPnrStatus(String email) {
        User user = getUser(email);
        if (user != null) {
            List<Ticket> tickets = user.getTickets();
            if (!tickets.isEmpty()) {
                System.out.println("----------Ticket Details----------");
                for (Ticket t : tickets) {
                    System.out.println("Ticket Number - " + t.getPnr());
                    System.out.println("Status of the Ticket - " + t.getStatus());
                }
            } else {
                System.out.println("you haven't book Any Train yet");
            }
        } else {
            System.out.println("you haven't book Any Train yet");
        }
    }

    private void booking(String email) {
        User user = getUser(email);
        System.out.println("Enter your starting Location");
        String sLocation = sc.nextLine();
        System.out.println("Enter destination location");
        String dLocation = sc.nextLine();
        Train t = getTrain(sLocation, dLocation);
        if (t != null) {
            System.out.println("Enter passenger Name");
            String name = sc.nextLine();
            System.out.println("Enter passenger Age");
            int age = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter Passenger gender");
            String gender = sc.nextLine();
            System.out.println("Enter Passenger Phone Number");
            String number = sc.nextLine();
            Passenger passenger = new Passenger(name, age, gender, number);
            if (t.getNumberofseats() != 0) {
                System.out.println("Train Name - " + t.getName() + " And Price of the seat in the train is - Rs " + t.getFare());
                System.out.println("Do you want to Book the train? y/n");
                String choice = sc.nextLine();
                if (choice.equals("y")) {
                    Ticket ticket = new Ticket(t, passenger);
                    ticket.setStatus("CF");
                    user.setTickets(ticket);
                    t.setNumberofseats(t.getNumberofseats() - 1);
                    System.out.println("Train Details");
                    System.out.println(t.toString());
                    DataBase.getInstance().storeTickets(ticket);
                    System.out.println("Train Booked Successfully");
                }
            } else {
                System.out.println("Train is fully booked");
                System.out.println("Train Name - " + t.getName() + " And Price of the seat in the train is - Rs " + t.getFare());
                System.out.println("Do you want to Book the train? y/n");
                String choice = sc.nextLine();
                if (choice.equals("y")) {
                    Ticket ticket = new Ticket(t, passenger);
                    ticket.setStatus("WL");
                    user.setTickets(ticket);
                    DataBase.getInstance().storeWaitingList(ticket);
                    System.out.println("---------Train Details----------");
                    System.out.println(t.toString());
                    DataBase.getInstance().storeTickets(ticket);
                    System.out.println("Train Booked Successfully,Currently you are in Waiting List");
                }
            }
            DataBase.getInstance().storePassenger(passenger);
        }
    }

    private void viewTrainRoutes() {
        System.out.println("Enter the Train Name");
        String trainName = sc.nextLine();
        List<String> routes = getTrainRoutes(trainName);
        if (routes != null) {
            System.out.println("Train Routes of " + trainName);
            System.out.println(routes);
        } else {
            System.out.println("Train not found");
        }
    }

    private void cancelTicket(String email) {
        User u = getUser(email);
        System.out.println("Enter Ticket Number or PNR Number");
        int pnr = sc.nextInt();
        sc.nextLine();
        Ticket ticket = cancelTicket(u, pnr);
        if (ticket != null) {
            Passenger p = ticket.getPassenger();
            List<Passenger> passengers = DataBase.getInstance().getPassengers();
            passengers.remove(p);
            List<Ticket> tickets = u.getTickets();
            tickets.remove(ticket);
            System.out.println("Your Ticket Number " + ticket.getPnr() + " And The Ticket has been Cancelled Successfully");
        } else {
            System.out.println("Ticket not found");
        }
    }

    private void viewBookedTickets(String email) {
        User user = getUser(email);
        System.out.println("-------Ticket Details--------");
        List<Ticket> tickets = user.getTickets();
        if (!tickets.isEmpty()) {
            for (Ticket t : tickets) {
                Train train = t.getTrain();
                System.out.println("Ticket Number - " + t.getPnr());
                System.out.println("--------Train Details--------");
                System.out.println("Train Number - " + train.getId() + ", Train Name - " + train.getName());
            }
        } else {
            System.out.println("you haven't book Any Train yet");
        }
    }

}
