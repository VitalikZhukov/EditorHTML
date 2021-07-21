package com.javarush.task.task32.task3209;

import javax.swing.*;
import java.awt.event.ActionListener;

// вспомогательный класс для инициализации и настройки меню
public class MenuHelper {

    // parent - меню в которое мы добавляем пункт
    // text - текст добавляемого пункта
    // actionListener - слушатель действий добавляемого пункта меню
    public static JMenuItem addMenuItem(JMenu parent, String text, ActionListener actionListener) {
        JMenuItem menuItem = new JMenuItem(text); // great new item menu
        menuItem.addActionListener(actionListener); // add listener
        parent.add(menuItem); // add item to menu
        return menuItem;
    }

    // action - действие, которое необходимо выполнить при выборе пункта меню
    public static JMenuItem addMenuItem(JMenu parent, Action action) {
        JMenuItem menuItem = new JMenuItem(action); // great new item menu
        menuItem.addActionListener(action); // add listener
        parent.add(menuItem); // add item to menu
        return menuItem;
    }

    // parent - меню в которое мы добавляем пункт
    // text - текст добавляемого пункта
    // action - действие, которое необходимо выполнить при выборе пункта меню
    public static JMenuItem addMenuItem(JMenu parent, String text, Action action) {
        JMenuItem menuItem = addMenuItem(parent, action);
        menuItem.setText(text);
        return menuItem;
    }

    // инициализация меню помощи
    public static void initHelpMenu(View view, JMenuBar menuBar) {}

    // инициализация меню выбора шрифта
    public static void initFontMenu(View view, JMenuBar menuBar) {}

    // инициализация меню выбора цвета
    public static void initColorMenu(View view, JMenuBar menuBar) {}

    //инициализация меню выравнивания
    public static void initAlignMenu(View view, JMenuBar menuBar) {}

    //инициализация меню выбора стиля текста
    public static void initStyleMenu(View view, JMenuBar menuBar) {}

    //инициализация меню редактирования текста
    public static void initEditMenu(View view, JMenuBar menuBar) {}

    //инициализация меню Файл
    public static void initFileMenu(View view, JMenuBar menuBar) {}

}



    /*У меню будет следующая структура:
        - Файл
        - Новый
        - Открыть
        - Сохранить
        - Сохранить как...
        - Выход

        - Редактировать
        - Отменить
        - Вернуть
        - Вырезать
        - Копировать
        - Вставить

        - Стиль
        - Полужирный
        - Подчеркнутый
        - Курсив
        - Подстрочный знак
        - Надстрочный знак
        - Зачеркнутый

        - Выравнивание
        - По левому краю
        - По центру
        - По правому краю

        - Цвет
        - Красный
        - Оранжевый
        - Желтый
        - Зеленый
        - Синий
        - Голубой
        - Пурпурный
        - Черный

        - Шрифт
        - Шрифт
        - SansSerif, Serif, Monospaced, Dialog, DialogInput,
        - Размер шрифта
        - 6, 8, 10, 12, 14, 16, 20, 24, 32, 36, 48, 72

        - Помощь
        - О программе*/
