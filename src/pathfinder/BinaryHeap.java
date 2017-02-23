package pathfinder;

import java.util.NoSuchElementException;

public class BinaryHeap {
    /** The number of children each node has **/
    private static final int d = 2;
    private int heapSize;
    private Node_state[] heap;

    /** Constructor **/
    public BinaryHeap(int capacity)
    {
        heapSize = 0;
        heap = new Node_state[capacity + 1];
        //Arrays.fill(heap, -1);
    }

    /** Function to check if heap is empty **/
    public boolean isEmpty( )
    {
        return heapSize == 0;
    }

    /** Check if heap is full **/
    public boolean isFull( )
    {
        return heapSize == heap.length;
    }

    /** Clear heap */
    public void makeEmpty( )
    {
        heapSize = 0;
    }

    /** Function to  get index parent of i **/
    private int parent(int i)
    {
        return (i - 1)/d;
    }

    /** Function to get index of k th child of i **/
    private int kthChild(int i, int k)
    {
        return d * i + k;
    }

    /** Function to insert element */
    public void insert(Node_state x)
    {
        if (isFull( ) )
            throw new NoSuchElementException("Overflow Exception");
        /** Percolate up **/
        heap[heapSize++] = x;
        heapifyUp(heapSize - 1);
    }

    /** Function to find least element **/
    public Node_state findMin(int i )
    {
        if (isEmpty() )
        {
          System.out.println("No Solution");
        	throw new NoSuchElementException("Underflow Exception");
        }
        return heap[0];
    }

    /** Function to delete min element **/
    public Node_state deleteMin()
    {
        Node_state keyItem = heap[0];
        delete(0);
        return keyItem;
    }

    /** Function to delete element at an index **/
    public Node_state delete(int ind)
    {
        if (isEmpty() )
        {
        	System.out.println("No Solution");
        	throw new NoSuchElementException("Underflow Exception");
        	
        }
        Node_state keyItem = heap[ind];
        heap[ind] = heap[heapSize - 1];
        heapSize--;
        heapifyDown(ind);
        return keyItem;
    }

    /** Function heapifyUp  **/
    private void heapifyUp(int childInd)
    {
        Node_state tmp = heap[childInd];
        while (childInd > 0 && tmp.f < heap[parent(childInd)].f)
        {
            heap[childInd] = heap[ parent(childInd) ];
            childInd = parent(childInd);
        }
        heap[childInd] = tmp;
    }

    /** Function heapifyDown **/
    private void heapifyDown(int ind)
    {
        int child;
        Node_state tmp = heap[ ind ];
        while (kthChild(ind, 1) < heapSize)
        {
            child = minChild(ind);
            if (heap[child].f < tmp.f)
                heap[ind] = heap[child];
            else
                break;
            ind = child;
        }
        heap[ind] = tmp;
    }

    /** Function to get smallest child **/
    private int minChild(int ind)
    {
        int bestChild = kthChild(ind, 1);
        int k = 2;
        int pos = kthChild(ind, k);
        while ((k <= d) && (pos < heapSize))
        {
            if (heap[pos].f < heap[bestChild].f)
                bestChild = pos;
            pos = kthChild(ind, k++);
        }
        return bestChild;
    }


    int find(Node_state s)
    {
        int i=0;
        while(i<heapSize)
        {
            if(s.x == heap[i].x)
            {
                if(s.y == heap[i].y)
                {
                    return i+1;
                }
            }
            i++;
        }
        return 0;
    }

    /** Function to print heap **/
    public void printHeap()
    {
        System.out.print("\nHeap = ");
        for (int i = 0; i < heapSize; i++)
            System.out.print(heap[i] +" ");
        System.out.println();
    }
}
