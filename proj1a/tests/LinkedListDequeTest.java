
import edu.princeton.cs.algs4.StdRandom;
import jh61b.utils.Reflection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

     @Test
     @DisplayName("LinkedListDeque has no fields besides nodes and primitives")
     void noNonTrivialFields() {
         Class<?> nodeClass = NodeChecker.getNodeClass(LinkedListDeque.class, true);
         List<Field> badFields = Reflection.getFields(LinkedListDeque.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(nodeClass) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not nodes or primitives").that(badFields).isEmpty();
     }

     @Test
     /* In this test, we have three different assert statements that verify that addFirst works correctly. */
     public void addFirstTestBasic() {
         Deque<String> lld1 = new LinkedListDeque<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
     }

     @Test
     /* In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
      *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
     public void addLastTestBasic() {
         Deque<String> lld1 = new LinkedListDeque<>();

         lld1.addLast("front"); // after this call we expect: ["front"]
         lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
         lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
     }

     @Test
     /* This test performs interspersed addFirst and addLast calls. */
     public void addFirstAndAddLastTest() {
         Deque<Integer> lld1 = new LinkedListDeque<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
     }

     //Below, you'll write your own tests for LinkedListDeque.
    @Test
    public void toListEmpty(){
         Deque<Integer> lld1 = new LinkedListDeque<>();
         assertThat(lld1.toList().isEmpty()).isEqualTo(true);
    }

    @Test
    public void isEmptyTestIfEmptyInt(){
         Deque<Integer> lld1 = new LinkedListDeque<>();
         assertThat(lld1.isEmpty()).isEqualTo(true);
    }

    @Test
    public void isEmptyTestIfEmptyString(){
         Deque<String> lld1 = new LinkedListDeque<>();
         assertThat(lld1.isEmpty()).isEqualTo(true);
    }

    @Test
    public void isEmptyTestIfNotEmptyInt(){
         Deque<Integer> lld1 = new LinkedListDeque<>();
         lld1.addFirst(-1);
         assertThat(lld1.isEmpty()).isEqualTo(false);
    }

    @Test
    public void isEmptyTestIfNotEmptyString(){
         Deque<String> lld1 = new LinkedListDeque<>();
         lld1.addFirst("apple");
         assertThat(lld1.isEmpty()).isEqualTo(false);
    }

    @Test
    public void SizeZeroTestIntList(){
         Deque<Integer> lld1 = new LinkedListDeque<>();
         assertThat(lld1.size()).isEqualTo(0);
    }

    @Test
    public void SizeZeroTestStringList(){
        Deque<String> lld1 = new LinkedListDeque<>();
        assertThat(lld1.size()).isEqualTo(0);
    }

    @Test
    public void SizeOneTestIntList(){
         Deque<Integer> lld1 = new LinkedListDeque<>();
         lld1.addFirst(1);
         assertThat(lld1.size()).isEqualTo(1);
    }

    @Test
    public void SizeOneTestStringList(){
        Deque<String> lld1 = new LinkedListDeque<>();
        lld1.addFirst("apple");
        assertThat(lld1.size()).isEqualTo(1);
    }

    @Test
    public void SizeFourTestIntList(){
         Deque<Integer> lld1 = new LinkedListDeque<>();
         lld1.addFirst(1);
         lld1.addFirst(0);
         lld1.addLast(2);
         lld1.addLast(3);
         assertThat(lld1.size()).isEqualTo(4);
    }

    @Test
    public void SizeFourTestStringList(){
        Deque<String> lld1 = new LinkedListDeque<>();
        lld1.addFirst("apple");
        lld1.addFirst("banana");
        lld1.addLast("orange");
        lld1.addLast("coffee");
        assertThat(lld1.size()).isEqualTo(4);
    }

    @Test
    public void getTestValidArgIntList(){
         Deque<Integer> lld1 = new LinkedListDeque<>();
         lld1.addFirst(1);
         lld1.addFirst(0);
         lld1.addLast(2);
         assertThat(lld1.get(2)).isEqualTo(2);
    }

    @Test
    public void getTestValidArgStringList(){
        Deque<String> lld1 = new LinkedListDeque<>();
        lld1.addFirst("apple");
        lld1.addFirst("banana");
        lld1.addLast("orange");
        assertThat(lld1.get(2)).isEqualTo("orange");
    }

    @Test
    public void getTestInValidArgIntList(){
        Deque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(1);
        assertThat(lld1.get(28723)).isEqualTo(null);
    }

    @Test
    public void getTestInValidArgStringList(){
        Deque<String> lld1 = new LinkedListDeque<>();
        lld1.addFirst("apple");
        assertThat(lld1.get(28723)).isEqualTo(null);
    }

    @Test
    public void getTestNegArgIntList(){
        Deque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(1);
        assertThat(lld1.get(-1)).isEqualTo(null);
    }

    @Test
    public void getRecursiveTestValidArgIntList(){
        Deque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(1);
        lld1.addFirst(0);
        lld1.addLast(2);
        assertThat(lld1.getRecursive(2)).isEqualTo(2);
    }

    @Test
    public void getRecursiveTestValidArgStringList(){
        Deque<String> lld1 = new LinkedListDeque<>();
        lld1.addFirst("apple");
        lld1.addFirst("banana");
        lld1.addLast("orange");
        assertThat(lld1.getRecursive(2)).isEqualTo("orange");
    }

    @Test
    public void getRecursiveTestInValidArgIntList(){
        Deque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(1);
        assertThat(lld1.getRecursive(28723)).isEqualTo(null);
    }

    @Test
    public void getRecursiveTestInValidArgStringList(){
        Deque<String> lld1 = new LinkedListDeque<>();
        lld1.addFirst("apple");
        assertThat(lld1.getRecursive(28723)).isEqualTo(null);
    }

    @Test
    public void getRecursiveTestNegArgIntList(){
        Deque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(1);
        assertThat(lld1.getRecursive(-2)).isEqualTo(null);
    }

    @Test
    public void removeFirstTest() {
        Deque<Integer> lld1 = new LinkedListDeque<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]
        lld1.removeFirst();  //[-1, 0, 1, 2]

        assertThat(lld1.toList()).containsExactly( -1, 0, 1, 2).inOrder();
    }

    @Test
    public void removeFirstTestEmpty() {
        Deque<Integer> lld1 = new LinkedListDeque<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */

        assertThat(lld1.removeFirst()).isEqualTo(null);
    }

    @Test
    public void removeLastTest() {
        Deque<Integer> lld1 = new LinkedListDeque<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]
        lld1.removeLast();  //[-2, -1, 0, 1]

        assertThat(lld1.toList()).containsExactly( -2, -1, 0, 1).inOrder();
    }

    @Test
    public void removeLastTestEmpty() {
        Deque<Integer> lld1 = new LinkedListDeque<>();

        assertThat(lld1.removeLast()).isEqualTo(null);
    }

    @Test
    public void removeFirstUntilEmpty() {
        Deque<Integer> lld1 = new LinkedListDeque<>();

        lld1.addLast(0);
        lld1.addLast(1);
        lld1.addLast(2);
        while (!lld1.isEmpty()){
            lld1.removeFirst();
        }

        assertThat(lld1.isEmpty()).isEqualTo(true);
    }

    @Test
    public void removeLastUntilEmpty() {
         Deque<Integer> lld1 = new LinkedListDeque<>();

         lld1.addFirst(2);
         lld1.addFirst(1);
         lld1.addFirst(0);
         while (!lld1.isEmpty()) {
             lld1.removeLast();
         }
         assertThat(lld1.isEmpty()).isEqualTo(true);
    }

    @Test
    public void removeLastToOne() {
        Deque<Integer> lld1 = new LinkedListDeque<>();

        lld1.addFirst(2);
        lld1.addFirst(1);
        lld1.addFirst(0);
        lld1.removeLast();
        lld1.removeLast();

        assertThat(lld1.get(0)).isEqualTo(0);
    }

    @Test
    public void removeFirstToOne() {
        Deque<Integer> lld1 = new LinkedListDeque<>();

        lld1.addFirst(2);
        lld1.addFirst(1);
        lld1.addFirst(0);
        lld1.removeFirst();
        lld1.removeFirst();

        assertThat(lld1.get(0)).isEqualTo(2);
    }

    @Test
    public void addFirstRemoveFirst() {
         Deque<Integer> lld1 = new LinkedListDeque<>();

         lld1.addFirst(0);

         assertThat(lld1.removeFirst()).isEqualTo(0);
    }

    @Test
    public void addFirstRemoveLast() {
         Deque<Integer> lld1 = new LinkedListDeque<>();

         lld1.addFirst(0);

         assertThat(lld1.removeLast()).isEqualTo(0);
    }

    @Test
    public void addLastRemoveFirst() {
         Deque<Integer> lld1 = new LinkedListDeque<>();

         lld1.addLast(1);
         lld1.removeFirst();
         lld1.addLast(0);

         assertThat(lld1.removeFirst()).isEqualTo(0);
    }

    @Test
    public void addLastRemoveLast() {
         Deque<Integer> lld1 = new LinkedListDeque<>();

         lld1.addLast(0);

         assertThat(lld1.removeLast()).isEqualTo(0);
    }

    @Test
    public void addFirstAfterRemoveToEmpty() {
         Deque<Integer> lld1 = new LinkedListDeque<>();

         lld1.addLast(0);
         lld1.removeLast();
         lld1.addFirst(1);

         assertThat(lld1.get(0)).isEqualTo(1);
    }

    @Test
    public void addLastAfterRemoveToEmpty() {
        Deque<Integer> lld1 = new LinkedListDeque<>();

        lld1.addLast(0);
        lld1.removeLast();
        lld1.addLast(1);

        assertThat(lld1.get(0)).isEqualTo(1);
    }

    @Test
    public void addLastRemoveFirstMul(){
         Deque<Integer> lld1 = new LinkedListDeque<>();
         lld1.addLast(0);
         lld1.removeFirst();
         lld1.addLast(1);
         lld1.removeFirst();
         lld1.addLast(2);
         lld1.addLast(3);
         lld1.removeFirst();
         lld1.addLast(4);
         lld1.addLast(5);
         lld1.removeFirst();
         lld1.removeFirst();
         lld1.addLast(6);
         lld1.addLast(7);
         lld1.removeFirst();

         assertThat(lld1.removeFirst()).isEqualTo(6);
    }

    @Test
    public void randomizedTest() {
        LinkedListDeque<Integer> linkedListDeque = new LinkedListDeque<>();

        int N = 1000000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 6);
            if (operationNumber == 0) {
                int randVal = StdRandom.uniform(0, 100);
                linkedListDeque.addFirst(randVal);
            } else if (operationNumber == 1) {
                int randVal = StdRandom.uniform(0, 100);
                linkedListDeque.addLast(randVal);
            } else if (linkedListDeque.isEmpty()) {
                assertThat(linkedListDeque.isEmpty()).isEqualTo(true);
            } else if (operationNumber == 2) {
                assertThat(linkedListDeque.isEmpty()).isEqualTo(false);
            } else if (operationNumber == 3) {
                linkedListDeque.removeFirst();
            } else if (operationNumber == 4) {
                linkedListDeque.removeLast();
            } else if (operationNumber == 5) {
                int randIndex = StdRandom.uniform(0, linkedListDeque.size());
                linkedListDeque.get(randIndex);
            }
        }
    }
}