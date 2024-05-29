package com.example.budgetplanner;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class homeActivity extends AppCompatActivity {


        private RecyclerView recyclerView;
        private IncomeAdapter incomeAdapter;
        private ExpenseAdapter expenseAdapter;
        private List<Income> incomeList = new ArrayList<>();
        private List<Expense> expenseList = new ArrayList<>();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            incomeAdapter = new IncomeAdapter(incomeList);
            expenseAdapter = new ExpenseAdapter(expenseList);

            recyclerView.setAdapter(incomeAdapter);

            TextView addIncomeTextView = findViewById(R.id.addIncome);
            addIncomeTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showAddIncomeDialog();
                }
            });

            TextView addExpenseTextView = findViewById(R.id.addExpense);
            addExpenseTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showAddExpenseDialog();
                }
            });
        }

        private void showAddIncomeDialog() {
            // Inflate the add income dialog layout
            View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_income, null);

            // Get references to the views in the dialog
            EditText amountEditText = dialogView.findViewById(R.id.amount);
            EditText noteEditText = dialogView.findViewById(R.id.note);
            EditText categoryEditText = dialogView.findViewById(R.id.category);

            // Create the dialog and set the custom view
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(dialogView);
            AlertDialog dialog = builder.create();

            // Set the positive button to save the income
            dialogView.findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Get the user input from the dialog
                    double amount = Double.parseDouble(amountEditText.getText().toString());
                    String note = noteEditText.getText().toString();
                    String category = categoryEditText.getText().toString();

                    // Create a new Income object and add it to the list
                    Income income = new Income(amount, note, category);
                    incomeList.add(income);

                    // Notify the adapter of the change
                    incomeAdapter.notifyDataSetChanged();

                    // Dismiss the dialog
                    dialog.dismiss();
                }
            });

            // Show the dialog
            dialog.show();
        }

        private void showAddExpenseDialog() {
            // Inflate the add expense dialog layout
            View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_expense, null);

            // Get references to the views in the dialog
            EditText amountEditText = dialogView.findViewById(R.id.amount);
            EditText noteEditText = dialogView.findViewById(R.id.note);
            EditText categoryEditText = dialogView.findViewById(R.id.category);
            RadioGroup typeRadioGroup = dialogView.findViewById(R.id.typeRadio);

            // Create the dialog and set the custom view
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(dialogView);
            AlertDialog dialog = builder.create();

            // Set the positive button to save the expense
            dialogView.findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Get the user input from the dialog
                    double amount = Double.parseDouble(amountEditText.getText().toString());
                    String note = noteEditText.getText().toString();
                    String category = categoryEditText.getText().toString();
                    int selectedRadioButtonId = typeRadioGroup.getCheckedRadioButtonId();
                    boolean isIncome = selectedRadioButtonId == R.id.incomeRadio;

                    // Create a new Expense object and add it to the list
                    Expense expense = new Expense(amount, note, category, isIncome);
                    if (isIncome) {
                        incomeList.add(expense);
                        incomeAdapter.notifyDataSetChanged();
                    } else {
                        expenseList.add(expense);
                        expenseAdapter.notifyDataSetChanged();
                    }

                    // Dismiss the dialog
                    dialog.dismiss();
                }
            });

            // Show the dialog
            dialog.show();
        }
    }
