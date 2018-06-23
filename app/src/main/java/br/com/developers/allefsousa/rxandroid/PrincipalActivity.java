package br.com.developers.allefsousa.rxandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.schedulers.Schedulers;

public class PrincipalActivity extends AppCompatActivity {

    private String TAG = "Allef";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        Observer<String> animalsObserver = operacoesObserver();

        getAnimalsObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(animalsObserver);

    }

    private Observer<String> operacoesObserver() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");

                /**
                 * onSubscribe (): O método será chamado quando um Observador se inscrever no Observable.
                 */
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "Name: " + s);
                /**
                 * onNext (): Este método será chamado quando Observable começar a emitir os dados.
                 */
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
                /**
                 * onError (): Em caso de qualquer erro, o método onError () será chamado.
                 */
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "All items are emitted!");
                /**
                 * onComplete (): quando um Observable conclui a emissão de todos os itens, onComplete () será chamado.
                 */
            }
        };
    }
    private Observable<String> getAnimalsObservable() {
        /**
         *
         */
        return Observable.just("Ant", "Bee", "Cat", "Dog", "Fox");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        /**
         * Disposable  é usado para descartar a assinatura quando um observador não deseja mais ouvir Observable.
         * No android descartável são muito úteis para evitar vazamentos de memória.
         */
        Disposables.disposed();
    }
}
