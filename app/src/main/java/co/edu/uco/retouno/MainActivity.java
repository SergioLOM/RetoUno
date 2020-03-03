package co.edu.uco.retouno;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import co.edu.uco.retouno.DTO.PersonaDTO;

public class MainActivity extends AppCompatActivity {

    private Button btnAceptar, btnFecha;
    private EditText txtNombre, txtApellido, txtFecha, txtEdad;
    PersonaDTO personaDTO = new PersonaDTO();

    Calendar calendar;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();

        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                int birthDay = calendar.get(Calendar.DAY_OF_MONTH);
                int birthMonth = calendar.get(Calendar.MONTH);
                int birthYear = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        txtFecha.setText(dayOfMonth + "/" + (month+1) +"/" + year);
                        getAge ( dayOfMonth,  month,  year);
                    }
                }, birthDay, birthMonth, birthYear);
                datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
                datePickerDialog.show();
            }

            public void getAge (int dayB, int monthB, int yearB){
                calendar = Calendar.getInstance();
                int actualDay = calendar.get(Calendar.DAY_OF_MONTH);
                int actualMonth = calendar.get(Calendar.MONTH);
                int actualYear = calendar.get(Calendar.YEAR);

                int age = actualYear-yearB;
                if(actualMonth > monthB){
                    age--;
                }else if (actualMonth == monthB){
                    if(actualDay > dayB){
                        age--;
                    }
                }
                txtEdad.setText(String.valueOf(age));
            }
        });

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSecondView();
                if(personaDTO.getEdad()>18){
                    Intent intent = new Intent (getApplicationContext(),SecondActivity.class);
                    intent.putExtra("persona", personaDTO);
                    startActivity(intent);

                }else {
                    Toast.makeText(getApplicationContext(), "Eres menor de edad", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initComponents() {
        btnFecha = findViewById(R.id.btnFecha);
        txtFecha = findViewById(R.id.txtFecha);
        txtNombre = findViewById(R.id.txtNombre);
        txtApellido = findViewById(R.id.txtApellido);
        txtEdad = findViewById(R.id.txtEdad);
        btnAceptar = findViewById(R.id.btnAceptar);
    }


    public void goToSecondView(){
        String fecha = txtFecha.getText().toString();
        String nombre = txtNombre.getText().toString();
        String apellido = txtApellido.getText().toString();
        String age = txtEdad.getText().toString();

        if("".equals(nombre)){
            txtNombre.setError(getString(R.string.requerido));
        }
        else if ("".equals(apellido)){
            txtApellido.setError(getString(R.string.requerido));
        }
        else if ("".equals(fecha) || fecha == null){
            txtFecha.setError(getString(R.string.requerido));
        }
        else if (age == null){
            Toast.makeText(getApplicationContext(), "Seleccione su fecha de nacimiento", Toast.LENGTH_SHORT).show();
        }
        else{
            personaDTO.setNombre(nombre);
            personaDTO.setApellido(apellido);
            personaDTO.setEdad(Integer.parseInt(age));
        }
    }
}
