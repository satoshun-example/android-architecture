package com.github.satoshun.example.architecture.repo;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;
import com.github.satoshun.example.architecture.R;
import com.github.satoshun.example.architecture.data.repo.Repo;
import com.github.satoshun.example.architecture.databinding.RepoActBinding;

public class RepoActivity extends AppCompatActivity {

  @NonNull
  public static Intent getCallingIntent(@NonNull Context context, @NonNull Repo repo) {
    return Henson.with(context)
            .gotoRepoActivity()
            .repo(repo)
            .build();
  }

  private RepoActBinding binding;
  @InjectExtra Repo repo;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Dart.inject(this);

    binding = DataBindingUtil.setContentView(this, R.layout.repo_act);
    setSupportActionBar(binding.toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    RepoFragment fragment = (RepoFragment) getSupportFragmentManager().findFragmentById(R.id.content_frame);
    if (fragment == null) {
      fragment = RepoFragment.newInstance();
      getSupportFragmentManager().beginTransaction()
              .add(R.id.content_frame, fragment)
              .commit();
    }
    fragment.setPresenter(new RepoPresenter(fragment, repo));
  }
}
