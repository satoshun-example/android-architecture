package com.github.satoshun_example.architecture.main;

interface MainContract {
    interface View {
    }

    interface Presenter {
        void subscribe();
        void unsubscribe();
    }
}
