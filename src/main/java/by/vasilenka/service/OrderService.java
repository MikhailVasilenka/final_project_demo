package by.vasilenka.service;

import by.vasilenka.domain.Drug;
import by.vasilenka.domain.Item;
import by.vasilenka.domain.Order;
import by.vasilenka.repository.Repository;
import by.vasilenka.repository.RepositoryFactory;
import by.vasilenka.repository.exception.RepositoryException;
import by.vasilenka.repository.impl.*;
import by.vasilenka.repository.specification.Specification;
import by.vasilenka.repository.specification.drug.GetDrugById;
import by.vasilenka.repository.specification.item.GetItemsByOrderId;
import by.vasilenka.repository.specification.order.GetOrderById;
import by.vasilenka.repository.specification.order.GetOrdersByUserId;
import by.vasilenka.service.exception.ServiceException;

import java.util.List;

public class OrderService {
    private RepositoryFactory repositoryFactory = JdbcRepositoryFactory.getInstance();
    private Repository<Order, Integer> orderRepository = repositoryFactory.getRepository(OrderRepository::new);
    private Repository<Order, Integer> orderTransactionalRepository = repositoryFactory.getTransactionalRepository(OrderRepository::new);
    private Repository<Item, Integer> itemTransactionalRepository = repositoryFactory.getTransactionalRepository(ItemRepository::new);
    private Repository<Drug, Integer> drugTransactionalRepository = repositoryFactory.getTransactionalRepository(DrugRepository::new);

    public void confirm(Order order) throws ServiceException {
        try {
            order.setStatus(Order.Status.COMPLETED.name());
            orderRepository.update(order);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public Order getOrderById(int id) throws ServiceException {
        Order order = new Order();
        order.setId(id);
        TransactionManager manager = new TransactionManager();
        try {
            manager.begin(orderTransactionalRepository, itemTransactionalRepository);
            List<Order> orderList = orderRepository.getQuery(order, new GetOrderById());
            if (orderList.isEmpty()) {
                return null;
            }
            Order resultOrder = orderList.get(0);
            Item item = new Item();
            item.setOrderId(resultOrder.getId());
            List<Item> itemList = itemTransactionalRepository.getQuery(item, new GetItemsByOrderId());
            resultOrder.setItemList(itemList);
            manager.end();
            return resultOrder;
        } catch (RepositoryException e) {
            try {
                manager.rollback();
            } catch (RepositoryException e1) {
                throw new ServiceException(e1);
            }
            throw new ServiceException(e);
        } finally {
            manager.end();
        }
    }

    public Order addOrder(Order order) throws ServiceException {
        TransactionManager manager = new TransactionManager();
        List<Item> itemList = order.getItemList();
        try {
            manager.begin(itemTransactionalRepository, orderTransactionalRepository,drugTransactionalRepository);
            Order resultOrder = orderTransactionalRepository.add(order);
            for (Item item : itemList) {
                item.setOrderId(resultOrder.getId());
                itemTransactionalRepository.add(item);
                List<Drug> drugList = drugTransactionalRepository.getQuery(item.getDrug(),new GetDrugById());
                if (drugList.isEmpty()){
                    throw new ServiceException("can't get drug by Id");
                }
                Drug drug = drugList.get(0);
                int resultAmount = drug.getAvailableAmount() - item.getAmount();
                drug.setAvailableAmount(resultAmount);
                drugTransactionalRepository.update(drug);
            }
            manager.commit();
            return order;
        } catch (RepositoryException e) {
            try {
                manager.rollback();
            } catch (RepositoryException e1) {
                throw new ServiceException(e1);
            }
            throw new ServiceException(e);
        } finally {
            manager.end();
        }
    }

    public void pay(Order order) throws ServiceException {

        try {
            order.setStatus(Order.Status.AT_WORK.name());
            orderRepository.update(order);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public List<Order> getAllUserOrders(int userId) throws ServiceException {
        Specification<Order> spec = new GetOrdersByUserId();
        try {
            Order order = new Order();
            order.setUserId(userId);
            return orderRepository.getQuery(order, spec);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
}
