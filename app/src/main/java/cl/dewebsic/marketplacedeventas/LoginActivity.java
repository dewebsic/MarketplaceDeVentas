package cl.dewebsic.marketplacedeventas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    Button btnRegresar;
    TextView txtRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnRegresar = findViewById(R.id.btnRegresar);

        txtRegister = findViewById(R.id.register);

        btnRegresar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent next = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(next);
            }
        });

        txtRegister.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent next = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(next);
            }
        });
    }
}