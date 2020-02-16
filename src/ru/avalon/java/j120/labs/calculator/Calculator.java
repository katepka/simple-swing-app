package ru.avalon.java.j120.labs.calculator;

import static ru.avalon.java.j120.labs.calculator.CalcWindow.*;
import java.awt.Toolkit;
import java.awt.datatransfer.*;

public class Calculator {
    // Ссылка на буфер обмена для копирования в него результатов вычислений:
    private static Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    
    /**
     * 
     * @param x число, набранное до дисплее до нажатия арифметической операции или сброса.
     * lastCommand - предыдущая операция.
     * Считает, выводит на дисплей результат и копирует его в буфер обмена.
     */
    public static void calculate(double x) {
        switch (lastCommand) {
            case "=":
                result = x;
                break;
            case "/":
                result /= x;
                break;
            case "*":
                result *= x;
                break;
            case "-":
                result -= x;
                break;
            case "+":
                result += x;
                break;
        }
        display.setText(Double.toString(result));
        copyToClipboard(Double.toString(result));
    }
    
    /**
     * @param text выделяется и копируется в буфер обмена.
     */
    private static void copyToClipboard(String text) {
        StringSelection selection = new StringSelection(text);
        clipboard.setContents(selection, selection);
    }
    
}
