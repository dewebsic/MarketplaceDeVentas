package cl.dewebsic.marketplacedeventas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SuperMarketActivity extends AppCompatActivity {


    Button btnRegresar;
    Button btnSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_market_main);

        btnSearch = findViewById(R.id.btnSearch);

        btnRegresar = findViewById(R.id.btnRegresar);


        btnSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent next = new Intent(SuperMarketActivity.this, MapaTienda.class);
                startActivity(next);
            }
        });

    }
}