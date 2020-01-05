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
    private HashMap<Philosopher, JTextArea> philosopherStateToGUIPhilosopher= new HashMap<Philosopher, JTextArea>();
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
        JTextArea firstState = new JTextArea();
        firstState.setEditable(false);
        firstState.setFont(new Font("Yu Gothic", Font.BOLD, 13));
        firstState.setLineWrap(true);
        firstState.setText(String.valueOf(diningRoom.getPhilosopherById(0).getPhilosopherState()));
        firstState.setBounds(60, 160, 200, 15);
        philosopherStateToGUIPhilosopher.put(diningRoom.getPhilosopherById(0) ,firstState);
        content.add(firstState);
        content.add(firstPhilosopher);

        JLabel secondPhilosopher = new JLabel("P2");
        secondPhilosopher.setOpaque(true);
        secondPhilosopher.setBackground(Color.BLUE);
        secondPhilosopher.setHorizontalAlignment(SwingConstants.CENTER);
        secondPhilosopher.setBounds(130 , 400, 100, 100);
        philosopherToGUIPhilosopher.put(diningRoom.getPhilosopherById(1), secondPhilosopher);
        JTextArea secondState = new JTextArea();
        secondState.setEditable(false);
        secondState.setFont(new Font("Yu Gothic", Font.BOLD, 13));
        secondState.setLineWrap(true);
        secondState.setText(String.valueOf(diningRoom.getPhilosopherById(1).getPhilosopherState()));
        secondState.setBounds(130, 360, 250, 15);
        philosopherStateToGUIPhilosopher.put(diningRoom.getPhilosopherById(1) ,secondState);
        content.add(secondState);
        content.add(secondPhilosopher);

        JLabel thirdPhilosopher = new JLabel("P3");
        thirdPhilosopher.setOpaque(true);
        thirdPhilosopher.setBackground(Color.BLUE);
        thirdPhilosopher.setHorizontalAlignment(SwingConstants.CENTER);
        thirdPhilosopher.setBounds(460, 400, 100, 100);
        philosopherToGUIPhilosopher.put(diningRoom.getPhilosopherById(2), thirdPhilosopher);
        JTextArea thirdState = new JTextArea();
        thirdState.setEditable(false);
        thirdState.setFont(new Font("Yu Gothic", Font.BOLD, 13));
        thirdState.setLineWrap(true);
        thirdState.setText(String.valueOf(diningRoom.getPhilosopherById(2).getPhilosopherState()));
        thirdState.setBounds(460, 350, 250, 15);
        philosopherStateToGUIPhilosopher.put(diningRoom.getPhilosopherById(2) ,thirdState);
        content.add(thirdState);
        content.add(thirdPhilosopher);

        JLabel fourthPhilosopher = new JLabel("P4");
        fourthPhilosopher.setOpaque(true);
        fourthPhilosopher.setBackground(Color.BLUE);
        fourthPhilosopher.setHorizontalAlignment(SwingConstants.CENTER);
        fourthPhilosopher.setBounds(560, 200, 100, 100);
        philosopherToGUIPhilosopher.put(diningRoom.getPhilosopherById(3), fourthPhilosopher);
        JTextArea fourthState = new JTextArea();
        fourthState.setEditable(false);
        fourthState.setFont(new Font("Yu Gothic", Font.BOLD, 13));
        fourthState.setLineWrap(true);
        fourthState.setText(String.valueOf(diningRoom.getPhilosopherById(3).getPhilosopherState()));
        fourthState.setBounds(560, 160, 250, 15);
        philosopherStateToGUIPhilosopher.put(diningRoom.getPhilosopherById(3) ,fourthState);
        content.add(fourthState);
        content.add(fourthPhilosopher);

        JLabel fifthPhilosopher = new JLabel("P5");
        fifthPhilosopher.setOpaque(true);
        fifthPhilosopher.setBackground(Color.BLUE);
        fifthPhilosopher.setHorizontalAlignment(SwingConstants.CENTER);
        fifthPhilosopher.setBounds(300, 100, 100, 100);
        philosopherToGUIPhilosopher.put(diningRoom.getPhilosopherById(4), fifthPhilosopher);
        JTextArea fifthState = new JTextArea();
        fifthState.setEditable(false);
        fifthState.setFont(new Font("Yu Gothic", Font.BOLD, 13));
        fifthState.setLineWrap(true);
        fifthState.setText(String.valueOf(diningRoom.getPhilosopherById(4).getPhilosopherState()));
        fifthState.setBounds(300, 60, 250, 15);
        philosopherStateToGUIPhilosopher.put(diningRoom.getPhilosopherById(4) ,fifthState);
        content.add(fifthState);
        content.add(fifthPhilosopher);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for (Philosopher philosopher: diningRoom.getPhilosophers()){
                    philosopherToGUIPhilosopher.get(philosopher).setBackground(state2Colour.get(philosopher.getPhilosopherState()));
                    philosopherStateToGUIPhilosopher.get(philosopher).setText(String.valueOf(philosopher.getPhilosopherState()) + philosopher.getForksInHands());
                }
            }
        }, 10, 10);
    }
}
