package com.stylet.nutronx.Activities.ui.Appointments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.stylet.nutronx.Activities.AddAppointment;
import com.stylet.nutronx.Adapter.TabFragmentAdapter;
import com.stylet.nutronx.Fragment.FragmentOne;
import com.stylet.nutronx.Fragment.FragmentThree;
import com.stylet.nutronx.Fragment.FragmentTwo;
import com.stylet.nutronx.R;

public class AppointmentFragment extends Fragment {

    private AppointmentViewModel appointmentViewModel;

    private TabLayout mTabs;
    private View mIndicator;
    private ViewPager mViewPager;

    private int indicatorWidth;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        /*appointmentViewModel =
                ViewModelProviders.of(this).get(AppointmentViewModel.class);*/
        View root = inflater.inflate(R.layout.fragment_appointment, container, false);

        mTabs = root.findViewById(R.id.tab);
        mIndicator = root.findViewById(R.id.indicator);
        mViewPager = root.findViewById(R.id.viewPager);

        //Set up the view pager and fragments
        TabFragmentAdapter adapter = new TabFragmentAdapter(getChildFragmentManager());
        adapter.addFragment(FragmentOne.newInstance(), "Yesterday");
        adapter.addFragment(FragmentTwo.newInstance(), "Today");
        adapter.addFragment(FragmentThree.newInstance(), "Tomorrow");
        mViewPager.setAdapter(adapter);
        mTabs.setupWithViewPager(mViewPager);

        final FloatingActionButton fab = root.findViewById(R.id.addappointmentfab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toAddAppointment = new Intent(getContext(), AddAppointment.class);
                startActivity(toAddAppointment);

            }
        });

        //Determine indicator width at runtime
        mTabs.post(new Runnable() {
            @Override
            public void run() {
                indicatorWidth = mTabs.getWidth() / mTabs.getTabCount();

                //Assign new width
                FrameLayout.LayoutParams indicatorParams = (FrameLayout.LayoutParams) mIndicator.getLayoutParams();
                indicatorParams.width = indicatorWidth;
                mIndicator.setLayoutParams(indicatorParams);
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            //To move the indicator as the user scroll, we will need the scroll offset values
            //positionOffset is a value from [0..1] which represents how far the page has been scrolled
            //see https://developer.android.com/reference/android/support/v4/view/ViewPager.OnPageChangeListener
            @Override
            public void onPageScrolled(int i, float positionOffset, int positionOffsetPx) {
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)mIndicator.getLayoutParams();

                //Multiply positionOffset with indicatorWidth to get translation
                float translationOffset =  (positionOffset+i) * indicatorWidth ;
                params.leftMargin = (int) translationOffset;
                mIndicator.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        /*final TextView textView = root.findViewById(R.id.text_gallery);
        appointmentViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
}