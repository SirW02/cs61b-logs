public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> deque = wordToDeque(word);
        boolean flag = true;
        while (true) {
            if (deque.size() < 2) {
                break;
            }
            if (!isPalindromeD(deque)) {
                flag = false;
            };
        }
        return flag;
    }

    private boolean isPalindromeD(Deque<Character> deque) {
            return deque.removeFirst() == deque.removeLast();
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = wordToDeque(word);
        boolean flag = true;
        while (true) {
            if (deque.size() < 2) {
                break;
            }
            if (!isPalindromeD(deque, cc)) {
                flag = false;
            }
        }
        return flag;
    }

    private boolean isPalindromeD(Deque<Character> deque, CharacterComparator cc) {
        return cc.equalChars(deque.removeFirst(), deque.removeLast());
    }
}