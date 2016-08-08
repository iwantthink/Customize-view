package com.marenbo.www.example;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

public class RxJavaActivity extends Activity {

    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);

        mBtn = ButterKnife.findById(this, R.id.btn_txt);


        mBtn.setText("change");

    }

    public void rxjava(View view) {


//        normal();

//        action();

//        useDoubleSub();

        new Thread(new Runnable() {
            @Override
            public void run() {
                useFlatMap();

            }
        }).start();


    }

    private void useFlatMap() {
        Subscription subscription = query().flatMap(new Func1<ArrayList<String>, Observable<String>>() {
            @Override
            public Observable<String> call(ArrayList<String> urls) {
                return Observable.from(urls);
            }
        }).filter(new Func1<String, Boolean>() {
            @Override
            public Boolean call(String s) {
                return !s.equals("http://baidu888.com");
            }
        }).take(5).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

                Log.e("RxJavaActivity", "e:" + e);

            }

            @Override
            public void onNext(String s) {

                Log.d("RxJavaActivity", "onNext" + s);

                mBtn.setText(s);

            }
        });

    }

    private void useDoubleSub() {
        query().subscribe(new Action1<ArrayList<String>>() {
            @Override
            public void call(ArrayList<String> urls) {

                Observable.from(urls).subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {

                        Log.d("RxJavaActivity query", s);

                    }
                });

            }
        });
    }

    private Observable<ArrayList<String>> query() {

        ArrayList<String> urls = new ArrayList<>();

        urls.add("http://baidu111.com");
        urls.add("http://baidu222.com");
        urls.add("http://baidu333.com");
        urls.add("http://baidu444.com");
        urls.add("http://baidu555.com");
        urls.add("http://baidu666.com");
        urls.add("http://baidu777.com");
        urls.add("http://baidu888.com");
        urls.add("http://baidu888.com");
        urls.add("http://baidu888.com");
        urls.add("http://baidu888.com");
        urls.add("http://baidu888.com");


        Observable<ArrayList<String>> urlObservable = Observable.just(urls);

        return urlObservable;


    }


    private void action() {

        Observable<String> myObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                subscriber.onNext("action111");

                subscriber.onCompleted();

            }
        });

        myObservable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {

                Log.d("RxJavaActivity normal", s);
            }
        });

    }

    private void normal() {

        Observable<String> myObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                subscriber.onNext("onNext");

                subscriber.onCompleted();

            }
        });

        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

                Log.d("RxJavaActivity", s);

            }
        };

        myObservable.subscribe(mySubscriber);
    }

}
