package cl.dewebsic.marketplacedeventas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SuperMarketActivity extends AppCompatActivity {


    Button btnRegresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_market_main);


        btnRegresar = findViewById(R.id.btnRegresar);

        btnRegresar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent next = new Intent(SuperMarketActivity.this,MainActivity.class);
                startActivity(next);
            }
        });
    }
}