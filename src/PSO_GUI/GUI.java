//Klasa i layout aplikacji-Tymoteusz
//Listenery-Kacper

package PSO_GUI;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.JavaBean;

public class GUI extends JFrame{

    private static final int WINDOW_HEIGHT = 700;
    //menu
    private JMenu fileMenu, settingsMenu;
    private JMenuBar menuBar;
    private JMenuItem menuItem1, menuItem2, menuItem3, menuItem4, menuItem5, menuItem6;

    //panels
    private static JPanel topPanel, leftPanel, rightPanel, centerPanel;

    //left
    private TitledBorder paramsTitle;
    private JTextField swarmSizeText, maxForceText, maxVelText;
    private JButton runSim, stopSim, choosePreset, buttonImmitatingJMenuItem;

    //here
    private JLabel labelVel, labelForce, labelSwarmSize, labelAlfa, labelBeta;
    private JSlider sliderVel, sliderForce, sliderIntelligence;
    private JCheckBox checkBoxMode;

    private JTextField par1, par2, par3;

    public GUI() throws HeadlessException{
        this.setSize(1100,WINDOW_HEIGHT);
        this.setTitle("PSO_GUI");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        //menu
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        menuItem1 = new JMenuItem("Load File");
        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoadFile fileSelectionDialog = new LoadFile();
                fileSelectionDialog.loadFile(GUI.this);
            }
        });
        menuItem2 = new JMenuItem("Save File");
        menuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //nic
            }
        });
//        menuItem3 = new JMenuItem("menuItem3");
//        menuItem3.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                //nic
//            }
//        });
        fileMenu.add(menuItem1);
        fileMenu.add(menuItem2);
//        fileMenu.add(menuItem3);
        menuBar.add(fileMenu);

        settingsMenu = new JMenu("Settings");
        menuItem4 = new JMenuItem("Language Settings");
        menuItem4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowLanguageDialog.showLanguageDialog(GUI.this);
            }
        });
        menuItem5 = new JMenuItem("Display Settings");
        menuItem5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //nic
            }
        });
//        menuItem6 = new JMenuItem("menuItem6");
//        menuItem6.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                //nic
//            }
//        });
        settingsMenu.add(menuItem4);
        settingsMenu.add(menuItem5);
//        settingsMenu.add(menuItem6);
        menuBar.add(settingsMenu);
        this.setJMenuBar(menuBar);

        //Przycisk "Help" imitujący obiekt typu JMenuItem, który po kliknięciu przekeirowywuje użytkownika do
        //strony GitHub do pliku READ_ME.txt
        buttonImmitatingJMenuItem = new JButton("Help");
        buttonImmitatingJMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowHtmlMessageDialog help = new ShowHtmlMessageDialog();
                help.showHtmlMessageDialog("HELP", "<html>If you encountered any trouble using the program, read through documentation available on <br><a href=\"https://github.com/Kacper-coder/ParticleSwarm\">GitHub</a></html>");
            }
        });
        buttonImmitatingJMenuItem.setContentAreaFilled(false);
        menuBar.add(buttonImmitatingJMenuItem);

        //Left panel
        leftPanel = new JPanel();

        leftPanel.setPreferredSize(new Dimension(this.getWidth()/4, WINDOW_HEIGHT));
//        leftPanel.setLayout(new GridLayout(3,1));
        leftPanel.setLayout(null);
        paramsTitle = BorderFactory.createTitledBorder("Parametry");
        swarmSizeText = new JTextField("Ustaw liczebność roju");
        swarmSizeText.setBounds(125,31+leftPanel.getHeight()/2, 140,20);
        maxForceText = new JTextField("Fmax");
        maxForceText.setBounds(180,100+leftPanel.getHeight()/2, 50,20);
        maxVelText = new JTextField("vmax");
        maxVelText.setBounds(180,200+leftPanel.getHeight()/2, 50,20);


        //here
        labelSwarmSize = new JLabel("Liczebność roju: ");
        labelSwarmSize.setBounds(5, 30+leftPanel.getHeight()/2, 140, 20);
        leftPanel.add(labelSwarmSize);

        labelForce = new JLabel("Maksymalna siła sterująca: ");
        labelForce.setBounds(5, 70+leftPanel.getHeight()/2, 200,20);
        leftPanel.add(labelForce);

        labelVel = new JLabel("Maksymalna prędkość cząstki: ");
        labelVel.setBounds(5, 170+leftPanel.getHeight()/2, 220, 20);
        leftPanel.add(labelVel);



        sliderForce = new JSlider(JSlider.HORIZONTAL, 0, 50, 0);
        sliderForce.setMinorTickSpacing(1);
        sliderForce.setMajorTickSpacing(10);
        sliderForce.setPaintTicks(true);
        sliderForce.setPaintLabels(true);
        sliderForce.setBounds(5, 100+leftPanel.getHeight()/2, 160,50);
        leftPanel.add(sliderForce);

        sliderVel = new JSlider(JSlider.HORIZONTAL, 0, 50, 0);
        sliderVel.setMinorTickSpacing(1);
        sliderVel.setMajorTickSpacing(10);
        sliderVel.setPaintTicks(true);
        sliderVel.setPaintLabels(true);
        sliderVel.setBounds(5, 200+leftPanel.getHeight()/2, 160, 50);
        leftPanel.add(sliderVel);


        checkBoxMode = new JCheckBox("Samodzielnie");
        checkBoxMode.setBounds(5, 260+leftPanel.getHeight()/2, 150,20);
        leftPanel.add(checkBoxMode);

        labelAlfa = new JLabel("α:");
        labelAlfa.setBounds(15, 465, 60, 20);
        labelBeta = new JLabel("β:");
        labelBeta.setBounds(70, 465, 60, 20);

        sliderIntelligence=new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        sliderIntelligence.setBounds(0, 410, 270,50);
        sliderIntelligence.setMajorTickSpacing(20);
        sliderIntelligence.setMinorTickSpacing(5);
        sliderIntelligence.setPaintTicks(true);

        sliderIntelligence.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider slider = (JSlider) e.getSource();
                double value = slider.getValue();

                labelAlfa.setText("α:"+value/100);
                labelBeta.setText("β:"+(100-value)/100);
            }
        });

        runSim = new JButton("Run Simulation");
        runSim.setBounds(10, 535, 160, 40);

        stopSim = new JButton("Stop Simulation");
        stopSim.setBounds(10, 575, 160, 40);

        choosePreset = new JButton("Choose Preset");
        choosePreset.setBounds(10, 495, 160, 40);

        choosePreset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowChoosePresetDialog.showChoosePresetDialog(GUI.this);
            }
        });


        leftPanel.add(runSim);
        leftPanel.add(stopSim);
        leftPanel.add(choosePreset);
        leftPanel.add(sliderIntelligence);
        leftPanel.add(labelAlfa);
        leftPanel.add(labelBeta);
        leftPanel.add(swarmSizeText);
        leftPanel.add(maxForceText);
        leftPanel.add(maxVelText);

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

