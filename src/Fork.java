import com.sun.org.apache.xpath.internal.operations.Bool;

public class Fork {

    private Boolean isAvialableToUse;
    private int forkId;
    private int holdenById = -1;

    public Fork(int forkId){
        isAvialableToUse = true;
        this.forkId = forkId;
    }

    public Boolean isForkAvialableToUse() {
        return this.isAvialableToUse;
    }

    public int getForkId(){
        return this.forkId;
    }
    public int getHoldenById(){
        return this.holdenById;
    }

    public synchronized void pickUpFork(Philosopher philosopher) throws InterruptedException{
        while (!isAvialableToUse){
            System.out.println(philosopher.getPhilosopherId() +" Waiting for fork " + forkId);
            philosopher.setPhilosopherState(Philosopher.State.WAITING4FORK);
            wait();
        }
        isAvialableToUse = false;
        holdenById = philosopher.getPhilosopherId();
    }

    public synchronized void putDownFork(){
        isAvialableToUse = true;
        holdenById = -1;
        notify();
    }

}
