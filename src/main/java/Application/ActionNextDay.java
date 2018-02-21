package Application;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ActionNextDay extends AbstractAction {

    private Tab tab;
    public ActionNextDay(String name, Tab tab) {
        super(name);
        this.tab = tab;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(Simulation.getInstance().getDay()==0 && Application.getAirports().size()>0) {
            Simulation.getInstance().runSimulation();
            tab.refreshView();
        }
        else
        {
            Simulation.getInstance().dailyUpdate();
            tab.refreshView();
        }
    }
}
