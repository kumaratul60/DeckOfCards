package Crio;

import java.util.Objects;

public class Card {

	// Enum representing playing card suits.

	public enum Suit {
		SPADES, HEARTS, DIAMONDS, CLUBS;
	}

	// The min and max valid card ranks
	private static final int MIN_RANK = 1;
	private static final int MAX_RANK = 13;

	// This instance's rank and suit
	private int rank;
	private Suit suit;

	// Construct a Card with a given rank and suit.

	public Card(int rank, Suit suit) {
		setRank(rank);
		setSuit(suit);
	}

	// Return the card's rank.

	public int getRank() {
		return rank;
	}

	// Set the card's rank, with input validation.

	public void setRank(int rank) {
		if (rank < MIN_RANK || rank > MAX_RANK)
			throw new RuntimeException(
					String.format("Invalid rank: %d (must be between %d and %d inclusive)", rank, MIN_RANK, MAX_RANK));
		this.rank = rank;
	}

	// Return the card's suit.

	public Suit getSuit() {
		return suit;
	}

	// Set the card's suit, with input validation.

	public void setSuit(Suit suit) {
		if (suit == null)
			throw new RuntimeException("Suit must be non-null");
		this.suit = suit;
	}

	@Override
	public String toString() {
		return String.format("%s[rank=%d, suit=%s]", getClass().getSimpleName(), getRank(), getSuit().name());
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Card))
			return false;
		if (obj == this)
			return true;

		Card that = (Card) obj;
		return that.getRank() == getRank() && that.getSuit() == getSuit();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getRank(), getSuit());
	}

	// Return the minimum allowed rank.

	public static int getMinRank() {
		return MIN_RANK;
	}

	// Return the maximum allowed rank.

	public static int getMaxRank() {
		return MAX_RANK;
	}

	// Return an array of the available suits.

	public static Suit[] getSuits() {
		return Suit.values();
	}

	public static void main(String[] args) {
		Card card;

		card = new Card(10, Suit.DIAMONDS);
		System.out.println("And a ten of diamonds:");
		System.out.println("Suit = " + card.getSuit().toString().toLowerCase());
		System.out.println("Rank = " + card.getRank());
		System.out.println("Hash = " + card.hashCode());
		System.out.println();

		card = new Card(7, Suit.CLUBS);
		System.out.println("And a 7 of clubs:");
		System.out.println("Suit = " + card.getSuit().toString().toLowerCase());
		System.out.println("Rank = " + card.getRank());
		System.out.println("Hash = " + card.hashCode());
	}
}
