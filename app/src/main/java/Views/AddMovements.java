package Views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartsavings.R;
import Database.DBHandler;

public class AddMovements extends AppCompatActivity {

    private EditText editTextDesc, editTextValue;
    //private Spinner spinnerCat;
    private Button buttonAdd;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_movements);

        editTextDesc = findViewById(R.id.idEditTextDesc);
        editTextValue = findViewById(R.id.idEditTextValue);
        buttonAdd = findViewById(R.id.buttonAdd);



        dbHandler = new DBHandler(AddMovements.this);


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String desc = editTextDesc.getText().toString();
                Float value = Float.parseFloat(editTextValue.getText().toString());



                dbHandler.addNewCourse(desc, value);



            }
        });


    }
}