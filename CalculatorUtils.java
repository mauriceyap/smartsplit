package SmartSplit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CalculatorUtils {
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
    return returnDebtList;
  }

  /**
   * Gives all Debt objects a positive value by switching the debtor and
   * creditor in the case of a negative value
   * @param debtList the list of Debt objects
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

  static void swapArrayElements(Object[] array, int elemOne,
                                        int elemTwo) {
    Object temp = array[elemOne];
    array[elemOne] = array[elemTwo];
    array[elemTwo]= temp;
  }
}
