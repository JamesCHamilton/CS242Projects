The Maximum Subarray Problem is the task of finding the contiguous
subarray, within an array of numbers, that has the largest sum. For example, for the sequence of values (−2, 1, −3, 4, −1, 2, 1, −5, 4) the contiguous
subsequence with the largest sum is (4, −1, 2, 1), with sum 6.


For an arbitrary input array of length n, two algorithms that compute
the sum of the maximum subarray were discussed in class: (a) a brute-force
algorithm that solves the problem in O(n^2) steps, and (b) a divide-andconquer algorithm that achieves O(n log n) running time.

1. Implement in Java the algorithms attached below as Algorithms 1, and 2. Your program must prompt the user to enter the size
of the array n, and output the time taken by each of the two algorithms.
To measure the running time use the snippet of code attached below.
Choose at random the numbers in the array (including the sign).

2. Test the algorithms with different values of n and fill the
following table with the running times measured in nanoseconds (put
the table in the code header).
![Table thing](Table.png "For number 2

You may run into problems, such as running out of memory or the
program taking too much time. If that is the case, adjust the values of
n accordingly, but make sure that you still have 5 columns of data.
Rubric: No points if a row has less than 4 values.")

3. Based on the running times observed, draw conclusions
about the running times obtained in the analysis. Do they match or
not? Provide your answers in the remarks section of the code header.
It is not enough to simply say: yes, they match. You have to justify
your claim based on the running times measured (the table). Also, it
is not enough to say Divide and conquer is faster. We know that, it
is written above. What you need to show is how your measurements
prove that Brute Force is O(n^2) and Divide and Conquer is O(n log n)
on these inputs. And for that you have to look at your measurements
column-to-column, see if they grow as those functions, and write in
your answer what cells in the table you looked at.
Rubric: No points if you do not say something like “ when n is multiplied by xxx the running time is yyy”. Partial points if your measurements do not match and you say it, but you do not explain what you
did to investigate the reason.

4. (Extra credit) There exists a dynamic-programming algorithm
due to Kadane that runs in linear time, which is optimal because you
need at least to read each number in the input. For extra credit, implement this dynamic programming algorithm as well and test it along
the other two. You can put all your measurements in the same table