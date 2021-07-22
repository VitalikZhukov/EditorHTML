package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.FrameListener;
import com.javarush.task.task32.task3209.listeners.TabbedPaneChangeListener;
import com.javarush.task.task32.task3209.listeners.TextEditMenuListener;

import javax.swing.*;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener {
    private Controller controller;
    private JTabbedPane tabbedPane = new JTabbedPane(); //панель (с двумя вкладками)
    private JTextPane htmlTextPane = new JTextPane(); //вкладка для визуального редактирования html
    private JEditorPane plainTextPane = new JEditorPane(); //вкладка для редактирования html (код, теги, содержимое)

    public View() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            ExceptionHandler.log(e);
        } catch (InstantiationException e) {
            ExceptionHandler.log(e);
        } catch (IllegalAccessException e) {
            ExceptionHandler.log(e);
        } catch (UnsupportedLookAndFeelException e) {
            ExceptionHandler.log(e);
        }
    }

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
    public void initMenuBar() {
        JMenuBar menuBar = new JMenuBar(); //панель меню
        MenuHelper.initFileMenu(this, menuBar);
        MenuHelper.initEditMenu(this, menuBar);
        MenuHelper.initStyleMenu(this, menuBar);
        MenuHelper.initAlignMenu(this, menuBar);
        MenuHelper.initColorMenu(this, menuBar);
        MenuHelper.initFontMenu(this, menuBar);
        MenuHelper.initHelpMenu(this, menuBar);
        //добавляем в верхнюю часть панели контента панель меню
        this.getContentPane().add(menuBar, BorderLayout.NORTH);
    }

    //отвечает за инициализацию панели редактора
    public void initEditor() {
        htmlTextPane.setContentType("text/html"); //устанавливаем тип контента, который обрабатывает редактор
        JScrollPane htmlScrollPane = new JScrollPane(htmlTextPane);
        tabbedPane.add("HTML", htmlScrollPane); //добавляем вкладку в панель
        JScrollPane plainScrollPane = new JScrollPane(plainTextPane);
        tabbedPane.add("Текст", plainScrollPane); //добавляем вкладку в панель
        tabbedPane.setPreferredSize(new Dimension(300, 350)); //устанавливаем размер панели
        //добавляем панель вкладок в панель контента
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
