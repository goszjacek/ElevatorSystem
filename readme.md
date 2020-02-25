# ElevatorsSystem algorithm implementation. 

Algorithm implemented in this software is better than first-come, first-served algorithm. It uses the elevators that are actually moving to collect people from floors on their way, while an elevator implemented using FCFS algorithm would skip such people. 
Sadly there is still one problem in my implementation that was explained in the test called `testWithInnerPickWithDirections` in `ElevatorSystemTest.java`

# Other Notes

PickUp methods of ElevatorsSystem return RuntimeException because in the target hardware there will be buttons enabling only legal operations. 



