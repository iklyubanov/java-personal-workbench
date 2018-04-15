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
 
 *The thread-local class also provides the remove() method that deletes the value stored in a
  thread-local variable for the thread that it's calling.
  The Java Concurrency API includes the InheritableThreadLocal class that provides
  inheritance of values for threads created from a thread. If thread A has a value in a thread-
  local variable and it creates another thread B , then thread B will have the same value as
  thread A in the thread-local variable. You can override the childValue() method that is
  called to initialize the value of the child thread in the thread-local variable. It receives the
  value of the parent thread as a parameter in the thread-local variable.*
  
   ### Grouping threads and processing uncontrolled exceptions in a group of threads(Recipe 9)
   
   Java provides the ThreadGroup class to work with a groups of threads. A ThreadGroup
   object can be formed by thread objects and another ThreadGroup object, generating a tree
   structure of threads.
   
   ThreadGroup have uncaughtException method which might be overriden for specific handling of 
   uncaught exceptions in inner threads of the thread group
   
   ### Creating threads through a factory (Recipe 10)
   
   The factory pattern is one of the most used design patterns in the object-oriented
   programming world. It is a creational pattern, and its objective is to develop an object
   whose mission should be this: creating other objects of one or several classes. With this, if
   you want to create an object of one of these classes, you could just use the factory instead of
   using a new operator.
   With this factory, we centralize the creation of objects with some advantages:
   * It's easy to change the class of the objects created or the way you'd create them.
   * It's easy to limit the creation of objects for limited resources; for example, we can
   * only have n objects of a given type.
   * It's easy to generate statistical data about the creation of objects.
   Java provides an interface, the ThreadFactory interface, to implement a thread object
   factory. Some advanced utilities of the Java concurrency API use thread factories to create
   threads.
   
   ## Basic Thread Synchronization (Chapter 2)
   
   One of the most common situations in concurrent programming occurs when more than
   one execution thread shares a resource. In a concurrent application, it is normal for multiple
   threads to read or write the same data structure or have access to the same file or database
   connection. These shared resources can provoke error situations or data inconsistency, and
   we have to implement mechanisms to avoid these errors. These situations are called race
   conditions and they occur when different threads have access to the same shared resource
   at the same time. Therefore, the final result depends on the order of the execution of
   threads, and most of the time, it is incorrect. You can also have problems with change
   visibility. So if a thread changes the value of a shared variable, the changes would only be
   written in the local cache of that thread; other threads will not have access to the change.
   The solution for these problems lies in the concept of critical section. A critical section is a
   block of code that accesses a shared resource and can't be executed by more than one thread
   at the same time.
    Java (and almost all programming languages) offers synchronization mechanisms. When a thread wants access to a critical
   section, it uses one of these synchronization mechanisms to find out whether there is any
   other thread executing the critical section. If not, the thread enters the critical section. If yes,
   the thread is suspended by the synchronization mechanism until the thread that is currently
   executing the critical section ends it. When more than one thread is waiting for a thread to
   finish the execution of a critical section, JVM chooses one of them and the rest wait for their
   turn. This chapter presents a number of recipes that will teach you how to use the two basic
   synchronization mechanisms offered by the Java language:
   * The synchronized keyword
   * The Lock interface and its implementations
   
   ### Synchronizing a method (Recipe 11)
   
   When you use the synchronized keyword with a method, the object reference is implicit.
   When you use the synchronized keyword in one or more methods of an object, only one
   execution thread will have access to all these methods. If another thread tries to access any
   method declared with the synchronized keyword of the same object, it will be suspended
   until the first thread finishes the execution of the method. In other words, every method
   declared with the synchronized keyword is a critical section, and Java only allows the
   execution of one of the critical sections of an object at a time. In this case, the object
   reference used is the own object, represented by the this keyword. Static methods have a
   different behavior. Only one execution thread will have access to one of the static methods
   declared with the synchronized keyword, but a different thread can access other non-
   static methods of an object of that class. You have to be very careful with this point because
   two threads can access two different synchronized methods if one is static and the other is
   not. If both methods change the same data, you can have data inconsistency errors. In this
   case, the object reference used is the class object.
   When you use the synchronized keyword to protect a block of code, you must pass an
   object reference as a parameter. Normally, you will use the this keyword to reference the
   object that executes the method, but you can use other object references as well. Normally,
   these objects will be created exclusively for this purpose. You should keep the objects used
   for synchronization private. For example, if you have two independent attributes in a class
   shared by multiple threads, you must synchronize access to each variable; however, it
   wouldn't be a problem if one thread is accessing one of the attributes and the other
   accessing a different attribute at the same time. Take into account that if you use the own
   object (represented by the this keyword), you might interfere with other synchronized
   code (as mentioned before, the this object is used to synchronize the methods marked with
   the synchronized keyword).
   
   * The synchronized keyword penalizes the performance of the application, so you must
     only use it on methods that modify shared data in a concurrent environment. If you have
     multiple threads calling a synchronized method, only one will execute them at a time
     while the others will remain waiting. If the operation doesn't use the synchronized
     keyword, all the threads can execute the operation at the same time, reducing the total
     execution time. If you know that a method will not be called by more than one thread, don't
     use the synchronized keyword. Anyway, if the class is designed for multithreading
     access, it should always be correct. You must promote correctness over performance. Also,
     you should include documentation in methods and classes in relation to their thread safety.
     You can use recursive calls with synchronized methods. As the thread has access to the
     synchronized methods of an object, you can call other synchronized methods of that
     object, including the method that is being executed. It won't have to get access to the
     synchronized methods again.
      We can use the synchronized keyword to protect access to a block of code instead of an
      entire method. We should use the synchronized keyword in this way to protect access to
      shared data, leaving the rest of the operations out of this block and obtaining better
      performance of the application. The objective is to have the critical section (the block of code
      that can be accessed only by one thread at a time) as short as possible. Also, avoid calling
      blocking operations (for example, I/O operations) inside a critical section. We have used the
      synchronized keyword to protect access to the instruction that updates the number of
      persons in the building, leaving out the long operations of the block that don't use shared
      data. When you use the synchronized keyword in this way, you must pass an object
      reference as a parameter. Only one thread can access the synchronized code (blocks or
      methods) of this object.*
      
      ### Using conditions in synchronized code (Recipe 12)
      
      Java provides the wait() , notify() , and notifyAll()
      methods implemented in the Object class. A thread can call the wait() method inside a
      synchronized block of code. If it calls the wait() method outside a synchronized block
      of code, JVM throws an IllegalMonitorStateException exception. When the thread
      calls the wait() method, JVM puts the thread to sleep and releases the object that controls
      the synchronized block of code that it's executing and allows other threads to execute
      other blocks of synchronized code protected by this object. To wake up the thread, you
      must call the notify() or notifyAll() methods inside a block of code protected by the
      same object.
      
      ### Synchronizing a block of code with a lock (Recipe 13)
      
      Java provides another mechanism for synchronizing blocks of code. It's a more powerful
      and flexible mechanism than the _synchronized_ keyword. It's based on the _Lock_ (of the
      java.util.concurrent.locks package) interface and classes that implement it (as
      ReentrantLock ). This mechanism presents some advantages, which are as follows:
      
      * It allows you to structure synchronized blocks in a more flexible way. With the
      synchronized keyword, you only have control over a synchronized block of
      code in a structured way. However, the Lock interface allows you to get more
      complex structures to implement your critical section.
      * The Lock interface provides additional functionalities over the synchronized
      keyword. One of the new functionalities is implemented by the tryLock()
      method. This method tries to get control of the lock, and if it can't, because it's
      used by another thread, it returns false . With the synchronized keyword, if
      thread (A) tries to execute a synchronized block of code when thread (B) is
      executing it, thread (A) is suspended until thread (B) finishes the execution of the
      synchronized block. With lock, you can execute the tryLock() method. This
      method returns a Boolean value indicating whether there is another thread
      running the code protected by this lock.
      * The ReadWriteLock interface allows a separation of read and write operations
      with multiple readers and only one modifier.
      * The Lock interface offers better performance than the synchronized keyword.
      
      The constructor of the ReentrantLock class admits a boolean parameter named fair ;
      this parameter allows you to control its behavior. The false value is the default value and
      it's called the non-fair mode. In this mode, if some threads are waiting for a lock and the
      lock has to select one of these threads to get access to the critical section, it randomly selects
      anyone of them. The true value is called the fair mode. In this mode, if some threads are
      waiting for a lock and the lock has to select one to get access to a critical section, it selects
      the thread that has been waiting for the longest period of time. Take into account that the
      behavior explained previously is only used in the lock() and unlock() methods. As the
      tryLock() method doesn't put the thread to sleep if the Lock interface is used, the fair
      attribute doesn't affect its functionality.
      
      The Lock interface (and the ReentrantLock class) includes another method to get control
      of the lock. It's the tryLock() method. The biggest difference with the lock() method is
      that this method, if the thread that uses it can't get control of the Lock interface, returns
      immediately and doesn't put the thread to sleep. It returns the boolean value true if the
      thread gets control of the lock and false if not. You can also pass a time value and a
      TimeUnit object to indicate the maximum amount of time the thread will wait to get the
      lock. If the time elapses and the thread doesn't get the lock, the method will return the false
      value. The TimeUnit class is an enumeration with the following constants: DAYS , HOURS ,
      MICROSECONDS , MILLISECONDS , MINUTES , NANOSECONDS , and SECONDS ; these indicate the
      units of time we pass to a method.
      The ReentrantLock class also allows the use of recursive calls. When a thread has control
      of a lock and makes a recursive call, it continues with the control of the lock, so the calling
      to the lock() method will return immediately and the thread will continue with the
      execution of the recursive call. Moreover, we can also call other methods. You should call
      the unlock() method as many times as you called the lock() method in your code.
      
      ### Synchronizing data access with read/write locks (Recipe 14)
      
      One of the most significant improvements offered by locks is the ReadWriteLock interface
      and the ReentrantReadWriteLock class, the unique class that implements that interface.
      This class has two locks: one for read operations and one for write operations. There can be
      more than one thread using read operations simultaneously, but only one thread can use
      write operations. If a thread is doing a write operation, other threads can't write or read.
        The lock used in read operations is obtained with the readLock() method declared in the ReadWriteLock interface. 
      This lock is an object that implements the Lock interface, so we can use the lock() , unlock() , and tryLock()
      methods. The lock used in write operations is obtained with the writeLock() method
      declared in the ReadWriteLock interface. This lock is also an object that implements the
      Lock interface, so we can use the lock() , unlock() , and tryLock() methods. It is the
      responsibility of the programmer to ensure correct use of these locks, using them for the
      same purposes for which they were designed. When you get the read lock of a Lock
      interface, you can't modify the value of the variable. Otherwise, you probably will have
      data errors related to inconsistency.