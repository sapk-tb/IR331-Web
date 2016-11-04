package tb.etu.ir331.services;

import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import tb.etu.ir331.entities.Contrat;

/**
 *
 * @author sapk
 */
@Stateless
@LocalBean
public class ContratBean implements IContratBean {

    SessionContext ctx;

    @Override
    public void create() {
        //TODO
    }

    @Override
    public Contrat[] list() {
        //TODO
        Contrat[] list = new Contrat[] {};
        return list;
    }

}
