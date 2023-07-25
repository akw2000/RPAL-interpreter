JFLAGS = -g
JC = javac
JAR = jar
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

# List all the Java source files here
CLASSES = \
	lexical_analyzer/LexicalAnalyzer.java \
	lexical_analyzer/Token.java \
	Parser/AST.java \
	Parser/LeafNode.java \
	Parser/Node.java \
	Parser/ParseException.java \
	Parser/ParseTree.java \
	control_structures/ControlStructures.java \
	control_structures/CSNode.java \
	cse_machine/CSE.java \
	cse_machine/EnvironmentTree.java \
	cse_machine/EnvNode.java \
	cse_machine/EvaluationException.java \
	cse_machine/RPALFunc.java \
	cse_machine/RPALBinaryOps.java \
	cse_machine/RPALUnaryOps.java \
	rpal20.java

# Target for building the classes
default: classes

# Rule for compiling Java source files into classes
classes: $(CLASSES:.java=.class)

# Target for building the jar file: keep commented, if needed comment the line 29 default first
# default: classes rpal20

# Rule for compiling the rpal20.java into an executable jar: keep commented
# rpal20jar: rpal20.java
# 	echo "Main-Class: rpal20" > manifest.txt
# 	$(JAR) cvfm rpal20.jar manifest.txt $(CLASSES:.java=.class)
# 	$(RM) manifest.txt
# run with "java -jar rpal20.jar <input_file>"


# Rule for compiling the rpal20.java into an executable script
# that can be run with following format
# > tar xvf <submission_file>.tar
# >make
# > java rpal20 rpal_test_programs/rpal_01 > output.01
# > diff output.01 rpal_test_programs/output01.test
# > java rpal20 rpal_test_programs/rpal_02 > output.02
# > diff output.02 rpal_test_programs/output02.test

# first test is sample test following the naming convention in the project description
# rest of the tests are the sample programs provided with the rpal 
test:
	java rpal20 rpal_test_programs/rpal_01 > output.01
	diff output.01 rpal_test_programs/output01.test

	java rpal20 tests_v2/add > output.01
	diff output.01 tests_v2/add_out
	
	java rpal20 tests_v2/conc.1 > output.03
	diff output.03 tests_v2/conc.1_out

	java rpal20 tests_v2/conc1 > output.04
	diff output.04 tests_v2/conc1_out

	## lambda closure
	java rpal20 tests_v2/defns.1 > output.06
	diff output.06 tests_v2/defns.1_out

	java rpal20 tests_v2/fn1 > output.11
	diff output.11 tests_v2/fn1_out

	java rpal20 tests_v2/fn2 > output.12
	diff output.12 tests_v2/fn2_out

	java rpal20 tests_v2/fn3 > output.13
	diff output.13 tests_v2/fn3_out

	java rpal20 tests_v2/ftst > output.14
	diff output.14 tests_v2/ftst_out

	java rpal20 tests_v2/infix > output.17
	diff output.17 tests_v2/infix_out

	java rpal20 tests_v2/infix2 > output.18
	diff output.18 tests_v2/infix2_out

	java rpal20 tests_v2/pairs1 > output.21
	diff output.21 tests_v2/pairs1_out

	java rpal20 tests_v2/pairs2 > output.22
	diff output.22 tests_v2/pairs2_out

	java rpal20 tests_v2/pairs3 > output.23
	diff output.23 tests_v2/pairs3_out

	java rpal20 tests_v2/picture > output.25
	diff output.25 tests_v2/picture_out

	java rpal20 tests_v2/towers > output.49
	diff output.49 tests_v2/towers_out

	java rpal20 tests_v2/vectorsum > output.53
	diff output.53 tests_v2/vectorsum_out

# original test: according to the project description
test2: 
	java rpal20 rpal_test_programs/rpal_01 > output.01
	diff output.01 rpal_test_programs/output01.test

# both build and test
check: default test

# clean all the class files
clean:
	find -name "*.class" -exec $(RM) {} +