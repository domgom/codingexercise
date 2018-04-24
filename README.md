#Primefinder
Primefinder is a web application that finds prime numbers until a given included limit


###Introduction
Primefinder is a Spring Boot application that supports several algorithms to find prime numbers.
There are many ways of implementing this problem: factorization, primality tests, different kind of sieves
 (Eratosthenes, Atkins), and different techniques to improve the results: memory optimization, cpu optimization,
 parallel programming, etc
 
 Given the time constraints and the really variable scope from a simple Factorization (few lines with java 8 lambdas) to
  a complex paginated 
 and distributed Eratosthenes Sieve (the most recent improvement seems to be found recently
  [AN IMPROVED SIEVE OF ERATOSTHENES-HARALD ANDRES HELFGOTT](https://arxiv.org/pdf/1712.09130.pdf) ) I have opted for
  providing only two implementations and focus on robust testing, sufficient documentation and clean code.

###Usage
Requires java8, although is compatible with latest java 10 just changing the _sourceCompatibility = 10_

To startup just run on the root of the project:
 ```./gradlew bootRun``` 
 That should startup the application on _localhost:8080_ and to start calculating you could browse:
 ```http://localhost:8080/primes/100000```
 or from console:
 ```curl 'http://localhost:8080/primes/100000' -H 'Accept: application/json' -w 'Time taken: '%{time_total}'secs'```
 
 ###EratosthenesSieveClassicStrategy
It uses the Sieve of Eratosthenes in a single thread mode without pagination. Starting from 2 every time a new unmarked
number is found (means it's prime), all the multiples of that number are marked as composite in the list. 

-The data structure chosen for holding the
sieve numbers is a BitSet for its improved memory occupied (storing only the 1s).
-The range of prime numbers is limited to the _int_ size, trading off functional completeness for efficiency.
-The estimatedSize of the result ArrayList is precalculated with an estimation to avoid the list to be reallocated on
 memory upon growth.


###Performance testing
Given the time constraints very limited performance testing has been carried out. To give some numbers, on a dual core
laptop this is a sample results table:

| Limit         | Eratosthenes  | BigInteger built-in|       
| ------------- |:-------------:|:------------------:|
|   1,000,000   | 0,138232 secs  | 11,822596 secs    |
|   4,000,000   | 0,338110 secs  | 36,878689 secs    |
| 100,000,000   | 8,014593 secs  | Too long!         |

 
 ###A note on coding practices
Clean code principles can often trade off raw efficiency for simplicity and readability. When developing math algorithms 
simplicity is important but efficiency is crucial. I tried to keep the whole application simple and provide the
*BigIntegerBuiltInStrategy* as a concise but not so efficient implementation and *EratosthenesSieveClassicStrategy*
as an example of some low level optimizations.

###Documentation
I planned to use Spring Rest Docs to document the rest endpoints but due to time constraints I preferred to use that 
time into writing better tests and a more comprehensive README.md.