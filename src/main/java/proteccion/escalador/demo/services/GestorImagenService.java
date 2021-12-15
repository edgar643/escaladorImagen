/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package proteccion.escalador.demo.services;

import java.awt.Image;
import java.awt.image.BufferedImage;


public interface GestorImagenService {
 
    public BufferedImage loadImage(String name); 
    public boolean saveImage(Image image);
    public boolean procesarImagen();     
}
