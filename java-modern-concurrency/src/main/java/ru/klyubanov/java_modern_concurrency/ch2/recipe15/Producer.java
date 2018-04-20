package ru.klyubanov.java_modern_concurrency.ch2.recipe15;

public class Producer extends Thread {

    private FileMock mock;
    private Buffer buffer;

    public Producer (FileMock mock, Buffer buffer, String name){
        super(name);
        this.mock = mock;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        buffer.setPendingLines(true);
        while (mock.hasMoreLines()){
            String line = mock.getLine();
            buffer.insert(line);
        }
        buffer.setPendingLines(false);
    }
}
