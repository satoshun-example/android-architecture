package com.github.satoshun_example.architecture.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.satoshun_example.architecture.R;
import com.github.satoshun_example.architecture.data.user.User;
import com.github.satoshun_example.architecture.databinding.MainFragBinding;

public class MainFragment extends Fragment implements MainContract.View {

  static MainFragment newInstance() {
    return new MainFragment();
  }

  private MainFragBinding binding;
  private MainContract.Presenter presenter;

  public MainFragment() {
  }

  @Override public void onResume() {
    super.onResume();

    presenter.subscribe();
  }

  @Override public void onPause() {
    super.onPause();

    presenter.unsubscribe();
  }

  @Nullable
  @Override public View onCreateView(LayoutInflater inflater,
                                     @Nullable ViewGroup container,
                                     @Nullable Bundle savedInstanceState) {
    binding = DataBindingUtil.inflate(inflater, R.layout.main_frag, container, false);
    return binding.getRoot();
  }

  @Override public void showUser(User user) {
    binding.name.setText(String.valueOf(user.getId()));
    binding.name.setVisibility(View.VISIBLE);

    binding.errorMessage.setVisibility(View.GONE);
  }

  @Override public void showUserError() {
    binding.name.setVisibility(View.GONE);

    binding.errorMessage.setVisibility(View.VISIBLE);
    binding.errorMessage.setText("failed load user");
  }

  public void setPresenter(MainContract.Presenter presenter) {
    this.presenter = presenter;
  }
}
