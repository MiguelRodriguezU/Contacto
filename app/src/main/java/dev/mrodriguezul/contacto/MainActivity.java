package dev.mrodriguezul.contacto;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private EditText etNombres;
    private EditText etTelefono;
    private EditText etEmail;
    private EditText etDescripcion;
    private DatePicker dtpFecNac;

    private Button btnSiguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombres = (EditText)findViewById(R.id.et_contacto_nombres);
        etTelefono = (EditText)findViewById(R.id.et_contacto_telefono);
        etEmail = (EditText)findViewById(R.id.et_contacto_email);
        etDescripcion = (EditText)findViewById(R.id.et_contacto_descripcion);
        dtpFecNac = (DatePicker)findViewById(R.id.dtp_contacto_fecnac);

        btnSiguiente = (Button)findViewById(R.id.btn_siguiente);

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                confirmar();
            }
        });

        Calendar calendar = Calendar.getInstance();
        dtpFecNac.init(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH),null);
    }

    private void confirmar(){
        String msgVal = validarContacto();

        if(msgVal.equals("")){
            Intent intent = new Intent(this,DetalleActivity.class);
            intent.putExtra(Constantes.NOMBRE,etNombres.getText().toString());
            intent.putExtra(Constantes.TELEFONO,etTelefono.getText().toString());
            intent.putExtra(Constantes.EMAIL,etEmail.getText().toString());
            intent.putExtra(Constantes.DESCRIPCION,etDescripcion.getText().toString());

            String fecNac = String.format("%02d", dtpFecNac.getDayOfMonth())+"/"+
                    String.format("%02d", (dtpFecNac.getMonth()+1))+"/"+
                    dtpFecNac.getYear();
            intent.putExtra(Constantes.FECNAC,fecNac);

            startActivity(intent);
        }else{
            Snackbar.make(btnSiguiente,msgVal,Snackbar.LENGTH_LONG)
                    .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
    }

    private String validarContacto(){
        String msg = "";
        if(etNombres.getText().toString().trim().equals("")){
            return this.getResources().getString(R.string.msg_val_nombre);
        }
        if(etTelefono.getText().toString().trim().equals("")){
            return this.getResources().getString(R.string.msg_val_tel);
        }
        if(etEmail.getText().toString().trim().equals("")){
            return this.getResources().getString(R.string.msg_val_email);
        }

        return msg;
    }
}
