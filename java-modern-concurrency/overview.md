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
 
 ### Controlling the interruption of a thread (Recipe 3)
 
 If the thread implements a complex algorithm divided into some methods or it has methods
 with recursive calls, we will need to use a better mechanism to control the interruption of
 the thread. Java provides the InterruptedException exception for this purpose. You can
 throw this exception when you detect the interruption of a thread and catch it in the run()
 method.
 
 ### Sleeping and resuming a thread (Recipe 4)
 
 Sometimes, you may be interested in pausing the execution of a thread during a determined
 period of time. For example, a thread in a program checks the sensor state once per minute.
 The rest of the time, it does nothing. During this time, the thread doesn't use any resources
 of the computer. After this period is over, the thread will be ready to continue with its
 execution when the operating system scheduler chooses it to be executed. You can use the
 sleep() method of the Thread class for this purpose. This method receives a long number
 as a parameter that indicates the number of milliseconds during which the thread will
 suspend its execution. After that time, the thread continues with its execution in the next
 instruction to the sleep() one when the JVM assigns it CPU time.
 Another possibility is to use the sleep() method of an element of the TimeUnit
 enumeration. This method uses the sleep() method of the Thread class to put the current
 thread to sleep, but it receives the parameter in the unit that it represents and converts it
 into milliseconds.
 
 *The Java concurrency API has another method that makes a thread object leave the CPU. It's
  the yield() method, which indicates to the JVM that the thread object can leave the CPU
  for other tasks. The JVM does not guarantee that it will comply with this request. Normally,
  it's only used for debugging purposes.*
  
  ### Waiting for the finalization of a thread (Recipe 5)
  
  In some situations, we will have to wait for the end of the execution of a thread. For example, we may have a program that will begin
  initializing the resources it needs before proceeding with the rest of the execution. We can
  run initialization tasks as threads and wait for their finalization before continuing with the
  rest of the program.
  For this purpose, we can use the join() method of the Thread class. When we call this
  method using a thread object, it suspends the execution of the calling thread until the object
  that is called finishes its execution.
  
  *Java provides two additional forms of the join() method:
   join (long milliseconds)
   join (long milliseconds, long nanos)
   In the first version of the join() method, instead of indefinitely waiting for the finalization
   of the thread called, the calling thread waits for the milliseconds specified as the parameter
   of the method.*
   
 ### Creating and running a daemon thread (Recipe 6)
 
 Java has a special kind of thread called daemon thread. When daemon threads are the only
 threads running in a program, the JVM ends the program after finishing these threads.
 With these characteristics, daemon threads are normally used as service providers for
 normal (also called user) threads running in the same program. They usually have an
 infinite loop that waits for the service request or performs the tasks of a thread. A typical
 example of these kinds of threads is the Java garbage collector.
 
 *You only can call the setDaemon() method before you call the start() method. Once the
  thread is running, you can't modify its daemon status calling the setDaemon() method. If
  you call it, you will get an IllegalThreadStateException exception.
  You can use the isDaemon() method to check whether a thread is a daemon thread (the
  method returns true ) or a non-daemon thread (the method returns false ).*
  
  ### Processing uncontrolled exceptions in a thread (Recipe 7)
 
 Java provides a mechanism to capture and process exceptions. There are
 exceptions that must be captured or re-thrown using the throws clause of a method. These
 exceptions are called checked exceptions. There are exceptions that don't have to be
 specified or caught. These are unchecked exceptions:
 * Checked exceptions: These must be specified in the throws clause of a method
 or caught inside them, for example, IOException or
 ClassNotFoundException .
 * Unchecked exceptions: These don't need to be specified or caught, for example,
 NumberFormatException . 
 When a checked exception is thrown inside the run() method of a thread object, we have to
 catch and treat them because the run() method doesn't accept a throws clause. When an
 unchecked exception is thrown inside the run() method of a thread object, the default
 behavior is to write the stack trace in the console and exit the program.
 Fortunately, Java provides us with a mechanism to catch and treat unchecked exceptions
 thrown in a thread object to avoid ending the program.
 
 *The Thread class has another method related to the process of uncaught exceptions. It's the
 static method setDefaultUncaughtExceptionHandler() that establishes an exception
 handler for all the thread objects in the application.
 When an uncaught exception is thrown in the thread, the JVM looks for three possible
 handlers for this exception.
 First it looks for the uncaught exception handler of the thread objects. 
 If this handler doesn't exist, the JVM looks for the uncaught exception handler of
 ThreadGroup. If this method doesn't exist, the JVM looks for the default uncaught
 exception handler. If none of the handlers exits, the JVM writes the stack trace of the exception in the console
 and ends the execution of the Thread that had thrown the exception.*
 
 ### Using thread local variables (Recipe 8)
 
 Sometimes, you will be interested in having an attribute that won't be shared among all the
 threads that run the same object. The Java Concurrency API provides a clean mechanism
 called thread-local variables with very good performance. They have some disadvantages
 as well. They retain their value while the thread is alive. This can be problematic in
 situations where threads are reused.