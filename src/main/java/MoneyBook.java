//นายณัฐภัทร ชาญธีระเดช 5810404995

import java.util.ArrayList;

public class MoneyBook {
    private int balance = 0;
    private int income = 0;
    private int expense = 0;

    ArrayList<Integer> setOfIncome = new ArrayList<>();
    ArrayList<Integer> setOfExpense = new ArrayList<>();

    public MoneyBook(){}

    public MoneyBook(int initialBalance){
        this.balance += initialBalance;
    }

    public void earnMoney(int money){
        if (money < 0){
        }else{
            this.balance += money;
            this.income +=money;
            setOfIncome.add(money);
        }
    }

    public void payMoney(int money) throws NotEnoughBalanceException{
        if (money > this.balance){
            throw new NotEnoughBalanceException("Your money is not enough to pay");
        }else{
            if (money < 0){
            }else{
                this.balance -= money;
                this.expense += money;
                setOfExpense.add(money);
            }
        }
    }

    public void editIncome(int index, int newValue){
        int oldValue = setOfIncome.get(index);

        this.income -= oldValue;
        this.income += newValue;
        this.balance -= oldValue;
        this.balance += newValue;

        setOfIncome.set(index, newValue);
    }

    public void editExpense(int index, int newValue){
        int oldValue = setOfExpense.get(index);

        this.expense -= oldValue;
        this.expense += newValue;
        this.balance += oldValue;
        this.balance -= newValue;

        setOfExpense.set(index, newValue);
    }

    public String showMoneyBook(){
        return "Balance : " + this.balance+"\nIncome : " + this.income+"\nExpense : " + this.expense;
    }

    public int getBalance() {
        return balance;
    }

    public int getIncome() {
        return income;
    }

    public int getExpense() {
        return expense;
    }

    public ArrayList<Integer> getSetOfIncome() {
        return setOfIncome;
    }

    public ArrayList<Integer> getSetOfExpense() {
        return setOfExpense;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public void setExpense(int expense) {
        this.expense = expense;
    }
}

