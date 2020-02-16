package ru.avalon.java.j120.labs.utils;

import java.awt.*;

/**
 * Класс-помощник в работе с менеджером компоновки GridBagLayout.
 * 
 * Расширяет GridBagConstraints, которые задает правила размещения
 * компонента в контейнере. Конструктор задает GridBagConstraints для
 * самого первого (gridx = 0 - первый столбец, gridy = 0 - первая строка)
 * размещаемого компонента. Методы возвращают дубликат GridBagConstraints
 * для размещения других компонентов, не изменяя исходный объект GridBagConstraints.
 */
public class GBHelper extends GridBagConstraints {
    
    /**
     * Создает helper для левого верхнего компонента,
     * который всегда заполняет ячейки по горизонтали и вертикали.
     */
    public GBHelper() {
        gridx = 0; // 1 столбец
        gridy = 0; // 1 строка
        fill = GridBagConstraints.BOTH; // заполняет ячейку по обим измерениям
    }
    
    /**
     * Перемещает курсор на одну колонку вправо.
     * @return GBHelper
     */
    public GBHelper nextCol() {
        gridx++; // следующий столбец
        return this;
    }
    
    /**
     * Перемещает курсор на следующую строку.
     * @return GBHelper
     */
    public GBHelper nextRow() {
        gridx = 0; // первый столбец
        gridy++; // следующуя строка
        return this;
    }
    
    /** 
     * Расширяемая ширина.
     * @return новый GBHelper с возможностью горизонтального расширения, 
     * при этом не изменяя оригинал.
     */
    public GBHelper expandW() {
        GBHelper duplicate = (GBHelper)this.clone();
        /* задает горизонтальный "вес" компонента.
        По умолчанию 0. Чем больше вес, тем больше под компонент выделяется 
        дополнительного места из неиспользованного при изменении размера
        контейнера по горизонтали.
        */
        duplicate.weightx = 1.0; 
        return duplicate;
    }
    
    /** 
     * Расширяемая высота.
     * @return новый GBHelper с возможностью вертикального расширения, 
     * при этом не изменяя оригинал.
     * 
     * НЕ используется в приложении.
     */
    public GBHelper expandH() {
        GBHelper duplicate = (GBHelper)this.clone();
        /* задает вертикальный "вес" компонента.
        По умолчанию 0. Чем больше вес, тем больше под компонент выделяется 
        дополнительного места из неиспользованного при изменении размера
        контейнера по вертикали.
        */
        duplicate.weighty = 1.0;
        return duplicate;
    }
    
    /**
     * Задает ширину области в занимаемых столбцах.
     * @param colsWide количество столбцов.
     * @return GBHelper с заданным количеством столбцов.
     * 
     * НЕ используется в приложении.
     */
    public GBHelper width(int colsWide) {
        GBHelper duplicate = (GBHelper)this.clone();
        duplicate.gridwidth = colsWide;
        return duplicate;
    }
    
    /**
     * Устанавливается ширина для всех оставшихся компонентов.
     * 
     * НЕ используется в приложении.
     */
    public GBHelper width() {
        GBHelper duplicate = (GBHelper)this.clone();
        duplicate.gridwidth = REMAINDER;
        return duplicate;
    }
    
    /**
     * Устанавливается высота области в строках.
     * @param rowsHigh количество строк.
     * @return GBHelper с заданным количеством строк.
     * 
     * НЕ используется в приложении.
     */
    public GBHelper height(int rowsHigh) {
        GBHelper duplicate = (GBHelper)this.clone();
        duplicate.gridheight = rowsHigh;
        return duplicate;
    }
    
    /**
     * Устанавливается высота для всех оставшихся компонентов.
     * 
     * НЕ используется в приложении.
     */
    public GBHelper height() {
        GBHelper duplicate = (GBHelper)this.clone();
        duplicate.gridheight = REMAINDER;
        return duplicate;
    }
    
    /**
     * Устанавливает выравнивание компонента.
     * @param alignment - выражается константами
     *                    GridBagConstraints.NORTH и т.д.
     * @return GBHelper с заданным выравниванием.
     * 
     * НЕ используется в приложении.
     */
    public GBHelper align(int alignment) {
        GBHelper duplicate = (GBHelper)this.clone();
        duplicate.fill   = NONE;
        duplicate.anchor = alignment;
        return duplicate;
        
    }
}