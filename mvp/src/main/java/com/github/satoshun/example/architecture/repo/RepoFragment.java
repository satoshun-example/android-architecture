package com.github.satoshun.example.architecture.repo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.satoshun.example.architecture.R;
import com.github.satoshun.example.architecture.data.repo.Repo;
import com.github.satoshun.example.architecture.databinding.RepoFragBinding;

public class RepoFragment extends Fragment implements RepoContract.View {

  @NonNull public static RepoFragment newInstance() {
    return new RepoFragment();
  }

  private RepoFragBinding binding;
  private RepoContract.Presenter presenter;

  public RepoFragment() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    binding = DataBindingUtil.inflate(inflater, R.layout.repo_frag, container, false);
    return binding.getRoot();
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  @Override public void onResume() {
    super.onResume();
    presenter.subscribe();
  }

  @Override public void onPause() {
    super.onPause();
    presenter.unsubscribe();
  }

  void setPresenter(@NonNull RepoContract.Presenter presenter) {
    this.presenter = presenter;
  }

  @Override public void renderRepo(@NonNull Repo repo) {
    binding.setModel(repo);
    binding.url.setMovementMethod(LinkMovementMethod.getInstance());
  }
}
