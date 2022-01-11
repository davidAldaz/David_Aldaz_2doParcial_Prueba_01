package com.nadershamma.apps.registro;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.nadershamma.apps.androidfunwithflags.DIAS_LoginActivity;
import com.nadershamma.apps.androidfunwithflags.DIAS_MainActivity;
import com.nadershamma.apps.androidfunwithflags.R;
import com.nadershamma.apps.database.DIAS_DBHelper;

public class DIAS_Registro extends AppCompatActivity {

    EditText Etusurname,EtPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Etusurname = findViewById(R.id.editTextTextUserName);
        EtPass = findViewById(R.id.editTextTextPassword);
    }

    /*Metodo Para registrar los datos del usuario*/
    public void RegistrarDataUser(View v){

        DIAS_DBHelper admin=new DIAS_DBHelper(this,"usuarios",null,1);
        /*Abrimos la base de datos para escritura*/
        SQLiteDatabase db=admin.getWritableDatabase();
        //
        String UserName=Etusurname.getText().toString();
        String PassUser=EtPass.getText().toString();

        ContentValues values = new ContentValues();

        values.put("username",UserName);
        values.put("clave_user",PassUser);

        db.insert("userstable",null,values);

        db.close();

        Toast.makeText(this,"Usuario registrado",Toast.LENGTH_SHORT).show();

        /*lanzar la otra actividad*/
        Intent intent = new Intent(this, DIAS_LoginActivity.class);
        /*iniciar la actividad*/
        startActivity(intent);
    }
}