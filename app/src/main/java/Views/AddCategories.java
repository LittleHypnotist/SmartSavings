package Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartsavings.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import Database.DBHandler;

public class AddCategories extends AppCompatActivity {

    private EditText editTextDescCat;
    private Button buttonAddCat;

    private DBHandler dbHandler;

    private BottomNavigationView bottomNavigationView;

    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_categories);

        editTextDescCat = findViewById(R.id.idEditTextDescCat);
        buttonAddCat = findViewById(R.id.buttonAddCat);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        floatingActionButton = findViewById(R.id.addMovement);

        dbHandler = new DBHandler(AddCategories.this);

        buttonAddCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String description = editTextDescCat.getText().toString();


                dbHandler.addNewCategory(description);


            }
        });

        bottomNavigationView.setBackground(null);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.all_movements){
                    Intent intent1 = new Intent(AddCategories.this, AllMovements.class);
                    startActivity(intent1);
                }
                return false;
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddCategories.this, AddMovements.class);
                startActivity(intent);
            }
        });


    }
}
