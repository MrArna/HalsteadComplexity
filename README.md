CS441 @ UIC: HOMEWORK1
====================
Developed by Marco Arnaboldi (marnab2@uic.edu)

Description
--------------------
The application measures the [Halstead Complexity](https://en.wikipedia.org/wiki/Halstead_complexity_measures) measures of a given Java project.

Development & Design choices
-----------------
The application was developed with IntelliJ. It has been designed in order to be as extendable as possible.
In detail, it's composed by 3 classes:

+ **Main**: this is the the core of the application, where the other classes are instantiated and used
+ **HalsteadVisitor**: this class extends the ASTVisitor class, in this way its possible to exploit polymorphism and use this custom class during the parsing by compilation unit. This class is in charge to visit the project structure, retrieve the files, parse them and count operands and operators. In order to achieve this last functionality several overloading versions of the method visit are provided. Each of those it's called when the AST node of the respective type is visited 
+ **HalsteadComplexity**: this class implements and provide all the measures being part of the Halstead Complexity 

Further information about the methods and their behaviors can be found in the comment inside the code.

Behaviour
----------------

Usage
----------------

Test
----------------
All the tests were made automated by using JUnit. In particular a test suite for each of the *HalsteadComplexity* and the *HalsteadVisitor* class were made. Each methods of both of them was tested except for the visitor methods since they were already safe.


Acknowledgments
---------------