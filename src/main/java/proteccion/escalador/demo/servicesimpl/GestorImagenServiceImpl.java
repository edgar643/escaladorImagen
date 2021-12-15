/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proteccion.escalador.demo.servicesimpl;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.springframework.stereotype.Service;
import proteccion.escalador.demo.services.GestorImagenService;

@Service
public class GestorImagenServiceImpl implements GestorImagenService {

    final static private String FORMAT = "jpg";

    final static private int WITDH_TARGET = 1123;
    final static private int LENGHT_TARGET = 796;
    final static private int AREA_A4 = WITDH_TARGET * LENGHT_TARGET;
    public String respuesta = "";

    @Override
    public BufferedImage loadImage(String name) {
        System.out.println("Cargando Imagen");
        try {
            return ImageIO.read(new File(System.getProperty("user.dir") + "/" + name));
        } catch (IOException e) {
            System.out.println("No se pudo cargar la imagen " + name + " de " + name);
            System.out.println("El error fue : " + e.getClass().getName() + " " + e.getMessage());

            return null;
        }
    }

    @Override
    public boolean saveImage(Image image) {

        System.out.println("Salvando Imagen ");
        File imagenOutput = new File("Users/edgargarcia/Desktop/salida/imageOutput.jpg");

        try {
            ImageIO.write((RenderedImage) image, FORMAT, new File("foto.jpg"));

        } catch (IOException ex) {
            Logger.getLogger(GestorImagenServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public boolean procesarImagen() {
        System.out.println("Procesando Imagen");
        BufferedImage image = loadImage("Imagen.jpg");
        int xWidth = image.getWidth();
        int xLength = image.getHeight();
        float ratio = xWidth / xLength;

 //Comprobamos el area de la imagen 
        if (xWidth * xLength > AREA_A4) {
            saveImage(redimensionar(image));
            return true;

        } else {
            System.out.println("Imagen mas pequeña no puede ser procesada");
            respuesta = "Imagen mas pequeña no puede ser procesada imagen no cumple tamaño minimo";
            return false;
        }

    }

    public BufferedImage redimensionar(BufferedImage bf) {
        int ancho = bf.getWidth();
        int alto = bf.getHeight();
        int escalaAncho = WITDH_TARGET;
        int escalaAlto = LENGHT_TARGET;
        BufferedImage bufim = new BufferedImage(escalaAncho, escalaAlto, bf.getType());
        Graphics2D g = bufim.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(bf, 0, 0, escalaAncho, escalaAlto, 0, 0, ancho, alto, null);
        g.dispose();
        return bufim;
    }

}
