package ru.avalon.java.j120.labs.mainwindow;

import ru.avalon.java.j120.labs.mainwindow.ClickReaction;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainWindow extends JFrame{
    public static MainWindow mainWindow = null;
    
    /** Приватный конструктор реализует паттерн проектирования Singleton:
     *  https://refactoring.guru/ru/design-patterns/singleton/java/example
     *  Экземпляр класса создается в статическом методе getInstance().
    */
    private MainWindow() {
        mainWindow = this;
        setTitle("Choose an application");
        setResizable(false); // размер не изменяется
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // по закрытию окна завершается программа
        setLocationRelativeTo(null); // начальное положение по центру экрана
        
        // Две кнопушки для выбора программы:
        JButton toColorPicker = new JButton("ColorPicker");
        JButton toCalculator = new JButton("Calculator");
        
        // Компоновка - друг за другом:
        setLayout(new FlowLayout());
        
        add(toColorPicker, JButton.CENTER);
        add(toCalculator, JButton.CENTER);
        // Добавляем ActionListener для отслеживания взаимодействия
        // пользователя с кнопками:
        ActionListener actionListener = new ClickReaction();
        toColorPicker.addActionListener(actionListener);
        toCalculator.addActionListener(actionListener);
        // Назвачаем ActionCommand, чтобы понимать в ActionListener'е,
        // какая кнопка нажата:
        toColorPicker.setActionCommand("toColorPicker");
        toCalculator.setActionCommand("toCalculator");
        
        pack();
        setVisible(true);
    }
    
    /**
     * @return экземпляр MainWindow
     */
    public static MainWindow getInstance() {
        return new MainWindow();
    }
    
}
