# NowItNow-Kata

The MowItNow company decided to develop an automatic lawn mower,
intended for rectangular surfaces.  

The mower can be programmed to cover the entire surface. The
the position of the mower is represented by a combination of coordinates (x,y)
and a letter indicating the orientation according to English cardinal notation (N,E,W,S).
The lawn is divided into a grid to simplify navigation.  

For example, the position of the trimmer can be "0, 0, N", which means that it
is located in the lower left corner of the lawn, and facing north.  

To control the mower, it is sent a simple sequence of letters. Letters
the possible ones are “D”, “G” and “A”. “D” and “G” rotate the mower 90° at
right or left respectively, without moving it. “A” means we are moving forward
the mower one space in the direction it faces, and without modifying
the orientation.  

If the position after movement is outside the lawn, the mower will not move , 
keep its orientation and process the next command.  

We assume that the square directly North of the position (x, y) has for
coordinates (x, y+1).  

To program the mower, it is provided with an embedded input file like
combination :  

> -The first line corresponds to the coordinates of the upper right corner of the
lawn, those in the lower left corner are assumed to be (0,0)  

> -The rest of the file allows you to control all the mowers that have been
deployed. Each mower has two lines about it:
o the first line gives the initial position of the mower, as well as
yarn orientation. Position and orientation are provided as
2 digits and a letter, separated by a space
where the second line is a series of instructions telling the mower
to explore the lawn. The instructions are a sequence of characters without
spaces.

Each mower moves sequentially, meaning the second
mower only moves when the first has completely passed its series
instructions.
When a mower completes a series of instructions, it communicates its position
and its direction.  


# GOAL
Design and write a program in Java. This program should implement the
specification above and pass the test below.

# TEST
The following file is provided as input:     
5 5   
1 2 N   
GAGAGAGAA   
3 3 E   
AADAADADDA  
We expect the following result (final position of the mowers): 1 3 N 5 1 E   
NB: The input data is integrated as a file.  

# To clean and run test
``` sh
mvn clean install  
```

# To run the jar with input file
``` sh
java -jar nowItNow-kata-1.0-SNAPSHOT.jar "classes/test.txt"
```

