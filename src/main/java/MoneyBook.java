//นายณัฐภัทร ชาญธีระเดช 5810404995

public class MoneyBook {
    private int balance = 0;
    private int income = 0;
    private int expense = 0;

    public MoneyBook(){}

    public MoneyBook(int initialBalance){
        this.balance += initialBalance;
    }

    public void earnMoney(int money){
        if (money < 0){
        }else{
            this.balance += money;
            this.income +=money;
        }
    }

    public void payMoney(int money) throws NotEnoughBalanceException{
        if (money > this.balance){
            throw new NotEnoughBalanceException("Your money is not enough to pay");
        }else{
            if (money < 0){
            }else{
                this.balance -= money;
                this.expense += money;}
        }
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

}

