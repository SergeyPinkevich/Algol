package com.example.algol;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.algol.retrofit.AlgolRestApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnalysisFragment extends Fragment {

    private static AlgolRestApi sRestApi;
    private Retrofit mRetrofit;

    private SeekBar mNumberElementsBar;
    private SeekBar mMaximumElementBar;

    private TextViewHelvetica mNumberElementsText;
    private TextViewHelvetica mMaximumElementText;

    public AnalysisFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
    }

    public void APIRequest(int numberElements, int maximumElement) {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://http://8b95a086.ngrok.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        sRestApi = mRetrofit.create(AlgolRestApi.class);

        sRestApi.bubbleSort(numberElements, maximumElement).enqueue(new Callback<Double>() {
            @Override
            public void onResponse(Call<Double> call, Response<Double> response) {
                Double val = response.body();
                Toast.makeText(getActivity(), String.valueOf(val), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Double> call, Throwable t) {
                Toast.makeText(getActivity(), "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_analysis, container, false);

        initializeTextViews(view);
        initializeSeekbars(view);
        final int numberElements = mNumberElementsBar.getProgress();
        final int maximumElement = mMaximumElementBar.getProgress();

        Button button = (Button) view.findViewById(R.id.start_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIRequest(numberElements, maximumElement);
            }
        });

        return view;
    }

    public void initializeTextViews(View view) {
        mNumberElementsText = (TextViewHelvetica) view.findViewById(R.id.number_elements);
        mMaximumElementText = (TextViewHelvetica) view.findViewById(R.id.maximum_element);
    }

    public void initializeSeekbars(View view) {
        mNumberElementsBar = (SeekBar) view.findViewById(R.id.seekbar_number_elements);
        mNumberElementsBar.setMax(1000);
        mNumberElementsBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mNumberElementsText.setText(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mMaximumElementBar = (SeekBar) view.findViewById(R.id.seekbar_maximum_element);
        mMaximumElementBar.setMax(1000);
        mMaximumElementBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mMaximumElementText.setText(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
