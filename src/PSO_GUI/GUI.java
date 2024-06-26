//Klasa i layout aplikacji-Tymoteusz
//Listenery-Kacper

package PSO_GUI;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Random;

public class GUI extends JFrame{

    private static final int WINDOW_HEIGHT = 700;
    //menu
    private JMenu fileMenu, settingsMenu;
    private JMenuBar menuBar;
    private JMenuItem menuItem1, menuItem2, menuItem3, menuItem4, menuItem5;

    //panels
    private static JPanel topPanel, leftPanel, centerPanel, rightRightPanel;

    private static Function function;
    private static FunctionPanel rightPanel;

    //left
    private TitledBorder paramsTitle;
    private JTextField swarmSizeText, maxForceText, maxVelText;
    public static JTextField seedField;
    public static JTextPane textPane;
    private JButton runSim, stopSim, choosePreset, buttonImmitatingJMenuItem, confirmParameters;

    //here
    private JLabel labelVel, labelForce, labelSwarmSize, labelAlfa, labelBeta;
    private JSlider sliderVel, sliderForce, sliderIntelligence;
    private JCheckBox checkBoxMode;

    private JTextField par1, par2, par3;
    private boolean blockRunButton = false;

    //do zmiany języków
    public void rebuildUI(){
        paramsTitle.setTitle(LanguageManager.getMessage("parameters"));
        fileMenu.setText(LanguageManager.getMessage("file"));
        menuItem1.setText(LanguageManager.getMessage("load_file"));
        menuItem2.setText(LanguageManager.getMessage("save_file"));
        menuItem3.setText(LanguageManager.getMessage("exit"));
        settingsMenu.setText(LanguageManager.getMessage("settings"));
        menuItem4.setText(LanguageManager.getMessage("lang_settings"));
        //menuItem5.setText(LanguageManager.getMessage("display_settings"));
        buttonImmitatingJMenuItem.setText(LanguageManager.getMessage("help"));
        swarmSizeText.setText(LanguageManager.getMessage("swarm_size_field"));
        labelSwarmSize.setText(LanguageManager.getMessage("swarm_size"));
        labelForce.setText(LanguageManager.getMessage("max_force"));
        labelVel.setText(LanguageManager.getMessage("max_vel"));
        runSim.setText(LanguageManager.getMessage("run"));
        stopSim.setText(LanguageManager.getMessage("stop"));
        choosePreset.setText(LanguageManager.getMessage("preset"));
        confirmParameters.setText(LanguageManager.getMessage("confirm_parameters"));
        leftPanel.repaint();
    }

    public GUI() throws HeadlessException{
        this.setSize(1100,WINDOW_HEIGHT);
        this.setTitle("PSO_GUI");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        textPane = new JTextPane();
        textPane.setText("Data"); // Example text
        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Adding JTextPane to centerPanel
        scrollPane.setBounds(600, 0, 220, 600); // Example bounds

        //menu
        menuBar = new JMenuBar();
        fileMenu = new JMenu(LanguageManager.getMessage("file"));
        menuItem1 = new JMenuItem(LanguageManager.getMessage("load_file"));
        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoadFile fileSelectionDialog = new LoadFile();
                try {
                    fileSelectionDialog.loadFile(GUI.this);
                    swarmSizeText.setText(Integer.toString(FunctionPanel.getSwarmSize()));
                    sliderIntelligence.setValue((int)Particle.getAlfa());
                    labelAlfa.setText(Double.toString(Particle.getAlfa()));
                    labelBeta.setText(Double.toString(Particle.getBeta()));
                    sliderForce.setValue((int)(Particle.getMaxForce() * Math.pow(10, 5)));
                    sliderVel.setValue((int)(Particle.getMaxVel()*Math.pow(10,2)));
                    maxForceText.setText(Double.toString(Particle.getMaxForce() * Math.pow(10, 5)));
                    maxVelText.setText(Double.toString(Particle.getMaxVel()*Math.pow(10,2)));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
            menuItem2 = new JMenuItem(LanguageManager.getMessage("save_file"));
        menuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveFile fileSaveDialog = new SaveFile();
                fileSaveDialog.saveSimToFile(GUI.this, FunctionPanel.getSwarmSize(),Particle.getMaxForce(),Particle.getMaxVel(),Particle.getAlfa(), Particle.getBeta(), Double.parseDouble(seedField.getText()));
            }
        });
        menuItem3 = new JMenuItem(LanguageManager.getMessage("exit"));
        menuItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(menuItem1);
        fileMenu.add(menuItem2);
        fileMenu.add(menuItem3);
        menuBar.add(fileMenu);


        settingsMenu = new JMenu(LanguageManager.getMessage("settings"));
        menuItem4 = new JMenuItem(LanguageManager.getMessage("lang_settings"));
        menuItem4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowLanguageDialog.showLanguageDialog(GUI.this);
                if(ShowLanguageDialog.shouldRebuildUI == true){
//                    fileMenu.setText(LanguageManager.getMessage("file"));
//                    revalidate();
//                    repaint();
                    rebuildUI();
                }
            }
        });
//        menuItem5 = new JMenuItem(LanguageManager.getMessage("display_settings"));
//        menuItem5.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                //nic
//            }
//        });

        settingsMenu.add(menuItem4);
//        settingsMenu.add(menuItem5);
        menuBar.add(settingsMenu);
        this.setJMenuBar(menuBar);

        //Przycisk "Help" imitujący obiekt typu JMenuItem, który po kliknięciu przekierowuje użytkownika do
        //strony GitHub do pliku READ_ME.txt
        buttonImmitatingJMenuItem = new JButton(LanguageManager.getMessage("help"));
        buttonImmitatingJMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowHtmlMessageDialog help = new ShowHtmlMessageDialog();
                help.showHtmlMessageDialog(LanguageManager.getMessage("help"), LanguageManager.getMessage("help_prompt") + "<html><a href=\"https://github.com/Kacper-coder/ParticleSwarm\">GitHub</a></html>");
            }
        });
        buttonImmitatingJMenuItem.setContentAreaFilled(false);
        menuBar.add(buttonImmitatingJMenuItem);

        //Left panel
        leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(this.getWidth()/4, WINDOW_HEIGHT));
//        leftPanel.setLayout(new GridLayout(3,1));
        leftPanel.setLayout(null);
        paramsTitle = BorderFactory.createTitledBorder(LanguageManager.getMessage("parameters"));

        swarmSizeText = new JTextField(LanguageManager.getMessage("swarm_size_field"));
        swarmSizeText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if(swarmSizeText.getText().equals(LanguageManager.getMessage("swarm_size_field"))){
                    swarmSizeText.setText("");
                }
            }


            @Override
            public void focusLost(FocusEvent e){
                if(swarmSizeText.getText().isEmpty()){
                    swarmSizeText.setText(LanguageManager.getMessage("swarm_size_field"));
                }
//                else {
//            try {
//                int swarmSize = Integer.parseInt(swarmSizeText.getText());
//                rightPanel.setSwarmSize(swarmSize);
//                blockRunButton = false;
//            } catch (NumberFormatException ex) {
//                JOptionPane.showMessageDialog(null, "Entered text is not an integer: " + swarmSizeText.getText(), "Error", JOptionPane.ERROR_MESSAGE);
//                swarmSizeText.setText(LanguageManager.getMessage("swarm_size_field")); // Reset text field to default message
//                blockRunButton = true;
//            }
//                }
            }
        });
        //w przyszłości w JTextFieldach będzie sprawdzanie czy wprowadzona wartości jest integerem
        swarmSizeText.setBounds(125,31+leftPanel.getHeight()/2, 140,20);
        maxForceText = new JTextField("1");
        maxForceText.setBounds(180,100+leftPanel.getHeight()/2, 50,20);
        maxVelText = new JTextField("1");
        maxVelText.setBounds(180,200+leftPanel.getHeight()/2, 50,20);


        //here
        labelSwarmSize = new JLabel(LanguageManager.getMessage("swarm_size"));
        labelSwarmSize.setBounds(5, 30+leftPanel.getHeight()/2, 140, 20);
        leftPanel.add(labelSwarmSize);

        labelForce = new JLabel(LanguageManager.getMessage("max_force"));
        labelForce.setBounds(5, 70+leftPanel.getHeight()/2, 200,20);
        leftPanel.add(labelForce);

        labelVel = new JLabel(LanguageManager.getMessage("max_vel"));
        labelVel.setBounds(5, 170+leftPanel.getHeight()/2, 220, 20);
        leftPanel.add(labelVel);



        sliderForce = new JSlider(JSlider.HORIZONTAL, 0, 3, 1);
        sliderForce.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                maxForceText.setText(Integer.toString(sliderForce.getValue()));
                //Particle.setMaxForce((sliderForce.getValue()*(1e-3)));
            }
        });
        sliderForce.setMinorTickSpacing(1);
        sliderForce.setMajorTickSpacing(10);
        sliderForce.setPaintTicks(true);
        sliderForce.setPaintLabels(true);
        sliderForce.setBounds(5, 100+leftPanel.getHeight()/2, 160,50);
        leftPanel.add(sliderForce);

        sliderVel = new JSlider(JSlider.HORIZONTAL, 0, 3, 1);
        sliderVel.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                maxVelText.setText(Integer.toString(sliderVel.getValue()));
                //Particle.setMaxVel((sliderVel.getValue()*(1e-2)));
            }
        });
        sliderVel.setMinorTickSpacing(1);
        sliderVel.setMajorTickSpacing(10);
        sliderVel.setPaintTicks(true);
        sliderVel.setPaintLabels(true);
        sliderVel.setBounds(5, 200+leftPanel.getHeight()/2, 160, 50);
        leftPanel.add(sliderVel);


        sliderIntelligence=new JSlider(JSlider.HORIZONTAL, 0, 100, 80);
        sliderIntelligence.setBounds(0, 260+leftPanel.getHeight()/2, 270,50);
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

//                System.out.println(value/100);
//                System.out.println((sliderIntelligence.getValue())/100);
            }
        });

        labelAlfa = new JLabel("α:" + (double)sliderIntelligence.getValue()/100);
        labelAlfa.setBounds(15, 315+leftPanel.getHeight()/2, 60, 20);
        labelBeta = new JLabel("β:" + (double)(100-sliderIntelligence.getValue())/100);
        labelBeta.setBounds(70, 315+leftPanel.getHeight()/2, 60, 20);


        confirmParameters = new JButton(LanguageManager.getMessage("confirm_parameters"));
        //później w poniższej czynności przycisku będzie po prostu metoda setParameters
        confirmParameters.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(swarmSizeText.getText().isEmpty()){
                    swarmSizeText.setText(LanguageManager.getMessage("swarm_size_field"));
                }else{
                    try{
                        rightPanel.setSwarmSize(Integer.parseInt(swarmSizeText.getText()));
                        blockRunButton = false;
                    }catch (NumberFormatException ex){
                        System.out.println(LanguageManager.getMessage("not_an_integer") + swarmSizeText.getText());
                        blockRunButton = true;
                    }
                }
                //trzeba wziąć wartość ze slidera, tylko wartość slidera sliderIntelligence ciągle pokazuje 0
                //rozwiązanie - dzielenie sliderIntelligence.getValue() przez 100 daje 0
                double value = sliderIntelligence.getValue();
                Particle.setAlfa(value/100);
                Particle.setBeta((100-value)/100);

                if (Double.parseDouble(maxForceText.getText())*(1e-5) < (sliderForce.getValue()*(1e-5))||Double.parseDouble(maxForceText.getText())*(1e-5)>(sliderForce.getValue()*(1e-5))){
                    Particle.setMaxForce((Double.parseDouble((maxForceText.getText())) * (1e-5)));
                    sliderForce.setValue((int)(Double.parseDouble(maxForceText.getText())));
                    System.out.println((Double.parseDouble((maxForceText.getText())) * (1e-5)));
                }else{
                    Particle.setMaxForce((sliderForce.getValue()*(1e-5)));
                    System.out.println((sliderForce.getValue()*(1e-5)));
                }

                if (Double.parseDouble(maxVelText.getText())* 1e-2 < sliderVel.getValue() * 1e-2 || Double.parseDouble(maxVelText.getText())* 1e-2 > sliderVel.getValue() * 1e-2) {
                    Particle.setMaxVel((Double.parseDouble(maxVelText.getText()))* 1e-2);
                    sliderVel.setValue((int)(Double.parseDouble(maxVelText.getText())));
                    System.out.println(Double.parseDouble(maxVelText.getText())* 1e-2);
                } else {
                    Particle.setMaxVel(sliderVel.getValue() * 1e-2);
                    System.out.println(sliderVel.getValue() * 1e-2);
                }

                System.out.println(value/100);
                System.out.println((100-value)/100);
            }
        });
        confirmParameters.setBounds(5, 350+leftPanel.getHeight()/2, 250,20);
        seedField = new JTextField("42");
        seedField.setBounds(5, 375+leftPanel.getHeight()/2, 100,20);
        leftPanel.add(seedField);
        leftPanel.add(confirmParameters);

        runSim = new JButton(LanguageManager.getMessage("run"));
        runSim.setBounds(10, 535, 160, 40);
        runSim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(blockRunButton == false){
                    double seed;
                    if(seedField.getText().isEmpty()){
                        seed = System.currentTimeMillis();
                    }else{
                        try{
                            seed = Double.parseDouble(seedField.getText());
                            System.out.println("seed set");
                        }catch (NumberFormatException ex){
                            seed = 42;
                        }
                    }
                    Utility.initRandom((long)seed);
//                    Particle.nullBest(); //tworzyło błąd w postaci cząstek nie słuchających się własnego LB, usuń

                    rightPanel.nullBest();
                    rightPanel.start();
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            for(int i=0; i<rightPanel.getSwarmSize(); i++){
                                rightPanel.addParticle(function);
                                if(FunctionPanel.GB!=null){
                                    GUI.this.textPane.setText(FunctionPanel.getGB());
                                }
                            }

                            Thread thread = new Thread(rightPanel);
                            thread.start();
                        }
                    });
                    blockRunButton = true;
                }
            }
        });

        stopSim = new JButton(LanguageManager.getMessage("stop"));
        stopSim.setBounds(10, 575, 160, 40);
        stopSim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPanel.deleteParticles();
                rightPanel.stop();
                blockRunButton = false;
//                timer.stop();
            }
        });



        choosePreset = new JButton(LanguageManager.getMessage("preset"));
        choosePreset.setBounds(10, 495, 160, 40);

        choosePreset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] languages = {"Beale", "Ackley", "Himmelblau","Goldstein","Bukin"};

                String selectedLanguage = (String) JOptionPane.showInputDialog(GUI.this,
                        LanguageManager.getMessage("function"), LanguageManager.getMessage("preset"),
                        JOptionPane.PLAIN_MESSAGE, null,
                        languages, languages[0]);



                if (selectedLanguage != null) {
                    JOptionPane.showMessageDialog(GUI.this, LanguageManager.getMessage("function_prompt")+ selectedLanguage);
                    Function function1;
                    switch(selectedLanguage){
                        case "Beale":
                            function1 = new Function(600, 600, Function.FunctionType.BEALE);
                            function = function1;
                            rightPanel.changeFunctionImage(function1.getBufferedImage());
                            break;
                        case "Ackley":
                            function1 = new Function(600, 600, Function.FunctionType.ACKLEY);
                            function = function1;
                            rightPanel.changeFunctionImage(function1.getBufferedImage());
//                            function.setFunctionType(Function.FunctionType.ACKLEY);

                            break;
                        case "Himmelblau":
                            function1 = new Function(600, 600, Function.FunctionType.HIMMELBLAU);
                            function = function1;
                            rightPanel.changeFunctionImage(function1.getBufferedImage());
//                            function.setFunctionType(Function.FunctionType.HIMMELBLAU);
                            break;
                        case "Goldstein":
                            function1 = new Function(600, 600, Function.FunctionType.GOLDSTEIN);
                            function = function1;
                            rightPanel.changeFunctionImage(function1.getBufferedImage());
//                            function.setFunctionType(Function.FunctionType.GOLDSTEIN);
                            break;
                        case "Bukin":
                            function1 = new Function(600, 600, Function.FunctionType.BUKIN);
                            function = function1;
                            rightPanel.changeFunctionImage(function1.getBufferedImage());
//                            function.setFunctionType(Function.FunctionType.BUKIN);
                            break;
                    }
                }
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
        leftPanel.setBorder(paramsTitle);
        this.add(leftPanel, BorderLayout.LINE_START);

        //Center panel
        centerPanel = new JPanel();
        centerPanel.add(scrollPane);
        this.add(centerPanel, BorderLayout.CENTER);
//        centerPanel.setBackground(Color.white);
//        centerPanel.setLayout(new GridLayout(1,1));
        centerPanel.setLayout(null);

        //Top panel
        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        this.add(topPanel,BorderLayout.NORTH);

        //Right panel
//        Function function = new Function(600, 600, Function.FunctionType.BEALE); //tylko tymczasowo, będzie brane z dropDown menu
        Function function = new Function(600, 600, Function.FunctionType.BEALE);
        rightPanel = new FunctionPanel(function.getBufferedImage());
        this.function = function;
//        rightPanel.setBounds(,300,400,400);
        rightPanel.setBounds(0,0,600,600);

        //dodanie cząstek - tymczasowe, będą się dodawać chyba dopiero po naciśnięciu przycisku Run simulation
//        for(int i=0; i<40; i++){
//            rightPanel.addParticle(function);
//        }

//        rightPanel.setBackground(Color.gray);
        centerPanel.add(rightPanel);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                rightPanel.stop();
                dispose();
            }
        });

//        System.out.println(rightPanel.getSize());
    }

    public static void main(String[] args){
        //odkomentarzuj te 3 linijki i zakomentarzuj resztę, jeśli nie będzie poprawnie działać
        GUI frame = new GUI();
        frame.setVisible(true);
        LanguageManager.setMainFrame(frame);

//        System.out.println(leftPanel.getWidth()/2);

        //niezależne od wyższego komentarza - odkomentarzuj poniższe SwingUtilities i zakomentarzuj resztę, jeśli przyciski run i stop nie będą poprawnie działać
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
////                JFrame frame = new JFrame("Image Panel Example");
////                frame.setSize(600, 600);
////                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
////                frame.setLocationRelativeTo(null);
////                Function function1 = new Function(600, 600, Function.FunctionType.BEALE);
////                FunctionPanel rightPanel = new FunctionPanel(function1.getBufferedImage());
//
//                GUI frame = new GUI();
//                frame.setVisible(true);
//                LanguageManager.setMainFrame(frame);
//
//                for(int i=0; i<200; i++){
//                    rightPanel.addParticle(function);
//                }
//
//                Thread thread = new Thread(rightPanel);
//                thread.start();
//            }
//        });
//        GUI frame = new GUI();
//        frame.setVisible(true);
//        LanguageManager.setMainFrame(frame);
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
////                JFrame frame = new JFrame("Image Panel Example");
////                frame.setSize(600, 600);
////                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
////                frame.setLocationRelativeTo(null);
////                Function function1 = new Function(600, 600, Function.FunctionType.BEALE);
////                FunctionPanel rightPanel = new FunctionPanel(function1.getBufferedImage());
//
//                GUI frame = new GUI();
//                frame.setVisible(true);
//                LanguageManager.setMainFrame(frame);
//
//                for(int i=0; i<100; i++){
//                    rightPanel.addParticle(function);
//                }
//
//                Thread thread = new Thread(rightPanel);
//                thread.start();
//            }
//        });
    }
}

