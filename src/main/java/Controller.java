//นายณัฐภัทร ชาญธีระเดช 5810404995

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Controller {
    @FXML
    private TextField income;
    @FXML
    private TextField expense;
    @FXML
    private TextField textFieldForEditIncome;
    @FXML
    private TextField textFieldForEditExpense;

    @FXML
    private TextArea textAreaIncome;
    @FXML
    private TextArea textAreaExpense;

    @FXML
    private Label showBalance;

    @FXML
    private Label checkIndex;

    private MoneyBook moneyBook = new MoneyBook();

    @FXML
    public void initialize() {
        int income = 0;
        int expense = 0;
        textAreaIncome.setEditable(false);
        textAreaExpense.setEditable(false);
        try {
            Path fileIncome = Paths.get("D:\\income.txt");
            BufferedReader readerIncome = Files.newBufferedReader(fileIncome ,
                    StandardCharsets.UTF_8);
            String lineIncome = null;
            while ((lineIncome = readerIncome.readLine()) != null) {
                moneyBook.setOfIncome.add(Integer.parseInt(lineIncome));
                income += Integer.parseInt(lineIncome);
            }
            readerIncome.close();

            Path fileExpense = Paths.get("D:\\expense.txt");
            BufferedReader readerExpense = Files.newBufferedReader(fileExpense ,
                    StandardCharsets.UTF_8);
            String lineExpense = null;
            while ((lineExpense = readerExpense.readLine()) != null) {
                moneyBook.setOfExpense.add(Integer.parseInt(lineExpense));
                expense += Integer.parseInt(lineExpense);
            }
            readerExpense.close();
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }

        if (income != 0){
            moneyBook.setIncome(income);
            moneyBook.setExpense(expense);
            moneyBook.setBalance(income - expense);
            showBalance.setTextFill(Color.BLUE);
            showBalance.setText(moneyBook.showMoneyBook());
            textAreaIncome.setText(moneyBook.getSetOfIncome().toString());
            textAreaExpense.setText(moneyBook.getSetOfExpense().toString());
        }
    }

    @FXML
    public void earnMoney(ActionEvent e) {
        checkIndex.setText("");
        int money = Integer.parseInt(income.getText());
        if (money <= 0) {
            showBalance.setTextFill(Color.RED);
            showBalance.setText("Please enter a positive integer." + "\nBalance : " + moneyBook.getBalance());
            income.clear();
        } else {
            moneyBook.earnMoney(money);
            showBalance.setTextFill(Color.BLUE);
            showBalance.setText(moneyBook.showMoneyBook());
            income.clear();

            try {
                Path file = Paths.get("D:\\income.txt");
                BufferedWriter writer = Files.newBufferedWriter(file,
                        StandardCharsets.UTF_8, StandardOpenOption.APPEND);

                writer.write(money + "");
                writer.newLine();
                writer.close();
                textAreaIncome.setText(moneyBook.setOfIncome.toString());
            } catch (IOException ex) {
                System.err.println("IOException: " + ex.getMessage());
                //income ครั้งแรก
                try {
                    Path file = Paths.get("D:\\income.txt");
                    BufferedWriter writer = Files.newBufferedWriter(file,
                            StandardCharsets.UTF_8);

                    writer.write(money+"");
                    writer.newLine();
                    writer.close();
                    textAreaIncome.setText(moneyBook.setOfIncome.toString());
                } catch (IOException ex2) {
                    System.err.println("IOException: " + ex2.getMessage());
                }
            }
        }
    }

    @FXML
    public void payMoney(ActionEvent e) throws NotEnoughBalanceException{
        checkIndex.setText("");
        int money = Integer.parseInt(expense.getText());
        if (money > moneyBook.getBalance()){
            showBalance.setTextFill(Color.RED);
            showBalance.setText("Your money is not enough to pay."+"\nPlease try again."+"\nBalance : "+moneyBook.getBalance());
            expense.clear();
        }else{
            if (money <= 0){
                showBalance.setTextFill(Color.RED);
                showBalance.setText("Please enter a positive integer."+"\nBalance : "+moneyBook.getBalance());
                expense.clear();
            }else{
                moneyBook.payMoney(money);
                showBalance.setTextFill(Color.BLUE);
                showBalance.setText(moneyBook.showMoneyBook());
                expense.clear();

                try {
                    Path file = Paths.get("D:\\expense.txt");
                    BufferedWriter writer = Files.newBufferedWriter(file,
                            StandardCharsets.UTF_8, StandardOpenOption.APPEND);
                    writer.write(money + "");
                    writer.newLine();
                    writer.close();
                    textAreaExpense.setText(moneyBook.setOfExpense.toString());
                } catch (IOException ex) {
                    System.err.println("IOException: " + ex.getMessage());
                    //expense ครั้งแรก
                    try {
                        Path file = Paths.get("D:\\expense.txt");
                        BufferedWriter writer = Files.newBufferedWriter(file,
                                StandardCharsets.UTF_8);
                        writer.write(money+"");
                        writer.newLine();
                        writer.close();
                        textAreaExpense.setText(moneyBook.setOfExpense.toString());
                    } catch (IOException ex2) {
                        System.err.println("IOException: " + ex2.getMessage());
                    }
                }
            }
        }
    }

    @FXML
    public void editIncome(ActionEvent e){
        checkIndex.setText("");
        int income = 0;

        String[] edit = textFieldForEditIncome.getText().split("=");
        int index = Integer.parseInt(edit[0]);
        int newValue = Integer.parseInt(edit[1]);

        if (index >= moneyBook.setOfIncome.size()){
            checkIndex.setText("Please enter an index for edit income < "+ moneyBook.setOfIncome.size());
            textFieldForEditIncome.clear();
            if (moneyBook.setOfIncome.size() == 0){
                checkIndex.setText("ยังไม่มีรายรับ");
            }
        }else {
            moneyBook.editIncome(index, newValue);

            try {
                Path file = Paths.get("D:\\income.txt");
                BufferedWriter writer = Files.newBufferedWriter(file,
                        StandardCharsets.UTF_8);
                for (int i = 0; i < moneyBook.setOfIncome.size(); i++) {
                    income += moneyBook.setOfIncome.get(i);
                    writer.write(moneyBook.setOfIncome.get(i) + "");
                    writer.newLine();
                }
                writer.close();
            } catch (IOException ex) {
                System.err.println("IOException: " + ex.getMessage());
            }

            textAreaIncome.setText(moneyBook.getSetOfIncome().toString());

            moneyBook.setIncome(income);
            textFieldForEditIncome.clear();
            showBalance.setText(moneyBook.showMoneyBook());
            showBalance.setTextFill(Color.BLUE);
        }
    }

    @FXML
    public void editExpense(ActionEvent e) {
        checkIndex.setText("");
        int expense = 0;

        String[] edit = textFieldForEditExpense.getText().split("=");
        int index = Integer.parseInt(edit[0]);
        int newValue = Integer.parseInt(edit[1]);

        if (index >= moneyBook.setOfExpense.size()) {
            checkIndex.setText("Please enter an index for edit expense < " + moneyBook.setOfExpense.size());
            textFieldForEditExpense.clear();
            if (moneyBook.setOfExpense.size() == 0) {
                checkIndex.setText("ยังไม่มีรายจ่าย");
            }
        } else {
            moneyBook.editExpense(index, newValue);

            try {
                Path file = Paths.get("D:\\expense.txt");
                BufferedWriter writer = Files.newBufferedWriter(file,
                        StandardCharsets.UTF_8);
                for (int i = 0; i < moneyBook.setOfExpense.size(); i++) {
                    expense += moneyBook.setOfExpense.get(i);
                    writer.write(moneyBook.setOfExpense.get(i) + "");
                    writer.newLine();
                }
                writer.close();
            } catch (IOException ex) {
                System.err.println("IOException: " + ex.getMessage());
            }
            textAreaExpense.setText(moneyBook.getSetOfExpense().toString());

            moneyBook.setExpense(expense);
            textFieldForEditExpense.clear();
            showBalance.setText(moneyBook.showMoneyBook());
            showBalance.setTextFill(Color.BLUE);
        }
    }
}


