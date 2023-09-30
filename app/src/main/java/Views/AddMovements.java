package Views;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartsavings.R;

import java.util.Calendar;
import java.util.Date;

import Database.DBHandler;

public class AddMovements extends AppCompatActivity {

    private EditText editTextDesc, editTextValue;
    //private Spinner spinnerCat;
    private Button buttonAdd, buttonPass;
    private DBHandler dbHandler;
    private TextView textViewDate;
    private RadioGroup radioGroupOption;

    private RadioButton radioButtonSpent, radioButtonGain;
    private DatePickerDialog.OnDateSetListener onDateSetListener;

    private Toolbar toolbarMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_movements);

        editTextDesc = findViewById(R.id.idEditTextDesc);
        editTextValue = findViewById(R.id.idEditTextValue);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonPass = findViewById(R.id.buttonPass);
        textViewDate = findViewById(R.id.textViewSelectDay);
        radioGroupOption = findViewById(R.id.groupRadioButtons);
        radioButtonSpent = findViewById(R.id.radioButtonSpent);
        radioButtonGain = findViewById(R.id.radioButtonGain);
        toolbarMenu = findViewById(R.id.toolbar);


        setSupportActionBar(toolbarMenu);

        textViewDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AddMovements.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        onDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                //Log.d(TAG, "onDataSet: mm/dd/yyy: " + day + "/" + month + "/" + year );

                month = month + 1;

                String date = month + "/" + day + "/" + year;
                textViewDate.setText(date);
            }
        };

        dbHandler = new DBHandler(AddMovements.this);


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int option = 0;

                String desc = editTextDesc.getText().toString();
                Float value = Float.parseFloat(editTextValue.getText().toString());
                String day = textViewDate.getText().toString();


                if (radioGroupOption.getCheckedRadioButtonId() == R.id.radioButtonSpent){
                    value = -value;
                    option= 1;
                }
                else{
                    option = 2;
                }



                dbHandler.addNewMovement(desc, value, day, option);


            }
        });

        buttonPass.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent i = new Intent(AddMovements.this, AllMovements.class);
                startActivity(i);

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if (item.getTitle().equals("Categories")){
            Intent i = new Intent(AddMovements.this, AllMovements.class);
            startActivity(i);
        }

        return true;
    }
}