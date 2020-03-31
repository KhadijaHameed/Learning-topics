package com.example.roomwrapperimplement.pojo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "person_info_table")
public class PersonInfo {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "first_name")
    private String firstName;

  /*  @NonNull
    @ColumnInfo(name = "last_name")
    private String lastName;*/

    public PersonInfo(String firstName) {
        this.firstName = firstName;
       // this.lastName = lastName;
    }

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NonNull String firstName) {
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return this.firstName;
    }

 /*   @NonNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(@NonNull String lastName) {
        this.lastName = lastName;
    }*/
}


