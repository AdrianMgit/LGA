package Gui;

import code.DrawData;

import javax.swing.*;
import java.awt.*;


public class AutDrawPanel extends JPanel {

    private DrawData drawData;
    int pointSize;

    public AutDrawPanel(DrawData dm) {

        this.drawData = dm;


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //------------------------------------- LGA -------------------------------------------
        if(drawData.getLGAModel()!=null){

            int scale=drawData.getScale();
            int iteration = drawData.getLGAModel().getIterationNow()%drawData.getLGAModel().getIterationSizeTab();

            pointSize=scale;

            //rysowanie kwadratow
            g.setColor(Color.BLUE);

                for (int y = 0; y < drawData.getLGAModel().getSpaceSizeY(); y++) {
                    for (int x = 0; x < drawData.getLGAModel().getSpaceSizeX(); x++) {
                        if(iteration!=0) {      //USUWAM POPRZEDNIA ITERACJE
                            if (drawData.getLGAModel().getSpace()[iteration - 1][y][x].getValue() == 1) {
                                g.setColor(Color.WHITE);
                                g.fillRect((pointSize * x), pointSize * y, pointSize, pointSize);
                            }
                        }
                        if (drawData.getLGAModel().getSpace()[iteration][y][x].getValue() == 1) {  //RYSOWANIE OBECNEJ ITERACJI
                            g.setColor(Color.BLUE);
                            g.fillRect((pointSize * x), pointSize * y, pointSize, pointSize);
                        }
                        if (drawData.getLGAModel().getSpace()[iteration][y][x].getValue() == -1) {  //RYSOWANIE OBECNEJ ITERACJI
                            g.setColor(Color.BLACK);
                            g.fillRect((pointSize * x), pointSize * y, pointSize, pointSize);
                        }

                    }
                }
        }
    }

    @Override
    public void repaint() {
        super.repaint();
    }



}