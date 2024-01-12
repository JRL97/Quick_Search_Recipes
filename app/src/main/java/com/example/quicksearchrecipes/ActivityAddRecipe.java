package com.example.quicksearchrecipes;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

//import com.example.recipeapp1.ui.notifications.NotificationsFragment;

public class ActivityAddRecipe extends AppCompatActivity {

    EditText etRecipeName, etIngredientNo, etCookTime, etInstructions, etIngredients;
    Button btnAddRecipe;
    DBHelper dbHelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addrecipe);
        etRecipeName = findViewById(R.id.etRecipeName);
        etIngredientNo = findViewById(R.id.etIngredientNo);
        etCookTime = findViewById(R.id.etCookTime);
        etInstructions = findViewById(R.id.etInstructions);
        etIngredients = findViewById(R.id.etIngredients);
        dbHelper = new DBHelper(this);
        btnAddRecipe = findViewById(R.id.btnAddRecipe);

        btnAddRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String recipe_name, ingredient_no, cook_time, instructions, ingredients;
                //Toast.makeText(ActivityAddRecipe.this, "Starting", Toast.LENGTH_LONG).show();

                recipe_name = etRecipeName.getText().toString();
                ingredient_no = etIngredientNo.getText().toString();
                cook_time = etCookTime.getText().toString();
                instructions = etInstructions.getText().toString();
                ingredients = etIngredients.getText().toString();
                if (recipe_name.equals("") || ingredient_no.equals("") || cook_time.equals("") || instructions.equals("") || ingredients.equals("")) {
                    Toast.makeText(ActivityAddRecipe.this, "Please Fill In All Available Fields", Toast.LENGTH_LONG).show();
                }

                boolean registeredSuccess = dbHelper.insertData2(recipe_name, ingredient_no, cook_time, instructions, ingredients);
                if(registeredSuccess) {
                    Toast.makeText(ActivityAddRecipe.this, "Recipe Successfully Added", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(ActivityAddRecipe.this, "Recipe Addition Failed", Toast.LENGTH_LONG).show();

            }

        });

    }}
