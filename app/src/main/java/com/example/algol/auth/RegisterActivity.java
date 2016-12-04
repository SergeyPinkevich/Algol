package com.example.algol.auth;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.algol.MenuActivity;
import com.example.algol.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText mInputEmail, mInputPassword;
    private Button mButtonSignIn, mButtonSignUp, mButtonResetPass;
    private ProgressBar mBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        mInputEmail = (EditText) findViewById(R.id.email);
        mInputPassword = (EditText) findViewById(R.id.password);
        mButtonSignIn = (Button) findViewById(R.id.sign_in_button);
        mButtonSignUp = (Button) findViewById(R.id.sign_up_button);
        mButtonResetPass = (Button) findViewById(R.id.reset_password_button);
        mBar = (ProgressBar) findViewById(R.id.progress_bar);

        mButtonResetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, ResetPasswordActivity.class));
            }
        });

        mButtonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mInputEmail.getText().toString().trim();
                String password = mInputPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), getString(R.string.enter_email), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), getString(R.string.enter_password), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), getString(R.string.short_password), Toast.LENGTH_SHORT).show();
                    return;
                }

                mBar.setVisibility(View.VISIBLE);

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(RegisterActivity.this, "createUserWithEmail:onComlete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                mBar.setVisibility(View.GONE);

                                if (task.isSuccessful()) {
                                    startActivity(new Intent(RegisterActivity.this, MenuActivity.class));
                                    finish();
                                }
                                else
                                    Toast.makeText(RegisterActivity.this, getString(R.string.auth_failed) + task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBar.setVisibility(View.GONE);
    }
}
