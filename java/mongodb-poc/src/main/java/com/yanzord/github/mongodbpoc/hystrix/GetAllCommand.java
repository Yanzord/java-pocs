package com.yanzord.github.mongodbpoc.hystrix;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixObservableCommand;
import com.yanzord.github.mongodbpoc.model.Song;
import com.yanzord.github.mongodbpoc.repository.SongDALImpl;
import rx.Observable;
import rx.Subscriber;

import java.util.Collections;
import java.util.List;

public class GetAllCommand extends HystrixObservableCommand<List<Song>> {

    private final SongDALImpl songDALImpl;

    public GetAllCommand(SongDALImpl songDALImpl) {
        super(HystrixCommandGroupKey.Factory.asKey("database"));
        this.songDALImpl = songDALImpl;
        HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(5000);
    }

    @Override
    protected Observable<List<Song>> construct() {
        return Observable.create(new Observable.OnSubscribe<List<Song>>()
        {
            @Override
            public void call(Subscriber<? super List<Song>> observer) {

                try {
                    if (!observer.isUnsubscribed()) {
                        observer.onNext(songDALImpl.getAllSongs());
                        observer.onCompleted();
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        });
    }

    @Override
    protected Observable<List<Song>> resumeWithFallback() {
        return Observable.just(Collections.emptyList());
    }
}
