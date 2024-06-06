import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DataBase {
    private static DataBase dataBase;
    private List<Credential> credentials = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<Train> trains = new ArrayList<>();
    private Queue<Ticket> waitingTickets = new LinkedList<>();
    private List<Ticket> tickets = new ArrayList<>();
    private List<Passenger> passengers = new ArrayList<>();

    public static DataBase getInstance() {
        if (dataBase == null) {
            dataBase = new DataBase();
        }
        return dataBase;
    }

     public void writeData() {
    	System.out.println(credentials);
    	System.out.println(passengers);
    	System.out.println(users);
    	System.out.println(trains);
    	System.out.println(waitingTickets);
    	System.out.println(tickets);
    	
    	writeCredentails();
    	writePassengerList();
    	writeTickets();
    	writeTrains();
    	writeUsers();
    	writeWaitingList();
    }
    private void writeTrains(){
        Gson gson = new Gson();
        String jsonString = gson.toJson(trains);

        try (FileWriter writer = new FileWriter(trainList)) {
            writer.write(jsonString);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void writeTickets(){
        Gson gson = new Gson();
        String jsonString = gson.toJson(tickets);

        try (FileWriter writer = new FileWriter(ticketList)) {
            writer.write(jsonString);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeWaitingList() {
        Gson gson = new Gson();
        String jsonString = gson.toJson(waitingTickets);

        try (FileWriter writer = new FileWriter(waitingTicketList)) {
            writer.write(jsonString);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writePassengerList() {
        Gson gson = new Gson();
        String jsonString = gson.toJson(passengers);

        try (FileWriter writer = new FileWriter(passengersList)) {
            writer.write(jsonString);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeUsers() {
        Gson gson = new Gson();
        String jsonString = gson.toJson(users);

        try (FileWriter writer = new FileWriter(userList)) {
            writer.write(jsonString);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeCredentails() {
        Gson gson = new Gson();
        String jsonString = gson.toJson(credentials);

        try (FileWriter writer = new FileWriter(userCredentials)) {
            writer.write(jsonString);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readData() {
    	credentials=getFileCredentials();
    	trains=getFileTrains();
    	tickets=getFileTickets();
    	passengers=getFilePassenger();
    	waitingTickets=getFileWaitingList();
    	this.users=loadUsers();
    	System.out.println(credentials);
    	System.out.println(passengers);
    	System.out.println(users);
    	System.out.println(trains);
    	System.out.println(waitingTickets);
    	System.out.println(tickets);
    }

    private Queue<Ticket> getFileWaitingList() {
        Gson gson = new Gson();
        Queue<Ticket> loadedList = new LinkedList<>();
        try {
            File file=new File(waitingTicketList);
            if(!file.exists()){
                file.createNewFile();
            }
        }catch (IOException e){
            e.printStackTrace();
        }


        try (
                FileReader reader = new FileReader(waitingTicketList)) {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                sb.append((char) c);
            }

            String jsonString = sb.toString();
            if (jsonString.isEmpty()) {
                return loadedList;
            }

            Type type = new TypeToken<Queue<Ticket>>() {
            }.getType();
            loadedList = gson.fromJson(jsonString, type);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return loadedList;


    }

    private List<Passenger> getFilePassenger() {
        Gson gson = new Gson();
        List<Passenger> loadedList = new ArrayList<>();
        try {
            File file=new File(passengersList);
            if(!file.exists()){
                file.createNewFile();
            }
        }catch (IOException e){
            e.printStackTrace();
        }


        try (
                FileReader reader = new FileReader(passengersList)) {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                sb.append((char) c);
            }

            String jsonString = sb.toString();
            if (jsonString.isEmpty()) {
                return loadedList;
            }

            Type type = new TypeToken<List<Passenger>>() {
            }.getType();
            loadedList = gson.fromJson(jsonString, type);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return loadedList;


    }

    private List<Ticket> getFileTickets() {
        Gson gson = new Gson();
        List<Ticket> loadedList = new ArrayList<>();
        try {
            File file=new File(ticketList);
            if(!file.exists()){
                file.createNewFile();
            }
        }catch (IOException e){
            e.printStackTrace();
        }


        try (
                FileReader reader = new FileReader(ticketList)) {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                sb.append((char) c);
            }

            String jsonString = sb.toString();
            if (jsonString.isEmpty()) {
                return loadedList;
            }

            Type type = new TypeToken<List<Ticket>>() {
            }.getType();
            loadedList = gson.fromJson(jsonString, type);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return loadedList;


    }

    private List<Train> getFileTrains() {
        Gson gson = new Gson();
        List<Train> loadedList = new ArrayList<>();
        try {
            File file=new File(trainList);
            if(!file.exists()){
                file.createNewFile();
            }
        }catch (IOException e){
            e.printStackTrace();
        }


        try (
                FileReader reader = new FileReader(trainList)) {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                sb.append((char) c);
            }

            String jsonString = sb.toString();
            if (jsonString.isEmpty()) {
                return loadedList;
            }

            Type type = new TypeToken<List<Train>>() {
            }.getType();
            loadedList = gson.fromJson(jsonString, type);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return loadedList;


    }

    private List<Credential> getFileCredentials() {
        Gson gson = new Gson();
        List<Credential> loadedList = new ArrayList<>();
        try {
            File file=new File(userCredentials);
            if(!file.exists()){
                file.createNewFile();
            }
        }catch (IOException e){
            e.printStackTrace();
        }


        try (
                FileReader reader = new FileReader(userCredentials)) {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                sb.append((char) c);
            }

            String jsonString = sb.toString();
            if (jsonString.isEmpty()) {
                return loadedList;
            }

            Type type = new TypeToken<List<Credential>>() {
            }.getType();
            loadedList = gson.fromJson(jsonString, type);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return loadedList;


    }



    private List<User> loadUsers()  {
        Gson gson = new Gson();
        List<User> loadedList = new ArrayList<>();
        try {
            File file=new File(userList);
            if(!file.exists()){
                file.createNewFile();
            }
        }catch (IOException e){
            e.printStackTrace();
        }


        try (
                FileReader reader = new FileReader(userList)) {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                sb.append((char) c);
            }

            String jsonString = sb.toString();
            if (jsonString.isEmpty()) {
                return loadedList;
            }

            Type type = new TypeToken<List<User>>() {
            }.getType();
            loadedList = gson.fromJson(jsonString, type);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return loadedList;


    }

    public void insertCredentials(Credential credential) {
        credentials.add(credential);
    }

    public List<Credential> getCredentials() {
        return credentials;
    }

    public void storeUser(User user) {
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public void storeTrain(Train b) {
        trains.add(b);
    }

    public List<Train> getTrains() {
        return trains;
    }

    public void storeWaitingList(Ticket ticket) {
        waitingTickets.add(ticket);
    }

    public Queue<Ticket> getWaitingList() {
        return waitingTickets;
    }

    public void storeTickets(Ticket ticket) {
        tickets.add(ticket);
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void storePassenger(Passenger passenger) {
        passengers.add(passenger);
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }
}
