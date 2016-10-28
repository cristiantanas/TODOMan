package org.uab.dedam.todoman;


import android.os.Bundle;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;


public class HomeActivity extends AppCompatActivity {




        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_home);

            If



            FirstFragment firstFragment = new FirstFragment();
            FragmentManager fragMgr = getSupportFragmentManager();

            FragmentTransaction fragmentTransaction = fragMgr.beginTransaction();
            fragmentTransaction.add(R.id.FrameHolder, firstFragment);
            fragmentTransaction.commit();

            else


            FirstFragment firstFragment = new FirstFragment();
            FragmentManager fragMgr = getSupportFragmentManager();

            FragmentTransaction fragmentTransaction = fragMgr.beginTransaction();
            fragmentTransaction.add(R.id.FramLayout1, firstFragment);
            fragmentTransaction.commit();





            SecondFragment secondFragment = new SecondFragment();
            FragmentManager fragMgr = getSupportFragmentManager();

            FragmentTransaction fragmentTransaction = fragMgr.beginTransaction();
            fragmentTransaction.add(R.id.FrameLayout2, secondFragment);
            fragmentTransaction.commit();





        }





    }