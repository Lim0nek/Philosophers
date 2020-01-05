public class Philosopher implements Runnable{
    private int philosopherId;
    private Fork rightFork;
    private Fork leftFork;
    private Boolean isEating;
    private Waiter waiter;
    private State philosopherState;
    public enum State {
        THINKING, WAITING, EATING, WAITING4FORK
    }

    public Philosopher(int philosopherId, Fork leftFork, Fork rightFork, Waiter waiter){
        this.philosopherId = philosopherId;
        this.rightFork = rightFork;
        this.leftFork = leftFork;
        this.waiter = waiter;
        this.philosopherState = State.THINKING;
    }

    public int getPhilosopherId() {
        return philosopherId;
    }

    public synchronized void getForks() throws InterruptedException {
        System.out.println(philosopherId + " picking up forks: " + rightFork.getForkId() + " and " + leftFork.getForkId());
        while(!waiter.givePermissionToEat(this)) {
            System.out.println(philosopherId + " waiting for a DISH!!!!!!!!!!!!!");
            synchronized (this){
                this.philosopherState = State.WAITING;
                this.wait();
            }
        }
        rightFork.pickUpFork(this);
        leftFork.pickUpFork(this);

    }

    public synchronized void putDownTheForks() throws InterruptedException {
        System.out.println(philosopherId + " finished eating with forks: " + rightFork.getForkId() + " and " + leftFork.getForkId());
       rightFork.putDownFork();
       leftFork.putDownFork();
       waiter.washForks();
    }

    public void eat() throws InterruptedException {
        getForks();
        philosopherState = State.EATING;
        System.out.println(philosopherId + " is eating with forks " + rightFork.getForkId() + " and " + leftFork.getForkId());
        Thread.currentThread().sleep((int)(10000.0 * Math.random()));
        philosopherState = State.THINKING;
        putDownTheForks();
    }

    public void think() throws InterruptedException {
        System.out.println(philosopherId + " is thinking");
        Thread.currentThread().sleep((int)(10000.0 * Math.random()));
    }

    public String getForksInHands(){
        String forksInHands = "(";
        if(rightFork.getHoldenById() == philosopherId){
            forksInHands += rightFork.getForkId();
        }
        if(leftFork.getHoldenById() == philosopherId){
            forksInHands += leftFork.getForkId();
        }
        forksInHands += ")";
        return forksInHands;
    }

    public State getPhilosopherState(){
        return this.philosopherState;
    }
    public void setPhilosopherState(State state){
        this.philosopherState = state;
    }

    @Override
    public void run() {
        System.out.println(philosopherId + " is running");
            while(true){
                if(philosopherState != State.EATING){
                    try {
                        think();
                        eat();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
    }
}
