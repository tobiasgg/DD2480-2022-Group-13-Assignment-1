# Launch Interceptor Program
### Siyao Liu, Edwin So, Filip Bäck, Tobias Gabi Goobar
#### *Assignment 1 in course DD2480, Spring 2022, KTH*
## Overview
This project models an hypothetical anti-ballistic missile system based on the specification by J. C. Knight and N. G. Leveson.

The main function is the **Decide()** function.
This function decides whether or not an interceptor should be launced based on planar data points representing radar echoes.
### Inputs
- A set of planar data points.
- **LCM** (*Logical Connector Matrix*) which is a 15 x 15 symmetric matrix where the elements are have values **ANDD, ORR, NOTUSED**.
- **PUV** (*Preliminary Unlocking Vector*) is a 15 x 1 boolean vector, explained below.

### Outputs
- A boolean (**TRUE/FALSE**) indicating if an interceptor should be launced.

### Functionality

The decision is made based on 15 different **LICs** (*Launch Interceptor Conditions*).

These 15 **LICs** have a one-to-one correspondance with the corresponding element in the **CMV** (*Conditions Met Vector*).

Each element **LCM**[i,j] defines what logical operator should be used for the pair **CMV**[i], **CMV**[j] to create the corresponding element **PUM**[i,j] (*Preliminary Unlocking Matrix*), which is a symmetric boolean matrix.

For example, let's say that element **LCM**[i,j] is **ANDD**, **LIC**[i] = **TRUE** and **LIC**[i] = **TRUE** then element **PUM**[i,j] will be **TRUE ANDD TRUE** which evaluates to **TRUE**.

The **FUV** (*Final Unlocking Vector*) is a 15 x 1 boolean vector where each element **FUV**[i] is **TRUE** either if *all* elements in row i of the **PUM** is true **or** if **PUV**[i] is **FALSE** indicating that this condition should not be used.

Finally, the function **Decide()** returns true if and only if *all* elements in the **FUV** are **TRUE**.

## How to use
Our project uses Maven as the build tool, so it is essential that it is installed before attempting to run the program.
To run the program, change the inputs in `src/main/java/Main.java` as you like and type the following in the terminal:
```
mvn clean install
mvn exec:java
```
You can also modify the tests contained in ``src/test/java`` correspondingly. To run the tests, type:
```
mvn test
```

## Way of Working
Currently, we are in the stage of *In Place*. This is because we have already established a bunch of principles and agreed on the tools that we use for the work, and they are being used by the whole team to perform the work. We would regularly inspect if the rules or principles are followed and if not, we would make changes and make sure we abide by them. <br>
But we are still not in the stage of *Working well* since we cannot naturally apply the practices without thinking about them. <br>
We think the obstacles to reaching the next stage is time because it has not been a long time since the group was formed and principles were established. <br>

## Contributions

#### Tobias Gabi Goobar:
Implemented
- LIC0
- LIC2
- LIC6
- LIC7
- LIC12

along with corresponding unit tests.

#### Filip Bäck
Implemented
- LIC3
- LIC5
- LIC8
- LIC10
- LIC14

along with corresponding unit tests.

#### Siyao Liu
Implemented
- LIC1
- LIC4
- LIC9
- LIC11
- LIC13

along with corresponding unit tests.

### Note to the grader(s):
- Basically 99% of the commits are linked to a valid issue with description. If it is not the case at the first glance, please check the comment area or the corresponding pull requests, and you will see the linked issue.
- Possible ambiguity: the way we interpret the requirements of the parameters is that the input of must fulfill them, otherwise we directly throw **assertion errors** and abort the program.

