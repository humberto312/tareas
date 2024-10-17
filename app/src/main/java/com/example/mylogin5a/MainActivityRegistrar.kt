package com.example.mylogin5a

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mylogin5a.databinding.ActivityMainRegistrarBinding

//Vincular vista Activity Main Registrar
private lateinit var bindingRegis: ActivityMainRegistrarBinding

override fun onCreate(savedInstanceState: Bundle?) {
   super.onCreate(savedInstanceState)
    bindingRegis = ActivityMainRegistrarBinding.inflate(layoutInflater)
    val view = bindingRegis.root
    setContentView(view)

    //Creamos la conexion a la BD
    val dbServicios = DBHelperUsuario(this)
    //Abrimos la BD para escribir
    val db = dbServicios.writableDatabase

    bindingRegis.btnRegistrar.setOnClickListener {
        //Tomamos los valores de las cajas de texto
        val user = bindingRegis.txtNewUsuario.getText().toString()
        val pass = bindingRegis.txtNewPassword.getText().toString()
        val corr = bindingRegis.txtCorreo.getText().toString()
        val nom = bindingRegis.txtNombre.getText().toString()

        //Creamos una variable para colocar llave -> valor
        val newReg = ContentValues()
        newReg.put("userLogin", user)
        newReg.put("userPass", pass)
        newReg.put("userEmail", corr)
        newReg.put("userNombre", nom)

        val res = db.insert("usuarios", null, newReg    )

        //Cerramos la BD
        db.close()

        //Evaluamos si se hizo el insert mediante la variable res
        if (res.toInt() == -1) {
            Toast.makeText(this, "no se registro el usuario", Toast.LENGTH_LONG).show()
        } else {
            //Limpiamos las cajas
            bindingRegis.txtNewUsuario.text.clear()
            bindingRegis.txtNewPassword.text.clear()
            bindingRegis.txtCorreo.text.clear()
            bindingRegis.txtNombre.text.clear()

            Toast.makeText(this, "se a registrado con exito", Toast.LENGTH_LONG).show()
            startActivity(intentPrinci)
        }
    }

}

