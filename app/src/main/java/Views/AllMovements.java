package Views;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartsavings.R;

import java.util.ArrayList;

import Database.DBHandler;
import Modal.MovementsModal;

public class AllMovements extends AppCompatActivity {

    private DBHandler dbHandler;
    private TextView TotalSpentValue, TotalGainValue, txtTotal;
    private ArrayList<MovementsModal> movementsModalsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_movements);


        DBHandler dbHandler = new DBHandler(this);
        ArrayList<MovementsModal> movementsList = dbHandler.readMovements();

        TotalSpentValue = findViewById(R.id.txtTotalSpentValue);
        TotalGainValue = findViewById(R.id.txtTotalEarnedValue);
        txtTotal = findViewById(R.id.txtTotal);

        double totalSpend = 0.0;
        double totalGain = 0.0;
        double totalGainSpend = 0.0;


        for(MovementsModal movement : movementsList){

            int option = movement.getOption();
            Log.d("Debug", "Opção do movimento: " + option);

            if (option == 1){
                double valor = Double.parseDouble(movement.getValue());
                totalSpend += valor;
            }
            else if (option == 2) {
                double valor = Double.parseDouble(movement.getValue());
                totalGain += valor;
            }


        }


        String totalSpendString = String.format("%.2f", totalSpend);
        TotalSpentValue.setText(totalSpendString);

        String totalGainString = String.format("%.2f", totalGain);
        TotalGainValue.setText(totalGainString);

        totalGainSpend = totalGain + totalSpend;
        String totalGainSpendString = String.format("%.2f", totalGainSpend);
        txtTotal.setText(totalGainSpendString);

    }


}
