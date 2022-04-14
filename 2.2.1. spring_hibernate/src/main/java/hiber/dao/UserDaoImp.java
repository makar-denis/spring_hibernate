package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserCar (String model, String series){
      String hql = "FROM User u WHERE u.car.model=:Mod and u.car.series=:Ser";
      Query query= sessionFactory.getCurrentSession().createQuery(hql);
      query.setParameter("Mod", model);
      query.setParameter("Ser", series);
      List<User> userList = query.getResultList();
//      return  (User) query.uniqueResult();
      return userList.get(0);
//      Car carUser = (Car) sessionFactory.getCurrentSession().
//              createQuery("FROM Car where model = '"+model+"' and series ='"+series+"'").
//              uniqueResult();
//      return carUser.getUser();

   }



}
