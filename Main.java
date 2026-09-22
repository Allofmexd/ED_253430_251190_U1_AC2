public class Main {
    private static final int CANTIDAD_GRUPOS = 2;
    private static final int CANTIDAD_TUTORES = 4;

    public static void main(String[] args) {
        Tutor[] tutores = crearTutores();

        Grupo grupoA = new Grupo("A");
        Grupo grupoB = new Grupo("B");

        inscribirAlumnos(grupoA, "A", tutores, 0);
        inscribirAlumnos(grupoB, "B", tutores, 2);

        verificarGrupos(grupoA, grupoB);

        // Se simulan las asistencias de lunes a viernes.
        simularSemana(grupoA, 0);
        simularSemana(grupoB, 5);

        Docente docente = new Docente(
                "DOC-001",
                "Laura Hernandez",
                "Estructura de Datos"
        );

        Grupo[] grupos = new Grupo[CANTIDAD_GRUPOS];
        grupos[0] = grupoA;
        grupos[1] = grupoB;

        docente.generarReporteSemanal(grupos);
    }

    private static Tutor[] crearTutores() {
        Tutor[] tutores = new Tutor[CANTIDAD_TUTORES];

        tutores[0] = new Tutor(
                "T-01",
                "Ana Martinez",
                "ana.martinez@universidad.edu"
        );

        tutores[1] = new Tutor(
                "T-02",
                "Carlos Ramirez",
                "carlos.ramirez@universidad.edu"
        );

        tutores[2] = new Tutor(
                "T-03",
                "Maria Lupez",
                "maria.lopez@universidad.edu"
        );

        tutores[3] = new Tutor(
                "T-04",
                "Jose Torres",
                "jose.torres@universidad.edu"
        );

        return tutores;
    }

    private static void inscribirAlumnos(
            Grupo grupo,
            String letraGrupo,
            Tutor[] tutores,
            int desplazamientoTutor
    ) {
        for (int i = 0; i < Grupo.CAPACIDAD; i++) {
            int numeroAlumno = i + 1;

            String matricula =
                    letraGrupo + String.format("%03d", numeroAlumno);

            String nombre =
                    "Alumno " + letraGrupo + " " + numeroAlumno;

            int posicionTutor =
                    (i + desplazamientoTutor) % tutores.length;

            Alumno alumno = new Alumno(
                    matricula,
                    nombre,
                    tutores[posicionTutor]
            );

            boolean agregado = grupo.agregarAlumno(alumno);

            if (!agregado) {
                System.out.println(
                        "No fue posible agregar al alumno "
                        + matricula
                        + " al grupo "
                        + grupo.getNombre()
                );
            }
        }
    }

    private static void verificarGrupos(Grupo grupoA, Grupo grupoB) {
        if (!grupoA.estaCompleto() || !grupoB.estaCompleto()) {
            throw new IllegalStateException(
                    "Cada grupo debe contener exactamente 28 alumnos."
            );
        }

        System.out.println(
                "Grupo A inscrito con "
                + grupoA.getCantidadAlumnos()
                + " alumnos."
        );

        System.out.println(
                "Grupo B inscrito con "
                + grupoB.getCantidadAlumnos()
                + " alumnos."
        );

        System.out.println();
    }

    private static void simularSemana(
            Grupo grupo,
            int desplazamiento
    ) {
        for (int i = 0; i < grupo.getCantidadAlumnos(); i++) {
            Alumno alumno = grupo.getAlumno(i);

            for (int dia = 0; dia < Alumno.DIAS_SEMANA; dia++) {
                int patron =
                        (i * 3 + dia * 2 + desplazamiento) % 17;

                EstadoAsistencia estado;

                if (patron == 0 || patron == 1) {
                    estado = EstadoAsistencia.FALTO;
                } else if (patron == 2 || patron == 3) {
                    estado = EstadoAsistencia.PERMISO;
                } else {
                    estado = EstadoAsistencia.ASISTIO;
                }

                alumno.registrarAsistencia(dia, estado);
            }
        }
    }
}