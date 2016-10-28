package org.uab.dedam.todoman;


import android.os.Bundle;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;


public class HomeActivity extends AppCompatActivity {


    boolean tablet;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_home);


            tablet = getResources().getBoolean(R.bool.Tablet);

            if (tablet) {

                FirstFragment firstFragment = new FirstFragment();
                SecondFragment secondFragment = new SecondFragment();

                FragmentManager fragMgr = getSupportFragmentManager();

                FragmentTransaction fragmentTransaction = fragMgr.beginTransaction();
                fragmentTransaction.add(R.id.FrameLayout1, firstFragment);
                fragmentTransaction.add(R.id.FrameLayout2, secondFragment);

                fragmentTransaction.commit();


            }

            else

            {


                FirstFragment firstFragment = new FirstFragment();
                FragmentManager fragMgr = getSupportFragmentManager();

                FragmentTransaction fragmentTransaction = fragMgr.beginTransaction();
                fragmentTransaction.add(R.id.FrameHolder, firstFragment);
                fragmentTransaction.commit();



            }


        }





    }