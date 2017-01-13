package com.example.algol;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class AlgorithmDetailsActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    public static final String DESCRIPTION = "description";
    public static final String NAME = "name";
    public static final String ALGORITHM_ID = "algorithmId";
    private int algorithmId;

    private DescriptionFragment mDescriptionFragment;
    private CanvasView canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algorithm_details);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        algorithmId = (Integer)getIntent().getExtras().get(ALGORITHM_ID);
        getSupportActionBar().setTitle(Algorithm.algorithms.get(algorithmId).getName());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        setupViewPager(mViewPager);

        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addDescriptionFragment();
        adapter.addFragment(new InteractionFragment(), "INTERACTION");
        adapter.addFragment(new AnalysisFragment(), "ANALYSIS");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addDescriptionFragment() {
            Bundle bundle = new Bundle();
            bundle.putString(DESCRIPTION, Algorithm.algorithms.get(algorithmId).getDescription());
            bundle.putString(NAME, Algorithm.algorithms.get(algorithmId).getName());

            mDescriptionFragment = new DescriptionFragment();
            mDescriptionFragment.setArguments(bundle);
            mFragmentList.add(mDescriptionFragment);
            mFragmentTitleList.add("DESCRIPTION");
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
 }