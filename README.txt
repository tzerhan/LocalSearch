This is a solution for a local search problem.

A set of M professors and N students are at a Computer Science cocktail party. The party is held in 10 meter by 10 meter square room, which is conveniently tiled with one hundred 1 m2 tiles.

Being socially-awkward computer scientists, each student prefers to stand as far as possible from each professor, and vice versa. Further, the professors have been recently embroiled in a bitter fight over a corner office and prefer to stand as far as possible from each other. The students don't care how far apart they are standing. Assume both the students and professors stand exactly in the center of their own tiles.

The discontent created by a professor / student pair is described by

C = 1 / d(si, pj)

for student si and professor pi, where d(x, y) is the Euclidian distance between x and y. Similarly, the discontent for each professor / professor pair is

C = 1 / d(pi, pj)

The discontent of the room as a whole is the sum of the discontent created by each pair.
You are to write a computer program that minimizes the total discontent by choosing locations for all professors and students, given the following number of each:

Problem A: M = 1, N = 1 (an awkward party, by any measure)
Problem B: M = 3, N = 10
Problem C: M = 15, N = 10
Problem D: M = 20, N = 20