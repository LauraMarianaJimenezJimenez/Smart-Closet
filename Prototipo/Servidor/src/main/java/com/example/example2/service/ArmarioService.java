package com.example.example2.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Blob;
import java.util.ArrayList;

import com.example.example2.model.Armario;
import com.example.example2.model.ArmarioRepository;
import com.example.example2.model.Prenda;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
class ArmarioService {

    @Autowired
    private ArmarioRepository repository;

    @Autowired
    private UsuarioService userService;

    @Autowired
    private PrendaService prendaService;
    
    @GetMapping("/armario/{nickname}")
    public Armario findClosetByUser(@PathVariable("nickname") String nickname) {
        return userService.findByNickname(nickname).getArmario();
    }

    @GetMapping("/armario/{nickname}/prendas")
    public Iterable<Prenda> getPrendasUser(@PathVariable("nickname") String nickname) {
        return findClosetByUser(nickname).getPrendas();
    }

    @GetMapping("/armario/{nickname}/prendas/superior")
    public Iterable<Prenda> getPrendasSuperiores(@PathVariable("nickname") String nickname) {
        ArrayList<Prenda> superiores = new ArrayList<Prenda>();
        Iterable<Prenda> prendas = getPrendasUser(nickname);
        for(Prenda actual: prendas) {
            if(actual.getSeccion().equalsIgnoreCase("Superior")){
                superiores.add(actual);
            }
        }
        return superiores;
    }

    @GetMapping("/armario/{nickname}/prendas/inferior")
    public Iterable<Prenda> getPrendasInferiores(@PathVariable("nickname") String nickname) {
        ArrayList<Prenda> inferiores = new ArrayList<Prenda>();
        Iterable<Prenda> prendas = getPrendasUser(nickname);
        for(Prenda actual: prendas) {
            if(actual.getSeccion().equalsIgnoreCase("Inferior")){
                inferiores.add(actual);
            }
        }
        return inferiores;
    }

    @GetMapping("/armario/{nickname}/prendas/zapatos")
    public Iterable<Prenda> getPrendasZapatos(@PathVariable("nickname") String nickname) {
        ArrayList<Prenda> zapatos = new ArrayList<Prenda>();
        Iterable<Prenda> prendas = getPrendasUser(nickname);
        for(Prenda actual: prendas) {
            if(actual.getSeccion().equalsIgnoreCase("Zapato")){
                zapatos.add(actual);
            }
        }
        return zapatos;
    }

    @GetMapping("/armario/{nickname}/prendas/accesorios")
    public Iterable<Prenda> getPrendasAccesorios(@PathVariable("nickname") String nickname) {
        ArrayList<Prenda> accesorios = new ArrayList<Prenda>();
        Iterable<Prenda> prendas = getPrendasUser(nickname);
        for(Prenda actual: prendas) {
            if(actual.getSeccion().equalsIgnoreCase("Accesorio")){
                accesorios.add(actual);
            }
        }
        return accesorios;
    }

    @GetMapping("/armario/{nickname}/prendas/favoritos")
    public Iterable<Prenda> getPrendasFavoritas(@PathVariable("nickname") String nickname) {
        ArrayList<Prenda> favoritas = new ArrayList<Prenda>();
        Iterable<Prenda> prendas = getPrendasUser(nickname);
        for(Prenda actual: prendas) {
            if(actual.isFavorito()){
                favoritas.add(actual);
            }
        }
        return favoritas;
    }

    @PostMapping("/crearArmario")
    public Armario crearArmario(@RequestBody Armario armario) {
        return repository.save(armario);
    }

    @PutMapping("/agregarPrenda/{nickname}")
    public Armario agregarPrenda(@PathVariable("nickname") String nickname, @RequestBody Prenda prenda){
        Armario armarioEncontrado = findClosetByUser(nickname);
        Long numPrendas = armarioEncontrado.getNumPrendas();
        Prenda newPrenda = new Prenda();
        newPrenda.setSeccion(prenda.getSeccion());
        newPrenda.setTipo(prenda.getTipo());
        newPrenda.setNivel_formalidad(prenda.getNivel_formalidad());
        newPrenda.setNivel_abrigo(prenda.getNivel_abrigo());
        newPrenda.setDisponible(prenda.isDisponible());
        newPrenda.setFavorito(prenda.isFavorito());
        newPrenda.setDescripcion(prenda.getDescripcion());
        newPrenda.setColor(prenda.getColor());
        newPrenda.setImg_url(prenda.getImg_url());
        newPrenda.setArmario(armarioEncontrado);
        //newPrenda.setImagen(BlobProxy.generateProxy(codeImage(prenda.getImg_url())));

        armarioEncontrado.setNumPrendas((numPrendas + 1));
        prendaService.crearPrenda(newPrenda);
        armarioEncontrado.getPrendas().add(newPrenda);
        
        return repository.save(armarioEncontrado);
    }

    public static byte[] codeImage(String img) {
        Path path = Paths.get("../../../../../../../../App/src/assets/images/" + img);
        byte[] data = null;
        try{
            data = Files.readAllBytes(path);
        }catch(IOException e){
            e.printStackTrace();
        }
        return data;
    }

    public static String decodeImage(Blob image, String img_url){
        String url = "../ImagesDB";
        try{
            InputStream is = image.getBinaryStream();
            Files.copy(is, Paths.get(url + img_url), StandardCopyOption.REPLACE_EXISTING);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return url;
    }

}