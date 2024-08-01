package ec.edu.ups.sd64.evarest.business;

import java.util.List;

import ec.edu.ups.sd64.evarest.dao.GeneralDao;
import ec.edu.ups.sd64.evarest.model.General;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

@Singleton
@Startup
public class GestionDatos {

    @Inject
    private GeneralDao daoUni;

    @PostConstruct
    public void init() {
        System.out.println("Inicializando datos...");

        General G1 = new General();
        G1.setNombre("Pepsi");
        G1.setDescripcion("Bebida Gaseosa");
        G1.setCaracteristica("00001");
        daoUni.insert(G1);
        
        General G2 = new General();
        G2.setNombre("220V");
        G2.setDescripcion("Bebida Energizante");
        G2.setCaracteristica("00002");
        daoUni.insert(G2);

        

        // Imprimir los libros para verificar la inserción
        System.out.println("\n------------- Elementos -------------");
        List<General> list = daoUni.getAll();
        for (General uni : list) {
            System.out.println(uni);
        }
        System.out.println("\n----------FIN-SECCION---------");
    }
}
