package com.yanzord.github.mongodbpoc.hystrix;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixObservableCommand;
import com.yanzord.github.mongodbpoc.model.Song;
import com.yanzord.github.mongodbpoc.repository.SongDALImpl;
import rx.Observable;
import rx.Subscriber;

public class GetSongByIdCommand extends HystrixObservableCommand<Song> {

    private final SongDALImpl songDALImpl;
    private final String id;

    public GetSongByIdCommand(SongDALImpl songDALImpl, String id) {
        super(HystrixCommandGroupKey.Factory.asKey("database"));
        this.songDALImpl = songDALImpl;
        this.id = id;
        HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(5000);
    }

    @Override
    protected Observable<Song> construct() {
        return Observable.create(new Observable.OnSubscribe<Song>()
        {

            @Override
            public void call(Subscriber<? super Song> observer) {

                try {
                    if (!observer.isUnsubscribed()) {
                        observer.onNext(songDALImpl.getSongById(id));
                        observer.onCompleted();
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        });
    }

    @Override
    protected Observable<Song> resumeWithFallback() {
        return Observable.just(new Song("0", "0"));
    }
}
