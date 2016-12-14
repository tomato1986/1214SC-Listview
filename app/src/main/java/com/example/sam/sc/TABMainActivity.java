package com.example.sam.sc;
//Kota created 12/13/2016

import android.content.Context;
//import android.content.Intent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;
public class TABMainActivity extends FragmentActivity implements TabHost.OnTabChangeListener {
    // TabHost
    private TabHost mTabHost;
    // Last selected tabId
    private String mLastTabId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabhost);
      //  setContentView(R.layout.activity_main);
      //  setContentView(R.layout.main);
        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup();

        /* Tab1 setting */
        TabSpec tab1 = mTabHost.newTabSpec("tab1");
        tab1.setIndicator("TAB1");
        tab1.setContent(new DummyTabFactory(this));
        mTabHost.addTab(tab1);

        // Tab2 setting
        TabSpec tab2 = mTabHost.newTabSpec("tab2");
        tab2.setIndicator("TAB2");
        tab2.setContent(new DummyTabFactory(this));
        mTabHost.addTab(tab2);

        // �^�u�ύX���C�x���g�n���h��
        mTabHost.setOnTabChangedListener(this);

        // initial tab
        onTabChanged("tab2");
    }
    public void onTabChanged(String tabId) {
        Log.d("TAB_FRAGMENT_LOG","tabId:" + tabId);
        if(mLastTabId != tabId){
            FragmentTransaction fragmentTransaction
                    = getSupportFragmentManager().beginTransaction();
            if("tab1" == tabId){
                fragmentTransaction
                        .replace(R.id.realtabcontent, new Fragment1());
              //  fragmentTransaction
              //            .replace(R.id.realtabcontent, new BTMainActivity());
                Intent intent = new Intent(this,BTMainActivity.class);
                startActivity(intent);
            }else if("tab2" == tabId){

                fragmentTransaction
                            .replace(R.id.realtabcontent, new Fragment2());
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
            }
            mLastTabId = tabId;
            fragmentTransaction.commit();
        }
    }
    private static class DummyTabFactory implements TabContentFactory {

        /* Context */
        private final Context mContext;

        DummyTabFactory(Context context) {
            mContext = context;
        }

        @Override
        public View createTabContent(String tag) {
            View v = new View(mContext);
            return v;
        }
    }

}