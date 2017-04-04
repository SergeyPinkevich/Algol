package com.example.algol;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.algol.database.AlgorithmRepo;

import java.util.ArrayList;
import java.util.List;

public class AlgorithmDetailsActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    public static final String DESCRIPTION = "description";
    public static final String NAME = "name";
    public static final String CATEGORY = "category";
    public static final String ALGORITHM_ID = "algorithmId";
    private int algorithmId;
    private AlgorithmRepo mAlgorithmRepo;
    private Algorithm algorithm;

    private DescriptionFragment mDescriptionFragment;
    private InteractionFragment mInteractionFragment;
    private AnalysisFragment mAnalysisFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algorithm_details);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        setTitleToolbar();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        setupViewPager(mViewPager);

        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    public void setTitleToolbar() {
        algorithmId = (Integer)getIntent().getExtras().get(ALGORITHM_ID);

        mAlgorithmRepo = new AlgorithmRepo(this);
        algorithm = mAlgorithmRepo.getAlgorithmById(algorithmId);

        getSupportActionBar().setTitle(algorithm.getName());
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addDescriptionFragment();
        adapter.addInteractionFragment();
        adapter.addAnalysisFragment();
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

        public void addAnalysisFragment() {
            Bundle bundle = new Bundle();
            bundle.putInt(ALGORITHM_ID, algorithmId);

            mAnalysisFragment = new AnalysisFragment();
            mAnalysisFragment.setArguments(bundle);
            mFragmentList.add(mAnalysisFragment);
            mFragmentTitleList.add("ANALYSIS");
        }

        public void addDescriptionFragment() {
            Bundle bundle = new Bundle();
            bundle.putString(DESCRIPTION, algorithm.getDescription());
            bundle.putString(NAME, algorithm.getName());

            mDescriptionFragment = new DescriptionFragment();
            mDescriptionFragment.setArguments(bundle);
            mFragmentList.add(mDescriptionFragment);
            mFragmentTitleList.add("DESCRIPTION");
        }

        public void addInteractionFragment() {
            Bundle bundle = new Bundle();
            bundle.putString(CATEGORY, algorithm.getCategory());

            mInteractionFragment = new InteractionFragment();
            mInteractionFragment.setArguments(bundle);
            mFragmentList.add(mInteractionFragment);
            mFragmentTitleList.add("INTERACTION");
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
 }