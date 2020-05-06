COL106 Assignment 2 README
Rohit Agarwal
2018EE10494

This assignment is simulation of a seller and buyer system using multithreading.

Buyers buy items which are available in the catalog, which basically stores all items currently up for sale.
Higher priority(numerically smallest) items are sold first.
The sellers have a shared list called inventory where all the items are stored.
Each buyer can put items from inventory to catalog and thus make them available to the customer.

Node implementation:
It basically stores an Item object and it priority. The getValue method returns the Item object and the getPriority method returns the priority.

Implementation of inventory through Queue:
I have implemented a circular queue using the front and rear variables given already.
front tells the index from where the element is to be taken out from and rear tells the index where a item is to be added.
Initially both of them are set to 0.
enqueue: First checks whether queue is full or not; if not then adds an element to the array at (rear % capacity)th index and increments rear by 1.
dequeue: First checks whether queue is full or not; if not the returns the array element stored at (front % capacity)th index and increments front by 1. If already empty then returns null (But this case never happens because before calling dequeue in Buyer, I check whether queue is empty or not).

Implementation of catalog through PriorityQueue:
This too is implemented in circular fashion. I created two int variables - front and rear, and initialised them to 0.
enqueue: same as queue's enqueue. O(1)
dequeue: I find out the index of the item with numerically smallest priority value by starting a for loop for i = front to (front + currentSize) and check the priority of item stored at (i % capacity)th index.
Then I swap values stored at this index and the front index. Then I return this value and increment front by 1. O(n)

Buyer buy() method:
First I acquire the lock.
Then in try block I first check whether catalog is empty or not, if yes then I put the thread to empty.await().
If the catalog is not empty or the thread has resumed then it dequeues an item from the catalog and stored it in a variable n which is then ised to print out the "Consumed" message.
Then I signal a seller thread waiting on full condition to resume(full.signal).
Then in fianlly block I release the lock.

Seller sell() method:
First I acquire the lock.
Then in try block I first check whether catalog is full or not, if yes then I put the thread to full.await().
Then I check whether the inventory is empty or not, if yes then just execute a return; to end the sell() method(to avoid dequeueing from empty inventory afterwards) but before that I first signal all the other seller threads waiting on full condition so that no thread remains awaiting a signal at end of program.
If thread is not empty then I dequeue an item from the inventory, typecast it to Node type from NodeBase type and then enqueue it to the catalog.
Then I signal a buyer thread waiting on empty condition to resume(empty.signal).
Then I release the lock in the finally method.

Note: There were to ways to structure the await and signal calls.
One was to use signalAll and put the await statements in a while loop (we cannot use a if block here as then all the threads will resume and error like trying to enqueue a full catalog may occur).
The other was to use just signal and put the await statements in a if block or a while loop (does not matter in this case).
Either way should work and I used signal and while loops.