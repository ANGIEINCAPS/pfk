package queue_singlelinkedlist;

import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}

	/**
	 * Inserts the specified element into this queue, if possible post: The
	 * specified element is added to the rear of this queue
	 * 
	 * @param e the element to insert
	 * @return true if it was possible to add the element to this queue, else false
	 */
	public boolean offer(E e) {
		QueueNode<E> n = new QueueNode<E>(e); // om listan är tom; skapa nytt objekt, länka till sig själv
		if (last == null) {
			last = n;
			last.next = last;
		} else {
			n.next = last.next; // kopplar nya elementet till huvudet
			last.next = n; // kopplar gamla last till nya elementet
			last = n; // nya elementet blir last
		}
		size++;
		return true;
	}

	/**
	 * Returns the number of elements in this queue
	 * 
	 * @return the number of elements in this queue
	 */
	public int size() {
		return size;
	}

	/**
	 * Retrieves, but does not remove, the head of this queue, returning null if
	 * this queue is empty
	 * 
	 * @return the head element of this queue, or null if this queue is empty
	 */
	public E peek() {
		if (size == 0) {
			return null;
		} else {
			return last.next.element;
		}
	}

	/**
	 * Retrieves and removes the head of this queue, or null if this queue is empty.
	 * post: the head of the queue is removed if it was not empty
	 * 
	 * @return the head of this queue, or null if the queue is empty
	 */
	public E poll() {
		if (size == 0) {
			return null;
		} else if (size == 1) {
			QueueNode<E> temp = last;
			last = null;
			size--;
			return temp.element;
		} else {
			QueueNode<E> temp = last.next;
			last.next = last.next.next;
			size--;
			return temp.element;
		}
	}

	/**
	 * Returns an iterator over the elements in this queue
	 * 
	 * @return an iterator over the elements in this queue
	 */
	public Iterator<E> iterator() {
		return new QueueIterator();
	}

	private class QueueIterator implements Iterator<E> {
		private QueueNode<E> pos;

		/* Konstruktor */
		private QueueIterator() {
			pos = last;
		}

		public boolean hasNext() {
			return pos != null;
		}

		public E next() {
			if (pos == null) {
				throw new NoSuchElementException();
			}
			pos = pos.next;
			E e = pos.element;
			if (pos == last) {
				pos = null;
			}
			return e;
		}
	}

	/**
	 * Appends the specified queue to this queue post: all elements from the
	 * specified queue are appended to this queue. The specified queue (q) is empty
	 * after the call.
	 * 
	 * @param q the queue to append
	 * @throws IllegalArgumentException if this queue and q are identical
	 */
	public void append(FifoQueue<E> q) {
		// throw om köerna är identiska
		if (this == q) {
			throw new IllegalArgumentException();
		} else if (q.size == 0) {
			return;
		} else if (this.size == 0) {
			this.last = q.last;
			this.size = q.size;
		} else {
			QueueNode<E> temp = this.last.next; // spara första elementet
			this.last.next = q.last.next;
			q.last.next = temp;
			this.last = q.last;
			this.size += q.size;
		}
		q.last = null;
		q.size = 0;
	}

	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}

}
