package ru.avalon.java.j120.labs.colorpicker;

import java.awt.*;
import javax.swing.*;
import ru.avalon.java.j120.labs.utils.GBHelper;

class ToolsPanel extends JPanel{
    
    private static final int GAP = 10;  // Граница окна в пикселях + расстояние между компонентами
    
    public ToolsPanel() {
        // Задать цвет фона панели с инструментами:
        setBackground(new Color (235, 235, 255));
        
        // Создаем слайдер Red:
        JSlider sliderRed = new JSlider(0, 255, 125); // начальное, конечное и текущее значение слайдра
        sliderRed.setMajorTickSpacing(255); // задать главные деления на слайдере (0 и 255)
        sliderRed.setMinorTickSpacing(15); // задать промежуточные деления на слайдере
        sliderRed.setPaintTicks(true); // отображать деления на слайдере (0 и 255)
        sliderRed.setPaintLabels(true); // отображать подписи к главным делениям на слайдере
        sliderRed.setName("Red"); // чтобы привязать stateChanged()
        sliderRed.addChangeListener(ColorPickerWindow.window); // т.к. обработка события в Window
        
        // Создаем слайдер Green:
        JSlider sliderGreen = new JSlider(0, 255, 125);
        sliderGreen.setMajorTickSpacing(255);
        sliderGreen.setMinorTickSpacing(15);
        sliderGreen.setPaintTicks(true);
        sliderGreen.setPaintLabels(true);
        sliderGreen.setName("Green");
        sliderGreen.addChangeListener(ColorPickerWindow.window);
        
        // Создаем слайдер Blue:
        JSlider sliderBlue = new JSlider(0, 255, 125);
        sliderBlue.setMajorTickSpacing(255);
        sliderBlue.setMinorTickSpacing(15); 
        sliderBlue.setPaintTicks(true);
        sliderBlue.setPaintLabels(true);
        sliderBlue.setName("Blue");
        sliderBlue.addChangeListener(ColorPickerWindow.window);
        
        // Создаем метки-подписи для слайдеров:
        JLabel labelRed = new JLabel("Red:");
        JLabel labelGreen = new JLabel("Green:");
        JLabel labelBlue = new JLabel("Blue:");
        
        // Установка внешних полей для панели:
        setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP)); 
        // Компоновка элементов на панели. Установка менеджера компоновки:
        setLayout(new GridBagLayout());
        // Создаем объект-помощник настройки GridBagConstraints:
        GBHelper position = new GBHelper();
        
        // Создаем панель-заглушку 10 на 10 пикселей для создания 
        // промежутков между метками и слайдерами:
        JComponent gap = new JPanel();
        gap.setPreferredSize(new Dimension(GAP, GAP));
        
        // Первая строка:
        add(labelRed, position);
        // Ставим разделитель в след.колонку:
        add(gap, position.nextCol()); // gap
        // Добавляем слайдер в след.колонку, делаем его расширяемым
        // при изменении размера окна:
        add(sliderRed, position.nextCol().expandW());
        // Добавляем  разделитель в следующую строку:
        add(gap, position.nextRow()); // gap1
        
        // Вторая строка:
        add(labelGreen, position.nextRow());
        add(gap, position.nextCol()); // gap2
        add(sliderGreen, position.nextCol().expandW());
        add(gap, position.nextRow()); // gap3
        
        // Третья строка:
        add(labelBlue, position.nextRow());
        add(gap, position.nextCol()); // gap4
        add(sliderBlue, position.nextCol().expandW());
        
        /*
        Итого, как это выглядит:
        ------------------------------------
        | labelRed   | gap |   sladerRed   |
        ------------------------------------
        | gap1                             |
        ------------------------------------
        | labelGreen | gap2|   sladerGreen |
        ------------------------------------
        | gap3                             |
        ------------------------------------
        | labelBlue  | gap4|   sladerBlue  |
        ------------------------------------
        */
        
    }
    
}
