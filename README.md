# Java Fraction API


Project to create a Fraction Calculator.

Java provides classes for working with several types of numbers, but it does not provide anything for working with fractions.

## Execution

- Run main() and folllow the instructions in the terminal.
- Press Q at anytime to quit the program.

## Program Defaults

The denominator can never be zero.

A zero numerator will always be represented as `0/1`.

Fractions are always reduced to lowest terms before calculation:

			4/6	=>	2/3
			17/34	=>	1/2
			0/999	=> 	0/1

Negatives cancel each other out:

			-5/-12	=> 	5/12

The sign is always carried by the numerator:

			-9/4  	=> 	-9/4
			9/-4  	=> 	-9/4


## Mathematical Operations

Add

			1/4 + 1/4 = 1/2

Subtract

			1/2 - 1/4 = 1/4
		
Multiply

			1/2 * 1/2 = 1/4

Divide

			1/4 / 1/2 = 1/2

Absolute

			 1/2 = 1/2
			-1/2 = 1/2

Negate

			 3/4 = -3/4 
			-2/3 = 2/3

Inverse

			2/5 = 5/2

Equals

			1/2 = 1/2 ? True
			2/3 = 3/4 ? False

Compare

			1/2 equals 1/2
			2/3 greater than 1/3
			1/4 less than 1/2


