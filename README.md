# Dijkstras-Algorithm-Run-Time-Test

copy this file to your gl and run these commands

javac DijkstraAlgorithmSet.java
java DijkstraAlgorithmSet

This will run the program. Here is the output of the current version. Note that it will crash because it runs out of memory aka, there are too many nodes.

Output:

linux2[207]% java DijkstraAlgorithmSet
Number of nodes: 10
Total mem of Dijkstra's: 2663624 units of memory
Total runtime of DJ: 3ms

Number of nodes: 100
Total mem of Dijkstra's: 2663624 units of memory
Total runtime of DJ: 53ms

Number of nodes: 1000
Total mem of Dijkstra's: 6670760 units of memory
Total runtime of DJ: 463ms

Number of nodes: 10000
Total mem of Dijkstra's: 1214500176 units of memory
Total runtime of DJ: 9790ms

Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        at DijkstraAlgorithmSet.<init>(DijkstraAlgorithmSet.java:58)
        at DijkstraAlgorithmSet.main(DijkstraAlgorithmSet.java:152)

