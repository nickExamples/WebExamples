/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorys;
import Entitys.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.NotAllowedException;
import org.hibernate.Query;

import org.hibernate.Session;

/**
 *
 * @author Nick
 */
@Stateless
@EJB(beanInterface = CustomerEJB.class, name = "CustomerEJB")
public class CustomerEJB {
    
    @PersistenceContext(unitName = "com.wb_restExamples_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
  
        
       public List<Customer> findAll_customer() {
           
                      
           TypedQuery<Customer> query = em.createNamedQuery("Customer.findAll", Customer.class);
           return query.getResultList();
       
           }
       
       public Customer findCustomerId(int id){
           
           
           Customer cs = em.find(Customer.class, id);
           if(cs == null){
               
                         
                throw new NotFoundException();
               }
           return cs;
           }
       
       public Collection<PurchaseOrder> getCustomerIdPO(int id){
           
           Customer cs = em.find(Customer.class, id);
           if(cs == null){
               throw new NotFoundException();
           }
           return cs.getPurchaseOrderCollection();
           
       }
       
       public Collection<PurchaseOrder> getAllCustomerPurchases(){
                      
           TypedQuery<Customer> query = em.createNamedQuery("Customer.findAll", Customer.class);
           List<Customer> Customerlist = query.getResultList();
           
           List<PurchaseOrder> po = new ArrayList<>();
           for(Customer cu : Customerlist){
               
               if(cu.getPurchaseOrderCollection() != null){
               
                   for(PurchaseOrder pu : cu.getPurchaseOrderCollection()){
                   po.add(pu);
                                  
                   }
                }
            }
        return po;   
       }
       
       public Customer addCustomer(Customer cu){
           /*
           List<Customer> cuList = findAll_customer();
           for(Customer c : cuList){
               if(cu.getCustomerId().equals(c.getCustomerId()) ){
                   
                   throw new NotAllowedException("customer id allready in use...");
               }
               
           }
          */
          // try{
               
           
           //em.getTransaction().begin();
           em.persist(cu);
           //em.getTransaction().commit();
           //em.close();
           return cu;
           //}
           //catch(Exception ex){
           //    ex.printStackTrace();
           //}
           //return null;
       }
       
       public Customer removeCustomer(int id){
            
           Customer cus = findCustomerId(id);
           
           //try{
           //    em.getTransaction().begin();
               em.remove(cus);
           //    em.getTransaction().commit();
           //    em.close();
           //}
           //catch(Exception ex){
           //    ex.printStackTrace();
            return cus;
            }
           
           
       }
    

