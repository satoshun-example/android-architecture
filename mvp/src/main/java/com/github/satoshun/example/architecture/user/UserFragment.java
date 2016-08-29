package com.github.satoshun.example.architecture.user;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.satoshun.example.architecture.R;
import com.github.satoshun.example.architecture.data.user.User3;
import com.github.satoshun.example.architecture.databinding.UserFragBinding;

public class UserFragment extends Fragment implements UserContract.View {

  static UserFragment newInstance() {
    return new UserFragment();
  }

  private UserFragBinding binding;
  private UserContract.Presenter presenter;

  public UserFragment() {
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
    binding = DataBindingUtil.inflate(inflater, R.layout.user_frag, container, false);
    return binding.getRoot();
  }

  @Override public void showUser(@NonNull User3 user) {
    binding.setUser(user);

    binding.name.setVisibility(View.VISIBLE);
    binding.errorMessage.setVisibility(View.GONE);
  }

  @Override public void showUserError() {
    binding.setUser(null);

    binding.errorMessage.setVisibility(View.VISIBLE);
    binding.errorMessage.setText("failed load user");
  }

  public void setPresenter(UserContract.Presenter presenter) {
    this.presenter = presenter;
  }
}
