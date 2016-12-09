package com.barranquero.manageproductsrecycler;

import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by usuario on 1/12/16.
 */
public class HomeActivity extends AppCompatActivity implements ListProductFragment.ListProductListener, ManageProductFragment.ManageProductListener {
    private ListProductFragment listProductFragment;
    private ManageProductFragment manageProductFragment;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_home);

        listProductFragment = new ListProductFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.framehome, listProductFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    /**
     *
     * @param item The item that has been tapped on
     * @return true when the event controlled by this has been consumed, false when it hasn't and gets propagated
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.action_settings_general:
                intent = new Intent(HomeActivity.this, GeneralSettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.action_settings_account:
                intent = new Intent(HomeActivity.this, AccountSettingsActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showManageProduct(Bundle bundle) {
        manageProductFragment = new ManageProductFragment(bundle);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.framehome, manageProductFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void showListProduct() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.framehome, listProductFragment);
        //ft.addToBackStack(null);
        ft.commit();
    }
}
