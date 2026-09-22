public class Grupo {
    public static final int CAPACIDAD = 28;

    private String nombre;

    // Arreglo estatico de objetos con capacidad exacta para 28 alumnos.
    private Alumno[] alumnos = new Alumno[28];

    private int cantidadAlumnos;

    public Grupo(String nombre) {
        this.nombre = nombre;
        this.cantidadAlumnos = 0;
    }

    public boolean agregarAlumno(Alumno alumno) {
        if (alumno == null) {
            return false;
        }

        if (cantidadAlumnos >= alumnos.length) {
            return false;
        }

        if (buscarAlumno(alumno.getMatricula()) != null) {
            return false;
        }

        alumnos[cantidadAlumnos] = alumno;
        cantidadAlumnos++;

        return true;
    }

    public Alumno buscarAlumno(String matricula) {
        if (matricula == null) {
            return null;
        }

        for (int i = 0; i < cantidadAlumnos; i++) {
            if (alumnos[i].getMatricula().equalsIgnoreCase(matricula)) {
                return alumnos[i];
            }
        }

        return null;
    }

    public boolean registrarAsistencia(
            String matricula,
            int numeroDia,
            EstadoAsistencia estado
    ) {
        Alumno alumno = buscarAlumno(matricula);

        if (alumno == null) {
            return false;
        }

        alumno.registrarAsistencia(numeroDia, estado);
        return true;
    }

    public Alumno getAlumno(int posicion) {
        if (posicion < 0 || posicion >= cantidadAlumnos) {
            throw new IllegalArgumentException(
                    "La posiciun del alumno no es valida."
            );
        }

        return alumnos[posicion];
    }

    public boolean estaCompleto() {
        return cantidadAlumnos == CAPACIDAD;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidadAlumnos() {
        return cantidadAlumnos;
    }
}