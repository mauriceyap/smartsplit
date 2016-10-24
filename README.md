#SmartSplit

SmartSplit is a Java library which consolidates a list of debts between 
members of a group, and returns a minimal list of payments due, in order
to satisfy the existing debts in the most efficient way possible.

##Using this tool
The `calculate()` method in `SmartSplitCalculator.java` generates the
list of payments (`Payment` objects) to satisfy a given list of debts 
(`Debt` objects).

Information about the `Debt` and `Payment` objects can be found in their
associated class files, `Debt.java` and `Payment.java` respectively.

##The algorithm
Repeat the following process until all debts have been eliminated:

1. Normalise the list of debts by:
  1. Combining together debts if they are between the same two people
  2. Making all debt amounts positive by swapping round the debtor and
  creditor if the debt is negative
  3. Removing all debts with an amount of zero.
2. Take the largest debt in the list. A new payment will be generated 
from the debtor (D) of this debt to its creditor (C). Remove this debt.
3. Get a list of all the debts to C and remove all these 
from the main list.
4. Add new debts to the main list, from these debtors to the D.
5. Total up the amount of debt to C and make this the amount
of the payment.
6. Generate a payment for this amount from D to C

##Tests
Two test files, `CalculatorTests.java` and `UtilsTests.java` are 
included. They have been used to test the main calculation method and 
all calculator utility methods using the *jUnit* testing framework.

##Why on earth did I do this?
**tl;dr: I got annoyed at Microsoft Excel one evening and this 
happened**

My flatmates and I use an extensive spreadsheet I created where we 
record rent payments, bills and debts to each other for things like 
food and other shopping. All these things are bundled together and 
consolidated into a couple of cells which say something like, *x owes y 
£32.47*.

There were cells like this for every single pair of flatmates. I could
see that there was obviously a simpler solution involving each of us 
paying off a bit of someone else's debt to minimise the number of 
payments to each other which needed to be made to settle all our debts.

I tried to write an algorithm on Excel to do this, but quickly realised
that there is no way to to this without writing a VBA macro.

And so I made this...