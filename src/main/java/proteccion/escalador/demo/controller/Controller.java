/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proteccion.escalador.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proteccion.escalador.demo.services.GestorImagenService;

@RestController
@RequestMapping("/")

public class Controller {

    @Autowired
    private GestorImagenService service;

    private String resultadoExitoso = "Procesado correctamente la imagen quedo alojado en la ruta: " + "ruta de prueba de salida";
    private String resultadoFallido = "la imagen no pudo ser reescalada: ";

    @GetMapping
    public String index() {
        return "INICIA PROYECTO WEB 2";
    }

    @GetMapping("/procesarImagen")
    public String procesarImagen() {
        System.out.println("Procesando Imagen");

        boolean res = service.procesarImagen();
        String resultado = "vacia";
        if (res == true) {
            resultado = resultadoExitoso;

        } else {
            resultado = resultadoFallido;

        }

        return resultado;
    }

}
