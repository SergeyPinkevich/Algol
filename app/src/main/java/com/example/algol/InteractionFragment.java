package com.example.algol;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.example.algol.algoritms.AlgorithmExplanation;
import com.example.algol.algoritms.sorting.BubbleSort;
import com.example.algol.algoritms.sorting.SortingAlgorithm;
import com.example.algol.visualizer.SortingVisualizer;


/**
 * A simple {@link Fragment} subclass.
 */
public class InteractionFragment extends Fragment {

    private FrameLayout mFrameLayout;
    private int[] mArraySorting;
    private ImageButton mStartButton;
    private SortingVisualizer mSortingVisualizer;
    private BubbleSort mAlgorithmExplanation;

    public InteractionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_interaction, container, false);

        mFrameLayout = (FrameLayout) view.findViewById(R.id.container_for_view);
        String category = getArguments().getString(AlgorithmDetailsActivity.CATEGORY);
        switch (category) {
            case "Sorting":
                generateArrayForSorting();
                mSortingVisualizer = new SortingVisualizer(getActivity().getApplicationContext());
                mAlgorithmExplanation = new BubbleSort(mSortingVisualizer, getActivity());
                mFrameLayout.addView(mSortingVisualizer);
                mAlgorithmExplanation.setData(mArraySorting);
                mSortingVisualizer.setData(mArraySorting);
                break;
        }

        mStartButton = (ImageButton) view.findViewById(R.id.start_button);
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlgorithmExplanation.startExecution();
                mAlgorithmExplanation.onCommandReceived(AlgorithmExplanation.COMMAND_START);
            }
        });
        return view;
    }

    public void generateArrayForSorting() {
        mArraySorting = new int[10];
        for (int i = 0; i < mArraySorting.length; i++)
            mArraySorting[i] = (int) (Math.random() * 10) + 1;
    }
}
