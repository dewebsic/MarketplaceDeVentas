package cl.dewebsic.marketplacedeventas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    /* campos */
    private EditText password;
    private EditText name;
    private EditText email;
    private EditText passwordConfirmation;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        password = findViewById(R.id.passwordText);
        passwordConfirmation = findViewById(R.id.passwordRepitText);
        email = findViewById(R.id.emailText);
        name = findViewById(R.id.nameText);
        btnRegister = findViewById(R.id.registerBtn);

        btnRegister.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                registerUser(view);
            }
        });

    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    public void registerUser(View view){

        //validamos la confirmacion de password
        if(password.getText().toString().equals(passwordConfirmation.getText().toString())){

            //ingresamos el emial y el correo para crear usuario para la autentifocacion
            mAuth.createUserWithEmailAndPassword(this.email.getText().toString(),this.password.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information

                                Map<String,Object> map = new HashMap<>();
                                map.put("name", name.getText().toString());
                                map.put("email", email.getText().toString());
                                map.put("password", password.getText().toString());
                                String id = mAuth.getCurrentUser().getUid();

                                // escuhamos el ingreso de de los datos del usuario a registrar
                                mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task2) {

                                        //validamos si no hubo errores
                                        if(task2.isSuccessful()){

                                            Toast.makeText(getApplicationContext(),"Se ha registrado correctamente",Toast.LENGTH_SHORT).show();
                                            FirebaseUser user = mAuth.getCurrentUser();

                                            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                            startActivity(intent);

                                        }else{

                                                // If sign in fails, display a message to the user.

                                                Toast.makeText(getApplicationContext(), "Authentication failed.",
                                                        Toast.LENGTH_SHORT).show();
                                                //updateUI(null);

                                        }
                                    }
                                });



                                //updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.

                                Toast.makeText(getApplicationContext(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }

                            // ...
                        }
                    });


        }else{

            Toast.makeText(this,"La contraseña no coinciden",Toast.LENGTH_SHORT).show();
        }


    }
}