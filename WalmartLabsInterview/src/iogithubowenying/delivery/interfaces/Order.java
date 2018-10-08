package iogithubowenying.delivery.interfaces;

/**
 * Order interface defines 3 getter/setters and 3 checking methods
 * which associated with OrderID, Direction, OrderTime.
 * 
 * A more complicated Order can implement this order and extend
 * is own functionalities; Also other Order interface can extend
 * this interface and add features to this Order.
 * 
 * Current the Order includes : WMXXXX N10S8 00:00:00, also 
 * with implementing this method, different categories of 
 * goods can have its more specific OrderID/Direction/OrderTime.
 * eg: GAMEXXXX [100,20] month/day/year_00:00:00
 * 
 * 
 * @author Owen
 *
 */
public interface Order {
	
	/**
	 * Get OrderID, which will return an interface type -- OrderID
	 * Since each order must has its ID.
	 * @return OrderID
	 */
	OrderID getOrderID();
	
	/**
	 * Set OrderID
	 * @param id
	 * @return whether set action is successful
	 */
	boolean setOrderID(OrderID id);
	
	/**
	 * Get Direction, which returns an OrderDirection interface type,
	 * indicates the User's direction.
	 * @return OrderDirection
	 */
	OrderDirection getDirection();
	
	/**
	 * Set OrderDirection
	 * @param orderDirection
	 * @return whether set action is successful
	 */
	boolean setDirection(OrderDirection orderDirection);
	
	/**
	 * Get OrderTime, which returns an OrderTime interface,
	 * indicating when does the user make this order.
	 * @return OrderTime
	 */
	OrderTime getOrderTime();
	
	/**
	 * Set OrderTime
	 * @param orderTime
	 * @return whether set action is successful
	 */
	boolean setOrderTime(OrderTime orderTime);
	
	/**
	 * When setting an OrderID, firstly need to check whether it is valid
	 * @param id
	 * @return whether the OrderID is valid
	 */
	boolean isOrderIDValid(OrderID id);
	/**
	 * When setting an Order Direction, firstly need to check whether it is valid
	 * @param orderDirection
	 * @return whether the OrderDirection is valid
	 */
	boolean isOrderDirectionValid(OrderDirection orderDirection);
	/**
	 * When setting an Order Arriving Time, firstly need to check whether it is valid
	 * @param orderTime
	 * @return whether the OrderTime is valid
	 */
	boolean isOrderTimeValid(OrderTime orderTime);
}
