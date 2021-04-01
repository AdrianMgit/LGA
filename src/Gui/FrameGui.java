package Gui;

import code.DrawData;
import code.LGAModel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class FrameGui extends JFrame {
    DrawData drawData;
    //wielkosc poczatkowa panelu
    private int minimumWidthPanel;
    private int minimumHeightPanel;
    private JPanel rootPanel;
    private JPanel controllPanel;
    private JPanel drawPanel;
    private JButton createButton;
    private JButton nextButton;
    private JButton startButton;
    private JTextField particleAmountTextField;
    private JTextField sizeXTextField;
    private JTextField sizeYTextField;
    private JTextField shiftTextField;
    private JTextField sizeTextField;
    private JSlider timeSlider;
    private JButton stopButton;
    private JTextField scaleTextField;
    private Timer LGATimer;


    public FrameGui(){
        //funkcja ustawiajaca wartosci poczatkowe atrybutow
        initialize();

        setMinimumSize(new Dimension(minimumWidthPanel, minimumHeightPanel));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("App");
        setLocationRelativeTo(null);
        drawPanel = new AutDrawPanel(drawData);
        rootPanel.add(BorderLayout.CENTER, drawPanel);
        rootPanel.add(BorderLayout.EAST, controllPanel);
        add(rootPanel);



        //----------------------------------------- ZADANIE TMERA ---------------------------------------
        ActionListener timerFunction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                boolean stillPlay = drawData.getLGAModel().play();
                if (stillPlay) ;
                else {
                    LGATimer.stop();
                    JOptionPane.showMessageDialog(null, "FINISH");
                }
                drawPanel.repaint();
            }
        };


        //----------------------------------- BUTTON CREATE  ----------------------------------------
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LGATimer.stop();
                try {
                    int sizeX = Integer.parseInt(sizeXTextField.getText());
                    int sizeY = Integer.parseInt(sizeYTextField.getText());
                    int particleAmount = Integer.parseInt(particleAmountTextField.getText());
                    int size=Integer.parseInt(sizeTextField.getText());
                    int shift=Integer.parseInt(shiftTextField.getText());
                    int scale=Integer.parseInt(scaleTextField.getText());
                    if (sizeX > 0 && sizeY > 0 && particleAmount > 0 && size>0 && size<sizeY && shift>0 && shift <sizeX
                    && sizeX*scale<1000 && sizeY*scale<1000) {
                        drawData.setLGAModel(new LGAModel(sizeX, sizeY, 10000));
                        drawData.setScale(scale);
                        drawData.getLGAModel().createBorder(size,shift);
                        LGATimer.addActionListener(timerFunction);


                        //LOSOWANIE PUNKTOW
                        Random rand = new Random();
                        int x;
                        int y;
                        int state;
                        for (int i = 0; i < particleAmount; i++) {
                            x = rand.nextInt(shift-2)+1;
                            y = rand.nextInt(drawData.getLGAModel().getSpaceSizeY() - 2) + 1;
                            state = rand.nextInt(4);

                            drawData.getLGAModel().getSpace()[0][y][x].setValue(1);
                            drawData.getLGAModel().getSpace()[0][y][x].setStan(state);
                        }


                        setSize(new Dimension(sizeX*scale+controllPanel.getWidth()+100, sizeY*scale+100));
                        setLocationRelativeTo(null);
                        drawPanel.repaint();
                    } else
                        JOptionPane.showMessageDialog(null, "WRONG VALUE!");
                }catch (NumberFormatException numberFormatException){
                    JOptionPane.showMessageDialog(null, "WRONG VALUE!");
                }

            }
        });


        // ------------------------------------------- NEXT STEP BUTTON ---------------------------------------
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (drawData.getLGAModel() != null) {
                    drawData.getLGAModel().play();
                    drawPanel.repaint();
                }
            }
        });

            //---------------------------------- BUTTON START ---------------------------------------------
        startButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(drawData.getLGAModel()!=null)
                        LGATimer.start();


                }
            });

        // ------------------------------------------- TIME SLIDER -------------------------------------
        timeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                LGATimer.setDelay(timeSlider.getValue());
            }
        });


        // ------------------------------------------- BUTTON STOP --------------------------------
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(drawData.getLGAModel()!=null)
                    LGATimer.stop();
            }
        });
    }


    //-----------------------FUNKCJA NADAJACA POCZATKOWE WARTOSCI ATRYBUTOM-----------------
    private void initialize() {
        drawData = new DrawData();
        //wielkosc poczatkowa panelu
        minimumWidthPanel = 600;
        minimumHeightPanel = 600;

        LGATimer=new Timer(250,null);


    }
}
