package com.example.algol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.algol.retrofit.AlgolRestApi;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewAlgorithmActivity extends AppCompatActivity {

    @BindView(R.id.editText_title)
    EditText mTitle;
    @BindView(R.id.editText_description)
    EditText mDescription;
    @BindView(R.id.submit_button)
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_algorithm);
        ButterKnife.bind(this);

        mButton.setOnClickListener(v -> {
                    Callback<String> callback = createCallbackForRetrofit();
                    String title = mTitle.getEditableText().toString();
                    String description = mDescription.getEditableText().toString();
                    MenuActivity.sRestApi.newAlgorithm(title, description, "sergeipinkevich163@gmail.com").enqueue(callback);
                    Intent intent = new Intent(this, MenuActivity.class);
                    startActivity(intent);
                }
        );
    }

    public Callback<String> createCallbackForRetrofit() {
        return new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String answer = response.body();
                Toast.makeText(getApplicationContext(), answer, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getApplicationContext(), R.string.error, Toast.LENGTH_SHORT).show();
            }
        };
    }
}