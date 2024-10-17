package com.example.mylogin5a

import android.os.Bundle
import android.widget.Toast
import com.example.mylogin5a.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    //Creamos la conexión a la BD
    val dbHelper = DBHelperUsuario(this)

    //Click al botón Login
    binding.btnLogin.setOnClickListener {
        //Tomamos los valores de las cajas de texto
        val loginInput = binding.txtUsuario.getText().toString()
        val passInput = binding.txtPassword.getText().toString()

        //Abrimos la BD para solo lectura
        val db = dbHelper.readableDatabase

        //Creamos los argumentos para la consulta como array
        val selectionArgs = arrayOf(loginInput, passInput)

        //Creamos variable cursor para ejecutar la consulta
        val cursor = db.rawQuery(
            "SELECT * FROM usuarios WHERE userLogin = ? AND userPass = ?",
            selectionArgs
        )

        //Verificamos si se encontró ocurrencia en la consulta, moviendo el cursor al inicio
        if (cursor.moveToFirst()) {
            Toast.makeText(this, "El usuario es correcto :-)", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText( this, "Usuario inválido :(", Toast.LENGTH_SHORT).show()
        }

        //Cerramos el cursor
        cursor.close()

        //Cerramos la BD
        db.close()
    }
}
