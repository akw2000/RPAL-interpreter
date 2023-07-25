To build the program you must run "make" in the same directory as the makefile

## IMPORTANT
for testing the program, you must add a new directory called "rpal_test_programs" in the same directory as the makefile
that must contain test programs with naming convention "rpal_01", "rpal_02", etc.
the output of the test programs must be in the same directory with naming convention "output01.test", "output02.test", etc.

after adding the testing files you can add the testing commands under test rule in the make file
as follows:

java rpal20 rpal_test_programs/rpal_01 > output.01
diff output.01 rpal_test_programs/output01.test
java rpal20 rpal_test_programs/rpal_02 > output.02
diff output.02 rpal_test_programs/output02.test
...etc.

The outputs of each program will be written to a file called output.01, output.02, etc. in the same directory as the makefile

Then can simply run "make check" to build and run the tests

"make clean" will remove all the class files 