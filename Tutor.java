public class Tutor {
    private String clave;
    private String nombre;
    private String correo;

    public Tutor(String clave, String nombre, String correo) {
        this.clave = clave;
        this.nombre = nombre;
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    @Override
    public String toString() {
        return clave + " - " + nombre + " (" + correo + ")";
    }
}