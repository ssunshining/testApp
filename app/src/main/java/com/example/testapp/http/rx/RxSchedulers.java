//package com.example.testapp.http.rx;
//
//
//import java.util.concurrent.TimeUnit;
//
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.schedulers.Schedulers;
//import rx.Observable;
//
//
//public class RxSchedulers {
//
//
//
//    public static <T> Observable.Transformer<T, T> io_main() {
//        return new Observable.Transformer<T, T>() {
//            public Observable<T> call(Observable<T> paramObservable) {
//                return paramObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
//            }
//        };
//    }
//
//
//}
