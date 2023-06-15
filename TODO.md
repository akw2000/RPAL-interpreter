#lexical analyzer
RPAL prog file -> INPUT
	scanner
	screen using lexicon pdf
OUTPUT -> token list ( token object(type, value))

===============================
#parser
token list ( token object(type, value)) -> INPUT
	AST using grammar pdf
OUTPUT -> tree (with <id: fun>, let, ... nodes)
------------------------------
#ST(only required nodes)
================================

#Control structers, pre order traversal
======================================

#CSE 13 rules

============================
submit:
makefile
src code
REPORT