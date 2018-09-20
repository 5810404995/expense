//นายณัฐภัทร ชาญธีระเดช 5810404995

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UnitTestMoneyBook {
    MoneyBook moneyBook;

    @BeforeEach
    void init() throws NotEnoughBalanceException {
        moneyBook = new MoneyBook();
        moneyBook.earnMoney(150);
        moneyBook.payMoney(50);
        moneyBook.setBalance(moneyBook.getBalance()); // Balance = 100
    }

    @Test
    void testEarnMoney() {
        moneyBook.earnMoney(50);
        assertEquals(150, moneyBook.getBalance());
        assertEquals(200, moneyBook.getIncome());
        assertEquals(50, moneyBook.getExpense());
        assertEquals(moneyBook.showMoneyBook(), "Balance : " + 150 +"\nIncome : " + 200 +"\nExpense : " + 50);
    }

    @Test
    void testPayMoney() throws NotEnoughBalanceException {
        moneyBook.payMoney(50);
        assertEquals(50, moneyBook.getBalance());
        assertEquals(150, moneyBook.getIncome());
        assertEquals(100, moneyBook.getExpense());
        assertEquals(moneyBook.showMoneyBook(), "Balance : " + 50 +"\nIncome : " +150 +"\nExpense : " + 100);
    }

    @Test
    @DisplayName("throws NotEnoughBalanceException when pay money more than balance")
    void testPayMoneyMoreThanBalance() {
        assertThrows(NotEnoughBalanceException.class,
                () -> moneyBook.payMoney(10000));
        assertEquals(100, moneyBook.getBalance());
        assertEquals(150, moneyBook.getIncome());
        assertEquals(50, moneyBook.getExpense());
        assertEquals(moneyBook.showMoneyBook(), "Balance : " + 100 +"\nIncome : " + 150 +"\nExpense : " + 50);
    }

    @Test
    void testEditIncome(){
        moneyBook.editIncome(0, 200);
        int incomeIndex0 = moneyBook.setOfIncome.get(0);
        int expenseIndex0 = moneyBook.setOfExpense.get(0);

        assertEquals(150, moneyBook.getBalance());
        assertEquals(200, incomeIndex0);
        assertEquals(50, expenseIndex0);
        assertEquals(200, moneyBook.getIncome());
        assertEquals(50, moneyBook.getExpense());
    }

    @Test
    void testEditExpense(){
        moneyBook.editExpense(0, 100);
        int incomeIndex0 = moneyBook.setOfIncome.get(0);
        int expenseIndex0 = moneyBook.setOfExpense.get(0);

        assertEquals(50, moneyBook.getBalance());
        assertEquals(150, incomeIndex0);
        assertEquals(100, expenseIndex0);
        assertEquals(150, moneyBook.getIncome());
        assertEquals(100, moneyBook.getExpense());
    }
}
