import java.util.ArrayList;
import java.util.HashMap;

public class DiningRoom {
    private Waiter waiter;
    private HashMap<Integer, Philosopher> philosophers = new HashMap<>();
    private HashMap<Integer, Fork> forks = new HashMap<>();

    public DiningRoom(){
        this.waiter = new Waiter();
        for(int i = 0; i<5; i++){
            Fork fork = new Fork(i);
            this.forks.put(fork.getForkId(), fork);
        }
        for(int i = 0; i<5; i++){
           Philosopher philosopher = new Philosopher(i, getForkById(i), getForkById((i+1)%5), waiter);
           this.philosophers.put(philosopher.getPhilosopherId(), philosopher);
        }
    }

    public Fork getForkById(Integer id){
        return this.forks.get(id);
    }

    public Philosopher getPhilosopherById(Integer id){
        return this.philosophers.get(id);
    }

    public ArrayList<Philosopher> getPhilosophers(){
        return new ArrayList<Philosopher>(philosophers.values());
    }
}
