import javax.swing.JOptionPane;

public class Banco_App {
    public static void main(String[] args) {
        // Pedir al usuario que ingrese el nombre y número de usuario del cliente
        String nombreCliente = JOptionPane.showInputDialog("Ingrese el nombre del cliente");
        int numeroUsuario = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de usuario del cliente"));
        // Crear un objeto de la clase Cliente con los datos ingresados por el usuario
        Cliente cliente1 = new Cliente(nombreCliente, numeroUsuario);

        // Crear un objeto de la clase CuentaAhorro para el cliente
        CuentaAhorro cuentaAhorro1 = new CuentaAhorro(cliente1);
        // Mostrar al usuario la tasa de interés para la cuenta de ahorro
        JOptionPane.showMessageDialog(null, "La tasa de interés para la cuenta de ahorro es: " + cuentaAhorro1.getTasaInteres());

        // Pedir al usuario que ingrese el monto del primer depósito para la cuenta de ahorro
        double montoDeposito = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto del primer depósito para la cuenta de ahorro"));
        // Depositar el monto ingresado por el usuario en la cuenta de ahorro
        cuentaAhorro1.depositar(montoDeposito);

        // Pedir al usuario que ingrese el monto del retiro para la cuenta de ahorro
        double montoRetiro = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto del retiro para la cuenta de ahorro"));
        // Retirar el monto ingresado por el usuario de la cuenta de ahorro
        cuentaAhorro1.retirar(montoRetiro);

        // Calcular los intereses para la cuenta de ahorro
        cuentaAhorro1.calcularIntereses();
        // Mostrar al usuario el saldo en la cuenta de ahorro
        JOptionPane.showMessageDialog(null, "El saldo en la cuenta de ahorro es: " + cuentaAhorro1.getSaldo());

        // Pedir al usuario que ingrese el nombre, número de usuario y puesto del empleado
        String nombreEmpleado = JOptionPane.showInputDialog("Ingrese el nombre del empleado");
        int numeroEmpleado = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de usuario del empleado"));

        // Crear un arreglo con los puestos disponibles y mostrar un menú desplegable al usuario para que seleccione uno
        String[] puestos = {"Cajero", "Supervisor", "Recepcionista"};
        String puestoEmpleado = (String) JOptionPane.showInputDialog(null, "Seleccione el puesto del empleado", "Puesto", JOptionPane.QUESTION_MESSAGE, null, puestos, puestos[0]);

        // Pedir al usuario que ingrese la antigüedad del empleado en años
        int antiguedadEmpleado = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la antigüedad del empleado en años"));
        // Crear un objeto de la clase Empleado con los datos ingresados por el usuario
        Empleado empleado1 = new Empleado(nombreEmpleado, numeroEmpleado, puestoEmpleado, antiguedadEmpleado);
        // Mostrar al usuario el salario y los días de vacaciones del empleado
        JOptionPane.showMessageDialog(null, "El salario del empleado es: " + empleado1.getSalario());
        JOptionPane.showMessageDialog(null, "El empleado tiene " + empleado1.getDiasVacaciones() + " días de vacaciones al año");

        // Crear un objeto de la clase CuentaInversion para el cliente
        CuentaInversion cuentaInversion1 = new CuentaInversion(cliente1);
        // Mostrar al usuario la tasa de interés para la cuenta de inversión
        JOptionPane.showMessageDialog(null, "La tasa de interés para la cuenta de inversión es: " + cuentaInversion1.getTasaInteres());

        // Pedir al usuario que ingrese el monto del primer depósito para la cuenta de inversión
        double montoDepositoInversion = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto del primer depósito para la cuenta de inversión"));
        // Depositar el monto ingresado por el usuario en la cuenta de inversión
        cuentaInversion1.depositar(montoDepositoInversion);
       //Crear el monto de retiro de la cuenta de inversion.
        double montoRetiroInversion = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto del retiro para la cuenta de inversión"));
        cuentaInversion1.retirar(montoRetiroInversion);
        JOptionPane.showMessageDialog(null, "El saldo en la cuenta de inversión es: " + cuentaInversion1.getSaldo());
    }
}

// La clase Cliente representa a un cliente del banco.
class Cliente {
    // Variables de instancia privadas para el nombre y el número de usuario del cliente.
    private String nombre;
    private int numeroUsuario;

    // Constructor que toma el nombre y el número de usuario como argumentos y los asigna a las variables de instancia correspondientes.
    public Cliente(String nombre, int numeroUsuario) {
        this.nombre = nombre;
        this.numeroUsuario = numeroUsuario;
    }

    // Métodos getter y setter para las variables de instancia nombre y numeroUsuario.
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroUsuario() {
        return numeroUsuario;
    }

    public void setNumeroUsuario(int numeroUsuario) {
        this.numeroUsuario = numeroUsuario;
    }
}

// La clase CuentaBancaria es una clase abstracta que representa una cuenta bancaria.
abstract class CuentaBancaria {
    // Variables de instancia protegidas para el cliente y el saldo de la cuenta.
    protected Cliente cliente;
    protected double saldo;

    // Constructor que toma un objeto Cliente como argumento y lo asigna a la variable de instancia cliente.
    public CuentaBancaria(Cliente cliente) {
        this.cliente = cliente;
    }

    // Método para depositar dinero en la cuenta. Aumenta el saldo en la cantidad especificada.
    public void depositar(double monto) {
        saldo += monto;
    }

    // Método abstracto para retirar dinero de la cuenta. Debe ser implementado por las subclases.
    public abstract void retirar(double monto);

    // Métodos getter y setter para la variable de instancia saldo.
    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    // Método abstracto para obtener la tasa de interés de la cuenta. Debe ser implementado por las subclases.
    public abstract double getTasaInteres();
}

// La clase CuentaAhorro es una subclase de CuentaBancaria que representa una cuenta de ahorro.
//La clase CuentaAhorro hereda de la clase CuentaBancaria y representa una cuenta de ahorro en el banco. Esta clase tiene
// una constante estática TASA_INTERES que representa la tasa de interés anual para las cuentas de ahorro
class CuentaAhorro extends CuentaBancaria {
    // Constante estática para la tasa de interés de la cuenta de ahorro.
    private static final double TASA_INTERES = 0.05;

    public CuentaAhorro(Cliente cliente) {
        super(cliente);
    }
    //Metodo depositar, se utiliza para calcular el saldo y restringir un minimo del valor a depositar dentro de la cuenta
    // de ahorros
    @Override
    public void depositar(double monto) {
        if (saldo == 0 && monto < 1000) {
            JOptionPane.showMessageDialog(null, "El primer depósito debe ser de al menos $1000");
            return;
        }
        super.depositar(monto);
    }
    // El metodo retirar reliza el descuento correspondiente del saldo total de la cuenta de ahorros deacuerdo al monto que se retira,
    // con la restriccion de que debe quedar al menos 500 dentro de la cuenta y jamas debe estar en 0
    @Override
    public void retirar(double monto) {
        if (saldo - monto < 500) {
            JOptionPane.showMessageDialog(null, "La cuenta no puede quedar con menos de $500");
            return;
        }
        saldo -= monto;
    }
    // el metodo CalcularItereses realiza el calculo anual del interes de las cuentas de ahorro e inversion.
    public void calcularIntereses() {
        saldo += saldo * TASA_INTERES / 12;
    }

    @Override
    public double getTasaInteres() {
        return TASA_INTERES;
    }
}

// La clase CuentaInversion Hereda de la clase CuentaBancaria y representa una cuenta de inversión en el banco.
class CuentaInversion extends CuentaBancaria {
    // Constante estática para la tasa de interés de la cuenta de inversión.
    private static final double TASA_INTERES = 0.1;

    // Constructor que toma un objeto Cliente como parámetro y lo pasa al constructor de la clase base.
    public CuentaInversion(Cliente cliente) {
        super(cliente);
    }

    // Método sobrescrito para depositar dinero en la cuenta.
    @Override
    public void depositar(double monto) {
        // Si el saldo actual es cero (es decir, si es el primer depósito) y el monto del depósito es menor a 25000,
        // se muestra un mensaje al usuario indicando que el primer depósito debe ser de al menos $25000 y se retorna sin realizar el depósito.
        if (saldo == 0 && monto < 25000) {
            JOptionPane.showMessageDialog(null, "El primer depósito debe ser de al menos $25000");
            return;
        }
        // De lo contrario, se llama al método depositar de la clase base para realizar el depósito.
        super.depositar(monto);
    }

    // Método sobrescrito para retirar dinero de la cuenta.
    @Override
    public void retirar(double monto) {
        // Si el saldo después del retiro sería menor a 10000,
        // se muestra un mensaje al usuario indicando que la cuenta no puede quedar con menos de $10000 y se retorna sin realizar el retiro.
        if (saldo - monto < 10000) {
            JOptionPane.showMessageDialog(null, "La cuenta no puede quedar con menos de $10000");
            return;
        }
        // De lo contrario, se resta el monto del retiro del saldo.
        saldo -= monto;
    }

    // Método sobrescrito para obtener la tasa de interés de la cuenta.
    @Override
    public double getTasaInteres() {
        return TASA_INTERES;
    }
}

// La clase Empleado extiende la clase Cliente y representa un empleado del banco.
class Empleado extends Cliente {
    // Variables de instancia para almacenar el puesto y la antigüedad del empleado.
    private String puesto;
    private int antiguedad;

    // Constructor que toma como parámetros el nombre, número de usuario, puesto y antigüedad del empleado y los asigna a las variables de instancia correspondientes.
    public Empleado(String nombre, int numeroUsuario, String puesto, int antiguedad) {
        super(nombre, numeroUsuario);
        this.puesto = puesto;
        this.antiguedad = antiguedad;
    }

    // Método para calcular los días de vacaciones del empleado en función de su antigüedad.
    public int getDiasVacaciones() {
        int diasVacaciones = 5 + (antiguedad - 1) * 2;
        return Math.min(diasVacaciones, 20);
    }

    // Método para obtener el salario del empleado en función de su puesto.
    public double getSalario() {
        return switch (puesto) {
            case "Cajero" -> 1300000;
            case "Supervisor" -> 3000000;
            case "Recepcionista" -> 1200000;
            default -> 0;
        };
    }

    // Métodos getter y setter para las variables de instancia puesto y antiguedad.
    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }
}