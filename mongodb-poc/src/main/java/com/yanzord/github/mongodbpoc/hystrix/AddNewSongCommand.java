package com.yanzord.github.mongodbpoc.hystrix;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixObservableCommand;
import com.yanzord.github.mongodbpoc.model.Song;
import com.yanzord.github.mongodbpoc.repository.SongDALImpl;
import rx.Observable;
import rx.Subscriber;

public class AddNewSongCommand extends HystrixObservableCommand<Song> {

    private final SongDALImpl songDALImpl;
    private final Song song;

    public AddNewSongCommand(SongDALImpl songDALImpl, Song song) {
        super(HystrixCommandGroupKey.Factory.asKey("database"));
        this.songDALImpl = songDALImpl;
        this.song = song;
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
                        observer.onNext(songDALImpl.addNewSong(song));
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
