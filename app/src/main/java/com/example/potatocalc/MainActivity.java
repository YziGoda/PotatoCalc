package com.example.potatocalc;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.potatocalc.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.tvResult.setText("");
    }
    double buffer = 0;
    public String btn_operator = "";

    public void btn_number(View view)
    {
        binding.tvResult.append(((Button) view).getText().toString());
        binding.tvFormula.append(((Button) view).getText().toString());
        buffer = 0;
    }

    public void btn_operation(View view)
    {
        btn_operator = ((Button) view).getText().toString();
        binding.tvFormula.append(btn_operator);
        binding.tvResult.setText("");
        buffer = 0;
    }
    public void btn_equal(View view)
    {
        String formula = binding.tvFormula.getText().toString();
        String[] parts = formula.split("[+\\-*/=]");
        double op1 = Double.parseDouble(parts[0]);
        double op2 = Double.parseDouble(parts[1]);
        char operator = btn_operator.charAt(0);
        calculate(op1, operator, op2);
    }
    public void calculate(double op1, char operator, double op2)
    {
        switch (operator)
        {
            case '+':
                buffer = op1 + op2;
                break;

            case '-':
                buffer = op1 - op2;
                break;

            case '*':
                buffer = op1 * op2;
                break;

            case '/':
                if (op2 != 0)
                {
                    buffer = op1 / op2;
                    break;
                }
                else
                {
                    Toast.makeText(this,"Dalyba is 0 negalima", Toast.LENGTH_SHORT).show();
                    binding.tvResult.setText("");
                    break;
                }
        }
        binding.tvResult.setText(Double.toString(buffer));
        binding.tvFormula.setText("");
        btn_operator = "";
    }
    public void rushb(View view) // TODO atm works
    {
        String currentText = binding.tvResult.getText().toString();
        String formulaText = binding.tvFormula.getText().toString();
        if (currentText.length() > 0)
        {
            currentText = currentText.substring(0, currentText.length() - 1);
            formulaText = formulaText.substring(0, formulaText.length() - 1);
        }
        else
        {
            currentText = "0.0";
        }

        binding.tvResult.setText(currentText);
        binding.tvFormula.setText(formulaText);

        if (!currentText.equals(""))
        {
            buffer = Double.parseDouble(currentText);
        }
        else
        {
            buffer = 0;
        }
    }

    public void mistake(View view) // TODO atm works
    {
        String formula = binding.tvFormula.getText().toString();
        StringBuilder modifiedFormula = new StringBuilder();
        String[] parts = formula.split("[+\\-*/=]");

        if(parts.length>0)
        {
            for (int i =0; i< parts.length-i ; i++) // viska isskyrus last
            {
                modifiedFormula.append(parts[i]);
            }
            String newData = modifiedFormula.toString();
            binding.tvFormula.setText(newData);
            binding.tvResult.setText("");
        }
        else
        {
            binding.tvFormula.setText("");
        }
    }

    public void nuke(View view) // TODO atm works
    {
        binding.tvResult.setText("");
        binding.tvFormula.setText("");
        buffer = 0;
    }

    public void btn_dot(View view) //TODO atm works
    {
        if (!binding.tvResult.getText().toString().contains("."))
        {
            binding.tvResult.append(((Button) view).getText().toString());
            if (!binding.tvFormula.getText().toString().isEmpty())
            {
                binding.tvFormula.append(((Button) view).getText().toString());
            }
            else
            {
                binding.tvFormula.append("0" + ((Button) view).getText().toString());
            }
        }
        else
        {
            Toast.makeText(this, "Antro tasko deti negalima",Toast.LENGTH_SHORT).show();
        }
    }
    public void btn_sqr_root(View view) // TODO close enough
    {
        String result = binding.tvResult.getText().toString();
        double number = Double.parseDouble(result);
        buffer = Math.sqrt(number);

        binding.tvResult.setText(Double.toString(buffer));
        binding.tvFormula.setText("");
    }
}