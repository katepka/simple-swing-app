package ru.avalon.java.j120.labs.mainwindow;

import ru.avalon.java.j120.labs.calculator.CalcWindow;
import ru.avalon.java.j120.labs.colorpicker.ColorPickerWindow;
import java.awt.event.*;
import javax.swing.*;

/**
 * Следит за нажатиями на кнопки, реализует интрефейс ActionListener.
 */
public class ClickReaction implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent ae) {
        String actionCommand = ae.getActionCommand(); // определем, какая кнопка нажата
        switch (actionCommand) {
            case "toColorPicker":
                SwingUtilities.invokeLater(new Runnable() { // запускаем
                    public void run() { // ColorPickerWindow в отдельном потоке диспетчеризации событий
                        ColorPickerWindow.getInstance(); // как завещал Шилдт
                    }
                });
                break;
            case "toCalculator":
                SwingUtilities.invokeLater(new Runnable() { // запускаем
                    public void run() { // CalcWindow в отдельном потоке диспетчеризации событий
                        CalcWindow.getInstance();
                    }
                });
                break;
        }
    }
    
}
