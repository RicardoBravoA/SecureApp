package com.rba.secureapp.main;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import com.rba.secureapp.R;
import com.rba.secureapp.base.BaseActivity;
import com.rba.secureapp.util.DeviceUtil;
import com.scottyab.rootbeer.RootBeer;

/**
 * Created by Ricardo Bravo on 14/12/2017.
 */

public class MainActivity extends BaseActivity implements BaseActivity.AlertDialogListener {

    private static final int CODE_CLOSE = 100;
    private static final int CODE_DISMISS = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setAlertDialogListener(this);

        RootBeer rootBeer = new RootBeer(this);
        if(rootBeer.isRooted()){
            showMessageDialog(getString(R.string.message_root), CODE_CLOSE);
        }else if(!DeviceUtil.checkAppSignature(this)){
            showMessageDialog(getString(R.string.message_hash), CODE_CLOSE);
        }else if(DeviceUtil.isSecure(this)){
            showMessageDialog(getString(R.string.message_success), CODE_DISMISS);
        }else{
            showMessageDialog(getString(R.string.message_store), CODE_CLOSE);
        }
    }

    @Override
    public void onClickDialog(int code) {
        if(code == CODE_CLOSE){
            finish();
        }
    }
}