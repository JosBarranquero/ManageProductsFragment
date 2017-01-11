package com.barranquero.manageproductsrecycler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by usuario on 1/12/16
 */
public class HomeActivity extends AppCompatActivity implements ManageProductFragment.ManageProductListener, MultiListProductFragment.ListProductListener {
    private MultiListProductFragment listProductFragment;
    private ManageProductFragment manageProductFragment;
    private long mBackPressed = 0;
    private static final long MAX_TIME = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_navigation);

        listProductFragment = new MultiListProductFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.framehome, listProductFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mBackPressed + MAX_TIME > System.currentTimeMillis() || getSupportFragmentManager().getBackStackEntryCount() > 0) {
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        mBackPressed = System.currentTimeMillis();
    }

    /**
     * @param item The item that has been tapped on
     * @return true when the event controlled by this has been consumed, false when it hasn't and gets propagated
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
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
        manageProductFragment = ManageProductFragment.newInstance(bundle);
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
