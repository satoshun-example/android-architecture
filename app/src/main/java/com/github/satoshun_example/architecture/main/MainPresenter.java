package com.github.satoshun_example.architecture.main;

import android.support.annotation.NonNull;

class MainPresenter implements MainContract.Presenter {

    @NonNull
    private final MainContract.View view;

    MainPresenter(@NonNull MainContract.View view) {
        this.view = view;
    }

    @Override
    public void subscribe() {
        // TODO
    }

    @Override
    public void unsubscribe() {
        // TODO
    }
}
