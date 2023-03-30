package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
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
    public List<User> getUsersList() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public User findUser(String carName, int carSeries) {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        List<User> findCarList = query.getResultList();
        User resultUser = null;
        for (User user : findCarList) {
            if (user.getUserCar().getModel().equals(carName)
                    && user.getUserCar().getSeries() == carSeries) {
                resultUser = user;
            }
        }
        return resultUser;
    }
}
