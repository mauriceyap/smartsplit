package SmartSplit;

import java.util.List;

public class SmartSplitCalculator {
  public List<Payment> calculate(List<Debt> debtList) {
    // Consolidate Debts into a list consisting of no more than one Debt for
    // each pair of people, where all debts are non-zero and positive
    debtList = CalculatorUtils.consolidateDebts(debtList);

    return null;
  }
}
