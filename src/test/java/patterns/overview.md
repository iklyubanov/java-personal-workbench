# About Design Patterns

This Design Patterns refcard provides a quick reference to the original
23 Gang of Four design patterns, as listed in the book Design Patterns:
Elements of Reusable Object-Oriented Software. Each pattern includes
class diagrams, explanation, usage information, and a real world example.


   * **Creational Patterns**: Used to construct objects such that they can be decoupled from their implementing system.
   * **Structural Patterns**: Used to form large object structures between many disparate objects.
   * **Behavioral Patterns**: Used to manage algorithms, relationships, and responsibilities between objects.
   * **Object Scope**: Deals with object relationships that can be changed at runtime.
   * **Class Scope**: Deals with class relationships that can be changed at compile time.

---------------------------------------------------------------------------
| C  |	Abstract Factory   	| S 	|   Decorator 	|   C 	|   Prototype |
| --:|:--------------------|------|:--------------|:------|:-----------:|
| S  |	Adapter             |	S   |	Facade      |	S   |	Proxy     |
| S	 | Bridge               |	C 	| Factory Method| 	B 	| Observer    |
| C  | Builder |	S |	Flyweight |	C | 	Singleton |
|B 	| [Chain Of Responsibility](#chain-of-responsibility-object-behavioral) 	| B |	Interpreter 	| B |	State |
|B 	| [Command](#command-object-behavioral) 	| B |	Mediator |	B |	Template Method |
|S 	| Composite |	B |	Memento |	B |	Visitor |


# Chain Of Responsibility (Object Behavioral)

![chain](https://dzone.com/storage/rc-covers/10609-thumb.png)

![che2](https://upload.wikimedia.org/wikipedia/commons/6/6a/W3sDesign_Chain_of_Responsibility_Design_Pattern_UML.jpg)

## Purpose

Gives more than one object an opportunity to handle a request by linking receiving objects together.

Or

Effectively and compactly implement the mechanism for processing the flow of events / requests / messages in systems with potentially large number of processors.

### Use When

   * Multiple objects may handle a request and the handler doesn't have to be a specific object.
   * A set of objects should be able to handle a request with the handler determined at runtime.
   * A request not being handled is an acceptable potential outcome.

### Example

Exception handling in some languages implements this pattern.
When an exception is thrown in a method the runtime checks to see if
the method has a mechanism to handle the exception or if it should be
passed up the call stack. When passed up the call stack the process
repeats until code to handle the exception is encountered or until
there are no more parent objects to hand the request to.

**ChainOfResponsibilityTest** and **LoggerChainTest**


# Command (Object Behavioral)

![command](https://dzone.com/storage/rc-covers/10610-thumb.png)

![command2](https://upload.wikimedia.org/wikipedia/commons/c/c8/W3sDesign_Command_Design_Pattern_UML.jpg)

## Purpose

Encapsulates a request allowing it to be treated as an object. This allows the request to be handled in traditionally object based relationships such as queuing and callbacks.

### Use When

   * You need callback functionality.
   * Requests need to be handled at variant times or in variant orders.
   * A history of requests is needed.
   * The invoker should be decoupled from the object handling the invocation.

### Example

Job queues are widely used to facilitate the asynchronous processing of algorithms.
By utilizing the command pattern the functionality to be executed can be given to
a job queue for processing without any need for the queue to have knowledge of
the actual implementation it is invoking. The command object that is enqueued implements
its particular algorithm within the confines of the interface the queue is expecting.


#### _GUI buttons and menu items_

  In Swing and Borland Delphi programming, an Action is a command object.
  In addition to the ability to perform the desired command, an Action may have
  an associated icon, keyboard shortcut, tooltip text, and so on.
  A toolbar button or menu item component may be completely initialized using
  only the Action object.

#### _Macro recording_

If all user actions are represented by command objects, a program can
record a sequence of actions simply by keeping a list of the command
objects as they are executed. It can then "play back" the same actions
by executing the same command objects again in sequence.
If the program embeds a scripting engine, each command object can
implement a toScript() method, and user actions can then
be easily recorded as scripts.

#### _Mobile Code_

Using languages such as Java where code can be streamed/slurped from
one location to another via URLClassloaders and Codebases the commands
can enable new behavior to be delivered to remote locations (EJB Command, Master Worker)

#### _Multi-level undo_

If all user actions in a program are implemented as command objects,
the program can keep a stack of the most recently executed commands.
When the user wants to undo a command, the program simply pops the most recent command object and executes its undo() method.

#### _Networking_

It is possible to send whole command objects across the network to be
executed on the other machines, for example player actions in computer games.

#### _Parallel Processing_

Where the commands are written as tasks to a shared resource and
executed by many threads in parallel (possibly on remote machines -
this variant is often referred to as the Master/Worker pattern)

#### _Progress bars_

Suppose a program has a sequence of commands that it executes in order.
If each command object has a getEstimatedDuration() method,
the program can easily estimate the total duration. It can show a
progress bar that meaningfully reflects how close the program is to
completing all the tasks.

#### _Thread pools_

A typical, general-purpose thread pool class might have a public
addTask() method that adds a work item to an internal queue of tasks
waiting to be done. It maintains a pool of threads that execute
commands from the queue. The items in the queue are command objects.
Typically these objects implement a common interface such as
java.lang.Runnable that allows the thread pool to execute the
command even though the thread pool class itself was written
without any knowledge of the specific tasks for which it would be used.

#### _Transactional behavior_

Similar to undo, a database engine or software installer may keep
a list of operations that have been or will be performed.
Should one of them fail, all others can be reversed or discarded
(usually called rollback). For example, if two database tables that
refer to each other must be updated, and the second update fails,
the transaction can be rolled back, so that the first table does
not now contain an invalid reference.

#### _Wizards_

Often a wizard presents several pages of configuration for a single
action that happens only when the user clicks the "Finish" button on
the last page. In these cases, a natural way to separate user
interface code from application code is to implement the wizard using
a command object. The command object is created when the wizard is first displayed.
Each wizard page stores its GUI changes in the command object,
so the object is populated as the user progresses. "Finish" simply
triggers a call to execute(). This way, the command class will work.


