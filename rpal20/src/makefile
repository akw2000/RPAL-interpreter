JFLAGS = -g
JC = javac -d bin
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
	cse_machine/CSNodeType.java \
	cse_machine/EnvNode.java \
	cse_machine/EvaluationException.java \
	cse_machine/RPALBinaryOps.java \
	cse_machine/RPALUnaryOps.java \
    rpal20.java

# Target for building the classes
default: classes

# Rule for compiling Java source files into classes
classes: | bin
	$(JC) $(JFLAGS) $(CLASSES)

bin:
	mkdir bin



test:
	# # parser exception
	# java -cp bin rpal20 tests_v2/recurs.1 > output.30
	# fc output.30 tests_v2/recurs.1_out

test2: 
	java -cp bin rpal20 rpal_test_programs/rpal_01 > output.01
	fc output.01 rpal_test_programs/output01.test
# both build and test
# check: default test
check: default test

clean:
	del /Q /S bin