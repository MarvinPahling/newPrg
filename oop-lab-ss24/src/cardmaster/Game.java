package cardmaster;

import cardmaster.cards.Card;
import cardmaster.collections.AlgoArrayList;
import cardmaster.piles.DiscardPile;
import cardmaster.piles.DrawPile;
import cardmaster.piles.Hand;
import cardmaster.shop.Shop;

/**
 * Klasse für die Durchführung eines Spiels. Ein neues Spiel beginnt in Runde 1
 * im Modus {@link Mode#SHOPPING}. Nun muss mindestens eine Karte gekauft
 * werden. Dafür können die Methode für den Shopping-Modus (siehe unten)
 * verwendet werden. Der Nutzer dieser Klasse muss {@link #endShopping()}
 * aufrufen, um in den Playing-Modus ({@link Mode#PLAYING}) zu wechseln. Nun
 * werden die Methoden für den Playing-Modus verwendet, um wiederholt eine Karte
 * aus der Hand zu spielen ({@link #play(Card, int)}). Beim Wechseln in den
 * Playing-Modus und nach Legen einer Karte, wird die Hand wieder aufgefüllt,
 * sofern möglich. Wurde die letzte Karte aus der Hand gelegt, wird entweder,
 * falls das die letzte Runde war, in den End-Modus ({@link Mode#END})
 * gewechselt oder zurück in den Shopping-Modus. Beim Starten des Shopping-Modus
 * wird der cardmaster.shop.Shop mit komplett zufälligen neuen Gegenständen gefüllt. Am Anfang
 * passen fünf Gegenstände in den cardmaster.shop.Shop.
 */
public class Game {


	private Hand hand = new Hand();
	private DrawPile drawPile = new DrawPile();

	private DiscardPileContainer discardPiles;

	private Inventory inventory;
	public Shop shop;
	private Mode mode;
	private int roundsLeft;
	private final CardFactory cardFactory;





	/**
	 * Erzeugt ein neues Spiel.
	 *
	 * @param maxRounds Anzahl der Runden. Muss mindestens {@code 1} sein.
	 */
	public Game(int maxRounds) {
		this.cardFactory = new ChanceCardFactory();
		init(maxRounds, this.cardFactory);
	}
	public Game(int maxRounds, CardFactory cardFactory){
		this.cardFactory = cardFactory;
		init(maxRounds, cardFactory);
	}

	// Allgemein verwendbare Methoden.

	/**
	 * Gibt den aktuellen Punktestand zurück.
	 */
	public double getCredits() {
		return inventory.getCredits();
	}

	/**
	 * Gibt den aktuellen Modus zurück.
	 */
	public Mode getMode() {
		return mode;
	}

	/**
	 * Gibt die Anzahl an Ablagestapel zurück.
	 */
	public int getStacksCount() {
		return discardPiles.getSize();
	}

	// Methoden für den Shopping-Modus

	/**
	 * Gibt zurück, ob der cardmaster.shop.Shop leer gekauft wurde.
	 */
	public boolean isShopEmpty() {
		return shop.isEmpty();
	}

	/**
	 * Gibt die Anzahl der noch im cardmaster.shop.Shop verfügbaren Gegenstände an.
	 */
	public int getShopItemCount() {
		return shop.getOfferCount();
	}

	/**
	 * Gibt eine benutzerfreundliche Darstellung für die Konsole des Gegenstands mit
	 * index {@code shopItemIndex} zurück.
	 *
	 * @param shopItemIndex index aus dem Intervall
	 *                      {@code [0, this.getShopItemCount())}.
	 */
	public String getShopItemDescription(int shopItemIndex) {
		return shop.getDescription(shopItemIndex);
	}

	/**
	 * Gibt den Preis des Gegenstands mit index {@code shopItemIndex} zurück.
	 *
	 * @param shopItemIndex index aus dem Intervall
	 *                      {@code [0, this.getShopItemCount())}.
	 */
	public int getShopItemPrice(int shopItemIndex) {
		return shop.getPrice(shopItemIndex);
	}

	/**
	 * Versucht den Gegenstand an index {@code shopItemIndex} zu kaufen und entfernt
	 * den Gegenstand bei Erfolg aus dem Sortiment.
	 * <p>
	 * Bei erfolgreichem Kauf können sich die Indexe der Gegenstände ändern. Alle
	 * vorherigen Rückgaben von {@link #getShopItemCount} und
	 * {@link #getShopItemDescription(int)} sind nicht mehr gültig.
	 *
	 * @param shopItemIndex index aus dem Intervall
	 *                      {@code [0, this.getShopItemCount())}.
	 * @return Ob der Gegenstand gekauft wurde. Bei einem zu geringen Punktestand
	 *         hingegen {@code false}.
	 */
	public boolean buy(int shopItemIndex) {
		return shop.buy(shopItemIndex);
	}

	/**
	 * Gibt zurück, ob auf dem Nachziehstapel keine Karte liegt.
	 * <p>
	 * Dies ist am Anfang des Spiels der Fall. Da diese Methode nur im Modus
	 * {@link Mode#SHOPPING} verwendet werden darf, ist die Rückgabe nach dem ersten
	 * Kartenkauf immer {@code true}.
	 */
	public boolean isDrawPileEmpty() {
		return drawPile.isEmpty();
	}

	/**
	 * Beendet die cardmaster.shop.Shop-Interaktion und wechselt in den Playing-Modus. Wurde noch
	 * nie eine Karte gekauft, passiert nichts, da mindestens eine Karte fürs
	 * Spielen notwendig ist.
	 */
	public void endShopping() {
		if (!inventory.getCards().isEmpty()) {
			changeToPlay();
		}
	}

	private void changeToPlay() {
		this.mode = Mode.PLAYING;
		this.discardPiles = new DiscardPileContainer(inventory.getMaxDiscardPiles());
		AlgoArrayList<Card> cards = inventory.getCards();
		for (Card c : cards) {
			drawPile.addCard(c);
		}
		drawPile.shuffle();
		for (int i = 0; i < inventory.getMaxHandCards(); i++) {
			if(drawPile.isEmpty()){
				break;
			}
			hand.addCard(drawPile.cards.pop());
		}
	}

	private void changeToEnd() {
		this.mode = Mode.END;
	}

	private void changeToShopping() {
		roundsLeft--;
		this.mode = Mode.SHOPPING;
		this.shop = new Shop(inventory, cardFactory);

	}

	// Methoden für den Playing-Modus

	/**
	 * Gibt zurück, wie viele Karten aktuell auf der Hand gehalten werden.
	 */
	public int getHandCardsCount() {
		return hand.getCount();
	}

	/**
	 * Gibt die Handkarte mit index {@code handCardIndex} zurück.
	 *
	 * @param handCardIndex index aus dem Intervall
	 *                      {@code [0, this.getHandCardsCount())}.
	 */
	public Card getHandCard(int handCardIndex) {
		return hand.getCard(handCardIndex);
	}

	/**
	 * Legt die Handkarte auf den Ablagestapel mit index {@code stackIndex}. Stellen
	 * Sie mit assert sicher, dass die Karte auch wirklich auf der Hand ist.
	 * <p>
	 * Die Indexe der Karten kann sich hierbei ändern. Vor allem wird eine Karte -
	 * wenn möglich - nachgezogen. Dadurch sind alle vorherigen Rückgaben von
	 * {@link #getHandCardsCount()} und {@link #getHandCard(int)} nicht mehr gültig.
	 *
	 * @param card       die zu legende Handkarte.
	 * @param stackIndex index aus dem Intervall {@code [0, this.getStacksCount())}.
	 */
	public void play(Card card, int stackIndex) {

		assert stackIndex >= 0 && stackIndex < getStacksCount() : "Invalid stack index";
		assert hand.contains(card) : "Card not in hand";
		discardPiles.get(stackIndex).addCard(card);
		hand.removeCard(card);

		inventory.addCredits(card.calculateCredits(getTopShapes()));

		if(!isDrawPileEmpty()){
			hand.addCard(drawPile.cards.pop());
		}

		if(hand.isEmpty()){
			if(roundsLeft > 1){
				changeToShopping();
			}
			else {
				changeToEnd();
			}
		}
	}

	private void init(int maxRounds, CardFactory cardFactory){
		assert maxRounds >= 1 : "Invalid number of rounds";
		this.inventory = new Inventory(10);
		this.shop = new Shop(inventory, cardFactory);
		this.mode = Mode.SHOPPING;
		this.roundsLeft = maxRounds;
		this.discardPiles = new DiscardPileContainer(3);
	}

	/**
	 * Liefert die Form der auf den Ablagestapeln liegenden Karten. An Index
	 * {@code i} ist die Form für den Stapel mit index {@code i} oder {@code null}.
	 * {@code I} ist aus de Intervall {@code [0, this.getStacksCount())}.
	 */
	public Shape[] getTopShapes() {
		return discardPiles.getTopShapes();
	}

	public enum Mode {
		SHOPPING, PLAYING, END;
	}
}
