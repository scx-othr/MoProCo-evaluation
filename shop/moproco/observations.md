## 1 and 4

Tests of `NewOrderTest` initially failed because order UUID was not assigned.
Had to manually add `orderID = ` to the existing UUID generation method call.
After this, tests were executed with success.


