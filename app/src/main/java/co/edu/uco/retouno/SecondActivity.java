package co.edu.uco.retouno;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import co.edu.uco.retouno.DTO.PersonaDTO;

public class SecondActivity extends AppCompatActivity {
    private TextView txtNombre, txtApellido,txtEdad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initComponents();
        PersonaDTO personaDTO = (PersonaDTO) getIntent().getParcelableExtra("persona");
        txtNombre.setText(personaDTO.getNombre());
        txtApellido.setText(personaDTO.getApellido());
        txtEdad.setText(personaDTO.getEdad());

    }

    private void initComponents() {
        txtNombre = findViewById(R.id.txtNombre);
        txtApellido =findViewById(R.id.txtApellido);
        txtEdad  = findViewById(R.id.txtEdad);
    }
}
