# Traders-Max-Profit

![Trade](https://change-wm.com/wp-content/uploads/2018/07/678789789.jpg)

## Another problem within the Dynamic Programming paradigm

### Statement:

Given stock prices calculate the maximum possible profit a trader could make with at most two transactions in one day.

**Example:**

*Stock prices of one day:*

prices[] = {75, 4, 25, 20, 60, 45} 

*Expected Output:*

Maximum possible profit: 61

**Explanation:**

Suppose the trader initially had **n** dollars.

Buy the stock with the price 4, sell it at a price 25 -> one transaction

We now have: n-4+25 = n+21

But at a price 20, sell at 60 -> second transaction

At this step we have: - 20 + 60.

So in total: n-4+25-20+60 = n + 21 + 40 = n + 61. We are adding 61 to the initial amount he had, so he made a profit of 61.

### Theses:

1) **We can make one transaction only after the previous is done.**

2) **As in real world the time throughout the traders day is linear.** *Yeah, and?*

Consider this example array: {40, 5, 80, 20, 60). Suppose the traders working hours start at 9:00 am and that the first stock (40) is available to be bought at that time. Similarly his day, for example, ends at 17:00 pm and the last stock is available at that hour. Stocks inbetween, obviously, could be operated with in the hours between the start of the day and the end. For example stock with the price 80, say, at 15:00 pm. That means if he buys at first hour the 40-price stock and then sells it at the last hour at a price 60 he then can't do any other stock operations. The day has ended and the maximum profit he has made is n-40+60 -> 20. (But he could have made a profit of 115)

### Solution:

1) Create an array with size **n**. Where **n** is the size of input array (prices). Lets name it *profit*.

   - This array will hold all intermediate results of possible transactions (buy -> sell) that could be made.

2) Create a variable **max** and assing it's value as last element of the input array.

   - This variable will hold the maximum price at which we can sell in a one transaction.

3) Iterate over the input array once starting from the end and do the following:
   - Step 1: *if element at current index is greater than max -> change **max** to it*

   - Step 2: *at the current index of the **profit** array assign maximum of (previous result, maximum - element at current index of the input array) = max(**profit[i + 1]**, **max - prices[i]**)*
   
     What does this step mean? It means that at the current index of **profit** array will be stored the maximum possible profit in range from current index to the end of the array -> i.e from **i to n-1**. In an hours analogy this could be represented as what is maximum possible profit trader could have made in a time range from **i**th hour to the end of the day. Since it is two transactions available and time, as mentioned, is linear we can conditionally split the input array into two halves: first half — first transaction, second half — second transaction. So starting backwards from the last stock (hour) we are calculating the profit from the one of the two transcations.
     
     **At the end of this step we will have at index zero of the **profit** array the maximum possible profit from one transcation. I.e at **profit[0]**.**
     
4) Create variable **min** and initialize it as element at index zero of the input array -> **min = prices[0]**
   - This variable will hold the minimum price at which we can buy in this transaction.

5) Traverse the input array once again from the start and do the following:
   - Step 1: *if element at current index is smaller than min -> change **min** to it*
   
   - Step 2: *at the current index of the **profit** array assign maximum of (previous result, (max profit that has been calculated for this index at step 3 + element at current index of the input array - **min**)) = max(**profit[i - 1]**, **profit[i] + prices[i] - min**)*
   
   To clearly understand what is happening at this step you could use this question: what is going to be the traders max profit if he would had bought **at a price on the current index of the input array and sold at _max_** the stocks during the first transcation and is going to **buy at _min_ and sell at a price of current index of the input array?**
   
   **At the end of this step profit[n-1] will hold the result**

6) **return _profit[n-1]_**

———————————————————————————————————————

**See the java implementaion in the .java source file**
