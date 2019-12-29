import sun.awt.Mutex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Waiter {

    private Integer howManyEating = 0;

    private HashMap<Philosopher, ArrayList<Integer>> waitingPhilosophers = new HashMap<Philosopher, ArrayList<Integer>>();
    private Philosopher waitingPhilosopher;
//    private Mutex mutex = new Mutex();
//
//
//    public synchronized void givePermissionToEat(Fork rightFork, Fork lefrFork, Philosopher philosopher) throws InterruptedException{
//        while (!rightFork.isForkAvialableToUse() || !lefrFork.isForkAvialableToUse()){
//            System.out.println(philosopher.getPhilosopherId() + " waiting for PERM");
//
//            ArrayList<Integer> requeredForks = new ArrayList<>();
//            requeredForks.add(rightFork.getForkId());
//            requeredForks.add(lefrFork.getForkId());
//            waitingPhilosophers.put(philosopher, requeredForks);
//            synchronized (philosopher){
//                philosopher.wait();
//            }
//            System.out.println(philosopher.getPhilosopherId() + " WAKED UP");
//        }
//    }
////
//    public synchronized void washForks(Fork rightFork, Fork leftFork) {
//        System.out.println(waitingPhilosophers);
//        ArrayList<Philosopher> philosophers = new ArrayList<>();
//        this.waitingPhilosophers.forEach((k,v)-> {
//            int i = 0;
//            if(v.contains(rightFork.getForkId()) || v.contains(leftFork.getForkId())){
//                System.out.println(k + " gets NOTIFIED");
//                synchronized (k) {
//                    k.notify();
//                }
//                philosophers.add(k);
//            }
//        });
//        for(Philosopher ph: philosophers){
//                System.out.println("REMOVEING " + ph.getPhilosopherId());
//                waitingPhilosophers.remove(ph);
//        }
//    }

    public synchronized Boolean givePermissionToEat(Philosopher philosopher){
        if(howManyEating < 4) {
            howManyEating++;
            System.out.println("Number of eating philosophers: " + howManyEating);
            return true;
        }
        waitingPhilosopher = philosopher;
        return false;
    }

    public synchronized void washForks() {
        howManyEating--;
        if(waitingPhilosopher!=null){
         synchronized (waitingPhilosopher){
             waitingPhilosopher.notify();
             waitingPhilosopher = null;
         }
        }
        System.out.println("Number of eating philosophers finished: " + howManyEating);
    }


}
