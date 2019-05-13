package com.jhonjimenez.intergrupotest.ui.main;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.jhonjimenez.intergrupotest.R;
import com.jhonjimenez.intergrupotest.app.App;
import com.jhonjimenez.intergrupotest.models.Prospect;
import com.jhonjimenez.intergrupotest.ui.dashboard.DashboardFragment;
import com.jhonjimenez.intergrupotest.ui.login.LoginActivityImpl;
import com.jhonjimenez.intergrupotest.ui.prospects.ProspectEditActivity;
import com.jhonjimenez.intergrupotest.ui.prospects.ProspectsFragmentImpl;
import com.jhonjimenez.intergrupotest.utils.Constants;

import javax.inject.Inject;

public class MainActivityImpl extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        ProspectsFragmentImpl.IProspectsFragmentImpl, MainMVP.View {

    private Fragment fragment;
    private FragmentManager fragmentManager;
    private static final int EDIT_PROSPECT_REQUEST = 1;
    private Toolbar toolbar;

    @Inject
    public MainMVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((App) getApplication()).getComponent().inject(this);

        presenter.setView(this);

        toolbar = findViewById(R.id.toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setCheckedItem(R.id.nav_prospect);
        onNavigationItemSelected(navigationView.getMenu().getItem(0));
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_prospect) {
            fragment = new ProspectsFragmentImpl();
            toolbar.setTitle("Prospectos");
        } else if (id == R.id.nav_dashboard) {
            fragment = new DashboardFragment();
            toolbar.setTitle("Dashboard");
        } else if (id == R.id.nav_exit) {
            presenter.closeSession();
        }

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment, fragment).commit();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void editProspect(Prospect prospect) {
        Intent intent = new Intent(this, ProspectEditActivity.class);
        intent.putExtra(Constants.INTENT, prospect);
        startActivityForResult(intent, EDIT_PROSPECT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EDIT_PROSPECT_REQUEST) {
            if (resultCode == RESULT_OK) {
                if (fragment instanceof ProspectsFragmentImpl) {
                    Prospect prospect = (Prospect) data.getSerializableExtra(Constants.INTENT);
                    ((ProspectsFragmentImpl) fragment).updateProsptecLocal(prospect);
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dispose();
    }

    @Override
    public void closeSession() {
        runOnUiThread(() -> {
            Intent intent = new Intent(this, LoginActivityImpl.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

    }
}
