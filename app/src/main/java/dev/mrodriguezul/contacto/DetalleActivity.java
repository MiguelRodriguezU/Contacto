package dev.mrodriguezul.contacto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetalleActivity extends AppCompatActivity {
    private TextView tvNombres;
    private TextView tvFecNac;
    private TextView tvTelefono;
    private TextView tvEmail;
    private TextView tvDescripcion;
    private Button btnEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        tvNombres = (TextView)findViewById(R.id.tv_contacto_nombres);
        tvFecNac = (TextView)findViewById(R.id.tv_contacto_fec_nacimiento);
        tvTelefono = (TextView)findViewById(R.id.tv_contacto_telefono);
        tvEmail = (TextView)findViewById(R.id.tv_contacto_email);
        tvDescripcion = (TextView)findViewById(R.id.tv_contacto_descripcion);
        btnEditar = (Button)findViewById(R.id.btn_editar);

        Bundle extras = getIntent().getExtras();

        tvNombres.setText(extras.get(Constantes.NOMBRE).toString());
        tvFecNac.setText(extras.get(Constantes.FECNAC).toString());
        tvTelefono.setText(extras.get(Constantes.TELEFONO).toString());
        tvEmail.setText(extras.get(Constantes.EMAIL).toString());
        tvDescripcion.setText(extras.get(Constantes.DESCRIPCION).toString());

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editarContacto();
            }
        });
    }

    private void editarContacto(){

        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra(Constantes.NOMBRE,tvNombres.getText().toString());
        intent.putExtra(Constantes.TELEFONO,tvTelefono.getText().toString());
        intent.putExtra(Constantes.EMAIL,tvEmail.getText().toString());
        intent.putExtra(Constantes.DESCRIPCION,tvDescripcion.getText().toString());
        intent.putExtra(Constantes.FECNAC,tvFecNac.getText().toString());

        startActivity(intent);
        this.finish();
    }

}
