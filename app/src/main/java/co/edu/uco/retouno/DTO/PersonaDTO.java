package co.edu.uco.retouno.DTO;

import android.os.Parcel;
import android.os.Parcelable;

public class PersonaDTO  implements Parcelable {

    private String nombre;
    private String apellido;
    private int edad;

    public PersonaDTO(Parcel in) {
        nombre = in.readString();
        apellido = in.readString();
        edad = in.readInt();
    }

    public static final Creator<PersonaDTO> CREATOR = new Creator<PersonaDTO>() {
        @Override
        public PersonaDTO createFromParcel(Parcel in) {
            return new PersonaDTO(in);
        }

        @Override
        public PersonaDTO[] newArray(int size) {
            return new PersonaDTO[size];
        }
    };

    public PersonaDTO() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nombre);
        parcel.writeString(apellido);
        parcel.writeInt(edad);
    }


}
