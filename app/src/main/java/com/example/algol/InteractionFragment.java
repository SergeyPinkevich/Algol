package com.example.algol;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.algol.visualizer.BaseVisualizer;
import com.example.algol.visualizer.SortingVisualizer;


/**
 * A simple {@link Fragment} subclass.
 */
public class InteractionFragment extends Fragment {

    private FrameLayout mFrameLayout;
    private int[] mArraySorting;

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
                SortingVisualizer visualizer = new SortingVisualizer(getActivity().getApplicationContext());
                visualizer.setData(mArraySorting);
                mFrameLayout.addView(visualizer);
                break;
        }
        return view;
    }

    public void generateArrayForSorting() {
        mArraySorting = new int[10];
        for (int i = 0; i < mArraySorting.length; i++)
            mArraySorting[i] = (int) (Math.random() * 10);
    }
}
