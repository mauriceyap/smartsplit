package SmartSplit;

import java.util.ArrayList;
import java.util.List;

public class SmartSplitCalculator {
  /**
   * Generates a list of Payments required in order to satisfy all the given
   * Debts in the most efficient way possible
   *
   * @param debtList the list of Debts which are to be satisfied
   * @return the list of Payments to be made in order to satisfy the given Debts
   */
  public static List<Payment> calculate(List<Debt> debtList) {
    debtList = CalculatorUtils.consolidateDebts(debtList);
    List<Payment> paymentList = new ArrayList<>();

    // Eliminate Debts by creating payments until the list of Debts is empty
    while (!debtList.isEmpty()) {
      Payment newPayment = (generatePaymentFromDebts(debtList));
      paymentList.add(newPayment);
      // Consolidate the main list of Debts to account for any Debts added with
      // the same debtor-creditor combination as an existing Debt
      debtList = CalculatorUtils.consolidateDebts(debtList);
    }

    return paymentList;
  }

  private static Payment generatePaymentFromDebts(List<Debt> debtList) {
    // Find the largest existing Debt in the main list
    Debt largestDebt = CalculatorUtils.largestDebtFrom(debtList);
    int payee = largestDebt.getCreditor();
    int payer = largestDebt.getDebtor();

    // Get a list of Debts to the payee of this Payment and remove all of these
    // Debts from the main list of existing Debts
    List<Debt> debtsToPayee
        = CalculatorUtils.extractDebtsToCreditorFromList(debtList, payee);

    // Remove the payer's Debt to the payee
    float totalDebtToPayee = largestDebt.getAmount();
    debtsToPayee.remove(largestDebt);

    // Re-add the Debts removed from the main list as new debts to the payer of
    // this Payment. Total up the amounts of all these debts so they can be
    // resolved in this Payment
    for (Debt debtToPayee : debtsToPayee) {
      int debtor = debtToPayee.getDebtor();
      float amount = debtToPayee.getAmount();
      debtList.add(new Debt(debtor, payer, amount));
      totalDebtToPayee += amount;
    }

    return new Payment(payer, payee, totalDebtToPayee);
  }
}
