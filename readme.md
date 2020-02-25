# How to run. 

The software uses Lombok and JUnit 5 libraries. 
The project includes pom.xml that you should use to add dependencies to the software. 
When all dependencies are installed, you can run the tests.
The ElevatorSystem is presented using tests. The tests are located in `src/tests/java` folder.
 

# ElevatorSystem algorithm implementation. 

Algorithm implemented in this software is better than first-come, first-served algorithm. It uses the elevators that are actually moving to collect people from floors on their way, while an elevator implemented using FCFS algorithm would skip such people. 
Sadly there is still one problem in my implementation that was explained in the test called `testWithInnerPickWithDirections` in `ElevatorSystemTest.java`

# Other Notes

PickUp methods of ElevatorsSystem return RuntimeException because in the target hardware there will be buttons enabling only legal operations. 



