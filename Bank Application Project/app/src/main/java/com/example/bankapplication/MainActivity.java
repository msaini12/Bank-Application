package com.example.bankapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Bank bank;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bank = new Bank();
    }
    private void setContentsOfTextView(int id, String newContents){
        View view = findViewById(id);
        TextView textView = (TextView) view;
        textView.setText(newContents);
    }
    private String getInputOfTextField(int id){
        View view = findViewById(id);
        EditText editText = (EditText) view;
        String input = editText.getText().toString();
        return input;
    }

    private String getItemSelected(int id){
        View view = findViewById(id);
        Spinner spinner = (Spinner) view;
        String string = spinner.getSelectedItem().toString();
        return string;
    }

    public void createAccount(View view){
        String clientName = getInputOfTextField(R.id.inputName);
        String clientBalance = getInputOfTextField(R.id.inputBalance);

        double balance = Double.parseDouble(clientBalance);

        bank.addClient(clientName, balance);

        setContentsOfTextView(R.id.labelOutput, bank.getOutput());
    }
    public void completeTransaction(View view){
        String service = getItemSelected(R.id.spinnerService);
        String nameTo = getInputOfTextField(R.id.inputTo);
        String nameFrom = getInputOfTextField(R.id.inputFrom);
        String SAmount = getInputOfTextField(R.id.inputAmount);

        double amount = Double.parseDouble(SAmount);

        bank.setTransaction(service, nameFrom, nameTo, amount);

        setContentsOfTextView(R.id.labelOutput, bank.getTransaction());
    }
    public void outputStatement(View view){
        String name = getInputOfTextField(R.id.inputOutputName);

        bank.setOutputStatement(name);

        setContentsOfTextView(R.id.labelOutput, bank.getOutputStatement());
    }
}
