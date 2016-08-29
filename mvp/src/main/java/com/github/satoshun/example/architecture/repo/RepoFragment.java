package com.github.satoshun.example.architecture.repo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.satoshun.example.architecture.R;
import com.github.satoshun.example.architecture.databinding.RepoFragBinding;

public class RepoFragment extends Fragment {

  @NonNull public static RepoFragment newInstance() {
    return new RepoFragment();
  }

  private RepoFragBinding binding;

  public RepoFragment() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    binding = DataBindingUtil.inflate(inflater, R.layout.repo_frag, container, false);
    return binding.getRoot();
  }
}
