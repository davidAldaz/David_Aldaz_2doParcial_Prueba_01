package com.nadershamma.apps.androidfunwithflags;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.nadershamma.apps.database.DIAS_DBHelper;
import com.nadershamma.apps.registro.DIAS_Registro;

public class DIAS_LoginActivity extends AppCompatActivity {

    EditText et1,et2;
    private Cursor fila;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et1= findViewById(R.id.etusuario);
        et2= findViewById(R.id.edtclave);
    }

    //metodo de ingreso
    public void InicioSesion(View v){
        DIAS_DBHelper admin = new DIAS_DBHelper(this,"usuarios",null,1);

        SQLiteDatabase db = admin.getWritableDatabase();

        String usuario=et1.getText().toString();
        String contrasena=et2.getText().toString();

        fila=db.rawQuery("select username,clave_user from userstable where username='"+
                usuario+"' and clave_user='"+contrasena+"'",null);

        try {

            if(fila.moveToFirst()){

                String usua=fila.getString(0);
                String pass=fila.getString(1);

                if (usuario.equals(usua) && contrasena.equals(pass)){

                    Intent ven = new Intent(getApplicationContext(), DIAS_MainActivity.class);

                    startActivity(ven);

                    et1.setText("");
                    et2.setText("");
                }
            }
            else {
                Toast.makeText(this,"Datos incorrectos",Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {//capturamos los errores que ubieran
            Toast.makeText(this,"Error" + e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    //metodo que nos envia a la ventana registro
    public void RegistroData(View v){

        Intent rdata=new Intent(this, DIAS_Registro.class);

        startActivity(rdata);
    }
}