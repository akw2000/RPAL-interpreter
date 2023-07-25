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


test:
	java rpal20 tests_v2/add > output.01
	diff output.01 tests_v2/add_out
	
	java rpal20 tests_v2/clean > output.02
	diff output.02 tests_v2/clean_out

	java rpal20 tests_v2/conc.1 > output.03
	diff output.03 tests_v2/conc.1_out

	java rpal20 tests_v2/conc1 > output.04
	diff output.04 tests_v2/conc1_out

	java rpal20 tests_v2/conc3 > output.05
	diff output.05 tests_v2/conc3_out

	## lambda closure
	java rpal20 tests_v2/defns.1 > output.06
	diff output.06 tests_v2/defns.1_out

	java rpal20 tests_v2/defns.2 > output.07
	diff output.07 tests_v2/defns.2_out

	## new line problem
	java rpal20 tests_v2/defns.3 > output.08
	diff output.08 tests_v2/defns.3_out

	java rpal20 tests_v2/div > output.09
	diff output.09 tests_v2/div_out

	## new line problem
	java rpal20 tests_v2/envlist > output.10
	diff output.10 tests_v2/envlist_out

	java rpal20 tests_v2/fn1 > output.11
	diff output.11 tests_v2/fn1_out

	java rpal20 tests_v2/fn2 > output.12
	diff output.12 tests_v2/fn2_out

	java rpal20 tests_v2/fn3 > output.13
	diff output.13 tests_v2/fn3_out

	java rpal20 tests_v2/ftst > output.14
	diff output.14 tests_v2/ftst_out

	## new line problem
	java rpal20 tests_v2/if1 > output.15
	diff output.15 tests_v2/if1_out
	
	java rpal20 tests_v2/if2 > output.16
	diff output.16 tests_v2/if2_out

	java rpal20 tests_v2/infix > output.17
	diff output.17 tests_v2/infix_out

	java rpal20 tests_v2/infix2 > output.18
	diff output.18 tests_v2/infix2_out

	java rpal20 tests_v2/Innerprod > output.19
	diff output.19 tests_v2/Innerprod_out

	java rpal20 tests_v2/Innerprod2 > output.20
	diff output.20 tests_v2/Innerprod2_out

	java rpal20 tests_v2/pairs1 > output.21
	diff output.21 tests_v2/pairs1_out

	java rpal20 tests_v2/pairs2 > output.22
	diff output.22 tests_v2/pairs2_out

	java rpal20 tests_v2/pairs3 > output.23
	diff output.23 tests_v2/pairs3_out

	java rpal20 tests_v2/pf > output.24
	diff output.24 tests_v2/pf_out

	java rpal20 tests_v2/picture > output.25
	diff output.25 tests_v2/picture_out

	java rpal20 tests_v2/print1 > output.26
	diff output.26 tests_v2/print1_out

	java rpal20 tests_v2/print2 > output.27
	diff output.27 tests_v2/print2_out

	java rpal20 tests_v2/prog > output.28
	diff output.28 tests_v2/prog_out

	# # parser exception
	java rpal20 tests_v2/recurs.1 > output.30
	diff output.30 tests_v2/recurs.1_out

	java rpal20 tests_v2/reverse > output.31
	diff output.31 tests_v2/reverse_out

	## new line problem
	java rpal20 tests_v2/send > output.32
	diff output.32 tests_v2/send_out

	java rpal20 tests_v2/simple.div > output.33
	diff output.33 tests_v2/simple.div_out

	java rpal20 tests_v2/stem1 > output.34
	diff output.34 tests_v2/stem1_out

	java rpal20 tests_v2/stem2 > output.35
	diff output.35 tests_v2/stem2_out

	java rpal20 tests_v2/string1 > output.36
	diff output.36 tests_v2/string1_out

	java rpal20 tests_v2/sum > output.37
	diff output.37 tests_v2/sum_out

	java rpal20 tests_v2/t1 > output.38
	diff output.38 tests_v2/t1_out

	java rpal20 tests_v2/t16 > output.39
	diff output.39 tests_v2/t16_out

	java rpal20 tests_v2/t18 > output.40
	diff output.40 tests_v2/t18_out

	java rpal20 tests_v2/t19 > output.41
	diff output.41 tests_v2/t19_out

	java rpal20 tests_v2/t2 > output.42
	diff output.42 tests_v2/t2_out

	java rpal20 tests_v2/t3 > output.43
	diff output.43 tests_v2/t3_out

	java rpal20 tests_v2/t3.1 > output.44
	diff output.44 tests_v2/t3.1_out

	java rpal20 tests_v2/t9 > output.45
	diff output.45 tests_v2/t9_out

	java rpal20 tests_v2/test1 > output.46
	diff output.46 tests_v2/test1_out
	
	java rpal20 tests_v2/tiny > output.47
	diff output.47 tests_v2/tiny_out

	java rpal20 tests_v2/tiny.1 > output.48
	diff output.48 tests_v2/tiny.1_out

	java rpal20 tests_v2/towers > output.49
	diff output.49 tests_v2/towers_out

	java rpal20 tests_v2/Treepicture > output.50
	diff output.50 tests_v2/Treepicture_out

	java rpal20 tests_v2/trees > output.51
	diff output.51 tests_v2/trees_out

	java rpal20 tests_v2/tuples > output.52
	diff output.52 tests_v2/tuples_out

	java rpal20 tests_v2/vectorsum > output.53
	diff output.53 tests_v2/vectorsum_out

	java rpal20 tests_v2/wsum1 > output.54
	diff output.54 tests_v2/wsum1_out

	java rpal20 tests_v2/wsum2 > output.55
	diff output.55 tests_v2/wsum2_out

	java rpal20 test_extract/add_list > output.23
	diff output.23 test_extract/add_list_out

	java rpal20 test_extract/merge > output.24
	diff output.24 test_extract/merge_out

	java rpal20 test_extract/substr > output.25
	diff output.25 test_extract/substr_out

	java rpal20 test_extract/tuple > output.26
	diff output.26 test_extract/tuple_out

	java rpal20 test_extract/vectorsum > output.27
	diff output.27 test_extract/vectorsum_out

# original test: according to the project description
test2: 
	java rpal20 rpal_test_programs/rpal_01 > output.01
	diff output.01 rpal_test_programs/output01.test

# both build and test
check: default test

# clean all the class files
clean:
	find -name "*.class" -exec $(RM) {} +