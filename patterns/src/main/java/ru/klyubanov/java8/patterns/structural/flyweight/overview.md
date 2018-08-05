###Intent

Use sharing to support large numbers of fine-grained objects efficiently.

In computer programming, flyweight is a software design pattern. 
A flyweight is an object that minimizes memory use by sharing as 
much data as possible with other similar objects; it is a way to 
use objects in large numbers when a simple repeated representation would use an unacceptable amount of memory.

#### What problems can the Flyweight design pattern solve? [2]

    Large numbers of objects should be supported efficiently.
    Creating large numbers of objects should be avoided.

When representing large text documents, for example, creating an object for each character in the document would result in a huge amount of objects that couldn't be processed efficiently.

What solution does the Flyweight design pattern describe?

Define Flyweight objects that

    store intrinsic (invariant) state that can be shared and
    provide an interface through which extrinsic (variant) state can be passed in.

This enables clients to (1) reuse (share) Flyweight objects (instead of creating a new object each time) and (2) pass in extrinsic state when they invoke a Flyweight operation.
This greatly reduces the number of physically created objects.

![trash](https://upload.wikimedia.org/wikipedia/commons/4/4e/W3sDesign_Flyweight_Design_Pattern_UML.jpg)

###Immutability and equality
To enable safe sharing, between clients and threads, Flyweight objects must be immutable. Flyweight objects are by definition value objects. The identity of the object instance is of no consequence therefore two Flyweight instances of the same value are considered equal.

###Concurrency
Special consideration must be made in scenarios where Flyweight objects are created on multiple threads. If the list of values is finite and known in advance the Flyweights can be instantiated ahead of time and retrieved from a container on multiple threads with no contention. If Flyweights are instantiated on multiple threads there are two options:

    Make Flyweight instantiation single threaded thus introducing contention and ensuring one instance per value.
    Allow concurrent threads to create multiple Flyweight instances thus eliminating contention and allowing multiple instances per value. This option is only viable if the equality criterion is met.
