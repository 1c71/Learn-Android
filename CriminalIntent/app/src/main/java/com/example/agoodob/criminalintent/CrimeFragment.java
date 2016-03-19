package com.example.agoodob.criminalintent;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.Date;
import java.util.UUID;

/**
 * 单个 Crime 的 Activity
 *
 *
 * 注意 Fragment 的 onCreate 是 public
 * 而 Activity 的 onCreate 是 protected
 *
 * 因为 Fragment 的 onCreate 需要给 Activity 调用所以需要是 public
 */
public class CrimeFragment extends Fragment {

    public static final String EXTRA_CRIME_ID = "com.bignerdranch.android.crime_id";
    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;
    private static final String DIALOG_DATE = "date";
    private static final int REQUEST_DATE = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCrime = new Crime();
        /*
        * 注意和视图的连接不是在这里完成的，
        * Activity 是在 onCreate 里 setContentView
        * 而 Fragment 是 onCreateView
        * */

        UUID crimId = (UUID) getArguments().getSerializable(EXTRA_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstnceState) {

        View v = inflater.inflate(R.layout.fragment_crime, container, false);
        // 第三个 false 参数代表我们将通过 Activity 代码方式添加视图

        mTitleField = (EditText) v.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getTitlte());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitlte(s.toString()); //  设置数据。设置到 model 里去了
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mDateButton = (Button) v.findViewById(R.id.crime_date);
        updateDate();
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentManager fm = getActivity().getSupportFragmentManager();
                DatePickerFragment dialogFragment = DatePickerFragment.newInstance(mCrime.getDate());
                dialogFragment.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
                // 设置目标 Fragment 用于返回数据
                dialogFragment.show(fm, DIALOG_DATE);
            }
        });

        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mCrime.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(isChecked);
            }
        });

        return v;

    }

    public static CrimeFragment newInstance(UUID crimId){
        // 搞个 Bundle
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_CRIME_ID, crimId);

        // 新 Fragment & 设置 Argument
        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);

        return fragment;
    }


    public void returnResult(){
        getActivity().setResult(Activity.RESULT_OK, null);
    }

    /**
     * 处理收到的返回数据
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != Activity.RESULT_OK) return;
        if(requestCode == REQUEST_DATE) {
            //如果相等, 代表是从选日期那个界面返回的结果

            // 获取到返回的数据并且设置给 当前的单个Crime
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mCrime.setDate(date);

            updateDate();
        }
    }

    public void updateDate() {
        mDateButton.setText(mCrime.getDate().toString());
    }
}
