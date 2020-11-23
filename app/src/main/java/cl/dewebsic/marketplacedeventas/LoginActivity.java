package cl.dewebsic.marketplacedeventas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    Button btnRegresar;
    TextView txtRegister;
    EditText emailText;
    EditText passwordText;
    Button btnIngresar;

    private String email = "";
    private String password = "";

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        btnRegresar = findViewById(R.id.btnRegresar);

        txtRegister = findViewById(R.id.register);

        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);
        btnIngresar = findViewById(R.id.btnIngresar);

        btnIngresar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                email = emailText.getText().toString();
                password = passwordText.getText().toString();

                if(!email.isEmpty() && !password.isEmpty()){
                    loginUser();
                }else{

                    Toast.makeText(LoginActivity.this,"Ingrese su credenciales",Toast.LENGTH_SHORT);
                }

            }
        });

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

    //metodo para iniciar sesion
    private void loginUser(){

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                 if(task.isSuccessful()){

                     startActivity(new Intent(LoginActivity.this,MainActivity.class));
                 }else{
                     Toast.makeText(LoginActivity.this,"credenciales no validas",Toast.LENGTH_SHORT);
                 }
            }
        });
    }
}