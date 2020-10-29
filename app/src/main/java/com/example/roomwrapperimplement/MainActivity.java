package com.example.roomwrapperimplement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roomwrapperimplement.adapter.AllDataAdapter;
import com.example.roomwrapperimplement.db.PersonInfoViewModel;
import com.example.roomwrapperimplement.db.Repository;
import com.example.roomwrapperimplement.dialog.DialogGetAllData;
import com.example.roomwrapperimplement.pojo.NewWord;
import com.example.roomwrapperimplement.pojo.PersonInfo;
import com.facebook.stetho.DumperPluginsProvider;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.dumpapp.DumperPlugin;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    Repository dbRepo;
    EditText etNewWord;
    String newWord = " ";
    Button btnInsert, btnUpdate, btnDelete, btnGetAllData, btnDeleteAllData;
    TextView tvGetAllData;
    RecyclerView rvPersonData;
    private PersonInfoViewModel personInfoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

         /* LinearLayoutManager layoutManager = new LinearLayoutManager(this);
         rvPersonData.setLayoutManager(layoutManager);
        rvPersonData.setHasFixedSize(true);
        rvPersonData.setAdapter(allDataAdapter);*/
        AllDataAdapter allDataAdapter = new AllDataAdapter(this);

        personInfoViewModel = ViewModelProviders.of(this).get(PersonInfoViewModel.class);
        personInfoViewModel.getAlldata().observe(this, new Observer<List<PersonInfo>>() {
            @Override
            public void onChanged(List<PersonInfo> personInfos) {
                //      Toast.makeText(MainActivity.this, "onchange", Toast.LENGTH_SHORT).show();
                allDataAdapter.setPersonInfoList(personInfos);
            }
        });

       /*   new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {

                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        Toast.makeText(MainActivity.this, "onSwiped ", Toast.LENGTH_LONG).show();
                        personInfoViewModel.delete(allDataAdapter.getDataAt(viewHolder.getAdapterPosition()));
                        Toast.makeText(MainActivity.this, "delete " + viewHolder.getAdapterPosition() + " " + viewHolder.itemView.toString(), Toast.LENGTH_LONG).show();
                    }
                });

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(rvPersonData);*/
// personInfoViewModel.delete(allDataAdapter.getDataAt(viewHolder.getAdapterPosition()));


        dbRepo = new Repository(getApplicationContext());


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
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData();
            }
        });

        btnGetAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getAllDataData();
                DialogGetAllData dialogGetAllData = new DialogGetAllData(MainActivity.this, allDataAdapter);
                dialogGetAllData.show();
            }
        });

        btnDeleteAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAllData();
            }
        });
    }

    private void initViews() {
        etNewWord = findViewById(R.id.et_word);
        btnInsert = findViewById(R.id.button_insert);
        btnUpdate = findViewById(R.id.button_update);
        btnDelete = findViewById(R.id.button_delete);
        btnGetAllData = findViewById(R.id.button_get_all_data);
        btnDeleteAllData = findViewById(R.id.button_delete_all_data);
        tvGetAllData = findViewById(R.id.tv_get_all_data);
        rvPersonData = findViewById(R.id.rv_person_info);

    }

    //working
    private void insertData() {
        this.newWord = etNewWord.getText().toString();
        try {
            PersonInfo personInfo = new PersonInfo(newWord);
            personInfoViewModel.insert(personInfo);
            Toast.makeText(this, "Inserted! " + newWord, Toast.LENGTH_LONG).show();
            Log.d("data", "personInfo" + personInfo);
            //    dbRepo.updateWord(word, 9);
            //    dbRepo.deleteRowbyID(10);
            //    **it can also delete null space row <==>
            //    dbRepo.deleteRowbySpecificWord(word);

            // NewWord newWord = new NewWord("New First");
            //Repo.ninsert(newWord);
            // Toast.makeText(this, "Insert Successfully! \n" + personInfo.getFirstName()+"\n"+ personInfo.getLastName(), Toast.LENGTH_SHORT).show();
            etNewWord.setText(" ");
        } catch (Exception e) {
            Log.d("#error", e.getLocalizedMessage());
        }
    }

    private void updateData() {
        this.newWord = etNewWord.getText().toString();
        try {
            PersonInfo personInfo = new PersonInfo(newWord);
            // dbRepo.update(personInfo);
            Toast.makeText(this, "to Update list, Get all data" + newWord, Toast.LENGTH_LONG).show();
            //Log.d("data", "personInfo" + personInfo);
            etNewWord.setText(" ");
        } catch (Exception e) {
            Log.d("#error", e.getLocalizedMessage());
        }
    }

    private void deleteData() {
        try {
            this.newWord = etNewWord.getText().toString();
            PersonInfo personInfo = new PersonInfo(newWord);
            personInfoViewModel.delete(personInfo);

            // dbRepo.delete(personInfo);
            Toast.makeText(this, "Deleted " + newWord, Toast.LENGTH_LONG).show();
            Log.d("data", "personInfo" + personInfo);
            etNewWord.setText(" ");
        } catch (Exception e) {
            Log.d("#error", e.getLocalizedMessage());
        }
    }

    //done
    private void getAllDataData() {
        try {
            //\\ LiveData<List<PersonInfo>> allpersoninfo =   dbRepo.getAllInfo();
       /* List<PersonInfo> allpersoninfo = (List<PersonInfo>) personInfoViewModel.getAlldata();


            tvGetAllData.setText(allpersoninfo.size());

        */

            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            //    new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
            rvPersonData.setLayoutManager(layoutManager);
            rvPersonData.setHasFixedSize(true);
            // allDataAdapter = new AllDataAdapter();
            //  rvPersonData.setAdapter(allDataAdapter);
        } catch (Exception e) {
            Log.d("#error", e.getLocalizedMessage());
        }
    }

    private void deleteAllData() {
        try {
            dbRepo.deleteAllInfo();
            Toast.makeText(this, "delete all data ", Toast.LENGTH_LONG).show();
            Log.d("data", "delete ALL personInfo");
            etNewWord.setText(" ");
        } catch (Exception e) {
            Log.d("#error", e.getLocalizedMessage());
        }
    }

}
