package com.barranquero.manageproductsrecycler;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by usuario on 1/12/16
 */
public class HomeActivity extends AppCompatActivity implements ManageProductFragment.ManageProductListener, MultiListProductFragment.ListProductListener, ListPharmacyFragment.ListPharmacyListener {
    private MultiListProductFragment listProductFragment;
    private ManageProductFragment manageProductFragment;
    private ManagePharmacyFragment managePharmacyFragment;
    private ListPharmacyFragment listPharmacyFragment;
    private long mBackPressed = 0;
    private static final long MAX_TIME = 2500;

    private Toolbar mToolbar;
    private android.support.v4.widget.DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    private ActionBarDrawerToggle mActionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_navigation);

        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        mNavigationView = (NavigationView)findViewById(R.id.navigation_view);

        setupDrawerContent();

        mActionBarDrawerToggle = setupDrawerToggle();
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);

        if (savedInstanceState == null) {
            listProductFragment = new MultiListProductFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.framehome, listProductFragment).commit();
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mActionBarDrawerToggle.syncState();
    }

    /**
     * Method which controls the selected option
     */
    private void setupDrawerContent() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                switch (item.getItemId()) {
                    case R.id.action_products:
                        //onListProductListener();
                        getSupportFragmentManager().beginTransaction().replace(R.id.framehome, listProductFragment).commit();
                        break;
                    case R.id.action_chemist:
                        listPharmacyFragment = new ListPharmacyFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.framehome, listPharmacyFragment).commit();
                        break;
                    /*case R.id.action_home:
                        break;
                    case R.id.action_sale:
                        break;
                    case R.id.action_help:
                        break;
                    case R.id.action_settings:
                        break;*/
                    default:
                        item.setChecked(false);
                        break;
                }
                setTitle(item.getTitle());
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (mBackPressed + MAX_TIME > System.currentTimeMillis() || getSupportFragmentManager().getBackStackEntryCount() > 0) {
                super.onBackPressed();
                return;
            } else {
                Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
            }
            mBackPressed = System.currentTimeMillis();
        }
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
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showManageProduct(Bundle bundle) {
        manageProductFragment = ManageProductFragment.newInstance(bundle);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.framehome, manageProductFragment);
        //ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void showListProduct() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.framehome, listProductFragment);
        //ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void showManagePharmacy(Bundle bundle) {
        managePharmacyFragment = ManagePharmacyFragment.newInstance(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.framehome, managePharmacyFragment).commit();
    }
}
