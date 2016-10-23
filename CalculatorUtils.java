package SmartSplit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CalculatorUtils {

  /**
   * Finds all the Debts in a given list to a particular creditor, collates them
   * into a list which is returned, and removes these from the given list
   *
   * @param debtList the list of Debts from which Debts should be extracted
   * @param creditor the creditor of the Debts which should be extracted
   * @return the list of extracted Debts
   */
  static List<Debt> extractDebtsToCreditorFromList(List<Debt> debtList, int creditor) {
    List<Debt> debtsToCreditor = new ArrayList<>();
    Iterator<Debt> debtIterator = debtList.iterator();
    while (debtIterator.hasNext()) {
      Debt debt = debtIterator.next();
      if (debt.getCreditor() == creditor) {
        debtsToCreditor.add(debt);
        debtIterator.remove();
      }
    }
    return debtsToCreditor;
  }

  /**
   * Finds the Debt with the largest amount in a list
   *
   * @param debtList the list of Debts
   * @return the Debt in the list with the largest amount
   */
  static Debt largestDebtFrom(List<Debt> debtList) {
    Iterator<Debt> debtIterator = debtList.iterator();
    Debt largestDebt = debtIterator.next();
    while (debtIterator.hasNext()) {
      Debt thisDebt = debtIterator.next();
      if (thisDebt.compareTo(largestDebt) == 1) {
        largestDebt = thisDebt;
      }
    }
    return largestDebt;
  }

  /**
   * Consolidate Debts into a list consisting of no more than one Debt for
   * each pair of people, where all debts are non-zero and positive
   *
   * @param debtList the list of Debts to process
   * @return the list of consolidated Debts
   */
  static List<Debt> consolidateDebts(List<Debt> debtList) {
    List<Debt> returnDebtList = new ArrayList<>();

    // For each Debt in the list, search for any existing Debts between the same
    // two people. If one exits, add them together.
    for (Debt oldDebt : debtList) {
      boolean debtHasBeenProcessed = false;
      for (Debt newDebt : returnDebtList) {
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
   *
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
   *
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
}
