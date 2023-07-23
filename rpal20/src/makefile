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
	
	# java -cp bin rpal20 tests_v2/clean > output.02
	# fc output.02 tests_v2/clean_out

	# java -cp bin rpal20 tests_v2/conc.1 > output.03
	# fc output.03 tests_v2/conc.1_out

	java -cp bin rpal20 tests_v2/conc1 > output.04
	fc output.04 tests_v2/conc1_out

	## lambda closure
	# java -cp bin rpal20 tests_v2/defns.1 > output.06
	# fc output.06 tests_v2/defns.1_out

	##
	# java -cp bin rpal20 tests_v2/defns.3 > output.08
	# fc output.08 tests_v2/defns.3_out

	##
	# java -cp bin rpal20 tests_v2/envlist > output.10
	# fc output.10 tests_v2/envlist_out

	##
	# java -cp bin rpal20 tests_v2/if1 > output.15
	# fc output.15 tests_v2/if1_out
	
	## true
	# java -cp bin rpal20 tests_v2/if2 > output.16
	# fc output.16 tests_v2/if2_out

	# java -cp bin rpal20 tests_v2/Innerprod > output.19
	# fc output.19 tests_v2/Innerprod_out

	# java -cp bin rpal20 tests_v2/Innerprod2 > output.20
	# fc output.20 tests_v2/Innerprod2_out

	# java -cp bin rpal20 tests_v2/pairs1 > output.21
	# fc output.21 tests_v2/pairs1_out

	# java -cp bin rpal20 tests_v2/pairs2 > output.22
	# fc output.22 tests_v2/pairs2_out

	# java -cp bin rpal20 tests_v2/picture > output.25
	# fc output.25 tests_v2/picture_out

	# java -cp bin rpal20 tests_v2/recurs.1 > output.30
	# fc output.30 tests_v2/recurs.1_out

	# java -cp bin rpal20 tests_v2/reverse > output.31
	# fc output.31 tests_v2/reverse_out

	# java -cp bin rpal20 tests_v2/send > output.32
	# fc output.32 tests_v2/send_out

	# java -cp bin rpal20 tests_v2/t9 > output.45
	# fc output.45 tests_v2/t9_out

	#cse cannot augment non tuple
	# java -cp bin rpal20 tests_v2/test1 > output.46
	# fc output.46 tests_v2/test1_out

	# java -cp bin rpal20 tests_v2/tiny > output.47
	# fc output.47 tests_v2/tiny_out

	# java -cp bin rpal20 tests_v2/tiny.1 > output.48
	# fc output.48 tests_v2/tiny.1_out

	# java -cp bin rpal20 tests_v2/towers > output.49
	# fc output.49 tests_v2/towers_out

	# cse cse_machine err
	# java -cp bin rpal20 tests_v2/Treepicture > output.50
	# fc output.50 tests_v2/Treepicture_out

	# java -cp bin rpal20 tests_v2/trees > output.51
	# fc output.51 tests_v2/trees_out

	# error output instead of 10
	# java -cp bin rpal20 tests_v2/wsum1 > output.54
	# fc output.54 tests_v2/wsum1_out


test2: 
	java -cp bin rpal20 rpal_test_programs/rpal_01 > output.01
	fc output.01 rpal_test_programs/output01.test
# both build and test
# check: default test
check: default test

clean:
	del /Q /S bin