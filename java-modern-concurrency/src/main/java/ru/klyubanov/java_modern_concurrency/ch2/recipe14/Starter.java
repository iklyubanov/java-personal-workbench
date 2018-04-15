package ru.klyubanov.java_modern_concurrency.ch2.recipe14;

public class Starter {

    /**
     * While the writer has acquired the write lock, none of the reader tasks can read the data. You
     can see some messages of the reader tasks after the Write Lock Acquired message, but
     they are instructions that were executed before and not shown yet in the console. Once the
     writer task has released the lock, reader tasks gain access to the prices information again
     and show the new prices.
     * */
    public static void main(String[] args) {
        PricesInfo pricesInfo=new PricesInfo();

        Reader[] readers = new Reader[5];
        for (int i = 0; i < readers.length; i++) {
            readers[i] = new Reader(pricesInfo);
            readers[i].start();
        }
        Writer writer=new Writer(pricesInfo);
        writer.start();

    }
}
