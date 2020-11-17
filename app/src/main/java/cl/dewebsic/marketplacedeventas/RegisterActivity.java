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

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    /* campos */
    private EditText password;
    private EditText email;
    private EditText passwordConfirmation;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        password = findViewById(R.id.passwordText);
        passwordConfirmation = findViewById(R.id.passwordRepitText);
        email = findViewById(R.id.emailText);
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

            mAuth.createUserWithEmailAndPassword(this.email.getText().toString(),this.password.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information

                                Toast.makeText(getApplicationContext(),"Se ha registrado correctamente",Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mAuth.getCurrentUser();

                                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);

                                startActivity(intent);

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