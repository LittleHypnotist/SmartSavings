package Views;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartsavings.R;

import Database.DBHandler;

public class AddCategories extends AppCompatActivity {

    private EditText editTextDescCat;
    private Button buttonAddCat;

    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_categories);

        editTextDescCat = findViewById(R.id.idEditTextDescCat);
        buttonAddCat = findViewById(R.id.buttonAddCat);

        dbHandler = new DBHandler(AddCategories.this);

        buttonAddCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String description = editTextDescCat.getText().toString();


                dbHandler.addNewCategory(description);


            }
        });


    }
}
