package Views;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartsavings.R;

import java.util.ArrayList;

import Database.DBHandler;
import Modal.MovementsModal;

public class AllMovements extends AppCompatActivity {

    private DBHandler dbHandler;
    private TextView TotalSpentValue;
    private ArrayList<MovementsModal> movementsModalsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_movements);


        DBHandler dbHandler = new DBHandler(this);
        ArrayList<MovementsModal> movementsList = dbHandler.readMovements();

        TextView TotalSpentValue = findViewById(R.id.txtTotalSpentValue);

        double totalSpend = 0.0;

        for(MovementsModal movement : movementsList){
            double valor = Double.parseDouble(movement.getValue());
            totalSpend += valor;
        }

        String totalSpendString = String.format("%.2f", totalSpend);
        TotalSpentValue.setText(totalSpendString);



    }


}
