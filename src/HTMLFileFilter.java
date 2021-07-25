package com.javarush.task.task32.task3209;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class HTMLFileFilter extends FileFilter {
    //отображение в окне выбора файла только ".html" или ".htm" без учета регистра
    @Override
    public boolean accept(File f) {
        return f.isDirectory() || f.getName().toLowerCase().endsWith(".html") || f.getName().toLowerCase().endsWith(".htm");
    }

    //текст будет отображаться в описании доступных файлом
    @Override
    public String getDescription() {
        return "HTML и HTM файлы";
    }
}
