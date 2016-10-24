package SmartSplit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CalculatorTests {
  @Test
  public void singletonDebtListTest() {
    List<Debt> debtList = new ArrayList<>(1);
    debtList.add(new Debt(3, 4, 43f));
    List<Payment> paymentList = SmartSplitCalculator.calculate(debtList);
    assertThat(paymentList.size(), is(1));
    assertThat(paymentList.get(0).getPayer(), is(3));
    assertThat(paymentList.get(0).getPayee(), is(4));
    assertThat(paymentList.get(0).getAmount(), is(43f));
  }

  @Test
  public void twoSizedDebtListTest() {
    List<Debt> debtList = new ArrayList<>(1);
    debtList.add(new Debt(0, 1, 10f));
    debtList.add(new Debt(0, 2, 20f));
    List<Payment> paymentList = SmartSplitCalculator.calculate(debtList);
    assertThat(paymentList.size(), is(2));
    assertThat(paymentList.get(0).getPayer(), is(0));
    assertThat(paymentList.get(0).getPayee(), is(1));
    assertThat(paymentList.get(0).getAmount(), is(10f));
    assertThat(paymentList.get(1).getPayer(), is(0));
    assertThat(paymentList.get(1).getPayee(), is(2));
    assertThat(paymentList.get(1).getAmount(), is(20f));
  }

  @Test
  public void sevenSizedRandomDebtListTest() {
    final int NUMBER_OF_GROUP_MEMBERS = 7;
    List<Debt> debtList
        = new ArrayList<>((NUMBER_OF_GROUP_MEMBERS
        * (NUMBER_OF_GROUP_MEMBERS - 1)) / 2);
    float[] balancesOfEachPersonBefore = {0, 0, 0, 0, 0, 0, 0};
    float[] balancesOfEachPersonAfter = {0, 0, 0, 0, 0, 0, 0};

    // Create random debts between all seven members of the group and record
    // their balances each. i is debtor, j is creditor
    for (int i = 0; i < NUMBER_OF_GROUP_MEMBERS; i++) {
      for (int j = i; j < NUMBER_OF_GROUP_MEMBERS; j++) {
        Random randomAmount = new Random();
        // Amount for each debt is a random value between -50 and 49 inclusive
        float amount = (float) (randomAmount.nextInt(100) - 50);
        debtList.add(new Debt(i, j, amount));
        // Debtor's balance adjustment
        balancesOfEachPersonBefore[i] -= amount;
        // Creditor's balance adjustment
        balancesOfEachPersonBefore[j] += amount;
      }
    }

    List<Payment> paymentList = SmartSplitCalculator.calculate(debtList);

    // Take generated payments and use them to adjust the balances of members
    // after making and receiving their payments
    for (Payment thisPayment : paymentList) {
      int payer = thisPayment.getPayer();
      int payee = thisPayment.getPayee();
      float amount = thisPayment.getAmount();
      balancesOfEachPersonAfter[payer] -= amount;
      balancesOfEachPersonAfter[payee] += amount;
    }

    // Check that balances of each person are the same before and after the
    // payments being made
    for (int i = 0; i < NUMBER_OF_GROUP_MEMBERS; i++) {
      assertThat(balancesOfEachPersonBefore[i],
          is(balancesOfEachPersonAfter[i]));
    }
  }
}
