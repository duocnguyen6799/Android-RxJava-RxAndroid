package com.npdstudio.android_rxjava_rxandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {
    final String TAG = MainActivity.class.getName();
    Disposable disposable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Observable with String data
        Observable<String> footballPlayerObservable = Observable.just("Messi", "Ronaldo", "Modric", "Salah", "Mbappe");
        Observer<String> footballPlayerObserver = getListFootballPlayer();
        footballPlayerObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(footballPlayerObserver);
//        //Observable with User data
//        List<User> listUser = new ArrayList<>();
//        for(int i=1;i<=10;i++){
//            listUser.add(new User(Integer.toString(i),"User "+i));
//        }
//        Observable<User> userObservable = Observable.just(listUser.get(0));
//        Observer<User> userObserver = getListUser();
//        userObservable.observeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(userObserver);

    }
//    public Observer<User> getListUser(){
//        return new Observer<User>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//                disposable = d;
//                Log.d(TAG,"Disposable is onSubscribe");
//            }
//
//            @Override
//            public void onNext(@NonNull User users) {
//                Log.d(TAG,users.toString());
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d(TAG,"User completed");
//            }
//        };
//    }
    public Observer<String> getListFootballPlayer(){
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "Name: " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "All items are emitted!");
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
        Log.d(TAG,"Disposable is onDestroy");
    }
}