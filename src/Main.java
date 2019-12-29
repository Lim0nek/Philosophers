import java.awt.*;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    DiningRoom diningRoom = new DiningRoom();
                    new PhilosophersDiningGUI(diningRoom);
                    Thread thread1 = new Thread(diningRoom.getPhilosopherById(0));
                    Thread thread2 = new Thread(diningRoom.getPhilosopherById(2));
                    Thread thread3 = new Thread(diningRoom.getPhilosopherById(3));
                    Thread thread4 = new Thread(diningRoom.getPhilosopherById(4));
                    Thread thread5 = new Thread(diningRoom.getPhilosopherById(1));
                    thread1.start();
                    thread2.start();
                    thread3.start();
                    thread4.start();
                    thread5.start();
                } catch (Exception ex){

                }
            }
        });
    }

}
