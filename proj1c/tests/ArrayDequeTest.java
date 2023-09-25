import deque.LinkedListDeque;
import edu.princeton.cs.algs4.StdRandom;
import jh61b.utils.Reflection;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import deque.Deque;
import deque.ArrayDeque;


import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDequeTest {

    @Test
    @DisplayName("ArrayDeque has no fields besides backing array and primitives")
    void noNonTrivialFields() {
        List<Field> badFields = Reflection.getFields(ArrayDeque.class)
                .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                .toList();

        assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
    }
    @Test
    public void arrayDequeCanBeLinkedListDeque() {
        Deque<String> lld1 = new LinkedListDeque<>();
        Deque<String> ad1 = new ArrayDeque<>();
        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");
        ad1.addLast("front");
        ad1.addLast("middle");
        ad1.addLast("back");
        assertThat(ad1).isEqualTo(lld1);
    }
    @Test
    public void printDeque() {
        Deque<String> lld1 = new LinkedListDeque<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        System.out.println(lld1);
    }

    @Test
    public void iterableTest() {
        Deque<String> lld1 = new ArrayDeque<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");
        for (String s : lld1) {
            System.out.println(s);
        }
    }
    @Test
    public void addLastTestBasicWithoutToList() {
        Deque<String> lld1 = new ArrayDeque<>();

        lld1.addLast("front"); // after this call we expect: ["front"]
        lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1).containsExactly("front", "middle", "back");
    }
    @Test
    public void addFirstBasic() {
        Deque<String> ad1 = new ArrayDeque<>();
        ad1.addFirst("Back");
        assertThat(ad1.toList()).containsExactly("Back").inOrder();
        ad1.addFirst("Middle");
        assertThat(ad1.toList()).containsExactly("Middle", "Back").inOrder();
        ad1.addFirst("Front");
        assertThat(ad1.toList()).containsExactly("Front", "Middle", "Back").inOrder();
    }
    @Test
    public void testEqualDeques() {
        Deque<String> lld1 = new ArrayDeque<>();
        Deque<String> lld2 = new ArrayDeque<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        lld2.addLast("front");
        lld2.addLast("middle");
        lld2.addLast("back");

        assertThat(lld1).isEqualTo(lld2);
    }
    @Test
    public void addLastBasic() {
        Deque<String> ad1 = new ArrayDeque<>();
        ad1.addLast("Front");
        assertThat(ad1.toList()).containsExactly("Front").inOrder();
        ad1.addLast("Middle");
        assertThat(ad1.toList()).containsExactly("Front", "Middle").inOrder();
        ad1.addLast("Back");
        assertThat(ad1.toList()).containsExactly("Front", "Middle", "Back").inOrder();
    }

    @Test
    public void addFisrtToFull() {
        Deque<Integer> ad1 = new ArrayDeque<>();
        ad1.addFirst(7);
        ad1.addFirst(6);
        ad1.addFirst(5);
        ad1.addFirst(4);
        ad1.addFirst(3);
        ad1.addFirst(2);
        ad1.addFirst(1);
        ad1.addFirst(0);
        assertThat(ad1.toList()).containsExactly(0, 1, 2, 3, 4, 5, 6, 7).inOrder();
    }

    @Test
    public void addLastToFull() {
        Deque<Integer> ad1 = new ArrayDeque<>();
        ad1.addLast(1);
        ad1.addLast(2);
        ad1.addLast(3);
        ad1.addLast(4);
        ad1.addLast(5);
        ad1.addLast(6);
        ad1.addLast(7);
        ad1.addLast(8);
        assertThat(ad1.toList()).containsExactly(1, 2, 3, 4, 5, 6, 7, 8).inOrder();
    }
    @Test
    public void addFirstToPlusOne() {
        Deque<Integer> ad1 = new ArrayDeque<>();
        ad1.addFirst(8);
        ad1.addFirst(7);
        ad1.addFirst(6);
        ad1.addFirst(5);
        ad1.addFirst(4);
        ad1.addFirst(3);
        ad1.addFirst(2);
        ad1.addFirst(1);
        ad1.addFirst(0);
        assertThat(ad1.toList()).containsExactly(0, 1, 2, 3, 4, 5, 6, 7, 8).inOrder();
    }

    @Test
    public void addLastToPlusOne() {
        Deque<Integer> ad1 = new ArrayDeque<>();
        ad1.addLast(1);
        ad1.addLast(2);
        ad1.addLast(3);
        ad1.addLast(4);
        ad1.addLast(5);
        ad1.addLast(6);
        ad1.addLast(7);
        ad1.addLast(8);
        ad1.addLast(9);
        assertThat(ad1.toList()).containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9).inOrder();
    }

    @Test
    public void addLastMoreAddFirstToPlusOne() {
        Deque<Integer> ad1 = new ArrayDeque<>();
        ad1.addLast(5);
        ad1.addLast(6);
        ad1.addLast(7);
        ad1.addLast(8);
        ad1.addLast(9);
        ad1.addFirst(4);
        ad1.addFirst(3);
        ad1.addFirst(2);
        ad1.addFirst(1);
        assertThat(ad1.toList()).containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9).inOrder();
    }

    @Test
    public void addFirstMoreAddLastToPlusOne() {
        Deque<Integer> ad1 = new ArrayDeque<>();
        ad1.addLast(6);
        ad1.addLast(7);
        ad1.addLast(8);
        ad1.addLast(9);
        ad1.addFirst(5);
        ad1.addFirst(4);
        ad1.addFirst(3);
        ad1.addFirst(2);
        ad1.addFirst(1);
        assertThat(ad1.toList()).containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9).inOrder();
    }

    @Test
    public void removeFirstBasic() {
        Deque<Integer> ad1 = new ArrayDeque<>();
        ad1.addLast(6);
        ad1.addLast(7);
        ad1.addLast(8);
        ad1.addLast(9);
        ad1.addFirst(5);
        ad1.addFirst(4);
        ad1.addFirst(3);
        ad1.addFirst(2);
        ad1.addFirst(1);
        assertThat(ad1.removeFirst()).isEqualTo(1);
        assertThat(ad1.removeFirst()).isEqualTo(2);
    }

    @Test
    public void removeFirstResizeDown() {
        Deque<Integer> ad1 = new ArrayDeque<>();
        ad1.addLast(6);
        ad1.addLast(7);
        ad1.addLast(8);
        ad1.addLast(9);
        ad1.addFirst(5);
        ad1.addFirst(4);
        ad1.addFirst(3);
        ad1.addFirst(2);
        ad1.addFirst(1);
        ad1.removeFirst();
        ad1.removeFirst();
        ad1.removeFirst();
        ad1.removeFirst();
        ad1.removeFirst();
        ad1.removeFirst();
        assertThat(ad1.toList()).containsExactly( 7, 8, 9).inOrder();
    }

    @Test
    public void removeLastBasic() {
        Deque<Integer> ad1 = new ArrayDeque<>();
        ad1.addLast(6);
        ad1.addLast(7);
        ad1.addLast(8);
        ad1.addLast(9);
        ad1.addFirst(5);
        ad1.addFirst(4);
        ad1.addFirst(3);
        ad1.addFirst(2);
        ad1.addFirst(1);
        assertThat(ad1.removeLast()).isEqualTo(9);
        assertThat(ad1.removeLast()).isEqualTo(8);
    }

    @Test
    public void removeLastResizeDown() {
        Deque<Integer> ad1 = new ArrayDeque<>();
        ad1.addLast(6);
        ad1.addLast(7);
        ad1.addLast(8);
        ad1.addLast(9);
        ad1.addFirst(5);
        ad1.addFirst(4);
        ad1.addFirst(3);
        ad1.addFirst(2);
        ad1.addFirst(1);
        ad1.removeLast();
        ad1.removeLast();
        ad1.removeLast();
        ad1.removeLast();
        ad1.removeLast();
        ad1.removeLast();
        assertThat(ad1.toList()).containsExactly( 1, 2, 3).inOrder();
    }

    @Test
    public void getTestValid() {
        Deque<Integer> ad1 = new ArrayDeque<>();
        ad1.addLast(6);
        ad1.addLast(7);
        ad1.addLast(8);
        ad1.addFirst(5);
        ad1.addFirst(4);
        ad1.addFirst(3);
        ad1.addFirst(2);
        ad1.addFirst(1);
        assertThat(ad1.get(4)).isEqualTo(5);
        assertThat(ad1.get(3)).isEqualTo(4);
        assertThat(ad1.get(2)).isEqualTo(3);
        assertThat(ad1.get(1)).isEqualTo(2);
        assertThat(ad1.get(0)).isEqualTo(1);
        assertThat(ad1.get(7)).isEqualTo(8);
        assertThat(ad1.get(6)).isEqualTo(7);
        assertThat(ad1.get(5)).isEqualTo(6);
    }

    @Test
    public void getTestToBig() {
        Deque<Integer> ad1 = new ArrayDeque<>();
        ad1.addLast(6);
        ad1.addLast(7);
        ad1.addLast(8);
        ad1.addFirst(5);
        ad1.addFirst(4);
        ad1.addFirst(3);
        ad1.addFirst(2);
        ad1.addFirst(1);
        assertThat(ad1.get(15)).isEqualTo(null);
    }

    @Test
    public void getTestNegative() {
        Deque<Integer> ad1 = new ArrayDeque<>();
        ad1.addLast(6);
        ad1.addLast(7);
        ad1.addLast(8);
        ad1.addFirst(5);
        ad1.addFirst(4);
        ad1.addFirst(3);
        ad1.addFirst(2);
        ad1.addFirst(1);
        assertThat(ad1.get(-1)).isEqualTo(null);
    }

    @Test
    public void isEmpty() {
        Deque<Integer> ad1 = new ArrayDeque<>();
        assertThat(ad1.isEmpty()).isEqualTo(true);
    }

    @Test
    public void isEmptyFalse() {
        Deque<Integer> ad1 = new ArrayDeque<>();
        ad1.addFirst(1);
        assertThat(ad1.isEmpty()).isEqualTo(false);
    }

    @Test
    public void sizeZero() {
        Deque<Integer> ad1 = new ArrayDeque<>();
        assertThat(ad1.size()).isEqualTo(0);
    }

    @Test
    public void sizeOne() {
        Deque<Integer> ad1 = new ArrayDeque<>();
        ad1.addFirst(1);
        assertThat(ad1.size()).isEqualTo(1);
    }

    @Test
    public void sizeFour() {
        Deque<Integer> ad1 = new ArrayDeque<>();
        ad1.addFirst(1);
        ad1.addFirst(1);
        ad1.addFirst(1);
        ad1.addFirst(1);
        assertThat(ad1.size()).isEqualTo(4);
    }

    @Test
    public void removeFirstEmpty() {
        Deque<Integer> ad1 = new ArrayDeque<>();
        assertThat(ad1.removeFirst()).isEqualTo(null);
    }

    @Test
    public void removeLastEmpty() {
        Deque<Integer> ad1 = new ArrayDeque<>();
        assertThat(ad1.removeLast()).isEqualTo(null);
    }

    @Test
    public void removeFirstUntilEmpty() {
        Deque<Integer> ad1 = new ArrayDeque<>();

        ad1.addLast(0);
        ad1.addLast(1);
        ad1.addLast(2);
        while (!ad1.isEmpty()){
            ad1.removeFirst();
        }

        assertThat(ad1.isEmpty()).isEqualTo(true);
    }

    @Test
    public void removeLastUntilEmpty() {
        Deque<Integer> ad1 = new ArrayDeque<>();

        ad1.addLast(0);
        ad1.addLast(1);
        ad1.addLast(2);
        while (!ad1.isEmpty()){
            ad1.removeLast();
        }

        assertThat(ad1.isEmpty()).isEqualTo(true);
    }
    @Test
    public void addFirstRemoveFirst() {
        Deque<Integer> ad1 = new ArrayDeque<>();

        ad1.addFirst(0);

        assertThat(ad1.removeFirst()).isEqualTo(0);
    }

    @Test
    public void addFirstRemoveLast() {
        Deque<Integer> ad1 = new ArrayDeque<>();

        ad1.addFirst(0);

        assertThat(ad1.removeLast()).isEqualTo(0);
    }

    @Test
    public void addLastRemoveFirst() {
        Deque<Integer> ad1 = new ArrayDeque<>();

        ad1.addLast(0);

        assertThat(ad1.removeFirst()).isEqualTo(0);
    }

    @Test
    public void addLastRemoveLast() {
        Deque<Integer> ad1 = new ArrayDeque<>();

        ad1.addLast(0);

        assertThat(ad1.removeLast()).isEqualTo(0);
    }

    @Test
    public void addFirstAfterRemoveToEmpty() {
        Deque<Integer> ad1 = new ArrayDeque<>();

        ad1.addLast(0);
        ad1.removeLast();
        ad1.addFirst(1);

        assertThat(ad1.get(0)).isEqualTo(1);
    }

    @Test
    public void addLastAfterRemoveToEmpty() {
        Deque<Integer> ad1 = new ArrayDeque<>();

        ad1.addLast(0);
        ad1.removeLast();
        ad1.addLast(1);

        assertThat(ad1.get(0)).isEqualTo(1);
    }

    @Test
    public void addLastRemoveFirstMul(){
        Deque<Integer> ad1 = new ArrayDeque<>();
        ad1.addLast(0);
        ad1.removeFirst();
        ad1.addLast(1);
        ad1.removeFirst();
        ad1.addLast(2);
        ad1.addLast(3);
        ad1.removeFirst();
        ad1.addLast(4);
        ad1.addLast(5);
        ad1.removeFirst();
        ad1.removeFirst();
        ad1.addLast(6);
        ad1.addLast(7);
        ad1.removeFirst();

        assertThat(ad1.removeFirst()).isEqualTo(6);
    }

    @Test
    public void sizeRemoveToEmpty() {
        Deque<Integer> ad1 = new ArrayDeque<>();
        ad1.addFirst(1);
        ad1.removeFirst();
        assertThat(ad1.size()).isEqualTo(0);
    }

    @Test
    public void toListEmpty() {
        Deque<Integer> ad1 = new ArrayDeque<>();
        assertThat(ad1.toList().isEmpty()).isEqualTo(true);
    }
    @Test
    public void randomizedTest() {
        Deque<Integer> ad1 = new ArrayDeque<>();
        int N = 1000000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 8);
            if (operationNumber == 0) {
                int randVal = StdRandom.uniform(0, 100);
                ad1.addFirst(randVal);
            } else if (operationNumber == 1) {
                int randVal = StdRandom.uniform(0, 100);
                ad1.addLast(randVal);
            } else if (ad1.isEmpty()) {
                assertThat(ad1.isEmpty()).isEqualTo(true);
            } else if (operationNumber == 2) {
                assertThat(ad1.isEmpty()).isEqualTo(false);
            } else if (operationNumber == 3) {
                ad1.removeFirst();
            } else if (operationNumber == 4) {
                ad1.removeLast();
            } else if (operationNumber == 5) {
                int randIndex = StdRandom.uniform(0, ad1.size());
                ad1.get(randIndex);
            }
        }
    }
}
