//นายณัฐภัทร ชาญธีระเดช 5810404995

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UnitTestMoneyBook {
    MoneyBook moneyBook;
    int initialBalance = 100;

    @BeforeEach
    void init() { moneyBook = new MoneyBook(initialBalance); }

    @Test
    void testEarnMoney() {
        moneyBook.earnMoney(50);
        assertEquals(150, moneyBook.getBalance());
        assertEquals(50, moneyBook.getIncome());
        assertEquals(0, moneyBook.getExpense());
        assertEquals(moneyBook.showMoneyBook(), "Balance : " + 150 +"\nIncome : " + 50 +"\nExpense : " + 0);
    }

    @Test
    void testPayMoney() throws NotEnoughBalanceException {
        moneyBook.payMoney(40);
        assertEquals(60, moneyBook.getBalance());
        assertEquals(0, moneyBook.getIncome());
        assertEquals(40, moneyBook.getExpense());
        assertEquals(moneyBook.showMoneyBook(), "Balance : " + 60 +"\nIncome : " + 0 +"\nExpense : " + 40);
    }

    @Test
    @DisplayName("throws NotEnoughBalanceException when pay money more than balance")
    void testPayMoneyMoreThanBalance() {
        assertThrows(NotEnoughBalanceException.class,
                () -> moneyBook.payMoney(10000));
        assertEquals(initialBalance, moneyBook.getBalance());
        assertEquals(0, moneyBook.getIncome());
        assertEquals(0, moneyBook.getExpense());
        assertEquals(moneyBook.showMoneyBook(), "Balance : " + 100 +"\nIncome : " + 0 +"\nExpense : " + 0);
    }
}
