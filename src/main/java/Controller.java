//นายณัฐภัทร ชาญธีระเดช 5810404995

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class Controller {
    @FXML
    private TextField income;
    @FXML
    private TextField expense;
    @FXML
    private Label showBalance;

    private MoneyBook moneyBook = new MoneyBook();

    @FXML
    public void earnMoney(ActionEvent e){
        int money = Integer.parseInt(income.getText());
        if (money < 0){
            showBalance.setTextFill(Color.RED);
            showBalance.setText("Please enter a positive integer."+"\nBalance : "+moneyBook.getBalance());
            income.clear();
        }else{
            moneyBook.earnMoney(money);
            showBalance.setTextFill(Color.BLUE);
            showBalance.setText(moneyBook.showMoneyBook());
            income.clear();
        }
    }

    @FXML
    public void payMoney(ActionEvent e) throws NotEnoughBalanceException{
        int money = Integer.parseInt(expense.getText());
        if (money > moneyBook.getBalance()){
            showBalance.setTextFill(Color.RED);
            showBalance.setText("Your money is not enough to pay."+"\nPlease try again."+"\nBalance : "+moneyBook.getBalance());
            expense.clear();
        }else{
            if (money < 0){
                showBalance.setTextFill(Color.RED);
                showBalance.setText("Please enter a positive integer."+"\nBalance : "+moneyBook.getBalance());
                expense.clear();
            }else{
                moneyBook.payMoney(money);
                showBalance.setTextFill(Color.BLUE);
                showBalance.setText(moneyBook.showMoneyBook());
                expense.clear();
            }
        }
    }
}

