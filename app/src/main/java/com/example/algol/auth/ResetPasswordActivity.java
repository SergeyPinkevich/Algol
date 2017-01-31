package com.example.algol.auth;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.algol.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {

    private TextView mTitle;
    private Typeface mTitleFont;
    private EditText mInputEmail;
    private Button mButtonBack, mButtonResetPass;
    private ProgressBar mBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        mTitle = (TextView) findViewById(R.id.app_title);
        mTitleFont = Typeface.createFromAsset(getAssets(), "Julius.ttf");
        mTitle.setTypeface(mTitleFont);

        mAuth = FirebaseAuth.getInstance();
        mInputEmail = (EditText) findViewById(R.id.email);
        mButtonBack = (Button) findViewById(R.id.button_back);
        mButtonResetPass = (Button) findViewById(R.id.reset_password_button);
        mBar = (ProgressBar) findViewById(R.id.progress_bar);

        mButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mButtonResetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mInputEmail.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), getString(R.string.enter_email), Toast.LENGTH_SHORT).show();
                    return;
                }

                mBar.setVisibility(View.VISIBLE);
                mAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful())
                                    Toast.makeText(ResetPasswordActivity.this, getString(R.string.success_reset), Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(ResetPasswordActivity.this, getString(R.string.failed_reset), Toast.LENGTH_SHORT).show();
                                mBar.setVisibility(View.GONE);
                            }
                        });
            }
        });
    }
}
