package com.example.actine.pizza;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView price;
    private RadioGroup group;
    private CheckBox topCheese;
    private CheckBox topMushrooms;
    private Button order;

    private int pizzaPrice;
    private boolean extraCheese;
    private boolean extraMushrooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        group = (RadioGroup) findViewById(R.id.pizza_choice);
        topCheese = (CheckBox) findViewById(R.id.top_cheese);
        topMushrooms = (CheckBox) findViewById(R.id.top_mushrooms);
        price = (TextView) findViewById(R.id.sum);
        order = (Button) findViewById(R.id.order);

        final SparseIntArray pizzaPrices = new SparseIntArray(4);
        pizzaPrices.put(R.id.choice1, 50);
        pizzaPrices.put(R.id.choice2, 60);
        pizzaPrices.put(R.id.choice3, 70);
        pizzaPrices.put(R.id.choice4, 65);

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                pizzaPrice = pizzaPrices.get(checkedId);
                updatePrice();
            }
        });

        topCheese.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                extraCheese = isChecked;
                updatePrice();
            }
        });

        topMushrooms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                extraMushrooms = isChecked;
                updatePrice();
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Дякуємо за замовлення", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updatePrice() {
        int finalPrice = pizzaPrice;
        if (extraCheese) {
            finalPrice += 5;
        }
        if (extraMushrooms) {
            finalPrice += 6;
        }

        price.setText("Сума: " + finalPrice + " грн.");
    }
}
