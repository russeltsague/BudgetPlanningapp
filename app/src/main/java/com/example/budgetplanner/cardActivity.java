package com.example.budgetplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class cardActivity extends AppCompatActivity {
    private ConstraintLayout mIncome;
    private ConstraintLayout mExpense;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_card);

       card();
    }
     private void card(){
            mIncome = findViewById(R.id.addIncome);
            mExpense = findViewById(R.id.addExpense);

         mIncome.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity( new Intent(getApplicationContext(), addActivity.class));
             }
         });
         mExpense.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity( new Intent(getApplicationContext(), addActivity.class));
             }
         });
     }
}