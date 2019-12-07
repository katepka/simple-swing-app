package ru.avalon.java.j120.labs.calculator;

import static ru.avalon.java.j120.labs.calculator.CalcWindow.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Следит за нажатиями на кнопки цифр, реализует интрефейс ActionListener.
 */
public class ClickOperand implements ActionListener {            
    @Override
    public void actionPerformed(ActionEvent ae) { 
        String operand = ae.getActionCommand(); // запоминает, какая цифра нажата
        if (start) { // если начало вычислений
            display.setText(""); // очищает "дисплей"
            start = false; // отмечает, что вычисления в процессе
        }
        // выводит на "дисплей" уже имеющийся текст + нажатую цифру
        display.setText(display.getText() + operand);
    }    
}
