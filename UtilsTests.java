package SmartSplit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UtilsTests {
  @Test
  public void consolidateDebtsSingleton() {
    List<Debt> inputDebtList = new ArrayList<>(1);
    inputDebtList.add(new Debt(2, 1, 20.0f));
    List <Debt> outputDebtList
        = CalculatorUtils.consolidateDebts(inputDebtList);
    assertThat(outputDebtList.size(), is(1));
    assertTrue(outputDebtList.contains(new Debt(2, 1, 20.0f)));
  }

  @Test
  public void consolidateDebtsSingletonDuplicates() {
    List<Debt> inputDebtList = new ArrayList<>(4);
    inputDebtList.add(new Debt(0, 1, 4f));
    inputDebtList.add(new Debt(0, 1, 2.1f));
    inputDebtList.add(new Debt(1, 0, 42f));
    inputDebtList.add(new Debt(0, 1, 69f));
    List <Debt> outputDebtList
        = CalculatorUtils.consolidateDebts(inputDebtList);
    assertThat(outputDebtList.size(), is(1));
    assertTrue(outputDebtList.contains(new Debt(0, 1, 33.1f)));
  }

  @Test
  public void consolidateDebtsAllUniqueAndSomeNegative() {
    List<Debt> inputDebtList = new ArrayList<>(4);
    inputDebtList.add(new Debt(0, 1, 1f));
    inputDebtList.add(new Debt(0, 2, 4f));
    inputDebtList.add(new Debt(1, 3, 2f));
    inputDebtList.add(new Debt(2, 1, -6f));
    List <Debt> outputDebtList
        = CalculatorUtils.consolidateDebts(inputDebtList);
    assertThat(outputDebtList.size(), is(4));
    assertTrue(outputDebtList.contains(new Debt(0, 1, 1f)));
    assertTrue(outputDebtList.contains(new Debt(0, 2, 4f)));
    assertTrue(outputDebtList.contains(new Debt(1, 3, 2f)));
    assertTrue(outputDebtList.contains(new Debt(1, 2, 6f)));
  }

  @Test
  public void consoldateDebtsDuplicates() {
    List<Debt> inputDebtList = new ArrayList<>(5);
    inputDebtList.add(new Debt(0, 1, -1f));
    inputDebtList.add(new Debt(0, 2, 4f));
    inputDebtList.add(new Debt(1, 0, -2f));
    inputDebtList.add(new Debt(2, 0, 4f));
    inputDebtList.add(new Debt(2, 1, -6f));
    List <Debt> outputDebtList
        = CalculatorUtils.consolidateDebts(inputDebtList);
    assertThat(outputDebtList.size(), is(2));
    assertTrue(outputDebtList.contains(new Debt(0, 1, 1f)));
    assertTrue(outputDebtList.contains(new Debt(1, 2, 6f)));
  }

  @Test
  public void makeAllPositiveTests() {
    List<Debt> inputDebtList = new ArrayList<>(3);
    inputDebtList.add(new Debt(1, 2, -3f));
    inputDebtList.add(new Debt(0, 1, 30f));
    inputDebtList.add(new Debt(0, 2, -300f));
    List<Debt> outputDebtList = CalculatorUtils.makeAllPositive(inputDebtList);
    assertThat(outputDebtList.size(), is(3));
    assertTrue(outputDebtList.contains(new Debt(2, 1, 3f)));
    assertTrue(outputDebtList.contains(new Debt(0, 1, 30f)));
    assertTrue(outputDebtList.contains(new Debt(2, 0, 300f)));
  }

  @Test
  public void removeAllZeroDebtsStartingZero() {
    List<Debt> inputDebtList = new ArrayList<>(3);
    inputDebtList.add(new Debt(1, 2, 0f));
    inputDebtList.add(new Debt(0, 1, 0f));
    inputDebtList.add(new Debt(0, 2, 40f));
    CalculatorUtils.removeAllZeroDebts(inputDebtList);
    assertTrue(inputDebtList.size() == 1);
    assertTrue(inputDebtList.contains(new Debt(0, 2, 40f)));
  }

  @Test
  public void removeAllZeroDebtsEndingZero() {
    List<Debt> inputDebtList = new ArrayList<>(4);
    inputDebtList.add(new Debt(1, 2, 1f));
    inputDebtList.add(new Debt(0, 1, 0f));
    inputDebtList.add(new Debt(0, 2, 40f));
    inputDebtList.add(new Debt(0, 3, 0f));
    CalculatorUtils.removeAllZeroDebts(inputDebtList);
    assertTrue(inputDebtList.size() == 2);
    assertTrue(inputDebtList.contains(new Debt(1, 2, 1f)));
    assertTrue(inputDebtList.contains(new Debt(0, 2, 40f)));
  }
}
