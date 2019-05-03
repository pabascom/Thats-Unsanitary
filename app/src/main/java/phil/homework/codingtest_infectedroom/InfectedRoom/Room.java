package phil.homework.codingtest_infectedroom.InfectedRoom;

import java.util.Stack;

public class Room {
    public final boolean isInfected;
    public boolean visited = false;
    Room(boolean infected)
    {
        isInfected = infected;
    }

    public static boolean isOutbreak(Room[][] floor) {
        boolean result = false;
        for(int i = 0; i < floor.length; i++){
            for(int j = 0; j < floor[0].length; j++){
                if(!floor[i][j].visited){
                    if(floor[i][j].isInfected) {
                        QuarantineSpecialist specialist = new QuarantineSpecialist(floor, i, j);
                        if(specialist.performSweep()) result = true;
                    } else {
                        floor[i][j].visited = true;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Room[][] verticalTrue = new Room[][] {
                {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) },
                {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) },
                {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) },
                {new Room(false), new Room(true), new Room(false), new Room(true), new Room(true), new Room(false), new Room(false), new Room(false), new Room(false) },
                {new Room(false), new Room(true), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) },
                {new Room(false), new Room(true), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) },
                {new Room(false), new Room(true), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) },
                {new Room(false), new Room(true), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) },
                {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) },
                {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) }
        };
        Room[][] horizontalTrue = new Room[][] {
                {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) },
                {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) },
                {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) },
                {new Room(false), new Room(true), new Room(true), new Room(true), new Room(true), new Room(true), new Room(false), new Room(false), new Room(false) },
                {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) },
                {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) },
                {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) },
                {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) },
                {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) },
                {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) }
        };
        Room[][] noInfection = new Room[][] {
                {new Room(true), new Room(false), new Room(true), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) },
                {new Room(false), new Room(true), new Room(false), new Room(true), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) },
                {new Room(true), new Room(false), new Room(true), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) },
                {new Room(false), new Room(true), new Room(false), new Room(true), new Room(false), new Room(true), new Room(false), new Room(false), new Room(false) },
                {new Room(false), new Room(true), new Room(false), new Room(false), new Room(true), new Room(false), new Room(false), new Room(false), new Room(false) },
                {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(true), new Room(false), new Room(false), new Room(false) },
                {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(true), new Room(false), new Room(false) },
                {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) },
                {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) },
                {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) }
        };

        System.out.println(Room.isOutbreak(verticalTrue));
        System.out.println(Room.isOutbreak(horizontalTrue));
        System.out.println(Room.isOutbreak(noInfection));
    }
}

class Location {
    int i;
    int j;
    Location(int i, int j){
        this.i = i;
        this.j = j;
    }
}

class QuarantineSpecialist{
    private Room[][] floor;
    private Stack<Location> searchStack;
    private int numConsecutiveInfections;

    QuarantineSpecialist(Room[][] floor, int i, int j){
        this.floor = floor;
        this.searchStack = new Stack<Location>();
        this.numConsecutiveInfections = 0;

        searchStack.push(new Location(i, j));
    }

    private boolean exists(Location loc) {
        return loc.i >= 0 && loc.i < floor.length && loc.j >= 0 && loc.j < floor[0].length;
    }

    private void searchLocation(Location loc) {
        floor[loc.i][loc.j].visited = true;
        if(floor[loc.i][loc.j].isInfected){
            numConsecutiveInfections++;
            searchStack.push(new Location(loc.i+1, loc.j));
            searchStack.push(new Location(loc.i-1, loc.j));
            searchStack.push(new Location(loc.i, loc.j+1));
            searchStack.push(new Location(loc.i, loc.j-1));
        }
    }

    boolean performSweep(){
        Location loc;
        while(numConsecutiveInfections < 5 && !searchStack.isEmpty()){
            loc = searchStack.pop();
            if(exists(loc) && !floor[loc.i][loc.j].visited){
                searchLocation(loc);
            }
        }
        if(numConsecutiveInfections >= 5) return true;
        else return false;
    }
}
