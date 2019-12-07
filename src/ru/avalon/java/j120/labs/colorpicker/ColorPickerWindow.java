package ru.avalon.java.j120.labs.colorpicker;

import java.awt.*;
import java.awt.datatransfer.*;
import javax.swing.*;
import javax.swing.event.*;

public class ColorPickerWindow extends JFrame implements ChangeListener {

    public static ColorPickerWindow window = null;
    private static final int WIDTH = 400; // ширина окна
    private static final int HEIGHT = 200; // высота окна
    private static ColorBox colorBox = null;
    private static ToolsPanel toolsPanel = null;
    private static int colorRed = 125; // значение красного (может быть от 0 до 255)
    private static int colorGreen = 125; // значение зеленого (может быть от 0 до 255)
    private static int colorBlue = 125; // значение синего (может быть от 0 до 255)
    private static String hexColor = "#7D7D7D"; // значение цвета в шестнадцатеричном виде, 
                                                // задано значение по умолчанию для dark grey;
    private static Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard(); // буфер обмена

    /**
     * Приватный конструктор реализует паттерн проектирования Singleton:
     * https://refactoring.guru/ru/design-patterns/singleton/java/example
     * Экземпляр класса создается в статическом методе getInstance().
     */
    private ColorPickerWindow() {
        window = this;
        setTitle("Color picker");
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT)); // предпочтительный размер окна
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // по закрытию окна, оно скрывается, 
        // но программа не завершается.
        setLocationRelativeTo(null); // начальное положение окна по центру экрана

        colorBox = new ColorBox();
        toolsPanel = new ToolsPanel();

        setLayout(new GridLayout(1, 2)); // устанавливаем для окна менеджер компоновки сетка 1х2
        getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // отступы от края окна

        add(colorBox);
        add(toolsPanel);
        colorBox.setToolTipText(hexColor); // всплывающая подсказка при наведении на colorBox

        pack(); // упаковываем, чтобы все пометилось на экране
        setVisible(true); // делаем видимым (не обязательно для JPanel)

    }

    /**
     * @return экземпляр окна
     */
    public static ColorPickerWindow getInstance() {
        return new ColorPickerWindow();
    }

    /**
     * Описан в интрефейсе ChangeListener, поэтому ColorPickerWindow его
     * реализует. Отслеживает изменения состояния слайдеров
     */
    @Override
    public void stateChanged(ChangeEvent ce) {
        // Получаем ссылку на слайдер, который связан с событием:
        JSlider slider = (JSlider) ce.getSource();
        // Узнаем имя слайдера:
        String sliderName = slider.getName();
        // При изменении положения слайдера с именем sliderName 
        // в соответствующую переменную присваивается int значение цвета:
        switch (sliderName) {
            case ("Red"):
                colorRed = slider.getValue();
                break;
            case ("Green"):
                colorGreen = slider.getValue();
                break;
            case ("Blue"):
                colorBlue = slider.getValue();
                break;
        }
        Color color = new Color(colorRed, colorGreen, colorBlue);
        colorBox.setBackground(color); // задаем полученный цвет как фон colorBox
        hexColor = getHexColor(colorBox.getBackground()); // получаем шестнадцатеричный код цвета
        colorBox.setToolTipText(hexColor); // обновляем всплывающую подсказку
        copyToClipboard(hexColor); // копируем шестнадцатеричный код цвета в буфер обмена

    }

    /**
     * @param color текущий цвет
     * @return значение текущего цвета в шестнадцатиричной форме
     */
    public static String getHexColor(Color color) {
        String hexColor = "#" + Integer.toHexString(colorRed)
                + Integer.toHexString(colorGreen) + Integer.toHexString(colorBlue);
        return hexColor.toUpperCase();
    }

    /**
     * Копирует заданный текст в буфер обмена.
     *
     * @param text
     */
    private void copyToClipboard(String text) {
        StringSelection selection = new StringSelection(text);
        clipboard.setContents(selection, selection);
    }

}
