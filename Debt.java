package SmartSplit;

public class Debt implements Comparable {
  private int debtor;
  private int creditor;
  private float amount;

  public Debt(int debtor, int creditor, float amount) {
    this.debtor = debtor;
    this.creditor = creditor;
    this.amount = amount;
  }

  int getDebtor() {
    return debtor;
  }

  int getCreditor() {
    return creditor;
  }

  float getAmount() {
    return amount;
  }

  void setAmount(float amount) {
    this.amount = amount;
  }

  void addAmount(float amount) {
    this.amount += amount;
  }

  /**
   * Swap the creditor and debtor and negate the amount
   */
  void reverse() {
    int oldCreditor = creditor;
    creditor = debtor;
    debtor = oldCreditor;
    amount *= -1;
  }

  @Override
  public String toString() {
    String amountString = amount < 0 ? "-£" : "£";
    amountString += amount < 0 ? -amount : amount;
    return "Person " + debtor + " owes person " + creditor + " " + amountString;
  }

  @Override
  public int compareTo(Object o) {
    assert o instanceof Debt : "Debts can only be compared to other debts";
    Debt compareDebt = (Debt) o;

    // Equal amounts
    if (compareDebt.getAmount() == amount) {
      return 0;
    }

    // Different amounts
    return compareDebt.getAmount() > amount ? -1 : 1;
  }

  @Override
  public boolean equals(Object otherObject) {
    if (otherObject instanceof Debt) {
      Debt otherDebt = (Debt) otherObject;
      return debtor == otherDebt.getDebtor()
          && creditor == otherDebt.getCreditor()
          && amount == otherDebt.getAmount();
    }
    return false;
  }

  @Override
  public int hashCode() {
    return ((31 * debtor) + (47 * creditor)) * (53 * ((int) amount));
  }
}
