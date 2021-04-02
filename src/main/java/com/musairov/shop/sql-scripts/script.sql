DELIMITER $$ ;
CREATE PROCEDURE get_orders()
SELECT * FROM orders;
$$

call get_orders();

DELIMITER $$
CREATE TRIGGER `update_order` after update ON `orders`
    FOR EACH ROW BEGIN
    INSERT INTO orders_history SET
                                   order_id = new.id,
                                   updated_at = now(),
                                   created_at = new.created_at,
                                   total = new.total,
                                   accepted = new.accepted;
END;
$$

DELIMITER $$
CREATE TRIGGER `create_order` after insert ON `orders`
    FOR EACH ROW BEGIN
    INSERT INTO orders_history SET
                                   order_id = new.id,
                                   updated_at = now(),
                                   created_at = new.created_at,
                                   total = new.total,
                                   accepted = new.accepted;
END;
$$