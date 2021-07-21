package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.FrameListener;
import com.javarush.task.task32.task3209.listeners.TabbedPaneChangeListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener {
    private Controller controller;
    private JTabbedPane tabbedPane = new JTabbedPane(); //панель (с двумя вкладками)
    private JTextPane htmlTextPane = new JTextPane(); //вкладка для визуального редактирования html
    private JEditorPane plainTextPane = new JEditorPane(); //вкладка для редактирования html (код, теги, содержимое)

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    //отвечает за инициализацию controller
    public void init() {
        initGui();
        FrameListener listener = new FrameListener(this);
        addWindowListener(listener);
        setVisible(true);
    }

    public void exit() {
        controller.exit();
    }

    //отвечает за инициализацию меню
    public void initMenuBar() {}

    //отвечает за инициализацию панели редактора
    public void initEditor() {
        htmlTextPane.setContentType("text/html"); //устанавливаем тип контента, который обрабатывает редактор
        JScrollPane htmlScrollPane = new JScrollPane(htmlTextPane);
        tabbedPane.add("HTML", htmlScrollPane); //добавляем вкладку в панель
        JScrollPane plainScrollPane = new JScrollPane(plainTextPane);
        tabbedPane.add("Текст", plainScrollPane); //добавляем вкладку в панель
        tabbedPane.setPreferredSize(new Dimension(40, 70)); //устанавливаем размер панели
        TabbedPaneChangeListener tabbedListener = new TabbedPaneChangeListener(this);
        tabbedPane.addChangeListener(tabbedListener);
        this.getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }

    //отвечает за инициализацию GUI
    public void initGui() {
        initMenuBar();
        initEditor();
        pack();
    }

    public void selectedTabChanged() {}
}
