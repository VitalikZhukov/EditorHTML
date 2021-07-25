package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.FrameListener;
import com.javarush.task.task32.task3209.listeners.TabbedPaneChangeListener;
import com.javarush.task.task32.task3209.listeners.TextEditMenuListener;
import com.javarush.task.task32.task3209.listeners.UndoListener;

import javax.swing.*;
import javax.swing.event.MenuListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener {
    private Controller controller;
    private JTabbedPane tabbedPane = new JTabbedPane(); //панель (с двумя вкладками)
    private JTextPane htmlTextPane = new JTextPane(); //вкладка для визуального редактирования html
    private JEditorPane plainTextPane = new JEditorPane(); //вкладка для редактирования html (код, теги, содержимое)
    private UndoManager undoManager = new UndoManager();
    private UndoListener undoListener = new UndoListener(undoManager);

    public View() throws HeadlessException {
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

    public UndoListener getUndoListener() {
        return undoListener;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    //будет вызваться при выборе пунктов меню, у которых наше представление указано в виде слушателя событий
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand(); //пункт меню, созданный событием
        switch (command) {
            case("Новый"):
                controller.createNewDocument();
                break;
            case("Открыть"):
                controller.openDocument();
                break;
            case("Сохранить"):
                controller.saveDocument();
                break;
            case("Сохранить как..."):
                controller.saveDocumentAs();
                break;
            case("Выход"):
                controller.exit();
                break;
            case("О программе"):
                this.showAbout();
                break;
        }

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

    //вызывается, когда произошла смена выбранной вкладки, устанавливает текст выбранной вкладки
    public void selectedTabChanged() {
        if (tabbedPane.getSelectedIndex() == 0) {
            String text = plainTextPane.getText();
            controller.setPlainText(text);
        } else if (tabbedPane.getSelectedIndex() == 1) {
            String text = controller.getPlainText();
            plainTextPane.setText(text);
        }
        this.resetUndo();
    }

    //можем ли отменить действие?
    public boolean canUndo() {
        return undoManager.canUndo();
    }

    //можем ли сделать возврат?
    public boolean canRedo() {
        return undoManager.canRedo();
    }

    //отмена
    public void undo() {
        try {
            undoManager.undo();
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    //возврат
    public void redo() {
        try {
            undoManager.redo();
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    //сброс отмены
    public void resetUndo() {
        undoManager.discardAllEdits();
    }

    //проверяет, какая вкладка выбрана
    public boolean isHtmlTabSelected() {
        return tabbedPane.getSelectedIndex() == 0;
    }

    //выбирает вкладку html и сбрасывает все правки
    public void selectHtmlTab() {
        tabbedPane.setSelectedIndex(0);
        resetUndo();
    }

    //получает document(Controller) и устанавливает его в панели html
    public void update() {
        HTMLDocument document = controller.getDocument();
        htmlTextPane.setDocument(document);
    }

    //информация о программе
    public void showAbout() {
        String message = "HTML редактор с графическим интерфейсом.\n" +
                "В качестве библиотеки для создания графического интерфейса воспользуется Swing.\n" +
                "А в качестве архитектурного каркаса приложения используется MVC модель.\n";
        JOptionPane.showMessageDialog(tabbedPane.getSelectedComponent(), message,
                "О программе", JOptionPane.INFORMATION_MESSAGE);
    }
}
