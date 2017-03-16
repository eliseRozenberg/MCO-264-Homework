package MMnotes;

/*
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.Stack;
*/

public class Notes {
	/*
	 * /* Question 1 -
	 * 
	 * a. it should support an add/insert, remove, remove all, is empty,
	 * iterator, toString
	 * 
	 * b. it can add and remove easier because an array would have to reformat
	 * itself while the linkedList can just shift the pointers
	 * 
	 * c. see code
	 * 
	 * d. no - because the arrayList you can just get the value thats being
	 * referenced based on the position in the array in a linked list you have
	 * to go through the entire list until you get to the one in the correct
	 * position
	 *
	 * e. because all you have to do is run through the iterator and keep track
	 * of how many iterations one u get to the right one u get the data and if
	 * you run through the iterator, then the position doesn't exist
	 * 
	 * f. see code
	 * 
	 * g. the linked list is more efficient because it just has to add a new
	 * node an array list may have to create another array that is bigger and
	 * move all the elements from one array to the next and keep track of so
	 * many different variables.
	 */

	/*
	 * Question 2-
	 * 
	 * a. because if you have to increase the size of one of the fields - you
	 * may overwrite into the next entry of the RAF. therefore you have a set
	 * size that will be large enough to fit different possible lengths.
	 * 
	 * c. you would need and index that would store the id andf location in the
	 * RAF of the item. it would be stored separately from the RAF in a class
	 * that (may hold)holds other information including (ex) the RAF name this
	 * class can be stored as one object using input and output stream
	 * 
	 * d. you have to write a string into the raf that has a fixed length that
	 * can accommodate for all options since the string can have different
	 * lengths see b. for code
	 */

	// b.
	// if there is a rewrite method as well then wouldn't check to see if the
	// location is null;

	/*
	 * public void writeToFile(RandomAccessFile file, Long location){
	 * 
	 * if (location==null){ location = file.length(); } file.seek(location);
	 * 
	 * file.writeLong(barcode); file.writeDouble(cost)
	 * file.writeInt(quantityInStock); file.writeInt(warehouseNumber); //d. also
	 * add the description file.writeUTF("%-11s", itemDescription); }
	 */

	/*
	 * Question3-
	 * 
	 * i. use an input and output stream because it will store all the
	 * information as one object that can be retrieved and restored.
	 * 
	 * ii. you would have to make all the classes implement Serializable and
	 * then in main - see code
	 * 
	 * iii. see code
	 * 
	 * iv. see code v. see code
	 * 
	 * vi. an arrayList is a better option because the necklace has a fixed
	 * length so there isn't the inefficiency of resizing the array just create
	 * the array according to the size. an arrayList can also sort easier
	 * according to the beads that will compare color. the max length and the
	 * current length are the length and the size of the array. the add color
	 * can add according to color by getting the position where the bead has the
	 * color i and adding the new bead after that element in a way this linked
	 * list is behaving like an arraylist.
	 */
	// put it into a method to get rid of red lines

	/*
	 * public void ignore() { try { ObjectOutputStream output = new
	 * ObjectOutputStream(new FileOutputStream("necklace.ser"));
	 * output.writeObject(aNecklace); output.close(); } catch (IOException e) {
	 * e.printStackTrace(); }
	 * 
	 * try { ObjectInputStream input = new ObjectInputStream(new
	 * FileInputStream("necklace.ser")); try { Necklace aNecklace = (Necklace)
	 * input.readObject(); } catch (ClassNotFoundException e) {
	 * e.printStackTrace(); } input.close(); } catch (IOException e) {
	 * e.printStackTrace(); } }
	 */

	/*
	 * Question 4 -
	 * 
	 * i. you can use a stack to implement this necklace because you can push
	 * and pop as you need until you are finished adding. it imitates putting
	 * beads on a string an array would have to use many loops to do this
	 */
	// ii - tested code in StringNecklaces

	/*
	 * private Stack<Bead> neck = new Stack();
	 * 
	 * public void addBead(Bead bead) { Bead[] temp = new Bead[neck.size()]; int
	 * i = 0; if (bead.getLength() <= (maxLength - currLength)) { if
	 * (neck.isEmpty()) { neck.push(bead); currLength += bead.getLength();
	 * return; } // go through and pop until get to the right color or the list
	 * is // empty - color not there yet while (!neck.isEmpty() &&
	 * neck.peek().getColor() != bead.getColor()) { temp[i] = neck.pop(); i++; }
	 * // if color not there yet - put all abck and add new one if
	 * (neck.isEmpty()) { for (int j = 0; j < temp.length; j++) {
	 * neck.push(temp[j]); } neck.push(bead); currLength += bead.getLength();
	 * return; } // otherwise push the bead with the right color and push the
	 * rest // back neck.push(bead); for (int j = 0; j < temp.length; j++) {
	 * neck.push(temp[j]); currLength += bead.getLength(); } } }
	 */

	/*
	 * Question 5 - i. see code - made an interface and an example stone and
	 * tested it in StringNecklace ii. does it become more generic because it
	 * can get any type of bead
	 */
}
