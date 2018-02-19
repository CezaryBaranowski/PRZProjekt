package Application;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ActionNextDay extends AbstractAction {

    public ActionNextDay(String name) {
        super(name);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(Simulation.getDay()==0 && Application.getAirports().size()>0) {
            Simulation.getInstance().runSimulation();
            Tab.refreshView();
        }
        else
        {
            Simulation.dailyUpdate();
            Tab.refreshView();
        }
    }
}
