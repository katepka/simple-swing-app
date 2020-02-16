package ru.avalon.java.j120.labs.calculator;

import static ru.avalon.java.j120.labs.calculator.CalcWindow.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Следит за нажатиями на кнопки операций, реализует интерфейс ActionListener.
 */
public class ClickCommand implements ActionListener {
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand(); // запоминает, какая команда выбрана
        if (start) { // начало вычислений:
            if (command.equals("-")) { // если минус в начале вычисления, относит его к числу
                display.setText(command); // выводит на экран
                start = false; // вычисления в процессе
            } else if (command.equals("CE")) { // на сброс
                result = 0; // результат вычислений сбрасывается
                display.setText("0"); // дисплей очищается
                lastCommand = "="; // последняя команда =
            } else {
                lastCommand = command; // другая команда запоминается как последняя
            }
        } else { // вычисления в процессе:
            if (command.equals("CE")) { // на сброс то же самое +
                result = 0;
                start = true; // задается старт вычислений
                display.setText("0");
                lastCommand = "=";
            } else { // число на дисплее парсится в double 
                // и передается в метод Calculator.calculate:
                Calculator.calculate(Double.parseDouble(display.getText()));
                lastCommand = command;// команда запоминается как последняя
                start = true; // задается старт вычислений
            }    
        }
    }
    
}
