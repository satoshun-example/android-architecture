package com.github.satoshun_example.architecture.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.github.satoshun_example.architecture.R;
import com.github.satoshun_example.architecture.data.user.UserRepository;
import com.github.satoshun_example.architecture.data.user.local.UserLocalDataSource;
import com.github.satoshun_example.architecture.data.user.remote.UserRemoteDataSource;
import com.github.satoshun_example.architecture.databinding.MainActBinding;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

  private MainActBinding binding;
  private MainPresenter presenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, R.layout.main_act);

    setSupportActionBar(binding.app.toolbar);

    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, binding.drawerLayout, binding.app.toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    binding.drawerLayout.setDrawerListener(toggle);
    toggle.syncState();

    binding.navView.setNavigationItemSelectedListener(this);

    MainFragment fragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.content_frame);
    if (fragment == null) {
      fragment = MainFragment.newInstance();
      getSupportFragmentManager().beginTransaction()
              .add(R.id.content_frame, fragment)
              .commit();
    }
    presenter = new MainPresenter(fragment, provideUserRepository());
    fragment.setPresenter(presenter);
  }

  private UserRepository provideUserRepository() {
    return new UserRepository(new UserLocalDataSource(), new UserRemoteDataSource());
  }

  @Override
  public void onBackPressed() {
    if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
      binding.drawerLayout.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    switch(id) {
      case R.id.nav_gallery:
        break;
    }

    binding.drawerLayout.closeDrawer(GravityCompat.START);
    return true;
  }
}
