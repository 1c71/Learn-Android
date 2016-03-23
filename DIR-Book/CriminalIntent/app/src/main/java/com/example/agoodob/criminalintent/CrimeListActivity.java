package com.example.agoodob.criminalintent;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

public class CrimeListActivity extends SingleFragmentActivity
    implements CrimeListFragment.Callbacks, CrimeFragment.Callbacks{

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_masterdetail; // 引用了 res/values/refs.xml 里
    }

    /*
        当点击时，看下右边有没有 detail 显示的Fragment
        没有就跳到 Pager 那边

        有就切换
     */
    @Override
    public void onCrimeSelected(Crime crime) {

        // 如果没这个 detail... 那就是单屏
        if (findViewById(R.id.detailfragmentContainer) == null) {
            Intent i  = new Intent(this, CrimePagerActivity.class);
            i.putExtra(CrimeFragment.EXTRA_CRIME_ID, crime.getId());
            startActivity(i);
        } else {
            android.support.v4.app.FragmentManager
                    fm = getSupportFragmentManager();

            android.support.v4.app.FragmentTransaction
                    ft = fm.beginTransaction();

            Fragment oldDetail = fm.findFragmentById(R.id.detailfragmentContainer);
            Fragment newDetail = CrimeFragment.newInstance(crime.getId());

            if(oldDetail != null){
                ft.remove(oldDetail);
            }
            ft.add(R.id.detailfragmentContainer, newDetail);
            ft.commit();
        }
    }

    @Override
    public void onCrimeUpdated(Crime crime) {
        android.support.v4.app.FragmentManager
                fm = getSupportFragmentManager();
        CrimeListFragment listFragment = (CrimeListFragment) fm.findFragmentById(R.id.fragmentContainer);
        listFragment.updateUI();
    }
}
