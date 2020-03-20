package com.example.roomwrapperimplement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roomwrapperimplement.db.Repository;
import com.facebook.stetho.DumperPluginsProvider;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.dumpapp.DumperPlugin;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    Repository dbRepo;
    EditText etNewWord;
    String newWord = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbRepo = new Repository(getApplicationContext());

        etNewWord = findViewById(R.id.et_word);

        //region stetho
        // Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(new DumperPluginsProvider() {
                    @Override
                    public Iterable<DumperPlugin> get() {
                        return new Stetho.DefaultDumperPluginsBuilder(MainActivity.this)
                                //  .provide(new MyDumperPlugin())
                                .finish();
                    }
                })
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
        //endregion stetho

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            try {
                this.newWord = etNewWord.getText().toString();
                OldWord word = new OldWord("Old First");
                dbRepo.insert(word);
                //    dbRepo.updateWord(word, 9);
                //    dbRepo.deleteRowbyID(10);
                //    **it can also delete null space row <==>
                //    dbRepo.deleteRowbySpecificWord(word);

               NewWord newWord = new NewWord("New First");
               dbRepo.ninsert(newWord);
                Toast.makeText(this, "both insert Successfully! \n" + word.getWord()+"\n"+newWord.getWord(), Toast.LENGTH_SHORT).show();
                etNewWord.setText(" ");
            } catch (Exception e) {
                Log.d("#error", e.getLocalizedMessage());
            }
        });

    }


}
