
/**
 * ItemInfoNode class that links and stores item information.
 * @author Avish Parmar
 */
public class ItemInfoNode {
	
	private ItemInfo itemInfo;
	private ItemInfoNode next;
	private ItemInfoNode prev;
	/**
	 * ItemInfoNode constructor
	 * @param itemInfo
	 * itemInfo object that contains all item information.
	 */
	public ItemInfoNode(ItemInfo itemInfo) {
		setInfo(itemInfo);
	}
	/**
	 * Sets the information of the item
	 * @param info
	 * Information of the item
	 */
	public void setInfo(ItemInfo info) {
		this.itemInfo = info;
	}
	/**
	 * Returns item information.
	 * @return itemInfo
	 * Information of the item.
	 */
	public ItemInfo getInfo() {
		return this.itemInfo;
	}
	/**
	 * Sets a link to the next ItemInfoNode.
	 * @param next
	 * Reference to the next node.
	 */
	public void setNext(ItemInfoNode next) {
		this.next = next;
	}
	/**
	 * Returns the next node in the list.
	 * @return next
	 * Reference to the next node in the list.
	 */
	public ItemInfoNode getNext() {
		return this.next;
	}
	/**
	 * Sets a link to the previous ItemInfoNode.
	 * @param prev
	 * Reference to the previous node
	 */
	public void setPrev(ItemInfoNode prev) {
		this.prev = prev;
	}
	/**
	 * Returns the previous node in the list.
	 * @return prev
	 * Reference to the previous node in the list
	 */
	public ItemInfoNode getPrev() {
		return this.prev;
	}
	/**
	 * Returns the information of the item in a String format.
	 */
	public String toString() {
		
		return this.itemInfo.toString();
	}
}
