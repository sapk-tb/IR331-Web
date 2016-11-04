package tb.etu.ir331.services;

import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import tb.etu.ir331.entities.Employe;

/**
 *
 * @author sapk
 */
@Stateless
@LocalBean
public class EmployeBean implements IEmployeBean {

    SessionContext ctx;
    @Override
    public void create(String name) {
    }

    @Override
    public Employe[] list() {
        Employe[] list = new Employe[] {};
        return list;
    }
}
