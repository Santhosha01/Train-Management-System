import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Train {

    static Scanner sc = new Scanner(System.in);
    private int id;
    private String name;
    private String departurelocation;
    private String arrivallocation;
    private String departuretime;
    private String arrivaltime;
    private List<String> routes = new ArrayList<>();
    private int numberofseats;
    private int fare;

    public Train(int numberofseats, int id, String name, String departuretime, String arrivaltime, int fare) {
        this.numberofseats = numberofseats;
        this.id = id;
        this.name = name;
        this.departurelocation = departurelocation;
        this.arrivallocation = arrivallocation;
        this.departuretime = departuretime;
        this.arrivaltime = arrivaltime;
        this.fare = fare;
        setRoutes();
    }

    public int getId() {
        return id;
    }

    public int getFare() {
        return fare;
    }

    public String getName() {
        return name;
    }

    public String getDeparturelocation() {
        return departurelocation;
    }

    public String getArrivallocation() {
        return arrivallocation;
    }

    public String getDeparturetime() {
        return departuretime;
    }

    public String getArrivaltime() {
        return arrivaltime;
    }

    public List<String> getRoutes() {
        return routes;
    }

    public int getNumberofseats() {
        return numberofseats;
    }

    public void setNumberofseats(int numberofseats) {
        this.numberofseats = numberofseats;
    }

    public void setRoutes() {
        boolean flag = false;
        if (!routes.isEmpty()) {
            do {
                System.out.println("Enter train route");
                String route = sc.nextLine();
                routes.add(route);
                System.out.println("Do you want to add another? (y/n)");
                String chance = sc.nextLine();
                if (chance.equals("y")) {
                    flag = true;
                } else {
                    flag = false;
                }
            } while (flag);
        } else {
            do {
                System.out.println("Enter train route");
                String route = sc.nextLine();
                routes.add(route);
                System.out.println("Do you want to add another? (y/n)");
                String chance = sc.nextLine();
                if (chance.equals("y")) {
                    flag = true;
                } else {
                    flag = false;
                }
            } while (flag);
        }
        this.departurelocation = routes.get(0);
        this.arrivallocation = routes.get(routes.size() - 1);
    }

    @Override
    public String toString() {
        return "Train{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", departurelocation='" + departurelocation + '\'' +
                ", arrivallocation='" + arrivallocation + '\'' +
                ", departuretime='" + departuretime + '\'' +
                ", arrivaltime='" + arrivaltime + '\'' +
                ", routes=" + routes +
                ", numberofseats=" + numberofseats +
                ", fare=" + fare +
                '}';
    }


}
