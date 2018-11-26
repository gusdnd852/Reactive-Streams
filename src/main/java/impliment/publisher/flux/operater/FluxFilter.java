package impliment.publisher.flux.operater;

import impliment.publisher.flux.Flux;

import java.util.concurrent.Flow;
import java.util.function.Predicate;

/**
 * @Author : Hyunwoong
 * @When : 2018-11-26 오전 9:05
 * @Homepage : https://github.com/gusdnd852
 */
public class FluxFilter<T> extends Flux<T> {

    private final Predicate<T> predicate;
    private final Flux<T> flux;

    public FluxFilter(Flux<T> flux, Predicate<T> predicate) {
        this.flux = flux;
        this.predicate = predicate;
    }


    @Override public void subscribe(Flow.Subscriber<? super T> subscriber) {
        flux.subscribe(new Flow.Subscriber<>() {
            @Override public void onSubscribe(Flow.Subscription subscription) {
                subscriber.onSubscribe(subscription);
            }

            @Override public void onNext(T item) {
                if (predicate.test(item))
                    subscriber.onNext(item);
            }

            @Override public void onError(Throwable throwable) {
                subscriber.onError(throwable);
            }

            @Override public void onComplete() {
                subscriber.onComplete();
            }
        });
    }
}