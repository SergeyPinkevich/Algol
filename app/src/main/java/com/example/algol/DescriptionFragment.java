package com.example.algol;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uncopt.android.widget.text.justify.JustifiedTextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DescriptionFragment extends Fragment {

    private JustifiedTextView mTextDescription;
    private TextView mTitleDescription;
    private Context mContext;

    public DescriptionFragment() {
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
        View view = inflater.inflate(R.layout.fragment_description, null);

        mTitleDescription = (TextView) view.findViewById(R.id.description_title);
        mTitleDescription.setText(getArguments().getString(AlgorithmDetailsActivity.NAME));

        mTextDescription = (JustifiedTextView) view.findViewById(R.id.description_text);
        mTextDescription.setText(getArguments().getString(AlgorithmDetailsActivity.DESCRIPTION));
        mTextDescription.setMovementMethod(new ScrollingMovementMethod());

        return view;
    }
}
