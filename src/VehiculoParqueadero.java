import java.util.Objects;

public class VehiculoParqueadero {
    private String placa;
    private String propietario;
    private String tipo;
    private String horaIngreso;

    public VehiculoParqueadero(String placa, String propietario, String tipo, String horaIngreso) {
        setPlaca(placa);
        this.propietario = propietario;
        this.tipo = tipo;
        this.horaIngreso = horaIngreso;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa.trim().toUpperCase();
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(String horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    @Override
    public String toString() {
        return "Placa: " + placa + " | Tipo: " + tipo + " | Propietario: " + propietario
                + " | Ingreso: " + horaIngreso;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        VehiculoParqueadero otro = (VehiculoParqueadero) obj;
        return Objects.equals(placa, otro.placa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(placa);
    }
}
