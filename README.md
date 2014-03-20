CarWarehouse
===========

An abstract warehouse to store cars and find intervals with max number of cars in warehouse.

In the warehouse each car is received, stored for several days & then it is sent out to next destination. For each car in the warehouse we have receiving date & date it is dispatched (if car has already left the warehouse).
Need to find time interval within all periods when we had maximum cars in warehouse.
For the car which has not left warehouse yet - we assume it is stored till today since we are looking for maximum
value as of today.