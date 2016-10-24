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