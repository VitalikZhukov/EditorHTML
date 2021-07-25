package com.javarush.task.task32.task3209;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

public class Controller {
    private View view; //визуализация
    private HTMLDocument document; //модель документа
    private File currentFile; //файл, который сейчас открыт в нашем редакторе (текущий файл)

    public Controller(View view) {
        this.view = view;
    }

    public HTMLDocument getDocument() {
        return document;
    }

    //сбрасывает текущий документ
    public void resetDocument() {
        if (document != null) {
            document.removeUndoableEditListener(view.getUndoListener());
        }
        document = (HTMLDocument) new HTMLEditorKit().createDefaultDocument();
        document.addUndoableEditListener(view.getUndoListener());
        view.update();
    }

    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init();
        controller.init();
    }

    //записывает переданный text с html в document
    public void setPlainText(String text) {
        resetDocument();
        StringReader reader = new StringReader(text);
        HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
        try {
            htmlEditorKit.read(reader, document, 0);
        } catch (IOException | BadLocationException e) {
            ExceptionHandler.log(e);
        }
    }

    //
    public String getPlainText() {
        StringWriter writer = new StringWriter();
        HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
        try {
            htmlEditorKit.write(writer, document, 0, document.getLength());
        } catch (IOException | BadLocationException e) {
            ExceptionHandler.log(e);
        }
        return writer.toString();
    }

    //отвечает за инициализацию view
    public void init() {
        this.createNewDocument();
    }

    public void createNewDocument() {
        view.selectHtmlTab(); //выбираем вкладку html
        this.resetDocument(); //сброс текущего документа
        view.setTitle("HTML редактор");
        currentFile = null;
    }

    public void openDocument() {
        view.selectHtmlTab(); //выбираем вкладку html
        JFileChooser fileChooser = new JFileChooser(); //выбор документа
        fileChooser.setFileFilter(new HTMLFileFilter()); //установить фильтр на выбор документа
        int choose = fileChooser.showOpenDialog(view);
        if (choose == JFileChooser.OPEN_DIALOG) { // если открут документ
            currentFile = fileChooser.getSelectedFile(); //выбираем файл
            this.resetDocument();
            view.setTitle(currentFile.getName());
            // считываем файл
            try(FileReader reader = new FileReader(currentFile)) {
                HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
                htmlEditorKit.read(reader, document, 0);
                view.resetUndo();
            } catch (IOException | BadLocationException e) {
                ExceptionHandler.log(e);
            }
        }
    }

    public void saveDocument() {
        view.selectHtmlTab();
        if (currentFile != null) {
            try (FileWriter writer = new FileWriter(currentFile)) {
                HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
                htmlEditorKit.write(writer, document, 0, document.getLength());
            } catch (IOException | BadLocationException e) {
                ExceptionHandler.log(e);
            }
        } else {
            saveDocumentAs();
        }
    }

    public void saveDocumentAs() {
        view.selectHtmlTab();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new HTMLFileFilter());
        int choose = fileChooser.showSaveDialog(view);
        if (choose == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            view.setTitle(currentFile.getName());
            try (FileWriter writer = new FileWriter(currentFile)){
                HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
                htmlEditorKit.write(writer, document, 0, document.getLength());

            } catch (IOException | BadLocationException e) {
                ExceptionHandler.log(e);
            }
        }
    }

    public void exit() {
        System.exit(0);
    }
}
