package ru.avalon.java.j120.labs.calculator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
TODO: Приложение должно позволять пользователю вводить число с использованием кнопок клавиатуры.
TODO: Окно приложения не поддерживает разворачивание на весь экран,
      НО поддерживает изменение размеров в заданном диапазоне (min-max).
*/

public class CalcWindow extends JFrame {

    public static CalcWindow window = null; // ссылка на окно
    private static final int WIDTH = 300; // ширина окна
    private static final int HEIGHT = 440; // высота окна
//    private static final int MAX_WIDTH = 340; // максимальная ширина. НЕ испольуется
//    private static final int MAX_HEIGHT = 500; // максимальная высота. НЕ испольуется
    public static JLabel display = null; // "дисплей" калькулятора
    private static JPanel buttonPanel = null; // панель со всеми кнопками
    private static JPanel resultPanel = null; // панель с кнопкой =
    public static double result = 0; // результат вычислений
    public static String lastCommand = "="; // последняя арифметическая операция
    // Логическое значение старта новых вычислений. Становится true каждый раз, когда
    // обновляется lastCommand (набирается новая арифметическая операция) или сброс:
    public static boolean start = true; 
    
    /** Приватный конструктор реализует паттерн проектирования Singleton:
     * https://refactoring.guru/ru/design-patterns/singleton/java/example
     * Экземпляр класса создается в статическом методе getInstance().
    */
    private CalcWindow() {
        window = this;
        setTitle("Calculator");
        this.setSize(new Dimension (WIDTH, HEIGHT)); // установить размер
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
//        setMaximumSize(new Dimension(MAX_WIDTH, MAX_HEIGHT)); // НЕ используется <- баг в Swing
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // скрыть окно при закрытии
        setResizable(false); // размер окна не изменяется
        setLocationRelativeTo(null); // начальное положение окна по центру экрана

        display = new JLabel("0", JLabel.RIGHT); // "дисплей" пуст, текстовая метка справа
        display.setPreferredSize(new Dimension(this.getWidth(), 70)); // ширина окна на 70 пкс.
        display.setFont(new Font("Arial", Font.BOLD, 36));
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10)); // границы 10, кроме нижней

        // Создаем панель с кнопками с раметкой 4x4,
        // 10 и 10 - промежутки по горизонтали и вертикали:
        buttonPanel = new JPanel(new GridLayout(4, 4, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10)); // границы 10, кроме нижней

        ActionListener clickOperand = new ClickOperand(); // Создаем ActionListener нажатия кнопки с цифрами и .
        ActionListener clickCommand = new ClickCommand(); // Создаем ActionListener нажатия кнопки с операциями.
        
        // В методе addButton создаем кнопки, назначаем слушателя,
        // назначаем actionCommand и размещаем на buttonPanel
        addButton("7", clickOperand);
        addButton("8", clickOperand);
        addButton("9", clickOperand);
        addButton("+", clickCommand);

        addButton("4", clickOperand);
        addButton("5", clickOperand);
        addButton("6", clickOperand);
        addButton("-", clickCommand);

        addButton("1", clickOperand);
        addButton("2", clickOperand);
        addButton("3", clickOperand);
        addButton("*", clickCommand);

        addButton("CE", clickCommand);
        addButton("0", clickOperand);
        addButton(".", clickOperand);
        addButton("/", clickCommand);

        // Отдельно создаем кнопку =, назначаем слушателя,
        // назначаем actionCommand, чтобы не добавлять на buttonPanel:
        JButton resultButton = new JButton("=");
        resultButton.setFont(new Font("Arial", Font.PLAIN, 20));
        resultButton.addActionListener(clickCommand);
        resultButton.setActionCommand(resultButton.getText());
        resultButton.setPreferredSize(new Dimension(this.getWidth(), 70)); // ширина окна на 70 пкс.
        resultPanel = new JPanel(); // создаем отдельную пенель под кнопку =
        resultPanel.setBorder(BorderFactory.createEmptyBorder(7, 7, 7, 7)); // границы по 7
        resultPanel.add(resultButton); // размещаем кнопку на resultPanel

        setLayout(new BorderLayout()); // задаем LayoutManager
        // Размещаем элементы в окне:
        add(display, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(resultPanel, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }
    
    /**
     * Создает кнопку, назначает слушателя, actionCommand, 
     * задает мнемонический символ (Alt + символ с клавиатуры = нажатие кнопки)
     * и размещает на buttonPanel
     * @param label - название кнопки = actionCommand
     * @param listener ActionListener
     */
    private void addButton(String label, ActionListener listener) {
        JButton button = new JButton(label);
        button.setFont(new Font("Arial", Font.PLAIN, 20));
        button.addActionListener(listener);
        button.setActionCommand(label);
        button.setMnemonic(label.charAt(0));
        buttonPanel.add(button);
    }
    
    /**
     * @return экземпляр CalcWindow
     */
    public static CalcWindow getInstance() {
        return new CalcWindow();
    }
}
