package com.github.satoshun.example.architecture.users;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.satoshun.example.architecture.R;
import com.github.satoshun.example.architecture.data.repo.RepoDataSource;
import com.github.satoshun.example.architecture.data.repo.RepoRepository;
import com.github.satoshun.example.architecture.databinding.ReposActBinding;

public class ReposActivity extends AppCompatActivity {

  private ReposActBinding binding;
  private ReposPresenter presenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, R.layout.repos_act);

    setSupportActionBar(binding.toolbar);

    ReposFragment fragment = (ReposFragment) getSupportFragmentManager().findFragmentById(R.id.content_frame);
    if (fragment == null) {
      fragment = ReposFragment.newInstance();
      getSupportFragmentManager().beginTransaction()
              .add(R.id.content_frame, fragment)
              .commit();
    }
    presenter = new ReposPresenter(fragment, new RepoRepository(RepoDataSource.Factory.create()));
    fragment.setPresenter(presenter);
  }
}
