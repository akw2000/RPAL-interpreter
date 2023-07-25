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

	java rpal20 rpal_test_programs/add > output.01
	diff output.01 rpal_test_programs/add_out
	
	java rpal20 rpal_test_programs/conc.1 > output.03
	diff output.03 rpal_test_programs/conc.1_out

	## lambda closure
	java rpal20 rpal_test_programs/defns.1 > output.04
	diff output.04 rpal_test_programs/defns.1_out

	java rpal20 rpal_test_programs/fn1 > output.05
	diff output.05 rpal_test_programs/fn1_out

	java rpal20 rpal_test_programs/fn2 > output.06
	diff output.06 rpal_test_programs/fn2_out

	java rpal20 rpal_test_programs/fn3 > output.07
	diff output.07 rpal_test_programs/fn3_out

	java rpal20 rpal_test_programs/ftst > output.08
	diff output.08 rpal_test_programs/ftst_out

	java rpal20 rpal_test_programs/infix > output.09
	diff output.09 rpal_test_programs/infix_out

	java rpal20 rpal_test_programs/infix2 > output.10
	diff output.10 rpal_test_programs/infix2_out

	java rpal20 rpal_test_programs/pairs1 > output.11
	diff output.11 rpal_test_programs/pairs1_out

	java rpal20 rpal_test_programs/pairs2 > output.12
	diff output.12 rpal_test_programs/pairs2_out

	java rpal20 rpal_test_programs/pairs3 > output.13
	diff output.13 rpal_test_programs/pairs3_out

	java rpal20 rpal_test_programs/picture > output.14
	diff output.14 rpal_test_programs/picture_out

	java rpal20 rpal_test_programs/towers > output.15
	diff output.15 rpal_test_programs/towers_out

	java rpal20 rpal_test_programs/vectorsum > output.16
	diff output.16 rpal_test_programs/vectorsum_out

# original test: according to the project description
test2: 
	java rpal20 rpal_test_programs/rpal_01 > output.01
	diff output.01 rpal_test_programs/output01.test

# both build and test
check: default test

# clean all the class files
clean:
	find -name "*.class" -exec $(RM) {} +