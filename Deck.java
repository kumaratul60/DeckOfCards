package Crio;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

class Deck implements Iterable<Card> {
	private final Card[] cards;
	private int top;

	/*
	 * Construct a deck. The cards will start out in an unspecified but
	 * deterministic order - you must call shuffle() yourself.
	 */
	public Deck() {
		cards = new Card[Card.getSuits().length * (Card.getMaxRank() - Card.getMinRank() + 1)];
		refresh();
	}

	// Repopulate the deck with a full set of cards.
	
	public void refresh() {
		Card.Suit[] suits = Card.getSuits();
		int min_rank = Card.getMinRank();
		int max_rank = Card.getMaxRank();

		int i = 0;
		for (Card.Suit suit : suits)
			for (int rank = min_rank; rank <= max_rank; rank++)
				cards[i++] = new Card(rank, suit);

		top = cards.length - 1;
		assert cards[top] != null;
	}

	// Shuffle the deck, leaving the cards in a random order.
	
	public void shuffle() {
	
		Random rng = new Random();

		for (int i = cards.length - 1; i > 0; i--) {
			// Swap the i-th card with a random one
			int j = rng.nextInt(i + 1);
			Card tmp = cards[j];
			cards[j] = cards[i];
			cards[i] = tmp;
		}
	}

	// Return true if the deck is empty.
	
	public boolean empty() {
		return top < 0;
	}

	// Take a card from the deck and return it.
	
	public Card takeCard() {
		if (empty())
			throw new IllegalStateException("Can't deal from an empty deck.");
		return cards[top--];
	}

	// Print the current state of the deck.
	
	public void print() {
		if (empty()) {
			System.out.println("The deck is empty.");
			return;
		}

		System.out.println("The current deck:");
		for (Card card : this)
			System.out.println("  " + card);
	}

	// Return an iterator of the deck's cards.
	
	 /* The behavior is unspecified if you modify the deck (including taking a card)
	 * during the lifetime of an iterator.
	 */
	public Iterator<Card> iterator() {
		return new Iterator<Card>() {
			private int cursor = top;

			public boolean hasNext() {
				return cursor >= 0;
			}

			public Card next() {
				if (hasNext())
					return cards[cursor--];
				throw new NoSuchElementException();
			}

			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	public static void main(String[] args) {
		Deck deck = new Deck();

		System.out.println("Before shuffling:");
		deck.print();
		System.out.println();

		System.out.println("After shuffling:");
		deck.shuffle();
		deck.print();
		System.out.println();

		System.out.println("After dealing every card:");
		for (int i = 0; i < 52; i++)
			deck.takeCard();
		deck.print();
	}
}
