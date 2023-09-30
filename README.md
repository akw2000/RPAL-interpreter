RPAL Project Group 29
=====================
This is an interpreter for the functional programming language, RPAL consisting of a scanner, parser and a CSE machine.
## Table of Contents

- [About](#about)
- [Features](#features)
- [Contributors](#contributors)


## About

The interpreter consists of the following main components.

### 1. Lexical Analyzer
    - Scans the given RPAL program and prepares the token list for the parser. Tokens are categorized according to the RPAL lexical rules.
    - INPUT: RPAL source file
    - OUTPUT: token list (list of token objects(type,value)

### 2. Parser
    - Gets the token list from the lexical analyser and constructs the Abstract Syntax Tree(AST) and Standard Tree (ST). 
    - INPUT: token list
    - OUTPUT: Standard Tree

### 3. CSE Machine
    - Performs pre-order traversal through the standard tree and generates control structures. Then evaluate the source program using 13 CSE rules to produce the output of the source program.

## Features
* Tokenizes a given RPAL program
* Construct the AST and ST
* Additional functions to print AST and ST
* Execute the RPAL program and produce the output.

## Contributors
- Kavindu Warnakulasuriya
- Navindu De Silva
- Ravindi Weerasinghe
- Oshadi Perera
