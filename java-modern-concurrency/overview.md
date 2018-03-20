Module for studying the recipes of Java 9 Concurrency Cookbook

#Thread Management

Topics: 
 * Creating, running, and setting the characteristics of a thread
 * Interrupting a thread
 * Controlling the interruption of a thread
 * Sleeping and resuming a thread
 * Waiting for the finalization of a thread
 * Creating and running a daemon thread
 * Processing uncontrolled exceptions in a thread
 * Using thread local variables
 * Grouping threads and processing uncontrolled exceptions in a group of threads
 * Creating threads through a factory
 
 ### Task 1. Creating, running, and setting the characteristics of a thread
 
 in package _task1_
 
 The Thread class store some information in the attributes:
 ID: This attribute stores a unique identifier for each thread.
 Name: This attribute stores the name of the thread.
 Priority: This attribute stores the priority of the Thread objects. In Java 9, threads
 can have priority between 1 and 10, where 1 is the lowest priority and 10 is the
 highest. It's not recommended that you change the priority of the threads. It's
 only a hint to the underlying operating system and it doesn't guarantee anything,
 but it's a possibility that you can use if you want.
 Status: This attribute stores the status of a thread. In Java, a thread can be present
 in one of the six states defined in the Thread.State enumeration: NEW ,
 RUNNABLE , BLOCKED , WAITING , TIMED_WAITING , or TERMINATED . The following
 is a list specifying what each of these states means:
 NEW : The thread has been created and it has not yet started
 RUNNABLE : The thread is being executed in the JVM
 BLOCKED : The thread is blocked and it is waiting for a monitor
 WAITING : The thread is waiting for another thread
 TIMED_WAITING : The thread is waiting for another thread with a
 specified waiting time
 TERMINATED : The thread has finished its execution
 
 ## Interrupting a thread
 
 A Java program with more than one execution thread only finishes when the execution of
 all of its threads end (more specifically, when all its non-daemon threads end their
 execution or when one of the threads uses the System.exit() method).
 
 Java provides an interruption mechanism that indicates to a thread that you want to finish
 it. One peculiarity of this mechanism is that thread objects have to check whether they have
 been interrupted or not, and they can decide whether they respond to the finalization
 request or not. A thread object can ignore it and continue with its execution.
 
 The Thread class has another method to check whether a thread has been interrupted or
 not. It's the static method, interrupted() , that checks whether the current thread has been
 interrupted or not.
 
         There is an important difference between the isInterrupted() and
         interrupted() methods. The first one doesn't change the value of the
         interrupted attribute, but the second one sets it to false .
 
 ### Controlling the interruption of a thread
 
 If the thread implements a complex algorithm divided into some methods or it has methods
 with recursive calls, we will need to use a better mechanism to control the interruption of
 the thread. Java provides the InterruptedException exception for this purpose. You can
 throw this exception when you detect the interruption of a thread and catch it in the run()
 method.
 
 