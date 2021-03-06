package br.ufscar.rcms.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import br.ufscar.rcms.dao.BaseDAO;

@Repository
public abstract class BaseDAOImpl<T, K extends Serializable> implements BaseDAO<T, K> {

    private static final long serialVersionUID = 4390975362917625589L;
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    private Class<T> clazz;

    @SuppressWarnings("unchecked")
    public BaseDAOImpl() {

        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        clazz = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

    @Override
    public T buscar(final Long id) {
        return entityManager.find(clazz, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> buscarTodos() {
        return createQuery("from " + clazz.getName()).getResultList();
    }

    @Override
    public Long count() {
        return (Long) createQuery("select count(*) from " + clazz.getName()).getSingleResult();
    }

    @Override
    public void salvar(final T entidade) {
        entityManager.persist(entidade);
    }

    @Override
    public T atualizar(final T entidade) {
        return entityManager.merge(entidade);
    }

    @Override
    public T saveOrUpdate(final T entidade) {
        getSession().saveOrUpdate(entidade);
        return entidade;
    }

    @Deprecated
    @Override
    public T salvarOuAtualizar(T entidade) {

        if (getEntityId(entidade) != null) {
            entidade = atualizar(entidade);
        } else {
            salvar(entidade);
        }

        return entidade;
    }

    @Override
    public void remover(final T entidade) {
        entityManager.remove(entityManager.contains(entidade) ? entidade : entityManager.merge(entidade));
    }

    @Override
    public void remover(final Long id) {
        T entidade = buscar(id);
        remover(entidade);
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public void setClazz(final Class<T> clazz) {
        this.clazz = clazz;
    }

    public Query createQuery(final String query) {
        return entityManager.createQuery(query);
    }

    public Query createNativeQuery(final String query) {
        return entityManager.createNativeQuery(query);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    private Long getEntityId(final T entidade) {
        return getEntityId(entidade, entidade.getClass());
    }

    private Long getEntityId(final T entidade, final Class<?> clazz) {
        Object id = null;
        try {
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Id.class)) {
                    field.setAccessible(true);
                    id = field.get(entidade);
                    break;
                }
            }

        } catch (IllegalArgumentException | IllegalAccessException e) {
            LOGGER.error(e.getMessage(), e);
        }

        if (id == null && clazz.getSuperclass() != null) {
            id = getEntityId(entidade, clazz.getSuperclass());
        }

        return id == null ? null : (Long) id;
    }
}