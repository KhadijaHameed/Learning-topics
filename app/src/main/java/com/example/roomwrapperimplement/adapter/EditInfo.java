package com.example.roomwrapperimplement.adapter;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomwrapperimplement.R;
import com.example.roomwrapperimplement.db.PersonInfoViewModel;
import com.example.roomwrapperimplement.db.Repository;

public  class EditInfo /*extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_info);
        personInfoViewModel = ViewModelProviders.of((this).get(PersonInfoViewModel.class));
*/
    extends AppCompatActivity {
    Button btnUpdate;
    EditText etNewName;
    private PersonInfoViewModel personInfoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_info);

        personInfoViewModel = ViewModelProviders.of(this).get(PersonInfoViewModel.class);


        String getOldName = getIntent().getExtras().getString("name");
        etNewName =findViewById(R.id.et_update_name);
        btnUpdate =findViewById(R.id.btn_update_name);

        etNewName.setText(getOldName);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String updatedname =   etNewName.getText().toString();
                personInfoViewModel.update(updatedname, getOldName);
                Toast.makeText(EditInfo.this, "Edit row "+  " "+getOldName +" TO "+ "edit "+""+"updatedname", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}
