package com.example.roomwrapperimplement.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomwrapperimplement.R;
import com.example.roomwrapperimplement.adapter.AllDataAdapter;
import com.example.roomwrapperimplement.db.PersonInfoViewModel;
import com.example.roomwrapperimplement.pojo.PersonInfo;

import java.util.List;

public class DialogGetAllData extends Dialog implements View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button btnCancel;
    RecyclerView rvGetAllData;
    private PersonInfoViewModel personInfoViewModel;
    AllDataAdapter allDataAdapter ;

    public DialogGetAllData(Activity a, AllDataAdapter allDataAdapter) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.allDataAdapter = allDataAdapter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog);
        btnCancel = (Button) findViewById(R.id.btn_cancel);
        rvGetAllData =  findViewById(R.id.rv_person_info);

       // allDataAdapter = new AllDataAdapter(c);
        getAllDataData();
        //allDataAdapter.setPersonInfoList();
       /* personInfoViewModel = ViewModelProviders.of(this).get(PersonInfoViewModel.class);
        personInfoViewModel.getAlldata().observe(this, new Observer<List<PersonInfo>>() {
            @Override
            public void onChanged(List<PersonInfo> personInfos) {
                //      Toast.makeText(MainActivity.this, "onchange", Toast.LENGTH_SHORT).show();
                allDataAdapter.setPersonInfoList(personInfos);
            }
        });*/

          /*personInfoViewModel = ViewModelProviders.of(c).get(PersonInfoViewModel.class);
        */

        btnCancel.setOnClickListener(this);
            }
    private void getAllDataData() {
        try {
            Log.d("dialog","getalldata ");
            //\\ LiveData<List<PersonInfo>> allpersoninfo =   dbRepo.getAllInfo();
       /* List<PersonInfo> allpersoninfo = (List<PersonInfo>) personInfoViewModel.getAlldata();


            tvGetAllData.setText(allpersoninfo.size());

        */

            LinearLayoutManager layoutManager = new LinearLayoutManager(c);
            //    new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
            rvGetAllData.setLayoutManager(layoutManager);
            rvGetAllData.setHasFixedSize(true);
           // allDataAdapter = new AllDataAdapter(c);
           rvGetAllData.setAdapter(allDataAdapter);
        } catch (Exception e) {
            Log.d("#error", e.getLocalizedMessage());
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel:
                //c.finish();
               dismiss();
                break;
               default:
                break;
        }
        dismiss();
    }
}
