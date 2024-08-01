package ec.edu.ups.sd64.evarest.business;

import java.util.List;

import ec.edu.ups.sd64.evarest.dao.GeneralDao;
import ec.edu.ups.sd64.evarest.model.General;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionGenerales {
    
    @Inject
    private GeneralDao daogen;

    public void guardarGeneral(General general) {
    	General gen = daogen.read(general.getId());
        if (gen != null){
        	daogen.update(general);
        } else {
        	daogen.insert(general);
        }
    }
    
    public void actualizarGeneral(General elemento) throws Exception {
    	General gen = daogen.read(elemento.getId());
        if (gen != null){
        	daogen.update(elemento);
        } else {
            throw new Exception("El Elemento no existe");
        }
    }
    
    public General getElementoPorExtra(String extra) throws Exception {
        if (extra == null || extra.isEmpty()) {
            throw new Exception("Informacion incorrecta");
        }
        return daogen.getPorExtra(extra);
    }
    
    public void borrarElemento(int codigo) {
    	daogen.remove(codigo);
    }
    
    public List<General> getGenerales() {
        return daogen.getAll();
    }
}
