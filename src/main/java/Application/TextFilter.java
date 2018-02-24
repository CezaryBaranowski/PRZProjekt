/*
package Application;

import Model.FlightOrder;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.ArrayList;
import java.util.function.Predicate;

public class TextFilter implements DocumentListener {

    JTextField field;
    ArrayList<FlightOrder> listOfOrders;
    Tab tab;

    TextFilter(JTextField field, Tab tab)
    {
        this.field = field;
        listOfOrders = new ArrayList<FlightOrder>();
        this.tab = tab;
    }
    @Override
    public void insertUpdate(DocumentEvent e) {
        filter();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        filter();

    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        filter();
    }

    public void filter()
    {
        String text = field.getText();
        if(text.isEmpty()) listOfOrders = Simulation.getInstance().getAvailableFlightOrders();
        else
        {
            for(FlightOrder fo : Simulation.getInstance().getAvailableFlightOrders())
            {
                if(fo.getDestination().getCity().startsWith(text))
                {
                    listOfOrders.add(fo);
                }
            }
           // tab.refreshTable2(field,);
        }
    }
}
*/
