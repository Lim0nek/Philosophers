import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class PhilosophersDiningGUI extends JFrame {

    private JPanel content;
    private ArrayList<JLabel> philosophers = new ArrayList<>();
    private HashMap<Philosopher, JLabel> philosopherToGUIPhilosopher= new HashMap<Philosopher, JLabel>();
    private HashMap<Philosopher.State, Color> state2Colour= new HashMap<Philosopher.State, Color>(){{
        put(Philosopher.State.THINKING, Color.BLUE);
        put(Philosopher.State.WAITING, Color.YELLOW);
        put(Philosopher.State.EATING, Color.GREEN);
        put(Philosopher.State.WAITING4FORK, Color.RED);
    }};


    public PhilosophersDiningGUI(DiningRoom diningRoom){
        super("Dining Philosophers");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 700);
        content = new JPanel();
        content.setBackground(Color.WHITE);
        content.setBorder(new EmptyBorder(5, 5, 5, 5));
        content.setLayout(null);
        setContentPane(content);
        setVisible(true);

        JLabel firstPhilosopher = new JLabel("P1");
        firstPhilosopher.setOpaque(true);
        firstPhilosopher.setBackground(Color.BLUE);
        firstPhilosopher.setHorizontalAlignment(SwingConstants.CENTER);
        firstPhilosopher.setBounds(60, 200, 100, 100);
        philosopherToGUIPhilosopher.put(diningRoom.getPhilosopherById(0) ,firstPhilosopher);
        content.add(firstPhilosopher);

        JLabel secondPhilosopher = new JLabel("P2");
        secondPhilosopher.setOpaque(true);
        secondPhilosopher.setBackground(Color.BLUE);
        secondPhilosopher.setHorizontalAlignment(SwingConstants.CENTER);
        secondPhilosopher.setBounds(130 , 400, 100, 100);
        philosopherToGUIPhilosopher.put(diningRoom.getPhilosopherById(1), secondPhilosopher);
        content.add(secondPhilosopher);

        JLabel thirdPhilosopher = new JLabel("P3");
        thirdPhilosopher.setOpaque(true);
        thirdPhilosopher.setBackground(Color.BLUE);
        thirdPhilosopher.setHorizontalAlignment(SwingConstants.CENTER);
        thirdPhilosopher.setBounds(460, 400, 100, 100);
        philosopherToGUIPhilosopher.put(diningRoom.getPhilosopherById(2), thirdPhilosopher);
        content.add(thirdPhilosopher);

        JLabel fourthPhilosopher = new JLabel("P4");
        fourthPhilosopher.setOpaque(true);
        fourthPhilosopher.setBackground(Color.BLUE);
        fourthPhilosopher.setHorizontalAlignment(SwingConstants.CENTER);
        fourthPhilosopher.setBounds(560, 200, 100, 100);
        philosopherToGUIPhilosopher.put(diningRoom.getPhilosopherById(3), fourthPhilosopher);
        content.add(fourthPhilosopher);

        JLabel fifthPhilosopher = new JLabel("P5");
        fifthPhilosopher.setOpaque(true);
        fifthPhilosopher.setBackground(Color.BLUE);
        fifthPhilosopher.setHorizontalAlignment(SwingConstants.CENTER);
        fifthPhilosopher.setBounds(300, 100, 100, 100);
        philosopherToGUIPhilosopher.put(diningRoom.getPhilosopherById(4), fifthPhilosopher);
        content.add(fifthPhilosopher);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for (Philosopher philosopher: diningRoom.getPhilosophers()){
                    philosopherToGUIPhilosopher.get(philosopher).setBackground(state2Colour.get(philosopher.getPhilosopherState()));
                }
            }
        }, 10, 10);
    }
}
