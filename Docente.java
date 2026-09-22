public class Docente {
    private static final String[] DIAS = {
        "Lunes",
        "Martes",
        "Miercoles",
        "Jueves",
        "Viernes"
    };

    private String numeroEmpleado;
    private String nombre;
    private String materia;

    public Docente(
            String numeroEmpleado,
            String nombre,
            String materia
    ) {
        this.numeroEmpleado = numeroEmpleado;
        this.nombre = nombre;
        this.materia = materia;
    }

    public void generarReporteSemanal(Grupo[] grupos) {
        if (grupos == null) {
            throw new IllegalArgumentException(
                    "El arreglo de grupos no puede ser null."
            );
        }

        int totalAlumnosConFalta = 0;
        int totalFaltas = 0;

        System.out.println("==============================================");
        System.out.println("       REPORTE SEMANAL DE INASISTENCIAS");
        System.out.println("==============================================");
        System.out.println("Docente: " + nombre);
        System.out.println("Numero de empleado: " + numeroEmpleado);
        System.out.println("Materia: " + materia);
        System.out.println();

        for (int i = 0; i < grupos.length; i++) {
            Grupo grupo = grupos[i];

            if (grupo == null) {
                continue;
            }

            int alumnosConFaltaEnGrupo = 0;

            System.out.println("----------------------------------------------");
            System.out.println("GRUPO " + grupo.getNombre());
            System.out.println("----------------------------------------------");

            for (int j = 0; j < grupo.getCantidadAlumnos(); j++) {
                Alumno alumno = grupo.getAlumno(j);

                if (alumno.tieneInasistencia()) {
                    alumnosConFaltaEnGrupo++;
                    totalAlumnosConFalta++;

                    int faltasAlumno = alumno.contarInasistencias();
                    totalFaltas += faltasAlumno;

                    System.out.println(
                            "Matricula: " + alumno.getMatricula()
                    );
                    System.out.println(
                            "Alumno: " + alumno.getNombre()
                    );
                    System.out.println(
                            "Tutor: " + alumno.getTutor().getNombre()
                    );
                    System.out.println(
                            "Correo del tutor: "
                            + alumno.getTutor().getCorreo()
                    );
                    System.out.print("Dias que faltu: ");

                    imprimirDiasDeFalta(alumno);

                    System.out.println(
                            "Total de faltas: " + faltasAlumno
                    );
                    System.out.println();
                }
            }

            if (alumnosConFaltaEnGrupo == 0) {
                System.out.println(
                        "No se registraron inasistencias en este grupo."
                );
            }

            System.out.println(
                    "Alumnos con falta en el grupo: "
                    + alumnosConFaltaEnGrupo
            );
            System.out.println();
        }

        System.out.println("==============================================");
        System.out.println("RESUMEN GENERAL");
        System.out.println(
                "Alumnos que registraron al menos una falta: "
                + totalAlumnosConFalta
        );
        System.out.println(
                "Total de faltas registradas: " + totalFaltas
        );
        System.out.println("==============================================");
    }

    private void imprimirDiasDeFalta(Alumno alumno) {
        boolean primerDia = true;

        for (int i = 0; i < Alumno.DIAS_SEMANA; i++) {
            if (alumno.getAsistencia(i) == EstadoAsistencia.FALTO) {
                if (!primerDia) {
                    System.out.print(", ");
                }

                System.out.print(DIAS[i]);
                primerDia = false;
            }
        }

        System.out.println();
    }
}