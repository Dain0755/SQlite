package com.example.sqldefinitivo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText nombre, id, pass;
    Button register, consult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombre = findViewById(R.id.et_nombre);
        id = findViewById(R.id.et_id);
        pass = findViewById(R.id.et_pass);
        register = findViewById(R.id.btn_Ingresar);
        consult = findViewById(R.id.btn_Consultar);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseDeDatos administrator = new BaseDeDatos(MainActivity.this, "DataBase", null, 1);
                SQLiteDatabase DataBase = administrator.getWritableDatabase();
                String document = id.getText().toString();
                String name = nombre.getText().toString();
                String clave = pass.getText().toString();
                if (!document.isEmpty()){
                    Cursor fila = DataBase.rawQuery("select identify, nombre, password from usuario where identify = "+document, null);
                    if(fila.moveToFirst()){
                        String idPersona = fila.getString(0);
                        if (!idPersona.isEmpty()){
                            Toast.makeText(MainActivity.this, "El registro ya existe", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "El usuario no se encuentra registrado", Toast.LENGTH_SHORT).show();
                        if (!document.isEmpty() && !name.isEmpty()  && !clave.isEmpty()){
                            ContentValues register = new ContentValues();
                            int cedula = Integer.parseInt(document);
                            register.put("identify", cedula);
                            register.put("nombre", name);
                            register.put("password", clave);
                            DataBase.insert("usuario", null, register);
                            DataBase.close();
                            id.setText("");
                            nombre.setText("");
                            pass.setText("");
                            Toast.makeText(MainActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "Por favor ingresar todos los campos", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else {
                    Toast.makeText(MainActivity.this, "Por favor ingrese la identificación", Toast.LENGTH_SHORT).show();
                }
            }
        });
        consult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseDeDatos admin = new BaseDeDatos(MainActivity.this, "DataBase", null, 1);
                SQLiteDatabase DB = admin.getWritableDatabase();
                String document = id.getText().toString();
                if (!document.isEmpty()){
                    Cursor fila = DB.rawQuery("Select nombre, password from usuario where identify ="+document, null);
                    if (fila.moveToFirst()){
                        nombre.setText(fila.getString(0));
                        pass.setText(fila.getString(1));
                        DB.close();
                        Toast.makeText(MainActivity.this, "Datos consultados correctamente", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, "No se encontró el usuario", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Ingrese un documento para la consulta", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}