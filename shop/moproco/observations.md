## 1

Tests of `NewOrderTest` initially failed because order UUID was not assigned.
In the constructor of `Order`, it was necessary to manually add `orderID = ` to the existing UUID generation method call.
After this, tests were executed with success.

## 4

Tests of `NewOrderTest` initially failed because order UUID was not assigned.
In the constructor of `Order`, it was necessary to manually add `orderID = ` to the existing UUID generation method call.
After this, tests were executed with success.

