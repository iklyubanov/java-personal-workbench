package ru.klyubanov.java_modern_concurrency.ch1.recipe3;

import lombok.AllArgsConstructor;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * , This thread starts going through folders by checking whether they have
 the file or not. For example, if you enter in the \b\c\d folder, the program will have three
 recursive calls to the directoryProcess() method. When it detects that it has been
 interrupted, it throws an InterruptedException exception and continues the execution
 in the run() method, no matter how many recursive calls have been made.
 * */
@AllArgsConstructor
public class FileSearch implements Runnable {
    private String initPath;
    private String fileName;

    @Override
    public void run() {

        File file = new File(initPath);
        if (file.isDirectory()) {
            try {
                directoryProcess(file);
            } catch (InterruptedException e) {
                System.out.printf("%s: The search has been interrupted",
                        Thread.currentThread().getName());
            }
        }
    }

    /**
     * This method will obtain the files
     and subfolders in a folder and process them. For each directory, the method will
     make a recursive call, passing the directory as a parameter. For each file, the
     method will call the fileProcess() method. After processing all files and
     folders, the method checks whether the thread has been interrupted; if yes, as in
     this case, it will throw an InterruptedException exception
     * */
    private void directoryProcess(File file) throws
            InterruptedException {
        File list[] = file.listFiles();
        if (list != null) {
            for (File aList : list) {
                if (aList.isDirectory()) {
                    directoryProcess(aList);
                } else {
                    fileProcess(aList);
                }
            }
        }
        if (Thread.interrupted()) {
            throw new InterruptedException("interrupted!");
        }
    }

    private void fileProcess(File file) throws
            InterruptedException {
        /*try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        if (file.getName().equals(fileName)) {
            System.out.printf("%s : %s\n",
                    Thread.currentThread().getName(),
                    file.getAbsolutePath());
        }
        if (Thread.interrupted()) {
            throw new InterruptedException("interrupted!");
        }
    }
}
