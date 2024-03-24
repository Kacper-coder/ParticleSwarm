package PSO_GUI;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame{

    private static final int WINDOW_HEIGHT = 600;
    //menu
    private JMenu fileMenu, settingsMenu;
    private JMenuBar menuBar;
    private JMenuItem menuItem1, menuItem2, menuItem3, menuItem4, menuItem5, menuItem6;

    //panels
    private static JPanel topPanel, leftPanel, rightPanel, centerPanel;

    //left
    private TitledBorder paramsTitle;
    private JTextField par1, par2, par3;
    private JButton runSim;

    public GUI() throws HeadlessException{
        this.setSize(900,WINDOW_HEIGHT);
        this.setTitle("PSO_GUI");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        //menu
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        menuItem1 = new JMenuItem("menuItem1");
        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //nic
            }
        });
        menuItem2 = new JMenuItem("menuItem2");
        menuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //nic
            }
        });
        menuItem3 = new JMenuItem("menuItem3");
        menuItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //nic
            }
        });
        fileMenu.add(menuItem1);
        fileMenu.add(menuItem2);
        fileMenu.add(menuItem3);
        menuBar.add(fileMenu);

        settingsMenu = new JMenu("Settings");
        menuItem4 = new JMenuItem("menuItem4");
        menuItem4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //nic
            }
        });
        menuItem5 = new JMenuItem("menuItem5");
        menuItem5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //nic
            }
        });
        menuItem6 = new JMenuItem("menuItem6");
        menuItem6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //nic
            }
        });
        settingsMenu.add(menuItem4);
        settingsMenu.add(menuItem5);
        settingsMenu.add(menuItem6);
        menuBar.add(settingsMenu);
        this.setJMenuBar(menuBar);

        //Left panel
        leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(this.getWidth()/8, WINDOW_HEIGHT));
//        leftPanel.setLayout(new GridLayout(3,1));
        leftPanel.setLayout(null);
        paramsTitle = BorderFactory.createTitledBorder("Parametry");
        par1 = new JTextField("Parametr 1");
        par1.setBounds(5,50+leftPanel.getHeight()/2, 100,20);
        //par1.setBounds(50,50,20,10);
        par2 = new JTextField("Parametr 2");
        par2.setBounds(5,100+leftPanel.getHeight()/2, 100,20);
        par3 = new JTextField("Parametr 3");
        par3.setBounds(5,150 + leftPanel.getHeight()/2, 100,20);

        runSim = new JButton("Run");
        runSim.setBounds(5, 475, 100, 40);


        leftPanel.add(runSim);
        leftPanel.add(par1);
        leftPanel.add(par2);
        leftPanel.add(par3);
        leftPanel.setBorder(paramsTitle);
        this.add(leftPanel, BorderLayout.LINE_START);

        //Center panel
        centerPanel = new JPanel();
        this.add(centerPanel, BorderLayout.CENTER);
        centerPanel.setBackground(Color.gray);

        //Top panel
        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        this.add(topPanel,BorderLayout.NORTH);

    }

    public static void main(String[] args){
        GUI frame = new GUI();
        frame.setVisible(true);
//        System.out.println(leftPanel.getWidth()/2);
    }
}

