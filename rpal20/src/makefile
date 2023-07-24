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
	# true  but outputs false
	java -cp bin rpal20 tests_v2/if2 > output.16
	fc output.16 tests_v2/if2_out

	# # ***** output.19
	# # (Args not both tuples, Args not both tuples, Args not both tuples, Args not both tuples)
	# # ***** TESTS_V2/INNERPROD_OUT
	# # (0, 32, Args of unequal length, Args not both tuples)
	# # *****
	# java -cp bin rpal20 tests_v2/Innerprod > output.19
	# fc output.19 tests_v2/Innerprod_out

	# # ***** output.20
	# # (Args not both tuples, Args not both tuples, Args not both tuples, Args not both tuples)
	# # ***** TESTS_V2/INNERPROD2_OUT
	# # (0, 32, Args of unequal length, Args not both tuples)
	# # *****
	# java -cp bin rpal20 tests_v2/Innerprod2 > output.20
	# fc output.20 tests_v2/Innerprod2_out

	# # ***** output.25
	# # T
	# # ***** TESTS_V2/PICTURE_OUT
	# # 4
	# # .   3
	# # .   .   T
	# # .   .   3
	# # .   .   .   T
	# # .   .   .   T
	# # .   .   .   T
	# # .   .   T
	# # .   2
	# # .   .   T
	# # .   .   T
	# # .   3
	# # .   .   T
	# # .   .   T
	# # .   .   0
	# # .   .   .   
	# # .   1
	# # .   .   T
	# # *****
	# java -cp bin rpal20 tests_v2/picture > output.25
	# fc output.25 tests_v2/picture_out

	# # parser exception
	# java -cp bin rpal20 tests_v2/recurs.1 > output.30
	# fc output.30 tests_v2/recurs.1_out

	# # parser exception
	# java -cp bin rpal20 tests_v2/t9 > output.45
	# fc output.45 tests_v2/t9_out
	
	# # parser exception
	# java -cp bin rpal20 tests_v2/tiny > output.47
	# fc output.47 tests_v2/tiny_out

	# # parser exception
	# java -cp bin rpal20 tests_v2/tiny.1 > output.48
	# fc output.48 tests_v2/tiny.1_out

	# # ***** output.50
	# # (T, 1(T), 4(3(T,3(T,T,T),T),2(T,T),3(T,T,T),1(T)))
	# # ***** TESTS_V2/TREEPICTURE_OUT
	# # (0(), 1(T), 4(3(T,3(T,T,T),T),2(T,T),3(T,T,0()),1(T)))
	# # *****
	# java -cp bin rpal20 tests_v2/Treepicture > output.50
	# fc output.50 tests_v2/Treepicture_out

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