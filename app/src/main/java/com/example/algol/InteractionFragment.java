package com.example.algol;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.algol.visualizer.SortingVisualizer;


/**
 * A simple {@link Fragment} subclass.
 */
public class InteractionFragment extends Fragment {

    private FrameLayout mFrameLayout;

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
        int[] array = new int[10];
        for (int i = 0; i < array.length; i++)
            array[i] = (int) (Math.random() * 10);
        SortingVisualizer visualizer = new SortingVisualizer(getActivity().getApplicationContext());
        visualizer.setData(array);
        mFrameLayout.addView(visualizer);

        return view;
    }

}
