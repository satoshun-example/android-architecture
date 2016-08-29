package com.github.satoshun.example.architecture.user;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.github.satoshun.example.architecture.R;
import com.github.satoshun.example.architecture.data.user.UserRepository;
import com.github.satoshun.example.architecture.data.user.local.UserLocalDataSource;
import com.github.satoshun.example.architecture.data.user.remote.UserRemoteDataSource;
import com.github.satoshun.example.architecture.databinding.UserActBinding;

public class UserActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

  private UserActBinding binding;
  private UserPresenter presenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, R.layout.user_act);

    setSupportActionBar(binding.app.toolbar);

    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, binding.drawerLayout, binding.app.toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    binding.drawerLayout.setDrawerListener(toggle);
    toggle.syncState();

    binding.navView.setNavigationItemSelectedListener(this);

    UserFragment fragment = (UserFragment) getSupportFragmentManager().findFragmentById(R.id.content_frame);
    if (fragment == null) {
      fragment = UserFragment.newInstance();
      getSupportFragmentManager().beginTransaction()
              .add(R.id.content_frame, fragment)
              .commit();
    }
    presenter = new UserPresenter(fragment, provideUserRepository());
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
    int id = item.getItemId();

    switch (id) {
      case R.id.nav_gallery:
        break;
    }

    binding.drawerLayout.closeDrawer(GravityCompat.START);
    return true;
  }
}
