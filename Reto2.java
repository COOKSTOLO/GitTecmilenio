import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;

public class Reto2 {
    private static Scanner entrada;
    
    public static void main(String[] args) throws IOException{
        entrada = new Scanner(System.in);
        
        System.out.println("Elija una de las siguientes opciones:\n1. Calculadora de promedio final\n2. Programa de venta de cajas");
        switch(entrada.nextInt()){
            case 1->{ // * Case 1
                
                int Cal;
                float promedio = 0;
                Formatter promFinal = new Formatter(); // * Init a Formatter object to give format to the result of the operation.
                //* Data input
                System.out.println("¿Cuantas calificaciones desea promediar?: ");
                Cal = entrada.nextInt();
                
                for(int i = 1; i <= Cal; i++){
                    System.out.println("Ingrese la calificacion #" + i + ": ");
                    promedio += entrada.nextFloat();
                }
                
                promedio /= Cal;
                promFinal.format("%.1f", promedio);
                //* Result output
                System.out.println("El promedio final del alumno es de: " + promFinal.toString());
                promFinal.close(); 
            }
            case 2->{ // * Case 2
                 
                Reto2 rtd = new Reto2();
                PuntoDeVenta programa = rtd.new PuntoDeVenta("admin", "admin");
                programa.start();
            }
        }
        entrada.close();
    }

    private class PuntoDeVenta{
        
        private String adminUsername, adminPassword;
        private int numVentas, numCompras, operacionesTotales;
        private float balance, ingresosTotales, egresosTotales;

        public PuntoDeVenta(String user, String pass){ 
            this.adminUsername = user;
            this.adminPassword = pass;
            this.balance = 0;
            this.ingresosTotales = 0;
            this.egresosTotales = 0;
        }
        
        public void start(){
            System.out.println(" PUNTO DE VENTA \"Venta de cajas\" ----");
            iniciarSesion(); 
            int opt = 0; 
            while(opt != 4){ 
                opt = obtenerOpcion(); 
                switch(opt){ 
                    case 1->{ // sell
                        int nCajas;
                        float costoCajas;
                        //* Data input
                        System.out.println("¿Cuantas cajas se van a comprar?: ");
                        nCajas = entrada.nextInt();
                        System.out.println("Costo por pieza: ");
                        costoCajas = entrada.nextFloat();
                        //* Result output
                        System.out.println("La compra realizada por " + nCajas + " cajas al costo de $" + costoCajas + " es igual a $" + compra(nCajas, costoCajas));
                    }
                    case 2->{ // buy
                        int nCajas;
                        float precioCajas;
                    
                        System.out.println("¿Cuantas cajas se van a vender?");
                        nCajas = entrada.nextInt();
                        System.out.println("Costo por pieza: ");
                        precioCajas = entrada.nextFloat();
                        //* Result output
                        System.out.println("La venta realizada por " + nCajas + " cajas al precio de $" + precioCajas + " es igual a $" + venta(nCajas, precioCajas));
                    }
                    case 3->{ 
                        System.out.println("--- REPORTE GENERAL ---");
                        System.out.println("Balance: " + imprimirBalance(balance));
                        System.out.println("Numero de ventas totales: " + numVentas);
                        System.out.println("Numero de compras totales: " + numCompras);
                        System.out.println("Ingresos totales: $" + ingresosTotales);
                        System.out.println("Egresos totales: $" + egresosTotales);
                    }
                    case 4->{ // exit
                        System.out.println("----------------------");
                        System.out.println("Balance final: " + imprimirBalance(balance));
                        System.out.println("Numero de ventas totales: " + numVentas);
                        System.out.println("Numero de compras totales: " + numCompras);
                        System.out.println("Operaciones totales: " + operacionesTotales);
                        System.out.println("Ingresos totales: $" + ingresosTotales);
                        System.out.println("Egresos totales: $" + egresosTotales);
                        System.out.println("--- ¡GRACIAS POR SU CONFIANZA! ---");
                    }
                    default->{ 
                        System.out.println("Inserte una opcion valida");
                    }
                }
            }
        }
        
        private void iniciarSesion(){
            String tempUser = ".";
            String tempPass = ".";
            while(tempUser.equals(this.adminUsername) == false && tempPass.equals(this.adminPassword) == false){
                System.out.println("username: ");
                tempUser = entrada.next();
                System.out.println("password: ");
                tempPass = entrada.next();
                if(tempUser.equals(this.adminUsername) == true && tempPass.equals(this.adminPassword) == true){
                    System.out.println("a iniciado sesion correctamente.");
                    break;
                }else{
                    System.out.println("Intenta de nuevo");
                }
            }
        }
       
        private int obtenerOpcion(){
            System.out.println("----------------------");
            System.out.println("¿Que desea hacer?\n1. Comprar cajas\n2. Vender cajas\n3. Mostrar reporte \n4. Salir del programa");
            int res;
            res = entrada.nextInt();
            return res;
        }
        
        private float compra(int cantCajas, float costCajas){
            float res = (float)(costCajas * cantCajas);
            numCompras++;
            operacionesTotales++;
            egresosTotales += res;
            balance -= res;
            return res;
        }
       
        private float venta(int cantCajas, float precioCajas){
            float res = (float)(cantCajas * precioCajas);
            numVentas++;
            operacionesTotales++;
            ingresosTotales += res;
            balance += res;
            return res;
        }
        
        private String imprimirBalance(float cantidad){
            if(cantidad<0){
                return (String)("-$"+Math.abs(cantidad));
            }else{
                return (String)("$"+Math.abs(cantidad));
            }
        }
    }}