package com.example.algol;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.algol.database.AlgorithmRepo;
import com.example.algol.retrofit.AlgolRestApi;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    private Button mStart;
    private TextViewHelvetica mComplexityText;
    private TextViewHelvetica mComplexityPow;
    private TextViewHelvetica mTime;
    private ToggleButton mSortedToggle;
    private ToggleButton mReverseToggle;

    private Algorithm mAlgorithm;

    private int algorithmId;

    public AnalysisFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
    }

    public void APIRequest(int numberElements, int maximumElement, boolean isSorted, boolean reverseOrder) {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://a9fa3249.ngrok.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        sRestApi = mRetrofit.create(AlgolRestApi.class);

        sRestApi.bubbleSort(numberElements, maximumElement, isSorted, reverseOrder).enqueue(new Callback<Double>() {
            Drawable drawable = getResources().getDrawable(R.color.aquamarine);

            @Override
            public void onResponse(Call<Double> call, Response<Double> response) {
                Double val = response.body();
                mTime.setText(getResources().getString(R.string.time) + " " + getTimeFormat(val.longValue()));
                mStart.setEnabled(false);
                mStart.setBackground(drawable);
            }

            @Override
            public void onFailure(Call<Double> call, Throwable t) {
                mStart.setEnabled(false);
                mStart.setBackground(drawable);
                Toast.makeText(getActivity(), R.string.error, Toast.LENGTH_SHORT).show();
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
        initializeToggles(view);

        mTime = (TextViewHelvetica) view.findViewById(R.id.time);

        mStart = (Button) view.findViewById(R.id.start_button);
        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIRequest(mNumberElementsBar.getProgress(), mMaximumElementBar.getProgress(), mSortedToggle.isChecked(), mReverseToggle.isChecked());

                mStart.setEnabled(false);
                Drawable drawable = getResources().getDrawable(R.color.lightGray);
                mStart.setBackground(drawable);
            }
        });

        return view;
    }

    public void initializeToggles(View view) {
        mSortedToggle = (ToggleButton) view.findViewById(R.id.toggle_is_sorted);
        mSortedToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mSortedToggle.isChecked())
                    mReverseToggle.setChecked(false);
            }
        });
        mReverseToggle = (ToggleButton) view.findViewById(R.id.toggle_reverse_order);
        mReverseToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mReverseToggle.isChecked())
                    mSortedToggle.setChecked(true);
            }
        });
    }

    public void initializeTextViews(View view) {
        mComplexityText = (TextViewHelvetica) view.findViewById(R.id.complexity);
        String complexity = mAlgorithm.getComplexity();
        if (complexity.split("_")[0] != null)
            mComplexityText.setText(complexity.split("_")[0]);
        else
            mComplexityText.setText(complexity);

        mComplexityPow = (TextViewHelvetica) view.findViewById(R.id.complexity_pow);
        try {
            mComplexityPow.setText(complexity.split("_")[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    public void initializeSeekbars(View view) {
        mNumberElementsBar = (DiscreteSeekBar) view.findViewById(R.id.seekbar_number_elements);
        mNumberElementsBar.setMax(10000);
        mNumberElementsBar.setProgress(1150);

        mMaximumElementBar = (DiscreteSeekBar) view.findViewById(R.id.seekbar_maximum_element);
        mMaximumElementBar.setMax(10000);
        mMaximumElementBar.setProgress(750);
    }

    public String getTimeFormat(long milliseconds) {
        Date date = new Date(milliseconds);
        SimpleDateFormat format = new SimpleDateFormat("mm:ss:SSS");
        return format.format(date);
    }
}
