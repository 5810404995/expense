//นายณัฐภัทร ชาญธีระเดช 5810404995

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StepDefMoneyBook {
    MoneyBook moneyBook;

    @Before
    public void init(){
        moneyBook = new MoneyBook();
    }

    @Given("a user with income (\\d+) and expense (\\d+)")
    public void a_user_with_balance_exists(int income, int expense) throws NotEnoughBalanceException{
        moneyBook.earnMoney(income);
        moneyBook.payMoney(expense);
    }

    @When("I earn money (\\d+) from my mother")
    public void i_earn_money_from_my_mother(int money){
        moneyBook.earnMoney(money);
    }

    @When("I pay (\\d+) to buy book")
    public void i_pay_money_amount_less_than_balance(int money) throws NotEnoughBalanceException{
        moneyBook.payMoney(money);
    }

    @When("I pay (\\d+) to buy smartphone")
    public void i_pay_money_amount_more_than_balance(int money) throws NotEnoughBalanceException{
        assertThrows(NotEnoughBalanceException.class,
                () -> moneyBook.payMoney(5000));
    }

    @When("I edit income in index (\\d+) to (\\d+)")
    public void i_edit_income_in_index_to(int index, int newValue){
        moneyBook.editIncome(index, newValue);
    }

    @When("I edit expense in index (\\d+) to (\\d+)")
    public void i_edit_expense_in_index_to(int index, int newValue){
        moneyBook.editExpense(index, newValue);
    }

    @Then("my money book balance is (\\d+)")
    public void my_money_book_balance_is(int balance){
        assertEquals(balance, moneyBook.getBalance());
    }



}

