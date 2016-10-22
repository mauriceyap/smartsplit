package SmartSplit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CalculatorUtils {
  /**
   * Consolidate Debts into a list consisting of no more than one Debt for
   * each pair of people, where all debts are non-zero and positive
   * @param debtList the list of Debts to process
   * @return the list of consolidated Debts
   */
  static List<Debt> consolidateDebts(List<Debt> debtList) {
    List<Debt> returnDebtList = new ArrayList<>();

    // For each Debt in the list, search for any existing Debts between the same
    // two people. If one exits, add them together.
    for (Debt oldDebt : debtList) {
      boolean debtHasBeenProcessed = false;
      for (Debt newDebt: returnDebtList) {
        if (newDebt.getDebtor() == oldDebt.getDebtor()
            && newDebt.getCreditor() == oldDebt.getCreditor()) {
          newDebt.addAmount(oldDebt.getAmount());
          debtHasBeenProcessed = true;
          break;
        }
        if (newDebt.getDebtor() == oldDebt.getCreditor()
            && newDebt.getCreditor() == oldDebt.getDebtor()) {
          newDebt.addAmount(-oldDebt.getAmount());
          debtHasBeenProcessed = true;
          break;
        }
      }
      if (!debtHasBeenProcessed) {
        returnDebtList.add(oldDebt);
      }
    }
    returnDebtList = makeAllPositive(returnDebtList);
    removeAllZeroDebts(returnDebtList);
    return returnDebtList;
  }

  /**
   * Removes all the Debts in a list with an amount of zero
   * @param debtList The list of Debts which may contain Debts with a zero
   *                 amount
   */
  static void removeAllZeroDebts(List<Debt> debtList) {
    for (int i = 0; i < debtList.size(); i++) {
      if (debtList.get(i).getAmount() == 0) {
        debtList.remove(i);
        i--;
      }
    }
  }

  /**
   * Gives all Debt objects a positive value by switching the debtor and
   * creditor in the case of a negative value
   * @param debtList the list of Debts
   */
  static List<Debt> makeAllPositive(List<Debt> debtList) {
    for (Debt debt : debtList) {
      if (debt.getAmount() < 0) {
        debt.reverse();
      }
    }
    return debtList;
  }

  /**
   * Sorts a list of Debts by descending order according to value
   * @param debtList the list of Debts to be sorted
   */
  static void orderDebtsDescending(List<Debt> debtList) {
    Collections.sort(debtList);
    Collections.reverse(debtList);
  }
}
