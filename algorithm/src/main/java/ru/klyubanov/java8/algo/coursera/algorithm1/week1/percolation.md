## Percolation

* N-by-N grid of sites. 
* Site is a square and a peace of grid (board).
* Blocked site is black.
* Open site is white.
* Each site is open with probability _p_ (or blocked with probability _1-p_).
* System **percolates** if top and bottom are connected by open sites. 
(in other words, where's a open way from top to the bottom)

### Task
Write a program to estimate the value of the percolation threshold via Monte Carlo simulation. 



# Summary of week 1

### Steps to developing a usable algorithm.
* Model the problem.
* Find an algorithm to solve it.
* Fast enough? Fits in memory?
* If not, figure out why.
* Find a way to address the problem.
* Iterate until satisfied.

### The scientific method.

### Mathematical analysis. 



##Interview Questions: Union–Find (ungraded)

1. **Social network connectivity.** 
Given a social network containing _n_ members and a log file containing _m_ timestamps 
at which times pairs of members formed friendships, design an algorithm to determine the earliest time 
at which all members are connected (i.e., every member is a friend of a friend of a friend ... of a friend). 
Assume that the log file is sorted by timestamp and that friendship is an equivalence relation. 
The running time of your algorithm should be _m log n_ or better and use extra space proportional to _n_.

####Solution
_Weighted quick-union with path compression_ could be used. Which will meet the time expectation of m log n.
Because you can reduce difficulty from _n_ to _lg N_ by introducing weighing and path compression, 
but you can't reduce count of m events (because they are random).