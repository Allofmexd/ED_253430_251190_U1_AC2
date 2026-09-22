public class Alumno {
    public static final int DIAS_SEMANA = 5;

    private String matricula;
    private String nombre;
    private Tutor tutor;

    // Un espacio por cada dia habil de la semana.
    private EstadoAsistencia[] asistenciasSemana =
            new EstadoAsistencia[DIAS_SEMANA];

    public Alumno(String matricula, String nombre, Tutor tutor) {
        if (tutor == null) {
            throw new IllegalArgumentException(
                    "Todo alumno debe tener un tutor asignado."
            );
        }

        this.matricula = matricula;
        this.nombre = nombre;
        this.tutor = tutor;

        // Por defecto, la asistencia todavia no ha sido registrada.
        for (int i = 0; i < asistenciasSemana.length; i++) {
            asistenciasSemana[i] = null;
        }
    }

    public void registrarAsistencia(
            int numeroDia,
            EstadoAsistencia estado
    ) {
        if (numeroDia < 0 || numeroDia >= DIAS_SEMANA) {
            throw new IllegalArgumentException(
                    "El numero de dia debe estar entre 0 y 4."
            );
        }

        if (estado == null) {
            throw new IllegalArgumentException(
                    "El estado de asistencia no puede ser null."
            );
        }

        asistenciasSemana[numeroDia] = estado;
    }

    public EstadoAsistencia getAsistencia(int numeroDia) {
        if (numeroDia < 0 || numeroDia >= DIAS_SEMANA) {
            throw new IllegalArgumentException(
                    "El numero de dia debe estar entre 0 y 4."
            );
        }

        return asistenciasSemana[numeroDia];
    }

    public boolean tieneInasistencia() {
        for (int i = 0; i < asistenciasSemana.length; i++) {
            if (asistenciasSemana[i] == EstadoAsistencia.FALTO) {
                return true;
            }
        }

        return false;
    }

    public int contarInasistencias() {
        int cantidad = 0;

        for (int i = 0; i < asistenciasSemana.length; i++) {
            if (asistenciasSemana[i] == EstadoAsistencia.FALTO) {
                cantidad++;
            }
        }

        return cantidad;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public Tutor getTutor() {
        return tutor;
    }

    @Override
    public String toString() {
        return matricula + " - " + nombre;
    }
}