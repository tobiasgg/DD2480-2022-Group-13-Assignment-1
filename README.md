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

## Way of Working

## Contributions

#### Tobias Gabi Goobar:
Implemented
- LIC0
- LIC2
- LIC6
- LIC7
- LIC12

along with corresponding unit tests.