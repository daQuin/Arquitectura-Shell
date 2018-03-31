package co.edu.ufps.cliente;

import co.edu.ufps.controlador.Conexion;
import co.edu.ufps.modelo.Mensaje;
import co.edu.ufps.modelo.Usuario;
import java.io.IOException;
import java.util.Scanner;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 *
 * @author Sergio
 */
public class Main{

    public static void main(String[] args) throws IOException {
        try {
            Scanner sc = new Scanner(System.in);

        System.out.println("¡Bienvenido!");
        System.out.print("Ingrese su usuario : ");
        String usuario = sc.next();
        Usuario remitente = Conexion.Login(usuario);
        if (remitente == null) {
            remitente = Conexion.registrarUsuario(usuario);
//            System.out.println("Hola, " + remitente.getUsuario());
        } else {
            System.out.println("Hola, " + remitente.getUsuario());
        }
        System.out.println("Ahora porfavor selecciona uno de los siguientes usuarios para chatear con él : ");
        Usuario[] lista = Conexion.listarUsuarios();
        for (int i = 0; i < lista.length; i++) {
            if (lista[i].getId() != remitente.getId()) {
                System.out.println(i + " - " + lista[i].getUsuario());
            }
        }
        System.out.print("Digita el ID del usuario con el que quieres chatear : ");
        int id =  sc.nextInt();
        Usuario destinatario = lista[id];
        
//        System.out.print("Escribe el mensaje : ");
        String texto = "";
            while(true) {
                System.out.print("Escribe el mensaje : ");
                texto = sc.nextLine();
                if(texto.equals("q")) {
                    System.out.println("slio");
                    break;
                } if(!texto.equals("")) {
                    Conexion.enviarMensaje(texto, remitente, destinatario); 
                   System.out.println("mensajes:");
                   Mensaje[] l = Conexion.listarMensaje();
                    for (int i = 0; i < l.length; i++) {
                            System.out.println(l[i].getRemitente().getUsuario() + " - " + l[i].getTexto());  
                    }
                }
                
            }
        } catch (Exception e) {
        }
        
    }

}
