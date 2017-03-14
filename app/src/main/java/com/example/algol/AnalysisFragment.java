package com.example.algol;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.algol.database.AlgorithmRepo;
import com.example.algol.retrofit.AlgolRestApi;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

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

    private DiscreteSeekBar mNumberElementsBar;
    private DiscreteSeekBar mMaximumElementBar;

    private TextViewHelvetica mComplexityText;
    private TextViewHelvetica mNumberElementsText;
    private TextViewHelvetica mMaximumElementText;

    private Algorithm mAlgorithm;

    private int algorithmId;

    public AnalysisFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
    }

    public void APIRequest(int numberElements, int maximumElement) {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://e5b8f2e7.ngrok.io/")
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

    private void initializeAlgorithm() {
        algorithmId = getArguments().getInt(AlgorithmDetailsActivity.ALGORITHM_ID);
        AlgorithmRepo repo = new AlgorithmRepo(getActivity());
        mAlgorithm = repo.getAlgorithmById(algorithmId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_analysis, container, false);

        initializeAlgorithm();
        initializeTextViews(view);
        initializeSeekbars(view);

        Button button = (Button) view.findViewById(R.id.start_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int numberElements = mNumberElementsBar.getProgress();
                final int maximumElement = mMaximumElementBar.getProgress();
                APIRequest(numberElements, maximumElement);
            }
        });

        return view;
    }

    public void initializeTextViews(View view) {
        mComplexityText = (TextViewHelvetica) view.findViewById(R.id.complexity);
        mComplexityText.setText(Html.fromHtml(mAlgorithm.getComplexity()));
        mNumberElementsText = (TextViewHelvetica) view.findViewById(R.id.number_elements);
        mMaximumElementText = (TextViewHelvetica) view.findViewById(R.id.maximum_element);
    }

    public void initializeSeekbars(View view) {
        mNumberElementsBar = (DiscreteSeekBar) view.findViewById(R.id.seekbar_number_elements);
        mNumberElementsBar.setMax(1000);
        mNumberElementsBar.setProgress(250);

        mMaximumElementBar = (DiscreteSeekBar) view.findViewById(R.id.seekbar_maximum_element);
        mMaximumElementBar.setMax(1000);
        mMaximumElementBar.setProgress(750);
    }
}
