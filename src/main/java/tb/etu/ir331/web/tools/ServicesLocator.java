/* Based on TB classes in TP on EJB */
package tb.etu.ir331.web.tools;

import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ServicesLocator {
    //-----------------------------------------------------------------------------

    private Context initialContext;
    private Map<String, Object> cache;
    // Singleton
    private static final ServicesLocator instance = new ServicesLocator();
    //-----------------------------------------------------------------------------

    private ServicesLocator() {
        try {
            initialContext = new InitialContext();
            cache = new HashMap<String, Object>();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
    //-----------------------------------------------------------------------------

    public static ServicesLocator getInstance() {
        return instance;
    }
    //-----------------------------------------------------------------------------

    /**
     * Renvoie le stub du composant EJB.
     *
     * @param jndiName nom JNDI du composant EJB recherché
     * @return le stub du composant EJB correspondant au nom JNDI.
     * @throws ServiceLocatorException levé en cas de problèmes liés à la
     * recherche.
     */
    public Object getRemoteInterface(String nomEJB) throws ServicesLocatorException {
        System.out.println("ServicesLocator.getRemoteInterface(" + nomEJB + ")");
        // Le nom JNDI pour la récupération du service distant (stub du
        // composant EJB) est de la forme :
        //   java:global/<nom projet EAR>/<nom sous-projet EJB>/<nom bean session EJB>!<nom complet avec package de l'interface remote du bean>
        // Exemple :
        //   java:global/CabinetRecrutement_EAR/CabinetRecrutement_EJB/ServiceEntreprise!eu.telecom_bretagne.cabinet_recrutement.service.IServiceEntreprise
        //java:global/IR331-EJB/EmployeBean!services.IEmployeBean
        //java:global/IR331-EJB/EmployeBean!services.EmployeBean
        String nomJNDI = null;
        switch (nomEJB) {
            case "ServiceBean":
            case "ContratBean":
            case "EmployeBean":
//                nomJNDI = "java:global/IR331-EJB/"+nomEJB+"!services.I"+nomEJB;
                nomJNDI = "java:global/IR331-Web/" + nomEJB + "!tb.etu.ir331.services.I" + nomEJB;
                break;
            default:
                throw new ServicesLocatorException("Il n'y a pas d'EJB avec ce nom...");

        }
        // La méthode recherche d'abord le stub dans le cache, s'il est absent,
        // il est récupéré via JNDI.
        Object remoteInterface = cache.get(nomJNDI);
        if (remoteInterface == null) {
            try {
                remoteInterface = initialContext.lookup(nomJNDI);
                cache.put(nomJNDI, remoteInterface);
            } catch (Exception e) {
                throw new ServicesLocatorException(e);
            }
        }
        return remoteInterface;
    }
    //-----------------------------------------------------------------------------
}
